$(document).ready(function() {
	
	function checkExtension(){
		var selection = document.getElementsByName('uploadfiles');
		for (var i=0; i<selection.length; i++) {
		    var ext = selection[i].files[0].name.substr(-4);
		    if(ext!== "xlsx")  {
		        alert('not an accepted file extension: ' + ext);
		        return false;
		    }
		} 
		
		return true;
	}
	
	$("form").submit(function(evt) {
		evt.preventDefault();
		
		var formData = new FormData($(this)[0]);
		
		if(checkExtension()) {
			$.ajax({
				url : '/api/uploadfiles',
				type : 'POST',
				data : formData,
				async : false,
				cache : false,
				contentType : false,
				enctype : 'multipart/form-data',
				processData : false,
				success : function(response) {
					$("#uploadedfile").empty();
					if(response.status === "ok"){
						$("#uploadedfile").append("<a href=" + "\'" + "/api/file" + "\'>customers.xlsx</a>");
						$("#uploadedfile").css("display", "block");
						alert("Upload Successfully!");
					}else{
						$("#uploadedfile").css("display", "none");
						alert("Fail!");
					}
				},
				error: function(e){
					alert("Fail! " + e);
				}
			});
		}
		
		return false;
	});
	
})