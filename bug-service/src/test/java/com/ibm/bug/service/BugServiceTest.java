package com.ibm.bug.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ibm.bug.STATUS;
import com.ibm.bug.entity.Bug;
import com.ibm.bug.repo.BugRepository;

class BugServiceTest {
	BugService bugService;
	BugRepository dummyRepo;
	Bug bug ;

	@BeforeEach
	void init(){
		bugService = new BugService();
		dummyRepo = new DummyBugRepository();
		bugService.setBugRepository(dummyRepo);
		bug = new Bug();
	}
	
	/**
	 * method to test the createBug() method
	 */
	@Test
	void testCreateBug() {
		String bugId = bugService.createBug(bug);
		assertNotNull(bugId);
	}

	/**
	 * method to test UpdateBug method via STATUS parameter
	 */
	@Test
	void testUpdateBugStatus() {
		STATUS status = bugService.updateBugStatus(bug);
		assertNotNull(status);
		assertEquals(STATUS.VERIFIED, status);
	}

	/**
	 * method to test the getBugs method
	 */
	@Test
	void testGetBugs() {
		List<Bug> bugsList = bugService.getBugs();  
		assertNotNull(bugsList);
 		assertEquals("24398t84gb32oi", bugsList.get(0).getId());
	}
	
	/**
	 * method to test the getBug method using bugId parameter
	 */
//	@Test
//	void testGetBugById() {
//		Optional<Bug> bug1 = bugService.getBug("24398t84gb32oi");
//		assertNotNull(bug1);
//	}
	
	
	

}
