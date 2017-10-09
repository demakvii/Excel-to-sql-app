package com.excel.to.sql.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "mak_excel_to_sql_output")
public class ServerOutputFileUpload {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Lob
	@Column(name = "byte_file")
	private byte[] file;

	@Column(name = "file_type")
	private String mimeType;

	@Column(name = "file_name")
	private String fileName;

	/*
	 * @OneToOne(mappedBy = "outputFile", cascade = CascadeType.ALL, fetch =
	 * FetchType.EAGER, orphanRemoval = true)
	 * 
	 * @JoinColumn(name = "id") private ServerInputFileUpload inputFile;
	 */

	/*
	 * public ServerInputFileUpload getInputFile() { return inputFile; }
	 * 
	 * public void setInputFile(ServerInputFileUpload inputFile) {
	 * this.inputFile = inputFile; }
	 */
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getId() {
		return id;
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

	public ServerOutputFileUpload() {

	}

	public ServerOutputFileUpload(byte[] file, String mimeType, String fileName) {
		this.file = file;
		this.mimeType = mimeType;
		this.fileName = fileName;
	}

}
