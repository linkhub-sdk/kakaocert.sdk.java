package com.kakaocert.api.test;

import org.junit.Test;

import com.kakaocert.api.KakaocertException;
import com.kakaocert.api.KakaocertService;
import com.kakaocert.api.KakaocertServiceImp;
import com.kakaocert.api.ResponseESign;
import com.kakaocert.api.VerifyResult;
import com.kakaocert.api.esign.RequestESign;
import com.kakaocert.api.esign.ResultESign;

public class TEST_ESign {

	private final String testLinkID = "TESTER";
	private final String testSecretKey = "SwWxqU+0TErBXy/9TVjIPEnI0VTUMMSQZtJf3Ed8q3I=";
	
	private KakaocertService kakaocertService;
	
	public TEST_ESign() {
		KakaocertServiceImp service = new KakaocertServiceImp();
		service.setLinkID(testLinkID);
		service.setSecretKey(testSecretKey);
		service.setUseStaticIP(false);
		
		kakaocertService = service;
		
	}
	
	@Test
	public void requestESign_TEST() throws KakaocertException{
		try {
			RequestESign request = new RequestESign();
			request.setAllowSimpleRegistYN(true);
			request.setVerifyNameYN(true);
			request.setCallCenterNum("1600-9999");
			request.setCallCenterName("콜센터명");
			request.setExpires_in(60);
			request.setPayLoad(null);
			request.setReceiverBirthDay("19700101");
			request.setReceiverHP("01012341234");
			request.setReceiverName("홍길동");
			request.setTMSMessage(null);
			request.setSubClientID("");
			request.setTMSTitle("TMS Title");
			request.setToken("token value");
			
			ResponseESign response = kakaocertService.requestESign("020040000001", request, false);
			System.out.println(response.getReceiptId());
			System.out.println(response.getTx_id());
			
		} catch(KakaocertException ke) {
			System.out.println(ke.getCode());
			System.out.println(ke.getMessage());
		}
	}
	
	@Test
	public void getESignResult_TEST() throws KakaocertException {
		try {
			ResultESign result = kakaocertService.getESignState("020040000001", "020090815353800001");
			
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
			System.out.println(result.isAppUseYN());
		} catch (KakaocertException ke) {
			System.out.println(ke.getCode());
			System.out.println(ke.getMessage());
		}
	}
	
	@Test
	public void verifyESIGN_TEST() throws KakaocertException {
		try {
			VerifyResult result = kakaocertService.verifyESign("020040000001", "020090815353800001");
			
			System.out.println(result.getReceiptId());
			System.out.println(result.getSignedData());
			
		} catch (KakaocertException ke) {
			System.out.println(ke.getCode());
			System.out.println(ke.getMessage());
		}
	}
	
	@Test
	public void requestESignApp_TEST() throws KakaocertException{
		try {
			RequestESign request = new RequestESign();
			request.setAllowSimpleRegistYN(true);
			request.setVerifyNameYN(true);
			request.setCallCenterNum("1600-9999");
			request.setExpires_in(1);
			request.setPayLoad(null);
			request.setReceiverBirthDay("19900108");
			request.setReceiverHP("01043245117");
			request.setReceiverName("정요한");
			request.setTMSMessage(null);
			request.setSubClientID("");
			request.setTMSTitle("TMS Title");
			request.setToken("token value");
			
			ResponseESign receiptID = kakaocertService.requestESign("020040000001", request, true);
			System.out.println(receiptID.getReceiptId());
			System.out.println(receiptID.getTx_id());
			
		} catch(KakaocertException ke) {
			System.out.println(ke.getCode());
			System.out.println(ke.getMessage());
		}
	}
	
	
}
