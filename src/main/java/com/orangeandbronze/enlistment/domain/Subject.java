package com.orangeandbronze.enlistment.domain;

public class Subject {

	public Subject(String subjectID) {
		checkIfValid(subjectID);
	}
	
	private void checkIfValid(String subjectID) {
		checkIfNull(subjectID);
		checkIfBlank(subjectID);
		checkIfContainsNonalphanumericCharacters(subjectID);
	}

	private void checkIfNull(String subjectID) {
		if(subjectID == null){
			throw new IllegalArgumentException("Argument 'subjectID' "
					+ "should not be null.");
		}
	}

	private void checkIfBlank(String subjectID) {
		if(subjectID.equals("")){
			throw new IllegalArgumentException("Argument 'subjectID' "
					+ "should not be blank.");
		}
	}

	private void checkIfContainsNonalphanumericCharacters(String subjectID) {
		if(!subjectID.matches("[a-zA-Z0-9]+")){
			throw new IllegalArgumentException("Argument 'subjectID' "
					+ "should contain only alphanumeric characters. "
					+ "sectiondID was " + subjectID);
		}
	}

}
