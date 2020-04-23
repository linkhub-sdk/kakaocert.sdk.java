package com.kakaocert.api.esign;

import java.io.Serializable;

/**
 * class for RequestESign Information
 * 
 * @author John
 * @version 1.0.0
 */
public class RequestESign implements Serializable{
	private static final long serialVersionUID = -612033468073051353L;
	
	private String callCenterNum;
	private int expires_in;
	private String payLoad;
	private String receiverBirthDay;
	private String receiverHP;
	private String receiverName;
	private String subClientID;
	private String tMSMessage;
	private String tMSTitle;
	private String token;
	private boolean isAllowSimpleRegistYN;
	private boolean isVerifyNameYN;
	
	/**
	 * 고객센터 전화번호 확인
	 * 
	 * @return CallCenterNum
	 */
	public String getCallCenterNum() {
		return callCenterNum;
	}
	
	/**
	 * 고객센터 전화번호 설정
	 * 
	 * @param callCenterNum
	 */
	public void setCallCenterNum(String callCenterNum) {
		this.callCenterNum = callCenterNum;
	}
	
	/**
	 * 인증요청 만료시간(초) 확인
	 * 
	 * @return Expires_in
	 */
	public int getExpires_in() {
		return expires_in;
	}
	
	/**
	 * 인증요청 만료시간(초) 설정 
	 * @param expires_in
	 */
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	
	/**
	 * Payload 확인 
	 * 
	 * @return PayLoad
	 */
	public String getPayLoad() {
		return payLoad;
	}
	
	/**
	 * Payload 설정
	 * 
	 * @param payLoad
	 */
	public void setPayLoad(String payLoad) {
		this.payLoad = payLoad;
	}
	
	/**
	 * 수신자 생년월일 확인 
	 * 
	 * @return ReceiverBirthDay
	 */
	public String getReceiverBirthDay() {
		return receiverBirthDay;
	}
	
	/**
	 * 수신자 생년월일 설정 
	 * 
	 * @param receiverBirthDay
	 */
	public void setReceiverBirthDay(String receiverBirthDay) {
		this.receiverBirthDay = receiverBirthDay;
	}
	
	/**
	 * 수신자 휴대폰번호 확인 
	 * 
	 * @return ReceiverHP
	 */
	public String getReceiverHP() {
		return receiverHP;
	}
	
	/**
	 * 수신자 휴대폰번호 설정
	 * 
	 * @param receiverHP
	 */
	public void setReceiverHP(String receiverHP) {
		this.receiverHP = receiverHP;
	}
	
	/**
	 * 수신자명 확인 
	 * 
	 * @return ReceiverName
	 */
	public String getReceiverName() {
		return receiverName;
	}
	
	/**
	 * 수신자명 설정 
	 * 
	 * @param receiverName
	 */
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
	/**
	 * 별칭코드 확인 
	 * 
	 * @return SubClientID
	 */
	public String getSubClientID() {
		return subClientID;
	}
	
	/**
	 * 별칭코드 설정 
	 * 
	 * @param subClientID
	 */
	public void setSubClientID(String subClientID) {
		this.subClientID = subClientID;
	}
	
	/**
	 * 인증요청 부가메시지 확인
	 * 
	 * @return TMSMessage
	 */
	public String getTMSMessage() {
		return tMSMessage;
	}
	
	/**
	 * 인증요청 부가메시지 설정 
	 * 
	 * @param tMSMessage
	 */
	public void setTMSMessage(String TMSMessage) {
		tMSMessage = TMSMessage;
	}
	
	/**
	 * 인증요청 메시지제목 확인
	 * 
	 * @return TMSTitle
	 */
	public String getTMSTitle() {
		return tMSTitle;
	}
	
	/**
	 * 인증요청 메시지제목 설정
	 * 
	 * @param tMSTitle
	 */
	public void setTMSTitle(String TMSTitle) {
		tMSTitle = TMSTitle;
	}
	
	/**
	 * 전자서명 토큰원문 확인 
	 * 
	 * @return Token
	 */
	public String getToken() {
		return token;
	}
	
	/**
	 * 전자서명 토큰원문 설정 
	 * 
	 * @param token
	 */
	public void setToken(String Token) {
		token = Token;
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
	

}
