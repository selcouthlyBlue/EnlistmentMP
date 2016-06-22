package com.orangeandbronze.enlistment.domain;

import java.util.Collection;
import java.util.HashSet;

public class Subject {
	
	private final String subjectID;
	private final Collection<Subject> prerequistes = new HashSet<Subject>();

	public Subject(String subjectID) {
		checkIfValid(subjectID);
		this.subjectID = subjectID;
	}
	
	public Subject(String subjectID, Subject[] prerequisites) {
		checkIfValid(subjectID);
		checkIfAtLeastOnePrequisiteIsNullIn(prerequisites);
		this.subjectID = subjectID;
		for(Subject prerequisite : prerequisites){
			this.prerequistes.add(prerequisite);
		}
	}

	private void checkIfAtLeastOnePrequisiteIsNullIn(Subject[] prerequisites) {
		if(prerequisites == null){
			throw new IllegalArgumentException("Argument 'prerequisites' "
					+ "should not be null");
		}
		for(Subject prerequisite : prerequisites){
			if(prerequisite == null)
				throw new IllegalArgumentException("Argument 'prerequisites' "
						+ "should not have null values.");
		}
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
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if (!(obj instanceof Subject))
			return false;
		Subject otherSubject = (Subject) obj;
		if(!(this.subjectID.equals(otherSubject.subjectID)))
			return false;
		return true;
	}
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + subjectID == null ? 0 : subjectID.hashCode();
        return result;
    }

}
