package com.ibm.bug;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ibm.bug.entity.Bug;
import com.ibm.bug.service.BugService;

// This deals with the frontend part
//Jenkins test Comment
@RestController
public class BugController {
	@Autowired
	BugService bugService;

	/**
	 * method to get all the bugs present in db
	 * 
	 * @return all the bugs
	 */

	@GetMapping("/bug")
	List<Bug> getBugs() {
		return bugService.getBugs();

	}

	/**
	 * method to get the bug with particular id
	 * 
	 * @param bugId
	 * @return zero or matching bug
	 */

//	@GetMapping("/bug/{id}")
//	Optional<Bug> getBug(@PathVariable("id") String bugId) {
//		return bugService.getBug(bugId);
//	}
	
	@GetMapping("/bug/title/{title}")
	Optional<Bug> getBugbyName(@PathVariable("title") String bugTitle) {
		return bugService.getBugbyName(bugTitle);
		}
	

	@GetMapping("/bug/status/{status}")
	List<Bug> getBugbyName(@PathVariable("status") STATUS bugStatus) {
		return bugService.getBugbyStatus(bugStatus);
		}
	
	

	@GetMapping("/bug/search")
	List<Bug> getBugbyStatusAndTitle(@PathParam("status") STATUS bugStatus,@PathParam("title") String bugTitle) {
		return bugService.getBugbyStatusAndTitle(bugStatus,bugTitle);
		}
	
	

	
	

	@PutMapping("/bug/{id}")
	void updateBugStatus(@RequestBody @Valid Bug bug, BindingResult bindingResult,@PathVariable("id") String bugId ) {
		validateModel(bindingResult);
		System.out.println(bugId);
		bug.setId(bugId);
		bugService.updateBugStatus(bug);	
	}
	/**
	 * 
	 * @param bug
	 * @param bindingResult
	 * @return creates bug
	 */
	@PostMapping("/bug")
	@ResponseStatus(code = HttpStatus.CREATED)
	String createBug(@RequestBody @Valid Bug bug, BindingResult bindingResult) {
		validateModel(bindingResult);
		System.out.println(bug);
		return bugService.createBug(bug);

	}

	private void validateModel(Errors bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException("Something went wrong.Please Retry!!");
		}
	}
	
	

	

}
