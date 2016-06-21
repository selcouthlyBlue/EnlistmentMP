package com.orangeandbronze.enlistment.domain;

import java.util.Collection;
import java.util.HashSet;

public class Section {
	
	private final String sectionID;
	private Collection<Student> enlistedStudents;
	private final Schedule schedule;
	private Room room;
	
	public Section(String sectionID, Schedule schedule, Room room) {
		checkIfValid(sectionID);
		this.sectionID = sectionID;
		this.enlistedStudents = new HashSet<Student>();
		this.schedule = schedule;
		this.room = room;
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

	public boolean hasSameScheduleAs(Section sectionToBeEnlisted) {
		return this.schedule.equals(sectionToBeEnlisted.schedule);
	}

}
