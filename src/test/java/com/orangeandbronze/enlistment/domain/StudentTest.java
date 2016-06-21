package com.orangeandbronze.enlistment.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class StudentTest {
	
	@Test
	public void studentWithPositiveStudentNumber() throws Exception {
		try {
			new Student(12345);
		} catch (IllegalArgumentException expected) {
			fail("Instantiating a Student object should not throw "
					+ "an exception if the studentNumber passed "
					+ "is a non-negative integer.");
		}
	}
	
	@Test
	public void studentWithNegativeStudentNumberRaisesAnException() 
			throws Exception {
		try {
			new Student(-12345);
			fail("Instantiating a Student object should throw "
					+ "an exception if the studentNumber passed "
					+ "is a non-negative integer.");
		} catch (IllegalArgumentException expected) {
			
		}
	}
}
