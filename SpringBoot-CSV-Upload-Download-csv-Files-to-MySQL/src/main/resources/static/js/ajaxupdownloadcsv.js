/**
 * Copyright by https://loizenai.com
 * Author: loizenai.com 
 */

$(document).ready(function() {
	
	/**
	 * Upload single file to SpringBoot 
	 * at RestAPI: /api/upload/csv/single
	 */
	$("#uploadSingleFileForm").submit(function(evt) {
		evt.preventDefault();
		
		var formData = new FormData($(this)[0]);
		
		$.ajax({
			url : '/api/upload/csv/single',
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
	 * Upload single file to SpringBoot 
	 * at RestAPI: /api/upload/csv/multiple
	 */
	$("#uploadMultipleFilesForm").submit(function(evt) {
		evt.preventDefault();
		
		var formData = new FormData($(this)[0]);
		
		$.ajax({
			url : '/api/upload/csv/multiple',
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
						
						displayInfo += "<li>" + response.messages[i].filename 
											+ "&nbsp; : &nbsp;" + response.messages[i].message
											+ "</li>";
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
})