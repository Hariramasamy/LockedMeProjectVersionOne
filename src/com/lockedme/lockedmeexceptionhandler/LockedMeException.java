package com.lockedme.lockedmeexceptionhandler;

public class LockedMeException extends Exception {

	private static final long serialVersionUID = 1L;

	public LockedMeException() {
		super();
	}

	public LockedMeException(String message) {
		super(message);
	}

}
