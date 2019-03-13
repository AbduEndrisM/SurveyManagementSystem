
function surveyTakerLogin() {
	var formData = $('#login_form').serialize();
	$.ajax({
		url : 'login/surveyTaker',
		method : 'POST',
		data : formData,
		success : function(data) {
			if (data == 'Account is found') {
				location.href = "home";
			} else {
				failureMessage(data);
			}
		}
	})
}

function adminLogin() {
	var formData = $('#admin_login_form').serialize();
	$.ajax({
		url : 'login/admin',
		method : 'POST',
		data : formData,
		success : function(data) {
			if (data == 'Account is found') {
				location.href = "survey";
			} else {
				failureMessage(data);
			}
		}
	})
}

(function() {
	$('#login_form').submit('click', function() {
		event.preventDefault();
		surveyTakerLogin();
	});

	$('#admin_login_form').submit('click', function() {
		event.preventDefault();
		adminLogin();
	});

})();