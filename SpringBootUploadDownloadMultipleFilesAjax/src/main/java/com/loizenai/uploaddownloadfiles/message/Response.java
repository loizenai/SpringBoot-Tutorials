package com.loizenai.uploaddownloadfiles.message;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright by https://loizenai.com
 * @author loizenai.com
 *
 */

public class Response {
	private List<Message> messages = null;
	private List<FileInfo> fileInfos = null;
	private Error error = null;
	private String errStatus = "";
	
	public Response() {
		this.messages = new ArrayList<Message>();
	}
	
	public Response(List<FileInfo> fileInfos) {
		this.fileInfos = fileInfos; 
	}
	
	public Response(String errStatus, Error err) {
		this.errStatus = errStatus;
		this.error = err;
	}
	
	public void addFileInfo(FileInfo file) {
		this.fileInfos.add(file);
	}
	
	public List<FileInfo> getFileInfos(){
		return this.fileInfos;
	}
	
	public void setMessages(List<Message> messages) {
		this.messages =  messages;
	}
	
	public List<Message> getMessages() {
		return this.messages;
	}
	
	public void addMessage(Message message) {
		this.messages.add(message);
	}
	
	public void setErrStatus(String status) {
		this.errStatus = status;
	}
	
	public String getErrStatus() {
		return this.errStatus;
	}
	
	public void setError(Error error) {
		this.error = error;
	}
	
	public Error getError() {
		return this.error;
	}
}