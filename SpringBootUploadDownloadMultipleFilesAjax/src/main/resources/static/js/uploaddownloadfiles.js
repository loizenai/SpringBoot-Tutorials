/**
 * Copyright by https://loizenai.com
 * Author: loizenai.com 
 */

$(document).ready(function() {
	
	/**
	 * Upload single file to SpringBoot 
	 * at RestAPI: /api/upload/file/single
	 */
	$("#uploadSingleFileForm").submit(function(evt) {
		evt.preventDefault();
		
		var formData = new FormData($(this)[0]);
		
		$.ajax({
			url : '/api/upload/file/single',
			type : 'POST',
			data : formData,
			async : false,
			cache : false,
			contentType : false,
			enctype : 'multipart/form-data',
			processData : false,
			success : function(response) {
				$("#response").empty();
				if(response.errStatus !== "error"){
					var displayInfo = response.messages[0].filename + " : " + response.messages[0].message + "<br>"; 
					
					$("#response").append(displayInfo);
					
					var downloadLink = "http://localhost:8080/api/download/file/" + response.messages[0].filename;
					var downloadAt = "&nbsp;&nbsp;&nbsp; -> Download File: " + "<a href=" + "\'" + downloadLink + "\'>" + downloadLink + "</a>";
					
					$("#response").append(downloadAt);
					
					// add some css
					$("#response").css("display", "block");
					$("#response").css("background-color", "#e6e6ff");
					$("#response").css("border", "solid 1px black");
					$("#response").css("border-radius", "3px");
					$("#response").css("margin", "10px");
					$("#response").css("padding", "10px");
				}else{
					$("#response").css("display", "none");
					var error = response.error.errDesc;
					alert(error);
				}
			},
			error: function(e){
				alert("Fail! " + e);
			}
		});
		
		return false;
	});
	

	/**
	 * Upload Multiple Files to SpringBoot RestAPI
	 */
	$("#uploadMultipleFilesForm").submit(function(evt) {
		evt.preventDefault();
		
		var formData = new FormData($(this)[0]);
		
		$.ajax({
			url : '/api/upload/file/multiple',
			type : 'POST',
			data : formData,
			async : false,
			cache : false,
			contentType : false,
			enctype : 'multipart/form-data',
			processData : false,
			success : function(response) {
				
				$("#responses").empty();
				if(response.errStatus !== "error"){
					
					var displayInfo = "<ul>";
					
					for(var i=0; i<response.messages.length; i++){
						
						displayInfo += "<li>" + response.messages[i].filename + "&nbsp; : &nbsp;" + response.messages[i].message;
						
						if (response.messages[i].status === "ok") {
							var downloadLink = "http://localhost:8080/api/download/file/" + response.messages[i].filename;
							var downloadAt = "&nbsp;&nbsp;&nbsp; -> Link: " + "<a href=" + "\'" 
												+ downloadLink + "\'>" + downloadLink + "</a>";
							
							displayInfo += "<br>" + downloadAt;  
						}
						
						displayInfo += "</li>";
					}
					$("#responses").append(displayInfo + "</ul>");
					$("#responses").css("display", "block");
					
					// add some css
					$("#responses").css("background-color", "#e6e6ff");
					$("#responses").css("border", "solid 1px black");
					$("#responses").css("border-radius", "3px");
					$("#responses").css("margin", "10px");
					$("#responses").css("padding", "10px");
				}else{
					$("#responses").css("display", "none");
					var error = response.error.errDesc;
					alert(error);
				}
			},
			error: function(e){
				alert("Fail! " + e);
			}
		});
		
		return false;
	});
	
	/**
	 * Get all uploaded files and download-links
	 */
	$( "#btnGetFiles").click(function() {
		$.get('/api/download/files', function (response, textStatus, jqXHR) {  // success callback
			
			var files = "<ul>";
			
			for(var i=0; i<response.fileInfos.length; i++) {
				files += "<li>" + response.fileInfos[i].filename + "<br>" 
									+ "&nbsp;&nbsp;&nbsp; Link -> <a href=" + "\'" + response.fileInfos[i].url + "\'" + ">"
									+ response.fileInfos[i].url 
									+ "</a></li>"
			}
			
			files += "</ul>";
			
			$("#uploadfiles").append(files);
			
			// add some css
			$("#uploadfiles").css("background-color", "#e6e6ff");
			$("#uploadfiles").css("border", "solid 1px black");
			$("#uploadfiles").css("border-radius", "3px");
			$("#uploadfiles").css("margin", "10px");
			$("#uploadfiles").css("padding", "10px");
		});
	});
})