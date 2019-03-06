function showAndHide(toHide, toShow) {
	$(toHide).addClass('hidden');
	$(toShow).fadeIn(1000);
	$(toShow).removeClass('hidden');
}




function submittingEvent() {
	var submitedData = {}
	submitedData = $("#new_event").serialize();

	var formData = {};

	var $form = $("#new_event"), formData = new FormData(), params = $form
			.serializeArray(), files = $form.find('[name="eventLogo"]')[0].files;

	$.each(files, function(i, file) {
		formData.append('attachement-' + i, file);
	});

	$.each(params, function(i, val) {
		formData.append(val.name, val.value);
	});

	$.ajax({
		type : "POST",
		url : 'app/formSubmission/newEvent',
		cache : false,
		contentType : false,
		processData : false,
		data : formData,
		success : function(data) {
			if (data == 'New Record is Successfully saved') {
				new PNotify({
					title : 'Left icon',
					text : data,
					addclass : 'alert alert-styled-left',
					type : 'info'
				});
				setTimeout(function() {// wait for 2 secs(2)
					location.href="dashboard"; // then return to tasks
					// page.(3)
				}, 1000);
			} else {
				new PNotify({
					title : 'Error',
					text : data,
					addclass : 'alert alert-styled-left',
					type : 'error'
				});
			}

		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	})
}

function editEvent() {
	var submitedData = {}
	submitedData = $("#edit_event").serialize();

	var formData = {};

	var $form = $("#edit_event"), formData = new FormData(), params = $form
			.serializeArray(), files = $form.find('[name="eventLogo"]')[0].files;

	$.each(files, function(i, file) {
		formData.append('attachement-' + i, file);
	});

	$.each(params, function(i, val) {
		formData.append(val.name, val.value);
	});

	$.ajax({
		type : "POST",
		url : 'app/formSubmission/editEvent',
		cache : false,
		contentType : false,
		processData : false,
		data : formData,
		success : function(data) {
			if (data == 'Record is Successfully updated') {
				new PNotify({
					title : 'Left icon',
					text : data,
					addclass : 'alert alert-styled-left',
					type : 'info'
				});
				setTimeout(function() {// wait for 2 secs(2)
					location.href="dashboard"; // then return to tasks
					// page.(3)
				}, 1000);
			} else {
				new PNotify({
					title : 'Error',
					text : data,
					addclass : 'alert alert-styled-left',
					type : 'error'
				});
			}

		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	})
}

(function() {
	$('#new_event').submit('click', function(event) {
		event.preventDefault();
		submittingEvent();
	});
	$('#edit_event').submit('click', function(event) {
		event.preventDefault();
		editEvent();
	});
})();

(function(){
	$("#new_survey").submit('click',function(event){
		event.preventDefault();
	    createSurvey();
	});
})();


function createSurvey(){
	var formData = {};
	formData = $('#new_survey').serialize();
	$.ajax({
		url : 'formSubmission/survey',
		method : 'POST',
		data : formData,
		success : function(data){
			if(data == 'Record is successfully saved!'){
				new PNotify({
					title : 'Success',
					text : data,
					addclass : 'alert alert-styled-left',
					type : 'info'
				});
				setTimeout(function() {
					location.reload();
				}, 1000)
			}else{
				new PNotify({
					title : 'Failure',
					text : data,
					addclass : 'alert alert-styled-left',
					type : 'error'
				});
			}
		}
	})
}



/* function to save institution by admin
$(document).on('submit','#i_form',function (){
	event.preventDefault();
	if($("#i_form").valid()){
		
		var submitedData = $('#i_form').serialize();
		$.ajax({
			url : 'app/formSubmission/saveinstitutionbyadmin',
			method : 'POST',
			data : submitedData,
			success : function(data) {
				if (data == 'New Record is Successfully saved') {
					new PNotify({
						title : 'Success',
						text : data,
						addclass : 'alert alert-styled-left',
						type : 'info'
					});
					setTimeout(function() {
						location.reload();
					}, 1000)
				} else {
					new PNotify({
						title : 'Failure',
						text : data,
						addclass : 'alert alert-styled-left',
						type : 'error'
					});
				}
			}
		})
	}
});*/


// function to  register invited applicant




