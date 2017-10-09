package com.excel.to.sql.dto;

public class ServerInputFileUploadDto {
	private int id;

	private byte[] file;

	private String mimeType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private String fileName;

	public ServerInputFileUploadDto(int id, byte[] file, String mimeType,
			String fileName) {
		this.id = id;
		this.file = file;
		this.mimeType = mimeType;
		this.fileName = fileName;
	}

}
