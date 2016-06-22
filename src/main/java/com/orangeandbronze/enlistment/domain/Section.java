package com.orangeandbronze.enlistment.domain;

import java.util.Collection;
import java.util.HashSet;

public class Section {
	
	private final String sectionID;
	private Collection<Student> enlistedStudents;
	private final Schedule schedule;
	private Room room;
	private final Subject subject;

	public Section(String sectionID, Schedule schedule, Room room, Subject subject) {
		checkIfValid(sectionID);
		checkIfNull(subject);
		this.sectionID = sectionID;
		this.enlistedStudents = new HashSet<Student>();
		this.schedule = schedule;
		this.room = room;
		this.subject = subject;
	}

	private void checkIfValid(String sectionID) {
		checkIfNull(sectionID);
		checkIfBlank(sectionID);
		checkIfContainsNonalphanumericCharacters(sectionID);
	}

	private void checkIfNull(String sectionID) {
		if(sectionID == null){
			throw new IllegalArgumentException("Argument 'sectionID' "
					+ "should not be null.");
		}
	}

	private void checkIfBlank(String sectionID) {
		if(sectionID.equals("")){
			throw new IllegalArgumentException("Argument 'sectionID' "
					+ "should not be blank.");
		}
	}

	private void checkIfContainsNonalphanumericCharacters(String sectionID) {
		if(!sectionID.matches("[a-zA-Z0-9]+")){
			throw new IllegalArgumentException("Argument 'sectionID' "
					+ "should contain only alphanumeric characters. "
					+ "sectiondID was " + sectionID);
		}
	}

	public boolean contains(Student student) {
		return enlistedStudents.contains(student);
	}

	private void checkIfNull(Subject subject) {
		if(subject == null){
			throw new IllegalArgumentException("Argument 'subject' "
					+ "should not be null.");
		}
	}

	public int getNumberOfEnlistedStudents() {
		return enlistedStudents.size();
	}

	public void enlist(Student student) {
		checkIfSectionIsFull();
		enlistedStudents.add(student);
	}
	
	private void checkIfSectionIsFull() {
		if(enlistedStudents.size() == Room.getCapacity(room)){
			throw new EnlistmentException("This section "
					+ this + " is already full.");
		}
	}

	public boolean hasSameScheduleAs(Section sectionToBeEnlisted) {
		return this.schedule.equals(sectionToBeEnlisted.schedule);
	}
	
	@Override
	public String toString() {
		return "Section ID: " + sectionID;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof Schedule))
			return false;
		Section otherSection = (Section) obj;
		if(!this.sectionID.equals(otherSection.sectionID))
			return false;
		return true;
	}
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + sectionID == null ? 0 : sectionID.hashCode();
        return result;
    }

}
