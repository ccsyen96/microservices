package com.app.error;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GenericException(String message) {
		super(com.app.util.CovidDefaultConstant.COVID_APP + " generic exception--> " + message);
		log.error(" ControllerException :" + message);
	}
}
