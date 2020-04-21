package com.kakaocert.api.verifyauth;

import java.io.Serializable;

/**
 * class for VerifyAuth Information
 * 
 * @author John
 * @version 1.0.0
 */
public class RequestVerifyAuth implements Serializable{

	private static final long serialVersionUID = -7183094820383012243L;

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
	
	public String getCallCenterNum() {
		return CallCenterNum;
	}
	
	public void setCallCenterNum(String callCenterNum) {
		this.CallCenterNum = callCenterNum;
	}
	public int getExpires_in() {
		return Expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.Expires_in = expires_in;
	}
	public String getPayLoad() {
		return PayLoad;
	}
	public void setPayLoad(String payLoad) {
		this.PayLoad = payLoad;
	}
	public String getReceiverBirthDay() {
		return ReceiverBirthDay;
	}
	public void setReceiverBirthDay(String receiverBirthDay) {
		this.ReceiverBirthDay = receiverBirthDay;
	}
	public String getReceiverHP() {
		return ReceiverHP;
	}
	public void setReceiverHP(String receiverHP) {
		this.ReceiverHP = receiverHP;
	}
	public String getReceiverName() {
		return ReceiverName;
	}
	public void setReceiverName(String receiverName) {
		this.ReceiverName = receiverName;
	}
	public String getSubClientID() {
		return SubClientID;
	}
	public void setSubClientID(String subClientID) {
		this.SubClientID = subClientID;
	}
	public String getTMSMessage() {
		return TMSMessage;
	}
	public void setTMSMessage(String tMSMessage) {
		TMSMessage = tMSMessage;
	}
	public String getTMSTitle() {
		return TMSTitle;
	}
	public void setTMSTitle(String tMSTitle) {
		TMSTitle = tMSTitle;
	}
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}
	public boolean isAllowSimpleRegistYN() {
		return isAllowSimpleRegistYN;
	}
	public void setAllowSimpleRegistYN(boolean isAllowSimpleRegistYN) {
		this.isAllowSimpleRegistYN = isAllowSimpleRegistYN;
	}
	public boolean isVerifyNameYN() {
		return isVerifyNameYN;
	}
	public void setVerifyNameYN(boolean isVerifyNameYN) {
		this.isVerifyNameYN = isVerifyNameYN;
	}
}
