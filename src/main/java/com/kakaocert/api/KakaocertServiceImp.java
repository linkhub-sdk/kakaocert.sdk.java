package com.kakaocert.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.google.gson.Gson;
import com.kakaocert.api.cms.RequestCMS;
import com.kakaocert.api.cms.ResultCMS;
import com.kakaocert.api.esign.RequestESign;
import com.kakaocert.api.esign.ResultESign;
import com.kakaocert.api.verifyauth.RequestVerifyAuth;
import com.kakaocert.api.verifyauth.ResultVerifyAuth;

import kr.co.linkhub.auth.Base64;
import kr.co.linkhub.auth.LinkhubException;
import kr.co.linkhub.auth.Token;
import kr.co.linkhub.auth.TokenBuilder;

public class KakaocertServiceImp implements KakaocertService {

    private static final String ServiceID = "KAKAOCERT";
    private static final String Auth_Static_URL = "https://static-auth.linkhub.co.kr";
    private static final String Auth_GA_URL = "https://ga-auth.linkhub.co.kr";
    private static final String ServiceURL_REAL = "https://kakaocert-api.linkhub.co.kr";
    private static final String ServiceURL_Static_REAL = "https://static-kakaocert-api.linkhub.co.kr";
    private static final String ServiceURL_GA_REAL = "https://ga-kakaocert-api.linkhub.co.kr";
    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";
    private final String APIVersion = "2.0";
    private String ServiceURL = null;
    private String AuthURL = null;

    private TokenBuilder tokenBuilder;

    private boolean isIPRestrictOnOff;
    private boolean useStaticIP;
    private boolean useGAIP;
    private boolean useLocalTimeYN;
    private String _linkID;
    private String _secretKey;
    private Gson _gsonParser = new Gson();

    private Map<String, Token> tokenTable = new HashMap<String, Token>();

    public KakaocertServiceImp() {
        isIPRestrictOnOff = true;
        useStaticIP = false;
        useGAIP = false;
        useLocalTimeYN = true;
    }

    public void setIPRestrictOnOff(boolean isIPRestrictOnOff) {
        this.isIPRestrictOnOff = isIPRestrictOnOff;
    }

    public void setUseStaticIP(boolean useStaticIP) {
        this.useStaticIP = useStaticIP;
    }

    public void setUseGAIP(boolean useGAIP) {
        this.useGAIP = useGAIP;
    }

    public void setUseLocalTimeYN(boolean useLocalTimeYN) {
        this.useLocalTimeYN = useLocalTimeYN;
    }

    public boolean isUseStaticIP() {
        return useStaticIP;
    }

    public boolean isUseGAIP() {
        return useGAIP;
    }

    public boolean isUseLocalTimeYN() {
        return useLocalTimeYN;
    }

    public String getServiceURL() {

        if (ServiceURL != null)
            return ServiceURL;

        if (useGAIP) {
            return ServiceURL_GA_REAL;
        } else if (useStaticIP) {
            return ServiceURL_Static_REAL;
        }
        return ServiceURL_REAL;
    }

    public void setServiceURL(String serviceURL) {
        ServiceURL = serviceURL;
    }

    public String getAuthURL() {
        return AuthURL;
    }

    public void setAuthURL(String authURL) {
        AuthURL = authURL;
    }

    public String getLinkID() {
        return _linkID;
    }

    public void setLinkID(String linkID) {
        this._linkID = linkID;
    }

    public String getSecretKey() {
        return _secretKey;
    }

    public void setSecretKey(String secretKey) {
        this._secretKey = secretKey;
    }

    private TokenBuilder getTokenbuilder() {
        if (this.tokenBuilder == null) {
            tokenBuilder = TokenBuilder.newInstance(getLinkID(), getSecretKey()).ServiceID(ServiceID).addScope("member")
                    .useLocalTimeYN(useLocalTimeYN);

            if (AuthURL != null) {
                tokenBuilder.setServiceURL(AuthURL);
            } else {
                // AuthURL 이 null이고, useGAIP가 True 일때 GA-AUTH, useStaticIP 가 True일때. GA-Static 호출.
                if (useGAIP) {
                    tokenBuilder.setServiceURL(Auth_GA_URL);
                } else if (useStaticIP) {
                    tokenBuilder.setServiceURL(Auth_Static_URL);
                }
            }

            tokenBuilder.addScope("310");
            tokenBuilder.addScope("320");
            tokenBuilder.addScope("330");
        }

        return tokenBuilder;
    }

