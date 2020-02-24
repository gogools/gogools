package com.gogools.sb.service.files;

public class StorageException extends RuntimeException {

	private static final long serialVersionUID = 699235772705557123L;

	public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
