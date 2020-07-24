package com.loizenai.uploaddownloadfiles.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.loizenai.uploaddownloadfiles.info.FileInfo;
import com.loizenai.uploaddownloadfiles.service.FileStorage;

/**
 * Copyright by https://loizenai.com
 * @author loizenai.com
 *
 */

@Controller
@RequestMapping("/api/download")
public class DownloadController {
	
	@Autowired
	FileStorage fileStorage;

	/*
	 * Retrieve Files' Information
	 */
	@GetMapping("/files")
	public String getListFiles(Model model) {
		
		List<FileInfo> fileInfos = fileStorage.getFiles().map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder
					.fromMethodName(DownloadController.class, "downloadFile", 
											path.getFileName().toString()).build()
					.toString();
			
			return new FileInfo(filename, url);
		}).collect(Collectors.toList());

		model.addAttribute("files", fileInfos);
		return "files.html";
	}

	/*
	 * Download Files
	 */
	@GetMapping("/file/{filename}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
		Resource file = fileStorage.loadFile(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, 
						"attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}