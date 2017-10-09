package com.excel.to.sql.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.excel.to.sql.entity.ServerInputFileUpload;
import com.excel.to.sql.entity.ServerOutputFileUpload;
import com.excel.to.sql.service.ServerManagerHandler;

@Controller
public class MainController {

	private static final int BUFFER_SIZE = 1024;

	@Autowired
	ServerManagerHandler handler;

	@RequestMapping(value = "/sendResp", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> sendDefaultResponse(
			HttpServletRequest req, HttpServletResponse resp) {
		Map<String, Object> respMsg = new HashMap<String, Object>();
		respMsg.put("success", true);
		respMsg.put("data", "Correctly recieved and sent");
		return respMsg;
	}

	@RequestMapping(value = "/UploadConvert", method = RequestMethod.POST)
	public void uploadInputFile(@RequestParam("files") MultipartFile[] files,
			HttpServletResponse resp) {
		ServerOutputFileUpload op = null;
		ServerInputFileUpload in = null;
		System.out.println(files.length);
		try {
			op = new ServerOutputFileUpload(files[1].getBytes(),
					files[1].getContentType(), files[1].getOriginalFilename());
			in = new ServerInputFileUpload(files[0].getBytes(),
					files[0].getContentType(), files[0].getOriginalFilename(),
					op);
			in = handler.uploadToServer(in);
		} catch (IOException e) {

		}
		byte[] sqlFile = null;
		try {
			sqlFile = handler.convertExcelToSql(true,
					Long.parseLong(in.getId() + ""));
		} catch (NumberFormatException | IOException e) {

		}
		if (sqlFile != null) {
			InputStream sqlFileFinal = new ByteArrayInputStream(sqlFile);
			resp.setContentType("text/plain");
			resp.setContentLength((int) sqlFile.length);
			resp.setHeader(
					"Content-Disposition",
					String.format("attachment; filename=\"%s\"",
							in.getFileName() + ".sql"));
			OutputStream outStream = null;
			try {
				outStream = resp.getOutputStream();
			} catch (IOException e) {

			}
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			try {
				while ((bytesRead = sqlFileFinal.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				sqlFileFinal.close();
				outStream.close();
			} catch (IOException e) {

			}

		}
		handler.flushFile(in.getFileName());

	}

	@RequestMapping(value = "/convert", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> convertExcelToSql(
			@RequestBody String sessionId, @RequestBody Boolean isMapped,
			HttpServletResponse resp) throws IOException {
		Map<String, Object> respMsg = new HashMap<String, Object>();
		respMsg.put("success", true);
		respMsg.put("data", "Converted Successfully");
		return respMsg;
	}
}
