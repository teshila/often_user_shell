$(function() {

	//验证手机号码
	$("input").eq(0).blur(function() {
			var val = $(this).val();
			var reg = /^1[3|4|5|8][0-9]\d{8}$/;
			if(reg.test(val) == false) {
				$(".mast").show();
				$(".erro-message").show();
				$(".yes").click(function() {
					$(".mast").hide();
					$(".erro-message").hide();
				})
			}
		})
		//获取验证码
	$(".btn3").click(function() {
			//验证码
			var code = 0;
			var str = "";
			for(var i = 0; i < 4; i++) {
				var num = parseInt(Math.random() * 10000) % 3;
				if(num == 0) {
					code = parseInt(Math.random() * 10000) % 10 + 48;
				}
				if(num == 1) {
					code = parseInt(Math.random() * 10000) % 26 + 65;
				}
				if(num == 2) {
					code = parseInt(Math.random() * 10000) % 26 + 97;
				}
				str = str.concat(String.fromCharCode(code));
			}
			$(".code").val(str);
		})
		//匹配验证码
	$("input").eq(1).blur(function() {
			var val = $(this).val();
			var code = $(".code").val();
			if(val != code || val == "") {
				$(".mast").show();
				$(".erro-yzm").show();
				$(".yes").click(function() {
					$(".mast").hide();
					$(".erro-yzm").hide();
				})
			}
		})
		//验证密码
	$("input").eq(4).blur(function() {
			var val = $(this).val();
			var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$/;
			if(reg.test(val) == false) {
				$(".mast").show();
				$(".erro-mm").show();
				$(".yes").click(function() {
					$(".mast").hide();
					$(".erro-mm").hide();
				})
			}
		})
		//确认密码
	$("input").eq(5).blur(function() {
			var pwdVal = $("input").eq(4).val();
			var val = $(this).val();
			if(val != pwdVal || val == "") {
				$(".mast").show();
				$(".erro-ss").show();
				$(".yes").click(function() {
					$(".mast").hide();
					$(".erro-ss").hide();
				})
			}
		})
		//点击注册
	$(".btn").click(function() {
		if($("input").eq(0).val() == ""|| $("input").eq(0).val().length != 11) {			
			return false;
		} else if($("input").eq(4).val() != $("input").eq(5).val() || $("input").eq(4).val() == ""||$("input").eq(4).val().length<6||$("input").eq(4).val().length>12) {
			return false;
		} else if($("input").eq(1).val()!=$(".code").val()){
			return false;
		}else{
			console.log("sucess");
			var username = $("input").eq(0).val();
			var pwd = $("input").eq(4).val();
			var arr = $.cookie("users") ? JSON.parse($.cookie("users")) : [];
			//4.检测用户名是否被使用
			//遍历arr
			var isExist = false;
			for(var i = 0; i < arr.length; i++) {
				if(username == arr[i]._username) {
					alert("用户名被使用!");
					isExist = true;
				}
			}
			if(!isExist) {
				//3.创建对象,将获取到的注册信息放入其中
				var obj = {
					_username: username,
					_pwd: pwd
				}
				arr.push(obj);
				//4.保存cookie
				$.cookie("users", JSON.stringify(arr), {
					expires: 30,
					path: "/"
				});
				//console.log( $.cookie("users") );
				location.href = "login.html";
			}
		}
	})
})