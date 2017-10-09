package com.excel.to.sql.service;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excel.to.sql.dao.IServerInputFileUploadDao;
import com.excel.to.sql.entity.ServerInputFileUpload;
import com.excel.to.sql.main.logic.ExcelToSqlMain;

@Service
public class ServerManagerHandler {

	@Autowired
	private IServerInputFileUploadDao serverFileUploadDao;

	@Transactional
	public ServerInputFileUpload uploadToServer(ServerInputFileUpload fileUpload) {
		fileUpload = serverFileUploadDao.uploadToServer(fileUpload);
		return fileUpload;
	}

	@Transactional(readOnly = true)
	public ServerInputFileUpload getFileFromServer(String fileName) {
		return serverFileUploadDao.getFileFromServer(fileName);
	}

	@Transactional
	public void flushFile(String fileName) {
		serverFileUploadDao.flushFile(fileName);
	}

	@Transactional
	public void flushAll() {
		serverFileUploadDao.flushAll();
	}

	public byte[] convertExcelToSql(Boolean isMapped, Long sessionId)
			throws IOException {
		ServerInputFileUpload inputFile = serverFileUploadDao
				.getFileFromServerById(sessionId);
		File inFile = new File(inputFile.getId() + inputFile.getFileName());
		File outFile = new File(inputFile.getId()
				+ inputFile.getOutputFile().getFileName());
		FileUtils.writeByteArrayToFile(inFile, inputFile.getFile());
		FileUtils.writeByteArrayToFile(outFile, inputFile.getOutputFile()
				.getFile());
		byte[] obj = ExcelToSqlMain.convertExcelToSql(isMapped, inputFile.getFile(), inputFile.getOutputFile().getFile());
		inFile.delete();
		outFile.delete();
		return obj;
	}
}
