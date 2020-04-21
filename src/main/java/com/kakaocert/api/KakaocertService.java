package com.kakaocert.api;

import com.kakaocert.api.cms.RequestCMS;
import com.kakaocert.api.cms.ResultCMS;
import com.kakaocert.api.esign.RequestESign;
import com.kakaocert.api.esign.ResultESign;
import com.kakaocert.api.verifyauth.RequestVerifyAuth;
import com.kakaocert.api.verifyauth.ResultVerifyAuth;

public interface KakaocertService  {

	
	public String requestESign(String ClientCode, RequestESign esignRequest) throws KakaocertException;
	public ResultESign getESignResult(String ClientCode, String receiptID) throws KakaocertException;
	
	
	public String requestVerifyAuth(String ClientCode, RequestVerifyAuth verifyAuthRequest) throws KakaocertException;
	public ResultVerifyAuth getVerifyAuthResult(String ClientCode, String receiptID) throws KakaocertException;
	
	public String requestCMS(String ClientCode, RequestCMS cmsRequest) throws KakaocertException;
	public ResultCMS getCMSResult(String ClientCode, String receiptID) throws KakaocertException;
}
