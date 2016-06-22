package com.orangeandbronze.enlistment.domain;

public class SameSubjectException extends IllegalArgumentException {

	public SameSubjectException(String subject) {
		super(subject + " was already enlisted.");
	}

}
