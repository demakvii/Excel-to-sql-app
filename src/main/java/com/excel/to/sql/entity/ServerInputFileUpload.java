package com.excel.to.sql.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NamedQueries({ @NamedQuery(

name = "findServerFileByName", query = "from ServerInputFileUpload sifu where sifu.fileName =:fileName")

})
@Entity
@Table(name = "mak_excel_to_sql_input")
public class ServerInputFileUpload {

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

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "outputFile_id")
	private ServerOutputFileUpload outputFile;

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

	public ServerInputFileUpload(){
		
	}
	
	public ServerInputFileUpload(byte[] file, String mimeType, String fileName,
			ServerOutputFileUpload outputFile) {
		this.file = file;
		this.mimeType = mimeType;
		this.fileName = fileName;
		this.outputFile = outputFile;
	}

	public ServerOutputFileUpload getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(ServerOutputFileUpload outputFile) {
		this.outputFile = outputFile;
	}

}
