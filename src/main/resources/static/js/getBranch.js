$( document ).ready(function() {		
		ajaxGet();
	});
	
	// DO GET
	function ajaxGet(){
		let dropdown = $('#branchName-dropdown');
		dropdown.empty();

		dropdown.append('<option selected="true" disabled>Choose Branch...</option>');
		dropdown.prop('selectedIndex', 0);

		const url = '/getbranch';

		// Populate dropdown with list of provinces
		$.getJSON(url, function (data) {
			console.log(data);
		  $.each(data, function (key, entry) {			  
		    dropdown.append($('<option></option>').attr('value', entry.branchName).text(entry.branchName));
		  })
		});
	}
