package com.loizenai.springboot.uploadcsv.mongodb.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loizenai.springboot.uploadcsv.mongodb.service.CsvFileServices;

/**
 * Copyright https://loizenai.com
 * @author loizenai.com
 *
 */
@RestController
public class DownloadCsvRestApi {
	
	@Autowired
	CsvFileServices csvFileService;

	/*
	 * Download CSV Files
	 */
	@GetMapping("/api/download/csv/")
	public void downloadFile(HttpServletResponse response) throws IOException {
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=customers.csv");
		csvFileService.loadFile(response.getWriter());
	}
}