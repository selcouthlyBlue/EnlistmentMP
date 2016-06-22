package com.orangeandbronze.enlistment.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.orangeandbronze.enlistment.dao.SectionDAO;
import com.orangeandbronze.enlistment.dao.StudentDAO;
import com.orangeandbronze.enlistment.dao.StudentSemEnlistmentsDAO;
import com.orangeandbronze.enlistment.domain.Days;
import com.orangeandbronze.enlistment.domain.EnlistmentException;
import com.orangeandbronze.enlistment.domain.Period;
import com.orangeandbronze.enlistment.domain.Room;
import com.orangeandbronze.enlistment.domain.Schedule;
import com.orangeandbronze.enlistment.domain.Section;
import com.orangeandbronze.enlistment.domain.Student;
import com.orangeandbronze.enlistment.domain.StudentSemEnlistments;
import com.orangeandbronze.enlistment.domain.Subject;

public class EnlistServiceTest {
	
	private SectionDAO sectionDao;
	private StudentDAO studentDao;
	private StudentSemEnlistmentsDAO studentSemEnlistmentsDao;
	private EnlistService service;

	@Before
	public void setUp() {
		sectionDao = mock(SectionDAO.class);
		studentDao = mock(StudentDAO.class);
		studentSemEnlistmentsDao = mock(StudentSemEnlistmentsDAO.class);
		service = new EnlistService();
		service.setSectionDao(sectionDao);
		service.setStudentDao(studentDao);
		service.setStudentSemEnlistmentsDao(studentSemEnlistmentsDao);
	}
	
	@Test
	public void enlist() throws Exception {
		final int studentNumber = 12345;
		final Student student = new Student(studentNumber);
		when(studentDao.findBy(studentNumber)).thenReturn(student);
		
		final Subject subject = new Subject("ML100");
		final Room room = Room.RH300;
		final String sectionID = "MEH1";
		final Schedule schedule = new Schedule(Days.MTH, Period.FIRSTPERIOD);
		final Section section = new Section(sectionID, schedule, room, subject);
		when(sectionDao.findBy(sectionID)).thenReturn(section);
		
		final StudentSemEnlistments studentSemEnlistments = service.enlist(studentNumber, sectionID);
		
		assertTrue(section.contains(student));
		verify(studentSemEnlistmentsDao).update(studentSemEnlistments);
	}
	
	@Test
	public void enlistingACurrentlyEnlistedSectionRaisesAnException() throws Exception {
		final int studentNumber = 12345;
		final Student student = new Student(studentNumber);
		when(studentDao.findBy(studentNumber)).thenReturn(student);
		
		final Subject subjectCommonToBothSections = new Subject("ML100");
		final Room room = Room.RH300;
		final String sectionID = "MEH1";
		final Schedule schedule = new Schedule(Days.MTH, Period.FIRSTPERIOD);
		final Section section = new Section(sectionID, schedule, room, subjectCommonToBothSections);
		when(sectionDao.findBy(sectionID)).thenReturn(section);
		
		final Schedule differentSchedule = new Schedule(Days.TF, Period.SECONDPERIOD);
		final String differentSectionID = "TFC1";
		final Section sectionWithSameSubjectAsAnotherSection = new Section(differentSectionID, differentSchedule, room, subjectCommonToBothSections);
		when(sectionDao.findBy(differentSectionID)).thenReturn(sectionWithSameSubjectAsAnotherSection);
		
		service.enlist(studentNumber, sectionID);
		try {
			service.enlist(studentNumber, differentSectionID);
			fail("enlist() method of Student object "
					+ "should thorw an exception if the section being enlisted "
					+ "has the same subject as any of the previously enlisted "
					+ "sections.");
		} catch (EnlistmentException expected) {
			
		}
	}
}
