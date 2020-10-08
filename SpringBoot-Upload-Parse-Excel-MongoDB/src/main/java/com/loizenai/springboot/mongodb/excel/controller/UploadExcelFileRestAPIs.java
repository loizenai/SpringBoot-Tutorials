package com.loizenai.springboot.mongodb.excel.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.loizenai.springboot.mongodb.excel.message.Message;
import com.loizenai.springboot.mongodb.excel.message.Response;
import com.loizenai.springboot.mongodb.excel.services.ExcelFileServices;
import com.loizenai.springboot.mongodb.excel.utils.ExcelUtils;

@RestController
public class UploadExcelFileRestAPIs {

	@Autowired
	ExcelFileServices fileServices;
	
	@PostMapping("/api/upload/excel/single")
	public Response uploadSingleExcelFile(@RequestParam("excelfile") MultipartFile uploadfile) {

		Response response = new Response();
		
        if (StringUtils.isEmpty(uploadfile.getOriginalFilename())) {
        	response.addMessage(new Message(uploadfile.getOriginalFilename(),
					"No selected file to upload! Please do the checking", "fail"));
	
			return response;
        }
        
		if(!ExcelUtils.isExcelFile(uploadfile)) { 
		    response.addMessage(new Message(uploadfile.getOriginalFilename(), "Error: this is not a Excel file!", "fail")); 
	        return response; 
		}
        
		try {
			// save file data to MongoDB
			fileServices.store(uploadfile);
			response.addMessage(new Message(uploadfile.getOriginalFilename(), "Upload File Successfully!", "ok"));
		} catch (Exception e) {
			response.addMessage(new Message(uploadfile.getOriginalFilename(), e.getMessage(), "fail"));
		}
	
		return response;
    }
	
	@PostMapping("/api/upload/excel/multiple")
	public Response uploadFileMulti(
	        @RequestParam("excelfiles") MultipartFile[] uploadfiles) {

		Response response = new Response();
		
        MultipartFile[] readyUploadedFiles = Arrays.stream(uploadfiles)
				.filter(x -> !StringUtils.isEmpty(x.getOriginalFilename())).toArray(MultipartFile[]::new);
        
        
        /*
		 * Checking whether having at least one file had been selected for uploading
		 */
		if (readyUploadedFiles.length == 0) {
			response.addMessage(new Message("", "No selected file to upload!", "fail"));
			return response;
		}
		
		/*
		 * Checking uploaded files are Excel files or NOT
		 */
        String notExcelFiles = Arrays.stream(uploadfiles).filter(x -> !ExcelUtils.isExcelFile(x))
        							.map(x -> x.getOriginalFilename())
        							.collect(Collectors.joining(" , "));
	
		if (!StringUtils.isEmpty(notExcelFiles)) {
			response.addMessage(new Message(notExcelFiles, "Not Excel Files", "fail"));
			return response;
		}

    	for(MultipartFile file: uploadfiles) {
            try {
        		fileServices.store(file);
        		response.addMessage(new Message(file.getOriginalFilename(), "Upload Successfully!", "ok"));
            } catch (Exception e) {
            	response.addMessage(new Message(file.getOriginalFilename(), e.getMessage(), "fail"));
            }
    	}
    	
    	return response;
    }
}