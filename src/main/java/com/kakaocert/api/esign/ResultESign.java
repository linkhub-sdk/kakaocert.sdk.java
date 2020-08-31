package com.kakaocert.api.esign;

public class ResultESign {
	
	private String receiptID;
	private String regDT;
	private int state;
	private int expires_in;
	private String callCenterNum;
	private String token;
	private boolean allowSimpleRegistYN;
	private boolean verifyNameYN;
	private String payload;
	private String requestDT;
	private String expireDT;
	private String clientCode;
	private String clientName;
	private String tmstitle;
	private String tmsmessage;
	private String signedData;
	
	private String subClientName;
	private String subClientCode;
	private String viewDT;
	private String completeDT;
	private String verifyDT;
	private boolean appUseYN;
	private String tx_id;
	
	public String getReceiptID() {
		return receiptID;
	}
	public String getRegDT() {
		return regDT;
	}
	public int getState() {
		return state;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public String getCallCenterNum() {
		return callCenterNum;
	}
	public String getToken() {
		return token;
	}
	public boolean isAllowSimpleRegistYN() {
		return allowSimpleRegistYN;
	}
	public boolean isVerifyNameYN() {
		return verifyNameYN;
	}
	public String getPayload() {
		return payload;
	}
	public String getRequestDT() {
		return requestDT;
	}
	public String getExpireDT() {
		return expireDT;
	}
	public String getClientCode() {
		return clientCode;
	}
	public String getClientName() {
		return clientName;
	}
	public String getTmstitle() {
		return tmstitle;
	}
	public String getTmsmessage() {
		return tmsmessage;
	}
	public String getSignedData() {
		return signedData;
	}
	public String getSubClientName() {
		return subClientName;
	}
	public String getSubClientCode() {
		return subClientCode;
	}
	public String getViewDT() {
		return viewDT;
	}
	public String getCompleteDT() {
		return completeDT;
	}
	public String getVerifyDT() {
		return verifyDT;
	}
	public boolean isAppUseYN() {
		return appUseYN;
	}
	public String getTx_id() {
		return tx_id;
	}
	
}