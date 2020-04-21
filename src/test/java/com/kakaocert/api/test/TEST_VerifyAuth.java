package com.kakaocert.api.test;

import org.junit.Test;

import com.kakaocert.api.KakaocertException;
import com.kakaocert.api.KakaocertService;
import com.kakaocert.api.KakaocertServiceImp;
import com.kakaocert.api.verifyauth.RequestVerifyAuth;
import com.kakaocert.api.verifyauth.ResultVerifyAuth;

public class TEST_VerifyAuth {
	
	private final String testLinkID = "KAKAOCERT0406";
	private final String testSecretKey = "9HOTRlrOipIPRGkDdELwYnESP4XTOGZbXrD67FvNyqU=";
	
	private KakaocertService kakaocertService;
	
	public TEST_VerifyAuth() {
		KakaocertServiceImp service = new KakaocertServiceImp();
		service.setLinkID(testLinkID);
		service.setSecretKey(testSecretKey);
		service.setAuthURL("https://dev-auth.linkhub.kr");
		service.setServiceURL("https://dev-kc-api.linkhub.kr");
		
		kakaocertService = service;
		
	}
	
	@Test
	public void request_TEST() throws KakaocertException{
		try {
			RequestVerifyAuth request = new RequestVerifyAuth();
			request.setAllowSimpleRegistYN(true);
			request.setVerifyNameYN(true);
			request.setCallCenterNum("1600-9999");
			request.setExpires_in(60);
			request.setPayLoad("payload");
			request.setReceiverBirthDay("19900108");
			request.setReceiverHP("01043245117");
			request.setReceiverName("정요한");
			request.setTMSMessage("TMS Message");
			request.setSubClientID("");
			request.setTMSTitle("TMS Title");
			request.setToken("token value");
			
			String receiptID = kakaocertService.requestVerifyAuth("020040000050", request);
			System.out.println(receiptID);
			
		} catch(KakaocertException ke) {
			System.out.println(ke.getCode());
			System.out.println(ke.getMessage());
		}
	}
	
	@Test
	public void getResult_TEST() throws KakaocertException {
		try {
			ResultVerifyAuth result = kakaocertService.getVerifyAuthResult("020040000050", "020042111054000001");
			
			System.out.println(result.getCallCenterNum());
			System.out.println(result.getReceiptID());
			System.out.println(result.getRegDT());
			System.out.println(result.getState());
			System.out.println(result.getReceiverHP());
			System.out.println(result.getReceiverName());
			System.out.println(result.getExpires_in());
			System.out.println(result.getToken());
			System.out.println(result.isAllowSimpleRegistYN());
			System.out.println(result.isVerifyNameYN());
			System.out.println(result.getPayload());
			System.out.println(result.getRequestDT());
			System.out.println(result.getExpireDT());
			System.out.println(result.getClientCode());
			System.out.println(result.getClientName());
			System.out.println(result.getTmstitle());
			System.out.println(result.getTmsmessage());
			System.out.println(result.getReturnToken());
			
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
}
