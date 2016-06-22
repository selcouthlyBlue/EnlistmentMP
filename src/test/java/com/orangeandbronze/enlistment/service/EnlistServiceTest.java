package com.orangeandbronze.enlistment.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.orangeandbronze.enlistment.dao.SectionDAO;
import com.orangeandbronze.enlistment.dao.StudentDAO;
import com.orangeandbronze.enlistment.dao.StudentSemEnlistmentsDAO;
import com.orangeandbronze.enlistment.domain.Days;
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
}
