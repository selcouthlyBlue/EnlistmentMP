package com.orangeandbronze.enlistment.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class SectionTest {
	
	private Section generateSectionWithValidScheduleRoomAndSubject(String sectionID){
		Schedule schedule = new Schedule(Days.MTH, Period.FIRSTPERIOD);
		Subject subject = new Subject("ML100");
		return new Section(sectionID, schedule, Room.GAB207, subject);
	}

	@Test
	public void sectionWithSectionIDContainingOnlyAlphanumericCharacters() 
			throws Exception{
		try {
			generateSectionWithValidScheduleRoomAndSubject("MEH1");
		} catch (IllegalArgumentException expected) {
			fail("Instantiating a Section object should not throw "
					+ "an exception if the sectionID passed "
					+ "contains only alphanumeric characters.");
		}
	}
	
	@Test
	public void newSectionWithNullSectionIDRaisesAnException() throws Exception {
		try {
			generateSectionWithValidScheduleRoomAndSubject(null);
			fail("Instantiating a Section object should throw "
					+ "an exception if the sectionID passed "
					+ "is null.");
		} catch (IllegalArgumentException expected) {
			
		}
	}
	
	@Test
	public void sectionWithBlankStudentIDRaisesAnException() throws Exception {
		try {
			generateSectionWithValidScheduleRoomAndSubject("");
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
			generateSectionWithValidScheduleRoomAndSubject("$#!+");
			fail("Instantiating a Section object should throw "
					+ "an exception if the sectionID passed "
					+ "contains a nonalphanumeric character.");
		} catch (IllegalArgumentException expected) {
			
		}
	}

}
