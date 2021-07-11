$(function() {
	$(".login-nav li").click(function() {
		var index = $(this).index();
		$(this).addClass("login-bor").siblings().removeClass("login-bor");
		$(".message .log-message").eq(index).show().siblings().hide();
	})
	//点击登录
	$(".login").click(function() {
		//1.获取cookie
		var users = $.cookie("users");
		//2.获取登录信息
		var username = $("input").eq(0).val();
		var pwd = $("input").eq(1).val();
		if(users){
			users = JSON.parse(users);
			//判断是否登录成功
			var isLoginSuccess = false;
			for(var i = 0; i < users.length; i++) {
				if(username == users[i]._username && pwd == users[i]._pwd) {
					location.href = "index.html";					
					isLoginSuccess = true;
				}
			}
			if(!isLoginSuccess) {
				if($("input").eq(0).val() == "" || $("input").eq(1).val() == "") {
					return false; //alert("请检查用户名和密码");
				} else {
					alert("您的用户名或密码输入有误, 请重新输入!");
				}
			}
		} else {
			alert("请先注册用户名!");
		}
	})

})