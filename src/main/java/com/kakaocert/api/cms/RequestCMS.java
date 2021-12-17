package com.kakaocert.api.cms;

import java.io.Serializable;

/**
 * class for CMS Information
 * 
 * @author John
 * @version 1.0.0
 */
public class RequestCMS implements Serializable{

	private static final long serialVersionUID = -411164392902817963L;

	private String CallCenterNum;
	private int Expires_in;
	private String PayLoad;
	private String ReceiverBirthDay;
	private String ReceiverHP;
	private String ReceiverName;
	private String SubClientID;
	private String TMSMessage;
	private String TMSTitle;
	private boolean isAllowSimpleRegistYN;
	private boolean isVerifyNameYN;
	private boolean isAppUseYN;
	
	private String BankAccountName;
	private String BankAccountNum;
	private String BankCode;
	private String ClientUserID;
	
	/**
	 * 고객센터 전화번호 확인
	 * 
	 * @return CallCenterNum
	 * 		고객센터 번호
	 */
	public String getCallCenterNum() {
		return CallCenterNum;
	}
	
	/**
	 * 고객센터 전화번호 설정
	 * 
	 * @param callCenterNum
	 * 		고객센터 전화번호
	 */
	public void setCallCenterNum(String callCenterNum) {
		this.CallCenterNum = callCenterNum;
	}
	
	/**
	 * 인증요청 만료시간(초) 확인 
	 * 
	 * @return Expires_in
	 */
	public int getExpires_in() {
		return Expires_in;
	}
	
	/**
	 * 인증요청 만료시간(초) 설정 
	 * 
	 * @param expires_in
	 * 		만료시간
	 */
	public void setExpires_in(int expires_in) {
		this.Expires_in = expires_in;
	}
	
	/**
	 * Payload 확인
	 * 
	 * @return Payload
	 */
	public String getPayLoad() {
		return PayLoad;
	}
	
	/**
	 * Payload 설정
	 * 
	 * @param payLoad
	 */
	public void setPayLoad(String payLoad) {
		this.PayLoad = payLoad;
	}
	
	/**
	 * 수신자 생년월일 확인
	 * 
	 * @return ReceiverBirthDay
	 */
	public String getReceiverBirthDay() {
		return ReceiverBirthDay;
	}
	
	/**
	 * 수신자 생년월일 설정 
	 * 
	 * @param receiverBirthDay
	 * 		생년월일
	 */
	public void setReceiverBirthDay(String receiverBirthDay) {
		this.ReceiverBirthDay = receiverBirthDay;
	}
	
	/**
	 * 수신자 휴대폰번호 확인
	 * 
	 * @return ReceiverHP
	 */
	public String getReceiverHP() {
		return ReceiverHP;
	}
	
	/**
	 * 수신자 휴대폰번호 설정
	 * 
	 * @param receiverHP
	 */
	public void setReceiverHP(String receiverHP) {
		this.ReceiverHP = receiverHP;
	}
	
	/**
	 * 수신자명 확인
	 * 
	 * @return ReceiverName
	 */
	public String getReceiverName() {
		return ReceiverName;
	}
	
	/**
	 * 수신자명 설정 
	 * 
	 * @param receiverName
	 */
	public void setReceiverName(String receiverName) {
		this.ReceiverName = receiverName;
	}
	
	/**
	 * 별칭코드 확인 
	 * 
	 * @return SubClientID
	 */
	public String getSubClientID() {
		return SubClientID;
	}
	
	/**
	 * 별칭코드 설정
	 * 
	 * @param subClientID
	 * 		별칭코드
	 */
	public void setSubClientID(String subClientID) {
		this.SubClientID = subClientID;
	}
	
	/**
	 * 인증요청 부가메시지 확인 
	 * 
	 * @return TMSMessage
	 */
	public String getTMSMessage() {
		return TMSMessage;
	}
	
	/**
	 * 인증요청 부가메시지 설정
	 * 
	 * @param tMSMessage
	 * 		알림톡 부가메시지
	 */
	public void setTMSMessage(String tMSMessage) {
		TMSMessage = tMSMessage;
	}
	
	/**
	 * 인증요청 메시지제목 확인
	 * 
	 * @return TMSTitle
	 */
	public String getTMSTitle() {
		return TMSTitle;
	}
	
	/**
	 * 인증요청 메시지제목 설정
	 * 
	 * @param tMSTitle
	 * 		인증요청 메시지제목
	 */
	public void setTMSTitle(String tMSTitle) {
		TMSTitle = tMSTitle;
	}
	
	/**
	 * 은행계좌 실명확인 생략여부 확인 
	 * 
	 * @return isAllowSimpleRegistYN
	 */
	public boolean isAllowSimpleRegistYN() {
		return isAllowSimpleRegistYN;
	}
	
	/**
	 * 은행계좌 실명확인 생략여부 설정 
	 * 
	 * @param isAllowSimpleRegistYN
	 */
	public void setAllowSimpleRegistYN(boolean isAllowSimpleRegistYN) {
		this.isAllowSimpleRegistYN = isAllowSimpleRegistYN;
	}
	
	/**
	 * 수신자 실명확인 여부 확인 
	 * 
	 * @return isVerifyNameYN
	 */
	public boolean isVerifyNameYN() {
		return isVerifyNameYN;
	}
	
	/**
	 * 수신자 실명확인 여부 설정
	 * 
	 * @param isVerifyNameYN
	 */
	public void setVerifyNameYN(boolean isVerifyNameYN) {
		this.isVerifyNameYN = isVerifyNameYN;
	}
	
	/**
	 * 예금주명 확인 
	 * 
	 * @return BankAccountName
	 */
	public String getBankAccountName() {
		return BankAccountName;
	}
	
	/**
	 * 예금주명 설정 
	 * 
	 * @param bankAccountName
	 */
	public void setBankAccountName(String bankAccountName) {
		BankAccountName = bankAccountName;
	}
	
	/**
	 * 계좌번호 확인
	 * 
	 * @return BankAccountNum
	 */
	public String getBankAccountNum() {
		return BankAccountNum;
	}
	
	/**
	 * 계좌번호 설정 
	 * 
	 * @param bankAccountNum
	 */
	public void setBankAccountNum(String bankAccountNum) {
		BankAccountNum = bankAccountNum;
	}
	
	/**
	 * 은행코드 확인
	 * 
	 * @return BankCode
	 */
	public String getBankCode() {
		return BankCode;
	}
	
	/**
	 * 은행코드 설정
	 * 
	 * @param bankCode
	 */
	public void setBankCode(String bankCode) {
		BankCode = bankCode;
	}
	
	/**
	 * 고객식별번호 확인
	 * 
	 * @return ClientUserID
	 */
	public String getClientUserID() {
		return ClientUserID;
	}
	
	/**
	 * 고객식별번호 설정
	 *  
	 * @param clientUserID
	 */
	public void setClientUserID(String clientUserID) {
		ClientUserID = clientUserID;
	}
	
	/**
	 * App to App 방식 이용 여부 확인
	 * 
	 * @return isAppUseYN
	 */
	public boolean isAppUseYN() {
		return isAppUseYN;
	}

	/**
	 * App to App 방식 이용 설정
	 * 
	 * @param isAppUseYN
	 */
	public void setAppUseYN(boolean isAppUseYN) {
		this.isAppUseYN = isAppUseYN;
	}
}
