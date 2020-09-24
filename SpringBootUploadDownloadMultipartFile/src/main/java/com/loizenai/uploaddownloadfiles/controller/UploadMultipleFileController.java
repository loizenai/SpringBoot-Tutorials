package com.loizenai.uploaddownloadfiles.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.loizenai.uploaddownloadfiles.service.FileStorage;

/**
 * Copyright by https://loizenai.com
 * @author loizenai.com
 *
 */

@Controller
public class UploadMultipleFileController {

	@Autowired
	FileStorage fileStorage;

	@GetMapping("/")
	public String index() {
		return "uploadmultiplefile.html";
	}

	@PostMapping("/")
	public String uploadMultipartFile(@RequestParam("uploadfile") MultipartFile[] files, Model model) {
		List<String> messages = new ArrayList<String>();
		
		for(MultipartFile file: files) {
			try {
				if(file.getOriginalFilename().isEmpty()) {
					messages.add("Fail! -> Empty File Name");
					continue;
				}
				fileStorage.store(file);
				messages.add("Successfully! -> upload filename: " + file.getOriginalFilename());
			} catch (Exception e) {
				messages.add("Fail! -> Existed Uploaded Filename: " + file.getOriginalFilename());
			}			
		}
		model.addAttribute("messages", messages);
		
		return "uploadmultiplefile.html";
	}
}