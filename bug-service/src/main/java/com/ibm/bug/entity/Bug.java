package com.ibm.bug.entity;
import java.util.Date;

//import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import com.ibm.bug.PRIORITY;
import com.ibm.bug.STATUS;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;

import com.ibm.bug.STATUS;
import com.ibm.bug.TYPE;
import com.ibm.bug.PRIORITY;

public class Bug {
	
	@Id
	private String id;
	@NotNull
	private PRIORITY priority;
	@NotNull
	private TYPE type;
	@NotNull
	private STATUS status;
	private String buildVersion;
	@NotNull
	@NotBlank
	private String projectId;
	private Date submitOn;
	private String module;
	@NotBlank
	@NotNull
	@Size(min = 5, max = 1000, message = "Should be between 5 and 1000 characters")
	private String description;
	@NotBlank
	@NotNull
	@Size(min = 5, max = 200, message = "Should be between 5 and 200 characters")
	private String title;
	private String product;
	
	public TYPE getType() {
		return type;
	}
	public void setType(TYPE type) {
		this.type = type;
	}

	public PRIORITY getPriority() {
		return priority;
	}
	public void setPriority(PRIORITY priority) {
		this.priority = priority;
	}
	public STATUS getStatus() {
		return status;
	}
	public void setStatus(STATUS status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBuildVersion() {
		return buildVersion;
	}
	public void setBuildVersion(String buildVersion) {
		this.buildVersion = buildVersion;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public Date getSubmitOn() {
		return submitOn;
	}
	public void setSubmitOn(Date submitOn) {
		this.submitOn = submitOn;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description.trim();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title.trim();
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
}
