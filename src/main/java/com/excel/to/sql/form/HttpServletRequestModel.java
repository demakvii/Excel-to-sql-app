package com.excel.to.sql.form;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public class HttpServletRequestModel {

	private List<MultipartHttpServletRequest> files;
	
	private int noOfFiles;

	public List<MultipartHttpServletRequest> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartHttpServletRequest> files) {
		this.files = files;
	}

	public int getNoOfFiles() {
		return noOfFiles;
	}

	public void setNoOfFiles(int noOfFiles) {
		this.noOfFiles = noOfFiles;
	}
}
