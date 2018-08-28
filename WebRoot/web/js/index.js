$(document).ready(function() {
	// 登录
	$('.submit').on('click', function(e) {
		var username = $('#username').val(),
			password = $('#password').val();
		if (!username) {
			alert('用户名不能为空');
		}
		if (!password) {
			alert('密码不能为空');
		}
		var param = {
			funNo: "20000",
			username: username,
			password: password
		};
		Qajax(param, function(data){
			if (data.error_no === "0"){
				window.location.href = './upload.html';
			} else {
				alert(data.error_info);
			}
		});
	});
	
	// 记住用户名
	$('#remcheck').on('click', function() {
		if ($(this).prop('checked')) {
			window.localStorage.setItem('_username_login_', $('#username').val());
		} else {
			window.localStorage.removeItem('_username_login_');
		}
	});
	
});