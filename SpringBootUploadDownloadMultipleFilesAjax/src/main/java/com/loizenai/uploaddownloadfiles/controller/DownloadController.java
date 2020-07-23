package com.loizenai.uploaddownloadfiles.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.loizenai.uploaddownloadfiles.message.FileInfo;
import com.loizenai.uploaddownloadfiles.message.Response;
import com.loizenai.uploaddownloadfiles.service.FileService;

@RestController
@RequestMapping("/api/download")
public class DownloadController {

	@Autowired
	FileService fileService;

	/*
	 * Retrieve Files' Information
	 */
	@GetMapping("/files")
	public Response getListFiles(Model model) {

		List<FileInfo> files = fileService.getFiles().map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder
					.fromMethodName(DownloadController.class, "downloadFile", path.getFileName().toString()).build()
					.toString();

			return new FileInfo(filename, url);
		}).collect(Collectors.toList());

		return new Response(files);
	}

	/*
	 * Download Files
	 */
	@GetMapping("/file/{filename}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
		
		MediaType contentType = MediaType.IMAGE_PNG;
		
		String extension = filename.split("\\.")[1];
		
		switch(extension) {
			case "txt": contentType = MediaType.TEXT_PLAIN;
						break;
			case "xlsx": contentType = MediaType.parseMediaType("application/vnd.ms-excel");
						break;
		}
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=" + filename);
		
		return ResponseEntity.ok().headers(headers)
									.contentType(contentType)
									.body(fileService.loadFile(filename));
	}
}