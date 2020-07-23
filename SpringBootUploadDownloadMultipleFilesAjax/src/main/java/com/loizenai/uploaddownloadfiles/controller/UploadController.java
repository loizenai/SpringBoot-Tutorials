package com.loizenai.uploaddownloadfiles.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.loizenai.uploaddownloadfiles.message.Message;
import com.loizenai.uploaddownloadfiles.message.Response;
import com.loizenai.uploaddownloadfiles.service.FileService;

@RestController
@RequestMapping("/api/upload/file")
public class UploadController {

	@Autowired
	FileService fileService;
	
	@PostMapping("/multiple")
	public Response uploadMultipleFiles(@RequestParam("uploadfiles") MultipartFile[] uploadfiles) {
		
		Response response = new Response();
		/*
		 * Filtering files had been selected for uploading (the files having names)
		 */
		MultipartFile[] readyUploadedFiles = Arrays.stream(uploadfiles).filter(
									x -> !StringUtils.isEmpty(x.getOriginalFilename())).toArray(MultipartFile[]::new);
	
	    /*
	     * Checking whether having at least one file had been selected for uploading
	     */
	    if (readyUploadedFiles.length == 0) {
	    	response.addMessage(new Message("", "No selected file to upload!", "fail"));
	    	return response;
	    }
	
	    /*
	     * Do the uploading
	     */
		for (MultipartFile file : readyUploadedFiles) {
			try {
				fileService.store(file);
				response.addMessage(new Message(file.getOriginalFilename(), "Upload Successfully!", "ok"));
			} catch (Exception e) {
				response.addMessage(new Message(file.getOriginalFilename(), e.getMessage(), "fail"));
			}
		}
		
		return response;
	}
	
	@PostMapping("/single")
	public Response uploadSingleFile(@RequestParam("uploadfile") MultipartFile uploadfile) {
		
		Response response = new Response();
	
		// Checking the upload-file before processing 
		if(uploadfile.getOriginalFilename().isEmpty()) {
			response.addMessage(new Message(uploadfile.getOriginalFilename(), 
								"No selected file to upload! Please do the checking", "fail"));
			
			return response;
		}
		
		try {
			// save file to disk
			fileService.store(uploadfile);
			response.addMessage(new Message(uploadfile.getOriginalFilename(), "Upload Successfully!", "ok"));
		} catch(Exception e) {
			response.addMessage(new Message(uploadfile.getOriginalFilename(), e.getMessage(), "fail"));
		}
		
		return response;
	}
}