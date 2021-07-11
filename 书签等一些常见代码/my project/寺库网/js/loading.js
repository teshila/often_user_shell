$(function(){
//	if($("input:checked")){
//		
//	}
//	
	$("input:button").click(function(e){
		e.preventDefault();
		var username=$("input").eq(0).val();
		var psd=$("input").eq(1).val();
		var users=$.cookie("user");
		//console.log(users);//如果没有获取到$.cookie("users");则返回undefined;
		//如果存在已经注册的用户
		var isSuccess=false;
		//设为赋值false;
		if(users==undefined){
			alert("亲,你还没注册呢,先注册一个吧");
		}
		else{
			userArr=JSON.parse(users);
			
			for (var i=0;i<userArr.length;i++) {
				if(username==userArr[i].name && psd==userArr[i].psd){
					alert("登陆成功")
					setTimeout(function(){
					location.href="index-main.html";
					},1000)
					isSuccess=true;
				}
					
					if($("#check").prop("checked")){
//						console.log($("#check").prop("checked"))
						username=userArr[i].name;
//						console.log(userArr[i].name)
						psd=userArr[i].psd;
//						console.log(userArr[i].psd)
					}
			/*如果登陆成功则改变success*/
		if(!isSuccess){
				console.log("输入有误")
			}
			}
		}
	});
	
	
	
	
	
})