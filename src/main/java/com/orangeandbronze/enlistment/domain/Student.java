package com.orangeandbronze.enlistment.domain;

import java.util.Collection;
import java.util.HashSet;

public class Student {

	private final int studentNumber;
	private Collection<Section> enlistedSections;

	public Student(int studentNumber) {
		checkIfValidStudentNumber(studentNumber);
		this.studentNumber = studentNumber;
		this.enlistedSections = new HashSet<Section>();
	}

	private void checkIfValidStudentNumber(int studentNumber) {
		if(studentNumber < 0){
			throw new IllegalArgumentException("Argument 'studentNumber' "
					+ "should be a positive integer. studentNumber was "
					+ studentNumber);
		}
	}
	
	@Override
	public String toString() {
		return "Student# " + studentNumber;
	}
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + studentNumber;
        return result;
    }
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof Student))
			return false;
		Student otherStudent = (Student) obj;
		if(this.studentNumber != otherStudent.studentNumber)
			return false;
		return true;
	}

	public StudentSemEnlistments enlist(Section sectionToBeEnlisted) throws ConflictInSectionEnlistmentException{
		checkIffPreviouslyEnlisted(sectionToBeEnlisted);
		checkIfAPreviouslyEnlistedSectionIsInConflictWith(sectionToBeEnlisted);
		sectionToBeEnlisted.enlist(this);
		enlistedSections.add(sectionToBeEnlisted);
		return new StudentSemEnlistments(this, new Semester());
	}

	private void checkIffPreviouslyEnlisted(Section section) {
		if(enlistedSections.contains(section)){
			throw new ConflictInSectionEnlistmentException("Section " + section 
					+ " was previously enlisted.");
		}
	}

	private void checkIfAPreviouslyEnlistedSectionIsInConflictWith(Section sectionToBeEnlisted) {
		for(Section section : enlistedSections){
			if(section.hasSameScheduleAs(sectionToBeEnlisted)){
				throw new ConflictInSectionEnlistmentException("Section " + sectionToBeEnlisted
						+ " is in conflict with " + section);
			}
		}
	}

}
