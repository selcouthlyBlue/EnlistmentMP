package com.orangeandbronze.enlistment.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class SubjectTest {
	
	private Subject generateSubject(String subjectID){
		return new Subject(subjectID);
	}
	
	private Subject generateSubjectWithValidSubjectID(Subject... prerequisites){
		return new Subject("ML100", prerequisites);
	}

	@Test
	public void subjectWithSubjectIDContainingOnlyAlphanumericCharacters() 
			throws Exception{
		try {
			generateSubject("ML100");
		} catch (IllegalArgumentException expected) {
			fail("Instantiating a Subject object should not throw "
					+ "an exception if the subjectID passed "
					+ "contains only alphanumeric characters.");
		}
	}
	
	@Test
	public void subjectWithNullSubjectIDRaisesAnException() throws Exception {
		try {
			generateSubject(null);
			fail("Instantiating a Subject object should throw "
					+ "an exception if the subjectID passed "
					+ "is null.");
		} catch (IllegalArgumentException expected) {
			
		}
	}
	
	@Test
	public void subjectWithBlankSubjectIDRaisesAnException() throws Exception {
		try {
			generateSubject("");
			fail("Instantiating a Subject object should throw "
					+ "an exception if the subjectID passed "
					+ "is blank.");
		} catch (IllegalArgumentException expected) {
			
		}
	}
	
	@Test
	public void subjectWithSubjectIDContainingANonAlphanumericCharacterRaisesAnException() 
			throws Exception {
		try {
			generateSubject("$#!+");
			fail("Instantiating a Subject object should throw "
					+ "an exception if the subjectID passed "
					+ "contains a nonalphanumeric character.");
		} catch (IllegalArgumentException expected) {
			
		}
	}
	
	@Test
	public void subjectWithValidPrerequisites() throws Exception {
		Subject prereqSubject = new Subject("CMSC142");
		try {
			generateSubjectWithValidSubjectID(prereqSubject);
		} catch (IllegalArgumentException e) {
			fail("Instantiating a Subject object should not throw "
					+ "an exception if the prerequisite subject(s) passed "
					+ "are valid.");
		}
	}
	
	@Test
	public void subjectWithNullPrequisitesRaisesAnException() throws Exception {
		try {
			generateSubjectWithValidSubjectID(null);
			fail("Instantiating a Subject object should throw "
					+ "an exception if the prerequisites passed "
					+ "is null.");
		} catch (IllegalArgumentException e) {
			
		}
	}
	
	@Test
	public void subjectWithAtLeastOneNullPrerequisiteRaisesAnException() 
			throws Exception {
		Subject prereqSubject = new Subject("CMSC142");
		try {
			generateSubjectWithValidSubjectID(prereqSubject, null);
			fail("Instantiating a Subject object should throw "
					+ "an exception if the prerequisites passed "
					+ "includes a null value.");
		} catch (IllegalArgumentException e) {
		}
	}

}
