package com.kakaocert.api.test;

import org.junit.Test;

import com.kakaocert.api.KakaocertException;
import com.kakaocert.api.KakaocertService;
import com.kakaocert.api.KakaocertServiceImp;
import com.kakaocert.api.VerifyResult;
import com.kakaocert.api.verifyauth.RequestVerifyAuth;
import com.kakaocert.api.verifyauth.ResultVerifyAuth;

public class TEST_VerifyAuth {
	
	private final String testLinkID = "TESTER";
	private final String testSecretKey = "SwWxqU+0TErBXy/9TVjIPEnI0VTUMMSQZtJf3Ed8q3I=";
	
	private KakaocertService kakaocertService;
	
	public TEST_VerifyAuth() {
		KakaocertServiceImp service = new KakaocertServiceImp();
		service.setLinkID(testLinkID);
		service.setSecretKey(testSecretKey);
		service.setUseStaticIP(false);
		
		kakaocertService = service;
		
	}
	
	@Test
	public void request_TEST() throws KakaocertException{
		try {
			RequestVerifyAuth request = new RequestVerifyAuth();
			request.setAllowSimpleRegistYN(true);
			request.setVerifyNameYN(true);
			request.setCallCenterNum("1600-9999");
			request.setCallCenterName("콜센터명");
			request.setExpires_in(1000);
			request.setPayLoad(null);
			request.setReceiverBirthDay("19700101");
			request.setReceiverHP("01012341234");
			request.setReceiverName("홍길동");
			request.setTMSMessage(null);
			request.setSubClientID("020040000004");
			request.setTMSTitle("TMS Title");
			request.setToken("token value");
			
			String receiptID = kakaocertService.requestVerifyAuth("020040000001", request);
			System.out.println(receiptID);
			
		} catch(KakaocertException ke) {
			System.out.println(ke.getCode());
			System.out.println(ke.getMessage());
		}
	}
	
	@Test
	public void getResult_TEST() throws KakaocertException {
		try {
			ResultVerifyAuth result = kakaocertService.getVerifyAuthState("020040000001", "020090815371100001");
			
			System.out.println(result.getCallCenterNum());
			System.out.println(result.getCallCenterName());
			System.out.println(result.getReceiptID());
			System.out.println(result.getRegDT());
			System.out.println(result.getState());
			System.out.println(result.getExpires_in());
			System.out.println(result.isAllowSimpleRegistYN());
			System.out.println(result.isVerifyNameYN());
			System.out.println(result.getPayload());
			System.out.println(result.getRequestDT());
			System.out.println(result.getExpireDT());
			System.out.println(result.getClientCode());
			System.out.println(result.getClientName());
			System.out.println(result.getTmstitle());
			System.out.println(result.getTmsmessage());
			
			System.out.println(result.getSubClientCode());
			System.out.println(result.getSubClientName());
			System.out.println(result.getRequestDT());
			System.out.println(result.getViewDT());
			System.out.println(result.getCompleteDT());
			System.out.println(result.getVerifyDT());
			
		} catch (KakaocertException ke) {
			System.out.println(ke.getCode());
			System.out.println(ke.getMessage());
		}
	}
	
	@Test
	public void verifyAuth_TEST() throws KakaocertException {
		try {
			VerifyResult result = kakaocertService.verifyAuth("020040000001", "020090815371100001");
			
			System.out.println(result.getReceiptId());
			System.out.println(result.getSignedData());
			
		} catch (KakaocertException ke) {
			System.out.println(ke.getCode());
			System.out.println(ke.getMessage());
		}
	}
}
