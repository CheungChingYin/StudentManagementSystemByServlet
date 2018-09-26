package com.management.exception;

public class AdministratorExistException extends Exception {

	public AdministratorExistException() {
		super();
	}

	public AdministratorExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AdministratorExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public AdministratorExistException(String message) {
		super(message);
	}

	public AdministratorExistException(Throwable cause) {
		super(cause);
	}

}
