package com.orangeandbronze.enlistment.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class SubjectSectionTest {
	
	private Section generateSectionWithValidSectionIDScheduleAndRoom(Subject subject){
		Schedule schedule = new Schedule(Days.MTH, Period.FIRSTPERIOD);
		return new Section("MHE1", schedule, Room.GAB207, subject);
	}

	@Test
	public void settingANullSubjectIntoASectionRaisesAnException() 
			throws Exception {
		try {
			generateSectionWithValidSectionIDScheduleAndRoom(null);
			fail("Instantiating a Section should throw an exception "
					+ "if the subject passed in the constructor is null");
		} catch (IllegalArgumentException e) {

		}
	}

}
