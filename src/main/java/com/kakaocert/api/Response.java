package com.kakaocert.api;

/**
 * Base Response of Kakaocert Service.
 * 
 * @author John
 * @version 1.0.0
 */
public class Response {
	private long code;
	private String message;
	
	/**
	 * return Response code
	 * 
	 * @return code of response
	 */
	public long getCode() {
		return code;
	}
	
	/**
	 * returnn Response Message.
	 * 
	 * @return message of response.
	 */
	public String getMessage() {
		return message;
	}
}
