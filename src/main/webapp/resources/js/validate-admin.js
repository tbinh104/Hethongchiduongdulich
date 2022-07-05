/**
 * 
 */
$(function() {
	$("#signup_form").validate({
		onfocusout: false,
		onkeyup: false,
		onclick: false,
		rules: {
			admin: {
				required: true,
				minlength: 4
			},
			password: {
				required: true,
				minlength: 8
			},
			retypepassword: {
				required: true,
				minlength: 8
			},
		}
	});
	$("#login_form").validate({
		rules: {
			admin: {
				required: true,
				minlength: 4
			},
			password: {
				required: true,
				minlength: 8
			},
			retypepassword: {
				required: true,
				minlength: 8,
				equalTo: "#password"
			},
		}
	});
})
