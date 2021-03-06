package com.kakaocert.api;

import kr.co.linkhub.auth.LinkhubException;

/**
 * Kakaocert Operation Exception.
 * 
 * @author John
 * @version 1.0.0
 *
 */
public class KakaocertException extends Exception{
	private static final long serialVersionUID = 1L;

	private long code;
	
	public KakaocertException(LinkhubException linkhubException) {
		super(linkhubException.getMessage(), linkhubException);
		this.code = linkhubException.getCode();
	}
	
	public KakaocertException(long code, String Message) {
		super(Message);
		this.code = code;
	}

	public KakaocertException(long code, String Message, Throwable innerException) {
		super(Message, innerException);
		this.code = code;
	}

	/**
	 * Return Kakaocert's result Error code. (ex. -31010009) In case of -99999999,
	 * check the getMessage() for detail.
	 * 
	 * @return error code.
	 */
	public long getCode() {
		return code;
	}
}
