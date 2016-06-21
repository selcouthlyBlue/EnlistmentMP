package com.orangeandbronze.enlistment.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class SectionTest {
	
	private Section generateSectionWithValidScheduleAndRoom(String sectionID){
		Schedule schedule = new Schedule(Days.MTH, Period.FIRSTPERIOD);
		return new Section(sectionID, schedule, Room.GAB207);
	}

	@Test
	public void sectionWithSectionIDContainingOnlyAlphanumericCharacters() 
			throws Exception{
		try {
			generateSectionWithValidScheduleAndRoom("MEH1");
		} catch (IllegalArgumentException expected) {
			fail("Instantiating a Section object should not throw "
					+ "an exception if the sectionID passed "
					+ "contains only alphanumeric characters.");
		}
	}
	
	@Test
	public void newSectionWithNullSectionIDRaisesAnException() throws Exception {
		try {
			generateSectionWithValidScheduleAndRoom(null);
			fail("Instantiating a Section object should throw "
					+ "an exception if the sectionID passed "
					+ "is null.");
		} catch (IllegalArgumentException expected) {
			
		}
	}
	
	@Test
	public void sectionWithBlankStudentIDRaisesAnException() throws Exception {
		try {
			generateSectionWithValidScheduleAndRoom("");
			fail("Instantiating a Section object should throw "
					+ "an exception if the sectionID passed "
					+ "is blank.");
		} catch (IllegalArgumentException expected) {
			
		}
	}
	
	@Test
	public void sectionWithSectionIDContainingANonAlphanumericCharacterRaisesAnException() 
			throws Exception {
		try {
			generateSectionWithValidScheduleAndRoom("$#!+");
			fail("Instantiating a Section object should throw "
					+ "an exception if the sectionID passed "
					+ "contains a nonalphanumeric character.");
		} catch (IllegalArgumentException expected) {
			
		}
	}

}
