package com.kakaocert.api;

import com.kakaocert.api.cms.RequestCMS;
import com.kakaocert.api.cms.ResultCMS;
import com.kakaocert.api.esign.RequestESign;
import com.kakaocert.api.esign.ResultESign;
import com.kakaocert.api.verifyauth.RequestVerifyAuth;
import com.kakaocert.api.verifyauth.ResultVerifyAuth;

/**
 * Kakaocert Service Interface.
 * 
 * @author John
 * @version 1.0.0
 */
public interface KakaocertService  {
	
	/**
	 * 전자서명 요청 
	 * 
	 * @param ClientCode
	 * 			이용기관코드
	 * @param esignRequest
	 * 			전자서명 요청정보
	 * @return receiptId(접수아이디)
	 * @throws KakaocertException
	 */
	@Deprecated
	public String requestESign(String ClientCode, RequestESign esignRequest) throws KakaocertException;
	
	
	
	/**
	 * 전자서명 요청 
	 * 
	 * @param ClientCode
	 * 			이용기관코드
	 * @param esignRequest
	 * 			전자서명 요청정보
	 * @return ResponseTxId
	 * @throws KakaocertException
	 */
	public ResponseESign requestESign(String ClientCode, RequestESign esignRequest, boolean appUseYN) throws KakaocertException;
	
	
	
	
	/**
	 * 전자서명 결과 확인
	 * 
	 * @param ClientCode
	 * 			이용기관코드
	 * @param receiptID
	 * 			전자서명 접수아이디
	 * @return ResultESign
	 * @throws KakaocertException
	 */
	public ResultESign getESignResult(String ClientCode, String receiptID) throws KakaocertException;
	
	/**
	 * 전자서명 결과 확인
	 * 
	 * @param ClientCode
	 * 			이용기관코드
	 * @param receiptID
	 * 			전자서명 접수아이디
	 * @param signature
	 * 			앱스킴 success 호출시 반환된 서명값 
	 * @return ResultESign
	 * @throws KakaocertException
	 */
	public ResultESign getESignResult(String ClientCode, String receiptID, String signature) throws KakaocertException;
	
	/**
	 * 본인인증 요청
	 * @param ClientCode
	 * 			이용기관코드
	 * @param verifyAuthRequest
	 * 			본인인증 요청정보
	 * @return receiptId(접수아이디)
	 * @throws KakaocertException
	 */
	public String requestVerifyAuth(String ClientCode, RequestVerifyAuth verifyAuthRequest) throws KakaocertException;
	
	/**
	 * 본인인증 결과 확인
	 * 
	 * @param ClientCode
	 * 			이용기관코드
	 * @param receiptID
	 * 			본인인증 접수아이디
	 * @return ResultVerifyAuth
	 * @throws KakaocertException
	 */
	public ResultVerifyAuth getVerifyAuthResult(String ClientCode, String receiptID) throws KakaocertException;
	
	/**
	 * 출금동의 요청
	 * 
	 * @param ClientCode
	 * 			이용기관코드
	 * @param cmsRequest
	 * 			출금동의 요청정보
	 * @return receiptId(접수아이디)
	 * @throws KakaocertException
	 */
	public String requestCMS(String ClientCode, RequestCMS cmsRequest) throws KakaocertException;
	
	/**
	 * 출금동의 결과 확인
	 * 
	 * @param ClientCode
	 * 			이용기관코드
	 * @param receiptID
	 * 			출금동의 접수아이디
	 * @return ResultCMS
	 * @throws KakaocertException
	 */
	public ResultCMS getCMSResult(String ClientCode, String receiptID) throws KakaocertException;



	
	
	
}
