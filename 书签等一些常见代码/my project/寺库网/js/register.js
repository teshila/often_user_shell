$(function(){
	/*账号*/
		var isTrue=true;
	$(".input_text").blur(function(){
		$(".form-div1").removeClass("form-diva");
		var val=$(".input_text").val();
		if(/^1[1|3|5|7|8]\d{9}$/.test(val) && val!=""){
			console.log("succes")
			$(".form-div1").html("账号有效");
			$(".form-div1").addClass("success");isTrue=false;
		}else{
			$(".form-div1").html("请输入正确的账号");
			$(".form-div1").addClass("form-diva");
			isTrue=false;
		}
		
	})
	/*密码*/ 
	var isTrue1=true;
	$(".input_psd").eq(0).blur(function(){
		$(".form-div2").removeClass("form-diva");
		$(".form-div2").html("");
		var valpsd=$(".input_psd").eq(0).val();
		if(/^.{8,16}$/.test(valpsd)){
			console.log("succes")
			$(".form-div2").html("输入成功");
			$(".form-div2").addClass("success");isTrue1=false;
		}else{
			$(".form-div2").html("请输入正确的密码");
			$(".form-div2").addClass("form-diva");
			isTrue1=false;
			
		}
	});
	/* 确认密码*/
	var isTrue2=true;
	$(".psdagain").blur(function(){
		if($(".psdagain").val()==$(".input_psd").eq(0).val() && $(".psdagain").val()!=""){
			console.log("success again")
			$(".form-div3").html("确认密码成功");
			$(".form-div3").addClass("success");
			isTrue2=false;
		}else{
			$(".form-div3").html("请输入正确的密码");
			$(".form-div3").addClass("form-diva");
			isTrue2=true;
		}
	})
	/*验证码*/
	var isTrue3=true;
	$.idcode.setCode();/*加载验证码*/
	$(".F4input").blur(function(){
		  var IsBy = $.idcode.validateCode();  //调用返回值，返回值结果为true或者false
			if(IsBy){
				 $(".F4html").html("验证码正确");
				 $(".F4html").addClass("success");
				 isTrue3=false;
			}else{
				$(".F4html").html("输入有误");
				$(".F4html").addClass("form-diva");
				isTrue3=true;
			}
	})
	
	/*判断提交*/
	$(".input_btn").click(function(){
		/*判断写的成功则为反*/
		if (!isTrue &&! isTrue1 &&!isTrue2 &&!isTrue3) {
			var name=$(".input_text").val();
			var psd=$(".input_psd").eq(0).val();
			//获取cookie里面的内容
			var str=$.cookie("user");
			var userObj=str?JSON.parse(str):[];//检查是否没有注册COOK
			console.log(userObj);
			var cookieno=true;
			 //遍历循环判断cookie里面的用户名是否与现在的用户名一样
			$.each(userObj,function(index,obj){
				if(obj.name==name){
					$(".form-div1").html("用户名以被注册");
					$(".form-div1").addClass("form-diva");
					cookieno=false;
				}
			})
			if(cookieno){
				userObj.push({"name":name,"psd":psd});
				console.log(userObj);
				alert("注册成功");
				setTimeout(function(){
					location.href="loading.html";
				},1000)
				$.cookie("user",JSON.stringify(userObj),{"expires":7,path:"/"})
			}
		}else{
			$(".right").html("注册失败")
		}
	})

})