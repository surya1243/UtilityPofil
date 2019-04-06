$( document ).ready(function() {		
		ajaxGetBranch();
		ajaxGetFiscal();
	});	
	// DO GET Branch
	function ajaxGetBranch(){		
		let dropdown1 = $('#branchName-dropdown');
		dropdown1.empty();
		dropdown1.append('<option selected="true" disabled>Choose Branch...</option>');
		dropdown1.prop('selectedIndex', 0);
		const url = '/getbranch';
		$.getJSON(url, function (data) {
		$.each(data, function (key, entry) {			  
		    dropdown1.append($('<option></option>').attr('value', entry.branchName).text(entry.branchName));
		  })
		  dropdown1.chosen();
		
		});
		$(window)
		.off('resize.chosen')
		.on('resize.chosen', function() {
			$('#branchName-dropdown').each(function() {
				 var $this = $(this);
				 $this.next().css({minWidth: '220px', width: 'auto'});
			})
		}).trigger('resize.chosen'); 
		
	}
	// DO GET Fiscal year
	function ajaxGetFiscal(){
		let dropdown2 = $('.fiscalYear-dropdown');
		dropdown2.empty();
		dropdown2.append('<option selected="true" disabled>Choose Fiscal Year...</option>');
		dropdown2.prop('selectedIndex', 0);
		const url = '/getfiscalyear';
		$.getJSON(url, function (data) {
		$.each(data, function (key, entry) {			  
		    dropdown2.append($('<option></option>').attr('value', entry.fiscalYear).text(entry.fiscalYear));
		  })
		  dropdown2.chosen();
		});
		$(window)
		.off('resize.chosen')
		.on('resize.chosen', function() {
			$('.fiscalYear-dropdown').each(function() {
				 var $this = $(this);
				 $this.next().css({minWidth: '220px', width: 'auto'});
			})
		}).trigger('resize.chosen'); 
	}
