package com.loizenai.uploaddownloadfiles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.loizenai.uploaddownloadfiles.service.FileStorage;

@Controller
public class UploadSingleFileController {

	@Autowired
	FileStorage fileStorage;

	@GetMapping("/uploadsinglefile")
	public String index() {
		return "uploadsinglefile.html";
	}
	
	@PostMapping("/uploadsinglefile")
	public String uploadSingleFile(@RequestParam("uploadfile") MultipartFile file, Model model) {
		
		if(file.getOriginalFilename().isEmpty()) {
			model.addAttribute("message", "Fail -> Upload filename is empty! Please check it!");
			return "uploadsinglefile.html";
		}
		
		try {
			fileStorage.store(file);
			model.addAttribute("message", "Successfully! -> upload filename: " + file.getOriginalFilename());
		} catch (Exception e) {
			model.addAttribute("message", "Fail! -> Existed Uploaded Filename: " + file.getOriginalFilename());
		}			
		
		return "uploadsinglefile.html";		
	}
}
