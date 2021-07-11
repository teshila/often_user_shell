$(function(){
	
	/*隐藏的小列表*/
	$(".top-con-acenterb").mouseover(function(){
		$(".top-con-acenterb-tel").show();
		
	});
	$(".top-con-acenterb-tel").mouseover(function(){
		$(".top-con-acenterb-tel").show();
		
	});
	$(".top-con-acenterb,.top-con-acenterb-tel").mouseout(function(){
		$(".top-con-acenterb-tel").hide();
	});
	/*所有图片移动改变透明度*/
	$("body").on("mouseenter", ".hover-img", function(){
		$(this).stop().animate({opacity:0.7},300,function(){
			$(this).stop().animate({opacity:1},300);
		})
	});
		/*.posiab鼠标移入改变left*/
//	$(".global-right-a img").hover(function(){
//		$(this).addClass(".posiab");
//		$(this).stop().animate({left:-50},500)
//	},
//	function(){
//		$(this).stop().animate({left:0},500)
//	})
//	$(".global-right-a").on("mouseenter",".global-right-a img",function(){
//		$(this).stop().animate({left:-50},300);
//	})
})

