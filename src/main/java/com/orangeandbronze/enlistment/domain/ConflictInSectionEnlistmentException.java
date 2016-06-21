package com.orangeandbronze.enlistment.domain;

public class ConflictInSectionEnlistmentException extends IllegalArgumentException {
	
	public ConflictInSectionEnlistmentException() {
	}

	public ConflictInSectionEnlistmentException(String message) {
		super(message);
	}
}
