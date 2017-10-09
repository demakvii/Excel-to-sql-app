package com.excel.to.sql.dao;

import org.springframework.stereotype.Repository;

import com.excel.to.sql.entity.ServerInputFileUpload;

@Repository
public interface IServerInputFileUploadDao {

	public ServerInputFileUpload uploadToServer(ServerInputFileUpload fileUpload);

	public ServerInputFileUpload getFileFromServer(String fileName);

	public void flushFile(String fileName);

	public void flushAll();

	public ServerInputFileUpload getFileFromServerById(Long id);

}
