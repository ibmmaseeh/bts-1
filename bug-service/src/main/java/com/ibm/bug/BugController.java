package com.ibm.bug;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.multipart.PartHttpMessageWriter;
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
	List<Bug> getBugByName(@PathVariable("title") String bugTitle) {
		return bugService.getBugByTitle(bugTitle);
	}
	
	@GetMapping("/bug/status/{status}")
	List<Bug> getBugByStatus(@PathVariable("status") STATUS bugStatus) {
		return bugService.getBugByStatus(bugStatus);
	}
	
	@GetMapping("/bug/search/{title}/{status}")
	List<Bug> findByStatusAndTitle(@PathVariable("status") STATUS bugStatus, @PathVariable("title") String bugTitle) {
		return bugService.findByStatusAndTitle(bugStatus, bugTitle);
	}
	@GetMapping("/bug/{name}")
	List<Bug>getBybyPartialSearch(@PathVariable("name") String bugName){
		return bugService.getBugByPartialSearch(bugName);
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
	
	@DeleteMapping("/bug/{id}")
	void deleteUser(@PathVariable("id") String bugId) {
		bugService.deleteBug(bugId);
	}

	private void validateModel(Errors bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException("Something went wrong.Please Retry!!");
		}
	}
	
	

	

}
