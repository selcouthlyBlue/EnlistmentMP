package com.orangeandbronze.enlistment.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class SubjectTest {
	
	private Subject generateSubject(String subjectID){
		return new Subject(subjectID);
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

}