    private String getSessionToken(String ClientCode, String ForwardIP) throws KakaocertException {

        if (ClientCode == null || ClientCode.isEmpty())
            throw new KakaocertException(-99999999, "이용기관 코드가 입력되지 않았습니다.");

        Token token = null;
        Date UTCTime = null;

        if (tokenTable.containsKey(ClientCode))
            token = tokenTable.get(ClientCode);

        boolean expired = true;

        if (token != null) {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            format.setTimeZone(TimeZone.getTimeZone("UTC"));

            SimpleDateFormat subFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            subFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            try {

                Date expiration = format.parse(token.getExpiration());
                UTCTime = subFormat.parse(getTokenbuilder().getTime());
                expired = expiration.before(UTCTime);

            } catch (LinkhubException le) {
                throw new KakaocertException(le);
            } catch (ParseException e) {
            }
        }

        if (expired) {
            if (tokenTable.containsKey(ClientCode))
                tokenTable.remove(ClientCode);

            try {

                if (isIPRestrictOnOff) {
                    token = getTokenbuilder().build(ClientCode, ForwardIP);
                } else {
                    token = getTokenbuilder().build(ClientCode, "*");
                }

                tokenTable.put(ClientCode, token);
            } catch (LinkhubException le) {
                throw new KakaocertException(le);
            }
        }

        return token.getSession_token();
    }

    protected class ReceiptResponse {
        public String receiptId;
    }

    /**
     * Convert Object to Json String.
     * 
     * @param Graph
     * @return jsonString
     */
    protected String toJsonString(Object Graph) {
        return _gsonParser.toJson(Graph);
    }

    /**
     * Convert JsonString to Object of Clazz
     * 
     * @param json
     * @param clazz
     * @return Object of Clazz
     */
    protected <T> T fromJsonString(String json, Class<T> clazz) {
        return _gsonParser.fromJson(json, clazz);
    }

    /**
     * 
     * @param url
     * @param CorpNum
     * @param PostData
     * @param UserID
     * @param clazz
     * @return returned object
     * @throws KakaocertException
     */
    protected <T> T httppost(String url, String CorpNum, String PostData, String UserID, Class<T> clazz) throws KakaocertException {
        return httppost(url, CorpNum, PostData, UserID, null, clazz);
    }

    /**
     * 
     * @param url
     * @param CorpNum
     * @param PostData
     * @param UserID
     * @param Action
     * @param clazz
     * @return returned object
     * @throws KakaocertException
     */
    protected <T> T httppost(String url, String CorpNum, String PostData, String UserID, String Action, Class<T> clazz) throws KakaocertException {
        return httppost(url, CorpNum, PostData, UserID, Action, clazz, null);
    }

    private static String sha256Base64(byte[] input) {
        MessageDigest md;
        byte[] btResult = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            btResult = md.digest(input);
        } catch (NoSuchAlgorithmException e) {
        }

