package com.loizenai.uploaddownloadfiles;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.loizenai.uploaddownloadfiles.service.FileService;

@SpringBootApplication
public class SpringBootUploadFilesAjaxApplication implements CommandLineRunner {

	@Resource
	FileService fileService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUploadFilesAjaxApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		fileService.deleteAll();
		fileService.init();
	}
}