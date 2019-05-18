package com.test.fiction.fictiongame.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EmptyDataException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7647539608777644003L;

	public EmptyDataException(String exception) {
		super(exception);
	}

}
