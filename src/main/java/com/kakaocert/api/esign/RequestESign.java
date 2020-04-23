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
	
	private String CallCenterNum;
	private int Expires_in;
	private String PayLoad;
	private String ReceiverBirthDay;
	private String ReceiverHP;
	private String ReceiverName;
	private String SubClientID;
	private String TMSMessage;
	private String TMSTitle;
	private String Token;
	private boolean isAllowSimpleRegistYN;
	private boolean isVerifyNameYN;
	
	/**
	 * 고객센터 전화번호 확인
	 * 
	 * @return CallCenterNum
	 */
	public String getCallCenterNum() {
		return CallCenterNum;
	}
	
	/**
	 * 고객센터 전화번호 설정
	 * 
	 * @param callCenterNum
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
	 * @param expires_in
	 */
	public void setExpires_in(int expires_in) {
		this.Expires_in = expires_in;
	}
	
	/**
	 * Payload 확인 
	 * 
	 * @return PayLoad
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
	 */
	public void setTMSTitle(String tMSTitle) {
		TMSTitle = tMSTitle;
	}
	
	/**
	 * 전자서명 토큰원문 확인 
	 * 
	 * @return Token
	 */
	public String getToken() {
		return Token;
	}
	
	/**
	 * 전자서명 토큰원문 설정 
	 * 
	 * @param token
	 */
	public void setToken(String token) {
		Token = token;
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
