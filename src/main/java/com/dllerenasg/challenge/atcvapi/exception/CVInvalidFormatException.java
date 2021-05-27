package com.dllerenasg.challenge.atcvapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CVInvalidFormatException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public CVInvalidFormatException(String messageDetails) {
		super(messageDetails);
		}
}
