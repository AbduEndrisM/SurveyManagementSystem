

function surveyTakerLogin(){
	var formData = $('#login_form').serialize();
	$.ajax({
		url : 'login/surveyTaker',
		method : 'POST',
		data : formData,
		success : function(data){
			if(data == 'Account is found'){
				location.href="home";
			}else{
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

})();