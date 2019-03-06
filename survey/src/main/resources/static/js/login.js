
function systemLogin() {
	var submitedData = {}
	submitedData = $("#login_form").serialize();
	$
			.ajax({
				type : "POST",
				url : 'process_login',
				data : submitedData,
				success : function(data) {
					var content=$.parseJSON(data);
					if (content.msg.includes("Success")) {
						if(content.user.position=="admin"){
							location.href="new_institution";
						}else{
							location.href="dashboard";
						}
					} else {
						new PNotify({
							title : 'Access Denied',
							text : content.msg,
							addclass : 'alert bg-danger alert-styled-right',
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
	$('#login_form').submit('click', function() {
		event.preventDefault();
		systemLogin();
	});

})();