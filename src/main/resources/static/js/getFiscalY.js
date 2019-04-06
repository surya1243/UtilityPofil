$( document ).ready(function() {		
		ajaxGetter();
	});
	
	// DO GET
	function ajaxGetter(){
		let dropdown = $('#fiscalYear-dropdown');
		dropdown.empty();

		dropdown.append('<option selected="true" disabled>Choose Fiscal Year...</option>');
		dropdown.prop('selectedIndex', 0);

		const url = '/getfiscalyear';

		// Populate dropdown with list of provinces
		$.getJSON(url, function (data) {
			console.log(data);
		  $.each(data, function (key, entry) {			  
		    dropdown.append($('<option></option>').attr('value', entry.fiscalYear).text(entry.fiscalYear));
		  })
		});
	}
