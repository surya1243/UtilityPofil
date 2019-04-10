$(document).ready(function () {			
		 $('#idElectricity').click(function(){
			$.post('/utilitySubmitTest',   // url
			   {
				branchName: $("#branchName").val(),
				fiscalYear: $("#fiscalYear").val(),
				month: $("#month").val()
				}, // data to be submit
				
			   function(result, status, jqXHR) {// success callback
					console.log(result);
					if(result.status){
						$('p').html("<p style='background-color:#B74635; color:white; padding:20px 20px 20px 20px'>" + 
								"Please insert new Utility Expense!!! <br>" + "Branch: " + result.data.branchName + "<br>Fiscal Year: " +
								result.data.fiscalYear + "<br>Month: " + result.data.month + "<br>" + result.data.expense[0].category
								+ ": Rs. " + result.data.expense[0].amount
								+ "<br>" + result.data.expense[1].category + ": Rs. " + result.data.expense[1].amount
								+ "<br>" + result.data.expense[2].category + ": Rs. " + result.data.expense[2].amount
								+ "<br>" + result.data.expense[3].category + ": Rs. " + result.data.expense[3].amount
								+ "<br>" + result.data.expense[4].category + ": Rs. " + result.data.expense[4].amount
								+ "<br>" + result.data.expense[5].category + ": Rs. " + result.data.expense[5].amount
								+"</p>");		
							$('#submit').hide();
							return false;
						
					/*alert("Data: " + data + "\nStatus: " + status);*/
					}else {
						$('p').html("<strong>Make sure to enter all the required fields.</strong>");
						$('#submit').show();
						return true;
					}
					
				});
			
			});
    });