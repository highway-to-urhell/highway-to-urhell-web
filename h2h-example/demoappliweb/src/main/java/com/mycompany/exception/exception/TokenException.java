package com.highway2urhell.exception.exception;

public class TokenException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TokenException() {
		super("Token null or incorrect");
	}
}