        return Base64.encode(btResult);
    }

    private static byte[] base64Decode(String input) {
        return Base64.decode(input);
    }

    private static String base64Encode(byte[] input) {
        return Base64.encode(input);
    }

    private static byte[] HMacSha256(byte[] key, byte[] input) throws KakaocertException {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA256_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signingKey);
            return mac.doFinal(input);
        } catch (Exception e) {
            throw new KakaocertException(-99999999, "Fail to Calculate HMAC-SHA256, Please check your SecretKey.", e);
        }
    }

    /**
     * 
     * @param url
     * @param CorpNum
     * @param PostData
     * @param UserID
     * @param Action
     * @param clazz
     * @param ContentType
     * @return
     * @throws KakaocertException
     */
    protected <T> T httppost(String url, String CorpNum, String PostData, String UserID, String Action, Class<T> clazz, String ContentType)
            throws KakaocertException {
        HttpURLConnection httpURLConnection;
        try {
            URL uri = new URL(getServiceURL() + url);
            httpURLConnection = (HttpURLConnection) uri.openConnection();
        } catch (Exception e) {
            throw new KakaocertException(-99999999, "Kakaocert API 서버 접속 실패", e);
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        String date = format.format(new Date());

        if (CorpNum != null && CorpNum.isEmpty() == false) {
            httpURLConnection.setRequestProperty("Authorization", "Bearer " + getSessionToken(CorpNum, null));
        }

        httpURLConnection.setRequestProperty("x-lh-date".toLowerCase(), date);

        httpURLConnection.setRequestProperty("x-lh-version".toLowerCase(), APIVersion);

        if (ContentType != null && ContentType.isEmpty() == false) {
            httpURLConnection.setRequestProperty("Content-Type", ContentType);
        } else {
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf8");
        }

        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");

        try {
            httpURLConnection.setRequestMethod("POST");
        } catch (ProtocolException e1) {
        }

        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoOutput(true);

        if ((PostData == null || PostData.isEmpty()) == false) {

            byte[] btPostData = PostData.getBytes(Charset.forName("UTF-8"));

            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(btPostData.length));

            String signTarget = "POST\n";
            signTarget += sha256Base64(btPostData) + "\n";
            signTarget += date + "\n";
            signTarget += APIVersion + "\n";

            String Signature = base64Encode(HMacSha256(base64Decode(getSecretKey()), signTarget.getBytes(Charset.forName("UTF-8"))));

            httpURLConnection.setRequestProperty("x-kc-auth", getLinkID() + " " + Signature);

            DataOutputStream output = null;

            try {
                output = new DataOutputStream(httpURLConnection.getOutputStream());
                output.write(btPostData);
                output.flush();
            } catch (Exception e) {
                throw new KakaocertException(-99999999, "Fail to POST data to Server.", e);
            } finally {
                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException e1) {
                    throw new KakaocertException(-99999999, "Kakaocert httppost func DataOutputStream close() Exception", e1);
                }
            }
        }

        String Result = parseResponse(httpURLConnection);

        return fromJsonString(Result, clazz);
    }

    /**
     * 
     * @param url
     * @param CorpNum
     * @param UserID
     * @param clazz
     * @return returned object
     * @throws KakaocertException
     */
    protected <T> T httpget(String url, String CorpNum, String UserID, Class<T> clazz) throws KakaocertException {
        HttpURLConnection httpURLConnection;
        try {
            URL uri = new URL(getServiceURL() + url);
            httpURLConnection = (HttpURLConnection) uri.openConnection();
        } catch (Exception e) {
            throw new KakaocertException(-99999999, "Kakaocert API 서버 접속 실패", e);
        }

        if (CorpNum != null && CorpNum.isEmpty() == false) {
            httpURLConnection.setRequestProperty("Authorization", "Bearer " + getSessionToken(CorpNum, null));
        }

        httpURLConnection.setRequestProperty("x-pb-version".toLowerCase(), APIVersion);

        if (UserID != null && UserID.isEmpty() == false) {
            httpURLConnection.setRequestProperty("x-pb-userid", UserID);
        }

        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");

        String Result = parseResponse(httpURLConnection);

        return fromJsonString(Result, clazz);
    }

    private class ErrorResponse {

        private long code;
        private String message;

        public long getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

    }

    protected class UnitCostResponse {

        public float unitCost;

    }

    protected class UploadFile {
        public UploadFile() {
        }

        public String fieldName;
        public String fileName;
        public InputStream fileData;
    }

    protected class URLResponse {
        public String url;
    }

    private static String fromStream(InputStream input) throws KakaocertException {
        InputStreamReader is = null;
        BufferedReader br = null;
        StringBuilder sb = null;

        try {
            is = new InputStreamReader(input, Charset.forName("UTF-8"));
            br = new BufferedReader(is);
            sb = new StringBuilder();

            String read = br.readLine();

            while (read != null) {
                sb.append(read);
                read = br.readLine();
            }

        } catch (IOException e) {
            throw new KakaocertException(-99999999, "Kakaocert fromStream func Exception", e);
        } finally {
            try {
                if (br != null)
                    br.close();
                if (is != null)
                    is.close();
            } catch (IOException e) {
                throw new KakaocertException(-99999999, "Kakaocert fromStream func finally close Exception", e);
            }
        }

        return sb.toString();
    }

    private static String fromGzipStream(InputStream input) throws KakaocertException {
        GZIPInputStream zipReader = null;
        InputStreamReader is = null;
        BufferedReader br = null;
        StringBuilder sb = null;

        try {
            zipReader = new GZIPInputStream(input);
            is = new InputStreamReader(zipReader, "UTF-8");
            br = new BufferedReader(is);
            sb = new StringBuilder();

            String read = br.readLine();

            while (read != null) {
                sb.append(read);
                read = br.readLine();
            }
        } catch (IOException e) {
            throw new KakaocertException(-99999999, "Kakaocert fromGzipStream func Exception", e);
        } finally {
            try {
                if (br != null)
                    br.close();
                if (is != null)
                    is.close();
                if (zipReader != null)
                    zipReader.close();
            } catch (IOException e) {
                throw new KakaocertException(-99999999, "Kakaocert fromGzipStream func finally close Exception", e);
            }
        }

        return sb.toString();
    }

    private String parseResponse(HttpURLConnection httpURLConnection) throws KakaocertException {

        String result = "";
        InputStream input = null;
        KakaocertException exception = null;

        try {
            input = httpURLConnection.getInputStream();

            if (null != httpURLConnection.getContentEncoding() && httpURLConnection.getContentEncoding().equals("gzip")) {
                result = fromGzipStream(input);
            } else {
                result = fromStream(input);
            }
        } catch (IOException e) {
            InputStream errorIs = null;
            ErrorResponse error = null;

            try {
                errorIs = httpURLConnection.getErrorStream();
                result = fromStream(errorIs);
                error = fromJsonString(result, ErrorResponse.class);
            } catch (Exception ignored) {

            } finally {
                try {
                    if (errorIs != null) {
                        errorIs.close();
                    }
                } catch (IOException e1) {
                    throw new KakaocertException(-99999999, "Kakaocert parseResponse func InputStream close() Exception", e1);
                }
            }

            if (error == null) {
                exception = new KakaocertException(-99999999, "Fail to receive data from Server.", e);
            } else {
                exception = new KakaocertException(error.getCode(), error.getMessage());
            }
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e2) {
                throw new KakaocertException(-99999999, "Kakaocert parseResponse func InputStream close() Exception", e2);
            }
        }

        if (exception != null)
            throw exception;

        return result;
    }

    @Override
    public String requestESign(String ClientCode, RequestESign esignRequest) throws KakaocertException {

        if (null == ClientCode || ClientCode.length() == 0)
            throw new KakaocertException(-99999999, "이용기관코드가 입력되지 않았습니다.");
        if (null == esignRequest)
            throw new KakaocertException(-99999999, "전자서명 요청정보가 입력되지 않았습니다.");

        esignRequest.setAppUseYN(false);

        String PostData = toJsonString(esignRequest);

        ReceiptResponse response = httppost("/SignToken/Request", ClientCode, PostData, null, ReceiptResponse.class);

        return response.receiptId;
    }

    @Override
    public ResponseESign requestESign(String ClientCode, RequestESign esignRequest, boolean appUseYN) throws KakaocertException {

        if (null == ClientCode || ClientCode.length() == 0)
            throw new KakaocertException(-99999999, "이용기관코드가 입력되지 않았습니다.");
        if (null == esignRequest)
            throw new KakaocertException(-99999999, "전자서명 요청정보가 입력되지 않았습니다.");

        esignRequest.setAppUseYN(appUseYN);

        String PostData = toJsonString(esignRequest);

        ResponseESign response = httppost("/SignToken/Request", ClientCode, PostData, null, ResponseESign.class);

        return response;
    }

    @Override
    public String requestVerifyAuth(String ClientCode, RequestVerifyAuth verifyAuthRequest) throws KakaocertException {

        if (null == ClientCode || ClientCode.length() == 0)
            throw new KakaocertException(-99999999, "이용기관코드가 입력되지 않았습니다.");
        if (null == verifyAuthRequest)
            throw new KakaocertException(-99999999, "본인인증 요청정보가 입력되지 않았습니다.");

        String PostData = toJsonString(verifyAuthRequest);

        ReceiptResponse response = httppost("/SignIdentity/Request", ClientCode, PostData, null, ReceiptResponse.class);

        return response.receiptId;
    }

    @Override
    public String requestCMS(String ClientCode, RequestCMS cmsRequest) throws KakaocertException {

        if (null == ClientCode || ClientCode.length() == 0)
            throw new KakaocertException(-99999999, "이용기관코드가 입력되지 않았습니다.");
        if (null == cmsRequest)
            throw new KakaocertException(-99999999, "자동이체 출금동의 요청정보가 입력되지 않았습니다.");

        cmsRequest.setAppUseYN(false);

        String PostData = toJsonString(cmsRequest);

        ReceiptResponse response = httppost("/SignDirectDebit/Request", ClientCode, PostData, null, ReceiptResponse.class);

        return response.receiptId;
    }

    @Override
    public ResponseCMS requestCMS(String ClientCode, RequestCMS cmsRequest, boolean appUseYN) throws KakaocertException {

        if (null == ClientCode || ClientCode.length() == 0)
            throw new KakaocertException(-99999999, "이용기관코드가 입력되지 않았습니다.");
        if (null == cmsRequest)
            throw new KakaocertException(-99999999, "자동이체 출금동의 요청정보가 입력되지 않았습니다.");

        cmsRequest.setAppUseYN(appUseYN);

        String PostData = toJsonString(cmsRequest);

        ResponseCMS response = httppost("/SignDirectDebit/Request", ClientCode, PostData, null, ResponseCMS.class);

        return response;
    }

    @Override
    public ResultESign getESignState(String ClientCode, String receiptID) throws KakaocertException {

        if (null == ClientCode || ClientCode.length() == 0)
            throw new KakaocertException(-99999999, "이용기관코드가 입력되지 않았습니다.");
        if (null == receiptID || receiptID.length() == 0)
            throw new KakaocertException(-99999999, "접수아이디가 입력되지 않았습니다.");

        return httpget("/SignToken/Status/" + receiptID, ClientCode, null, ResultESign.class);
    }

    @Override
    public ResultVerifyAuth getVerifyAuthState(String ClientCode, String receiptID) throws KakaocertException {

        if (null == ClientCode || ClientCode.length() == 0)
            throw new KakaocertException(-99999999, "이용기관코드가 입력되지 않았습니다.");
        if (null == receiptID || receiptID.length() == 0)
            throw new KakaocertException(-99999999, "접수아이디가 입력되지 않았습니다.");

        return httpget("/SignIdentity/Status/" + receiptID, ClientCode, null, ResultVerifyAuth.class);
    }

    @Override
    public ResultCMS getCMSState(String ClientCode, String receiptID) throws KakaocertException {

        if (null == ClientCode || ClientCode.length() == 0)
            throw new KakaocertException(-99999999, "이용기관코드가 입력되지 않았습니다.");
        if (null == receiptID || receiptID.length() == 0)
            throw new KakaocertException(-99999999, "접수아이디가 입력되지 않았습니다.");

        return httpget("/SignDirectDebit/Status/" + receiptID, ClientCode, null, ResultCMS.class);
    }

    @Override
    public VerifyResult verifyESign(String ClientCode, String receiptID) throws KakaocertException {

        if (null == ClientCode || ClientCode.length() == 0)
            throw new KakaocertException(-99999999, "이용기관코드가 입력되지 않았습니다.");
        if (null == receiptID || receiptID.length() == 0)
            throw new KakaocertException(-99999999, "접수아이디가 입력되지 않았습니다.");

        return httpget("/SignToken/Verify/" + receiptID, ClientCode, null, VerifyResult.class);
    }

    @Override
    public VerifyResult verifyESign(String ClientCode, String receiptID, String signature) throws KakaocertException {
        if (null == ClientCode || ClientCode.length() == 0)
            throw new KakaocertException(-99999999, "이용기관코드가 입력되지 않았습니다.");
        if (null == receiptID || receiptID.length() == 0)
            throw new KakaocertException(-99999999, "접수아이디가 입력되지 않았습니다.");

        return httpget("/SignToken/Verify/" + receiptID + "/" + signature, ClientCode, null, VerifyResult.class);
    }

    @Override
    public VerifyResult verifyAuth(String ClientCode, String receiptID) throws KakaocertException {
        if (null == ClientCode || ClientCode.length() == 0)
            throw new KakaocertException(-99999999, "이용기관코드가 입력되지 않았습니다.");
        if (null == receiptID || receiptID.length() == 0)
            throw new KakaocertException(-99999999, "접수아이디가 입력되지 않았습니다.");

        return httpget("/SignIdentity/Verify/" + receiptID, ClientCode, null, VerifyResult.class);
    }

    @Override
    public VerifyResult verifyCMS(String ClientCode, String receiptID) throws KakaocertException {
        if (null == ClientCode || ClientCode.length() == 0)
            throw new KakaocertException(-99999999, "이용기관코드가 입력되지 않았습니다.");
        if (null == receiptID || receiptID.length() == 0)
            throw new KakaocertException(-99999999, "접수아이디가 입력되지 않았습니다.");

        return httpget("/SignDirectDebit/Verify/" + receiptID, ClientCode, null, VerifyResult.class);
    }

    @Override
    public VerifyResult verifyCMS(String ClientCode, String receiptID, String signature) throws KakaocertException {
        if (null == ClientCode || ClientCode.length() == 0)
            throw new KakaocertException(-99999999, "이용기관코드가 입력되지 않았습니다.");
        if (null == receiptID || receiptID.length() == 0)
            throw new KakaocertException(-99999999, "접수아이디가 입력되지 않았습니다.");

        return httpget("/SignDirectDebit/Verify/" + receiptID + "/" + signature, ClientCode, null, VerifyResult.class);
    }

}
