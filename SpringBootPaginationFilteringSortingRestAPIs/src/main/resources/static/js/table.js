$(document).ready(function(){	
    function getCustomers(page, size){
    	size = (typeof size !== 'undefined') ?  size : 5
        $.ajax({
            type : "GET",
            url : "/api/customers/custom/pageable",
            data: { 
                page: page, 
                size: size
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
              
              if ($('ul.pagination li').length == 0){
              	  // build pagination list at the first time loading
                  buildPagination(response.totalPages);
              }
            },
            error : function(e) {
              alert("ERROR: ", e);
              console.log("ERROR: ", e);
              totalPages = 0;
            }
        });    	
    }
    
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
    
    (function(){
    	// get first-page
    	getCustomers(0);
    })();
        
    $(document).on("click", "ul.pagination li a", function() {
    	let val = $(this).text();
  	  	if(val.toUpperCase()==="NEXT"){
  	  		let activeValue = parseInt($("ul.pagination li.active").text());
  	  		let totalPages = $("ul.pagination li").length - 2; // -2 beacause 1 for Previous and 1 for Next 
  	  		if(activeValue < totalPages){
  	  			getCustomers(activeValue); // get next page value
  	  			// remove .active class for the old li tag
  	  			let currentActive = $("li.active");
  	  			currentActive.removeClass("active");
  	  			// add .active to next-pagination li
  	  			currentActive.next().addClass("active");
  	  		}
  	  	}else if(val.toUpperCase()==="PREVIOUS"){
  	  		let activeValue = parseInt($("ul.pagination li.active").text());
  	  		if(activeValue > 1){
  	  			// get the previous page
  	  			getCustomers(activeValue-2);
  	  			let currentActive = $("li.active");
  	  			currentActive.removeClass("active");
  	  			// add .active to previous-pagination li
  	  			currentActive.prev().addClass("active");
  	  		}
  	  	}else {
  	  		getCustomers(parseInt(val) - 1 );
  	  		// add focus to the li tag
  	  		$("li.active").removeClass("active");
  	  		$(this).parent().addClass("active");
  	  	}
    });
});