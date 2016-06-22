package com.orangeandbronze.enlistment.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
	
	@Test
	public void enlist() throws Exception {
		SectionDAO sectionDao = mock(SectionDAO.class);
		StudentDAO studentDao = mock(StudentDAO.class);
		StudentSemEnlistmentsDAO studentSemEnlistmentsDao = mock(StudentSemEnlistmentsDAO.class);
		EnlistService service = new EnlistService();
		service.setSectionDao(sectionDao);
		service.setStudentDao(studentDao);
		service.setStudentSemEnlistmentsDao(studentSemEnlistmentsDao);
		
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
