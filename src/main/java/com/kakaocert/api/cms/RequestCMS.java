package com.kakaocert.api.cms;

import java.io.Serializable;

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
	
	private String BankAccountName;
	private String BankAccountNum;
	private String BankCode;
	private String ClientUserID;
	
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
	public String getBankAccountName() {
		return BankAccountName;
	}
	public void setBankAccountName(String bankAccountName) {
		BankAccountName = bankAccountName;
	}
	public String getBankAccountNum() {
		return BankAccountNum;
	}
	public void setBankAccountNum(String bankAccountNum) {
		BankAccountNum = bankAccountNum;
	}
	public String getBankCode() {
		return BankCode;
	}
	public void setBankCode(String bankCode) {
		BankCode = bankCode;
	}
	public String getClientUserID() {
		return ClientUserID;
	}
	public void setClientUserID(String clientUserID) {
		ClientUserID = clientUserID;
	}
}
