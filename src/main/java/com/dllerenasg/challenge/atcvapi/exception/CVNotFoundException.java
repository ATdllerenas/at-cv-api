package com.dllerenasg.challenge.atcvapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CVNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CVNotFoundException(String messageDetails) {
		super(messageDetails);
		}
}
