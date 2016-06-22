package com.orangeandbronze.enlistment.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SectionEnlistmentTest {
	
	private Student student;
	private Section section;
	private Schedule schedule;
	private Subject prereqSubjectOfML100;
	
	@Before
	public void setUp() {
		student = new Student(12345);
		schedule = new Schedule(Days.MTH, Period.FIRSTPERIOD);
		prereqSubjectOfML100 = new Subject("CMSC142");
		section = new Section("MEH1", schedule, Room.RH300, prereqSubjectOfML100);
	}
	
	@Test
	public void studentEnlistingInASection() {
		student.enlist(section);
		assertTrue("Student should be enlisted in the section", 
				section.contains(student));
	}
	
	@Test
	public void numberOfStudentInTheSectionShouldBeUpdatedAfterAStudentEnlistsInTheSection() 
			throws Exception {
		int numberOfEnlistedStudentsBeforeAStudentEnlisted = 
				section.getNumberOfEnlistedStudents();
		student.enlist(section);
		assertFalse(numberOfEnlistedStudentsBeforeAStudentEnlisted - 
				section.getNumberOfEnlistedStudents() == 0);
	}
	
	@Test
	public void studentEnlistingInASectionPreviouslyEnlistedRaisesAnException() throws Exception {
		student.enlist(section);
		try {
			student.enlist(section);
			fail("enlist() method of Student class should throw an exception "
					+ "if the section enlisted was previously enlisted.");
		} catch (DuplicateSectionException expected) {
			
		}
	}
	
	@Test
	public void enlistingACurrentlyEnlistedSectionRaisesAnException() throws Exception {
		student.enlist(section);
		Schedule differentSchedule = new Schedule(Days.MTH, Period.SECONDPERIOD);
		Section sectionWithSubjectSameAsTheOtherSection = new Section("MEH3", differentSchedule, Room.GAB207, prereqSubjectOfML100);
		try {
			student.enlist(sectionWithSubjectSameAsTheOtherSection);
			fail("enlist() method of Student object "
					+ "should thorw an exception if the section being enlisted "
					+ "has the same subject as any of the previously enlisted "
					+ "sections.");
		} catch (SameSubjectException expected) {
			
		}
	}
	
	@Test
	public void enlistingInASectionInConflictAnyOfThePreviouslyEnlistedSections() throws Exception {
		Section sectionWithConflictingSchedule = new Section("MEH3", schedule, Room.GAB207, new Subject("CMSC142"));
		student.enlist(section);
		try {
			student.enlist(sectionWithConflictingSchedule);
			fail("enlist() method of Student class should throw an exception "
					+ "if the section enlisted is in conflict"
					+ " with any of the previously enlisted sections.");
		} catch (ConflictInSectionEnlistmentException e) {
		}
	}
	
	@Test
	public void enlistingInASectionThatIsAlreadyFull() throws Exception {
		fillSectionToFullCapacity();
		try {
			student.enlist(section);
			fail("enlist() method of Section class should throw an exception "
					+ "if the section is already full.");
		} catch (RoomCapacityReachedException e) {
			
		}
	}

	private void fillSectionToFullCapacity() {
		for(int i = 0; i < 40; i++){
			section.enlist(new Student(i));
		}
	}
}
