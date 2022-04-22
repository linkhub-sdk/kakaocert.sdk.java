package com.kakaocert.api.test;

import org.junit.Test;

import com.kakaocert.api.KakaocertException;
import com.kakaocert.api.KakaocertService;
import com.kakaocert.api.KakaocertServiceImp;
import com.kakaocert.api.ResponseCMS;
import com.kakaocert.api.VerifyResult;
import com.kakaocert.api.cms.RequestCMS;
import com.kakaocert.api.cms.ResultCMS;

public class TEST_CMS {
	private final String testLinkID = "TESTER";
	private final String testSecretKey = "SwWxqU+0TErBXy/9TVjIPEnI0VTUMMSQZtJf3Ed8q3I=";
	
	private KakaocertService kakaocertService;
	
	public TEST_CMS() {
		KakaocertServiceImp service = new KakaocertServiceImp();
		service.setLinkID(testLinkID);
		service.setSecretKey(testSecretKey);
		service.setUseStaticIP(false);
		service.setUseLocalTimeYN(true);
		kakaocertService = service;
		
	}
	
	@Test
	public void request_TEST_old() throws KakaocertException{
		try {
			RequestCMS request = new RequestCMS();
			request.setAllowSimpleRegistYN(false);
			request.setVerifyNameYN(false);
			request.setCallCenterNum("1600-9999");
			request.setCallCenterName("콜센터명");
			request.setExpires_in(60);
			request.setPayLoad(null);
			request.setReceiverBirthDay("19700101");
			request.setReceiverHP("01012341234");
			request.setReceiverName("홍길동");
			request.setTMSMessage(null);
			request.setSubClientID("020040000004");
			request.setTMSTitle("메시지명칭");
			
			request.setBankAccountName(null);
			request.setBankAccountNum("9-4324-5117-58");
			request.setBankCode("004");
			request.setClientUserID("123");
			
			
			String receiptId = kakaocertService.requestCMS("020040000001", request);
			System.out.println(receiptId);
			
		} catch(KakaocertException ke) {
			System.out.println(ke.getCode());
			System.out.println(ke.getMessage());
		}
	}
	
	@Test
	public void request_TEST() throws KakaocertException{
		try {
			RequestCMS request = new RequestCMS();
			request.setAllowSimpleRegistYN(false);
			request.setVerifyNameYN(false);
			request.setCallCenterNum("1600-9999");
			request.setCallCenterName("콜센터명");
			request.setExpires_in(60);
			request.setPayLoad(null);
			request.setReceiverBirthDay("19700101");
			request.setReceiverHP("01012341234");
			request.setReceiverName("홍길동");
			request.setTMSMessage(null);
			request.setSubClientID("020040000004");
			request.setTMSTitle("메시지명칭");
			
			request.setBankAccountName(null);
			request.setBankAccountNum("9-4324-5117-58");
			request.setBankCode("004");
			request.setClientUserID("123");
			
			
			ResponseCMS receipt = kakaocertService.requestCMS("020040000001", request, false);
			System.out.println(receipt.getReceiptId());
			System.out.println(receipt.getTx_id());
			
		} catch(KakaocertException ke) {
			System.out.println(ke.getCode());
			System.out.println(ke.getMessage());
		}
	}
	
	@Test
	public void getResult_TEST() throws KakaocertException {
		try {
			ResultCMS result = kakaocertService.getCMSState("020040000001", "021121711315300001");
			
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
			System.out.println(result.getTx_id());
			System.out.println(result.isAppUseYN());
			
		} catch (KakaocertException ke) {
			System.out.println(ke.getCode());
			System.out.println(ke.getMessage());
		}
	}
	
	@Test
	public void verifyCMS_TEST_old() throws KakaocertException {
		try {
			VerifyResult result = kakaocertService.verifyCMS("020040000001", "021121711315300001");
			
			System.out.println(result.getReceiptId());
			System.out.println(result.getSignedData());
			
		} catch (KakaocertException ke) {
			System.out.println(ke.getCode());
			System.out.println(ke.getMessage());
		}
	}
	
	@Test
	public void verifyCMS_TEST() throws KakaocertException {
		try {
			VerifyResult result = kakaocertService.verifyCMS("020040000001", "021121711252900001","123131444413");
			
			System.out.println(result.getReceiptId());
			System.out.println(result.getSignedData());
			
		} catch (KakaocertException ke) {
			System.out.println(ke.getCode());
			System.out.println(ke.getMessage());
		}
	}
}
