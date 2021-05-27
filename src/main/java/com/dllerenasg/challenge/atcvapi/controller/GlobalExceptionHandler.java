package com.dllerenasg.challenge.atcvapi.controller;

import java.time.LocalDateTime;
import com.dllerenasg.challenge.atcvapi.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	protected static final String URI_LABEL = "uri=";
	
	@ExceptionHandler(value = {CVNotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(CVNotFoundException exception, WebRequest request) {
        GlobalExceptionBody body = new GlobalExceptionBody();
        body.setTimestamp(LocalDateTime.now());
        body.setStatus(HttpStatus.NOT_FOUND.value());
        body.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        body.setMessage(exception.getMessage());
        body.setPath(request.getDescription(false).replace(URI_LABEL, ""));
        return handleExceptionInternal(exception, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
	
	@ExceptionHandler(value = {CVMissingRequiredFieldException.class})
    protected ResponseEntity<Object> handleMissingRequiredField(CVMissingRequiredFieldException exception, WebRequest request) {
        GlobalExceptionBody body = new GlobalExceptionBody();
        body.setTimestamp(LocalDateTime.now());
        body.setStatus(HttpStatus.BAD_REQUEST.value());
        body.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        body.setMessage(exception.getMessage());
        body.setPath(request.getDescription(false).replace(URI_LABEL, ""));
        return handleExceptionInternal(exception, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
