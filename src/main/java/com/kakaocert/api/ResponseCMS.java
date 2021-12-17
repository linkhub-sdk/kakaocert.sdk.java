package com.kakaocert.api;

public class ResponseCMS {
	
	private String receiptId;
	private String tx_id;
	
	/**
	 * 접수 아이디 확인
	 * 
	 * @return receiptId
	 */
	public String getReceiptId() {
		return receiptId;
	}
	
	/**
	 * 	카카오톡 트랜잭션아이디 확인
	 * @return tx_id
	 */
	public String getTx_id() {
		return tx_id;
	}
}