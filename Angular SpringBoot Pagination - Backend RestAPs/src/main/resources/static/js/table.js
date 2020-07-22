$(document).ready(function(){	
    function fetchCustomers(page, size, salary, agesorting, desc){
    	page = (typeof page !== 'undefined') ?  page : 0;
    	size = (typeof size !== 'undefined') ?  size : 5;
    	salary = (typeof salary !== 'undefined') ?  salary : -1;
    	agesorting = (typeof agesorting !== 'undefined') ?  agesorting: false;
    	desc = (typeof desc !== 'undefined') ?  desc: false;
    	
        $.ajax({
            type : "GET",
            url : "/api/customers/custom/pageable",
            data: { 
                page: page, 
                size: size,
                salary: salary,
                agesorting: agesorting,
                desc: desc
            },
            success: function(response){
              $('#customerTable tbody').empty();
              // add table rows
              $.each(response.customers, (i, customer) => {  
                let tr_id = 'tr_' + customer.id;
                let customerRow = '<tr>' +
          	  						  '<td>' + customer.id + '</td>' +
			                		  '<td>' + customer.firstname + '</td>' +
			                		  '<td>' + customer.lastname + '</td>' +
			                		  '<td>' + customer.age + '</td>' +
			                          '<td>' + '$' + customer.salary + '</td>' +
			                          '<td>' + customer.address + '</td>' +
			                          '<td>' + '<a href="https://loizenai.com">' + customer.copyrightBy + '</a>' + '</td>' +
			                       '</tr>';
                $('#customerTable tbody').append(customerRow);
              });              
              
              if ($('ul.pagination li').length - 2 != response.totalPages){
              	  // build pagination list at the first time loading
            	  $('ul.pagination').empty();
                  buildPagination(response.totalPages);
              }
            },
            error : function(e) {
              alert("ERROR: ", e);
              console.log("ERROR: ", e);
            }
        });    	
    }

    function isNumeric(value) {
        return /^-{0,1}\d+$/.test(value);
    }
    
    $("select").change(function() {
    	let salary = -1;
    	
    	if(isNumeric(this.value)){
    		salary = this.value;
    	}
    	
    	// re-fetch customer list again 
        fetchCustomers(0, 5, salary);
    });
    
    function getListSalaries(){
    	$.ajax({
            type : "GET",
            url : "/api/customers/salaries",
            success: function(response){
              $("#selected_form").empty();
              $('#selected_form').append("<option>All</option>");
              $.each(response.sort().reverse(), (i, salary) => {
            	// <option>All</option>
                let optionElement = "<option>" + salary + "</option>";
                $('#selected_form').append(optionElement);
              });              
            },
            error : function(e) {
              alert("ERROR: ", e);
              console.log("ERROR: ", e);
            }
    	});
    }    
    
    $('#age_sorting').on('change', function() {
        if(this.checked){
        	$("#desc_sorting").removeAttr("disabled");
        }else {
        	$("#desc_sorting").attr("disabled", true);
        	$("#desc_sorting").prop("checked", false);
        }
    }); 
    
    $(document).on("click", "#sortingbtn", function() {
    	let agesorting = false;
    	let desc = false;
    	let selectedSalary = getSeletedSalary();
    	
    	if($("#age_sorting"). prop("checked") == true){
    		agesorting = true;
    	}
    	
    	if($("#desc_sorting"). prop("checked") == true){
    		desc = true;
    	}
    	
    	fetchCustomers(0, 5, selectedSalary, agesorting, desc); // get next page value
    });
    
    function buildPagination(totalPages){
        // Build paging navigation
        let pageIndex = '<li class="page-item"><a class="page-link">Previous</a></li>';
        $("ul.pagination").append(pageIndex);
        
        // create pagination
        for(let i=1; i <= totalPages; i++){
      	  // adding .active class on the first pageIndex for the loading time
      	  if(i==1){
          	  pageIndex = "<li class='page-item active'><a class='page-link'>"
	  				+ i + "</a></li>"            		  
      	  } else {
          	  pageIndex = "<li class='page-item'><a class='page-link'>"
		  				+ i + "</a></li>"
      	  }
      	  $("ul.pagination").append(pageIndex);
        }
        
        pageIndex = '<li class="page-item"><a class="page-link">Next</a></li>';
        $("ul.pagination").append(pageIndex);
    }
    
    function getSeletedSalary(){
    	if(!isNumeric($("select").val())){
    		return -1;
    	}else return parseInt($("select").val());
    }
    
    (function(){
    	// get first-page
    	fetchCustomers(0);
    	getListSalaries();
    })();
        
    $(document).on("click", "ul.pagination li a", function() {
    	let val = $(this).text();
  	  	if(val.toUpperCase()==="NEXT"){
  	  		let activeValue = parseInt($("ul.pagination li.active").text());
  	  		let totalPages = $("ul.pagination li").length - 2; // -2 beacause 1 for Previous and 1 for Next 
  	  		if(activeValue < totalPages){
  	  			let selectedSalary = getSeletedSalary();
  	  			let currentActive = $("li.active");
  	  			
  	  			fetchCustomers(activeValue, 5, selectedSalary); // get next page value
  	  			
  	  			// remove .active class for the old li tag
  	  			$("li.active").removeClass("active");
  	  			
  	  			// add .active to next-pagination li
  	  			currentActive.next().addClass("active");
  	  		}
  	  	}else if(val.toUpperCase()==="PREVIOUS"){
  	  		let activeValue = parseInt($("ul.pagination li.active").text());
  	  		if(activeValue > 1){
  	  			// get the previous page
  	  			let selectSalary = getSeletedSalary();
  	  			fetchCustomers(activeValue-2, 5, selectSalary);
  	  			let currentActive = $("li.active");
  	  			currentActive.removeClass("active");
  	  			// add .active to previous-pagination li
  	  			currentActive.prev().addClass("active");
  	  		}
  	  	}else {
  	  		fetchCustomers(parseInt(val) - 1, 5,  );
  	  		// add focus to the li tag
  	  		$("li.active").removeClass("active");
  	  		$(this).parent().addClass("active");
  	  	}
    });
});