package com.ibm.bug.service;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.bug.STATUS;
import com.ibm.bug.entity.Bug;
import com.ibm.bug.repo.BugRepository;

@Service
public class BugService {
	@Autowired
	BugRepository bugRepository;

	/**
	 * method to return the List of the bugs present in db
	 * 
	 * @return all the bugs present in db
	 */
	public List<Bug> getBugs() {
		return bugRepository.findAll();
	}

	/**
	 * method to get the particular bug
	 * 
	 * @param bugId
	 * @return zero or matching bug
	 */

//	public Optional<Bug> getBug(String bugId) {
//		return bugRepository.findById(bugId);	
//		}
//	
	
	public Optional<Bug> getBugbyName(String bugTitle) {
		return bugRepository.findByName(bugTitle);
	}
	
	/**
	 * method to update the status of the code
	 * @param bug
	 * @returns updated bug status
	 */
	
	public STATUS updateBugStatus(@Valid Bug bug) {
		//bugRepository.save(bug);
		STATUS status = bug.getStatus();
		Optional<Bug> oldBug = bugRepository.findById(bug.getId());
		oldBug.ifPresent(oldbug->{
			STATUS oldstatus = oldbug.getStatus();
			if(oldstatus==STATUS.ASSIGNED)
			{
				if(status==STATUS.NEW) {
					throw new IllegalArgumentException("STATUS NOT ALLOWED");
				}
				else {
					bugRepository.save(bug);


				}
			}
			else if(oldstatus==STATUS.OPEN)
			{
				if(status==STATUS.ASSIGNED || status==STATUS.NEW) {
					throw new IllegalArgumentException(" STATUS NOT ALLOWED");
				}
				else {
					bugRepository.save(bug);


				}
			}
			else if(oldstatus==STATUS.FIXED)
			{
				if(status==STATUS.ASSIGNED || status==STATUS.NEW ||status==STATUS.OPEN) {
					throw new IllegalArgumentException(" STATUS NOT ALLOWED");
				}
				else {
					bugRepository.save(bug);


				}
			}
			else if(oldstatus==STATUS.PENDING_RETEST)
			{
				if(status==STATUS.ASSIGNED || status==STATUS.NEW ||status==STATUS.OPEN ||status==STATUS.FIXED) {
					throw new IllegalArgumentException(" STATUS NOT ALLOWED");
				}
				else {
					bugRepository.save(bug);


				}
			}
			else if(oldstatus==STATUS.RETEST)
			{
				if(status==STATUS.ASSIGNED || status==STATUS.NEW ||status==STATUS.OPEN ||status==STATUS.FIXED ||status==STATUS.PENDING_RETEST) {
					throw new IllegalArgumentException(" STATUS NOT ALLOWED");
				}
				else {
					bugRepository.save(bug);


				}
			}
			else if(oldstatus==STATUS.REOPEN)
			{
				if(status==STATUS.ASSIGNED || status==STATUS.NEW ||status==STATUS.OPEN ||status==STATUS.VERIFIED ||status==STATUS.CLOSED ) {
					throw new IllegalArgumentException(" STATUS NOT ALLOWED");
				}
				else {
					bugRepository.save(bug);


				}
			}
			else if(oldstatus==STATUS.VERIFIED)
			{
				if(status==STATUS.ASSIGNED || status==STATUS.NEW ||status==STATUS.OPEN ||status==STATUS.FIXED ||status==STATUS.PENDING_RETEST || status==STATUS.RETEST || status==STATUS.REOPEN) {
					throw new IllegalArgumentException(" STATUS NOT ALLOWED");
				}
				else {
					bugRepository.save(bug);


				}
			}
			else if(oldstatus==STATUS.CLOSED)
			{
				if(status==STATUS.ASSIGNED || status==STATUS.NEW ||status==STATUS.OPEN ||status==STATUS.FIXED ||status==STATUS.PENDING_RETEST || status==STATUS.RETEST || status==STATUS.REOPEN || status==STATUS.VERIFIED) {
					throw new IllegalArgumentException(" STATUS NOT ALLOWED");
				}
				else {
					bugRepository.save(bug);


				}
			}
			else {
				bugRepository.save(bug);
			}



		});
		return bug.getStatus();
		
	}
	
	public String createBug(@Valid Bug bug) {
		bugRepository.save(bug);
		return bug.getId();
	}

	//For purpose of JUNIT testing
	public BugRepository getBugRepository() {
		return bugRepository;
	}

	public void setBugRepository(BugRepository bugRepository) {
		this.bugRepository = bugRepository;
	}

}
