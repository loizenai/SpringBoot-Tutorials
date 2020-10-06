package com.loizenai.springboot.uploadcsv.mongodb.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.loizenai.springboot.uploadcsv.mongodb.message.Message;
import com.loizenai.springboot.uploadcsv.mongodb.message.Response;
import com.loizenai.springboot.uploadcsv.mongodb.service.CsvFileServices;
import com.loizenai.springboot.uploadcsv.mongodb.util.ApacheCommonsCsvUtil;

/**
 * 
 * Copyright by https://loizenai.com
 * @author loizenai.com
 *
 */

@RestController
@RequestMapping("/api/upload/csv")
public class UploadCsvRestApi {
	@Autowired
	CsvFileServices csvFileServices;

	@PostMapping("/multiple")
	public Response uploadMultipleFiles(@RequestParam("csvfiles") MultipartFile[] csvfiles) {
	
		Response response = new Response();
		/*
		 * Filtering files had been selected for uploading (the files having names)
		 */
		MultipartFile[] readyUploadedFiles = Arrays.stream(csvfiles)
				.filter(x -> !StringUtils.isEmpty(x.getOriginalFilename())).toArray(MultipartFile[]::new);
	
		/*
		 * Checking whether having at least one file had been selected for uploading
		 */
		if (readyUploadedFiles.length == 0) {
			response.addMessage(new Message("", "No selected file to upload!", "fail"));
			return response;
		}
	
		/*
		 * Checking uploaded files are CSV files or NOT
		 */
	
		String notCsvFiles = Arrays.stream(csvfiles).filter(x -> !ApacheCommonsCsvUtil.isCSVFile(x))
								 	.map(x -> x.getOriginalFilename()).collect(Collectors.joining(" , "));
	
		if (!StringUtils.isEmpty(notCsvFiles)) {
			response.addMessage(new Message(notCsvFiles, "Not Csv Files", "fail"));
			return response;
		}
		 
		/*
		 * Do the uploading
		 */
		for (MultipartFile file : readyUploadedFiles) {
			try {
				csvFileServices.store(file.getInputStream());
				response.addMessage(new Message(file.getOriginalFilename(), "Upload Successfully!", "ok"));
			} catch (Exception e) {
				response.addMessage(new Message(file.getOriginalFilename(), e.getMessage(), "fail"));
			}
		}
	
		return response;
	}

	@PostMapping("/single")
	public Response uploadSingleCSVFile(@RequestParam("csvfile") MultipartFile csvfile) {
	
		Response response = new Response();
	
		// Checking the upload-file's name before processing
		if (csvfile.getOriginalFilename().isEmpty()) {
			response.addMessage(new Message(csvfile.getOriginalFilename(),
					"No selected file to upload! Please do the checking", "fail"));
	
			return response;
		}
	
		// checking the upload file's type is CSV or NOT
		
		if(!ApacheCommonsCsvUtil.isCSVFile(csvfile)) { 
		    response.addMessage(new Message(csvfile.getOriginalFilename(), "Error: this is not a CSV file!", "fail")); 
	        return response; 
		}
		  
		 
		try {
			// save file data to database
			csvFileServices.store(csvfile.getInputStream());
			response.addMessage(new Message(csvfile.getOriginalFilename(), "Upload File Successfully!", "ok"));
		} catch (Exception e) {
			response.addMessage(new Message(csvfile.getOriginalFilename(), e.getMessage(), "fail"));
		}
	
		return response;
	}
}