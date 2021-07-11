$(function() {
	//是否发生滚动
	var ismoving = false;
	$(".fixed-r-ul li").click(function() {
			//		$(this).find("a").addClass("Active").parent().siblings().find("a").removeClass("Active");
			$(this).find("span").addClass("active").parent().siblings().find("span").removeClass("active");
			//滚动条(移动页面)
			var index = $(this).index();
			ismoving = true;
			var top = ($(".louti").eq(index).offset().top) - 150;
			$("html,body").stop().animate({
				"scrollTop": top
			}, 1000, function(){
				ismoving = false;
			})
		})
		/*吸顶fixed*/
	$(window).scroll(function() {
		var banner_bottom_top = $(".banner-bottom").offset().top;
		var winScroll = $(window).scrollTop();
		if(winScroll >= banner_bottom_top) {
			$("#top-fixed").fadeIn(1000);
			$("#fixed-r").show(500);
		} else {
			$("#top-fixed").fadeOut(1000);
			$("#fixed-r").hide(500);
		}
		//滚动
		if(ismoving == false) {
			var winScroll = $(window).scrollTop();
			var index = 0;
			$(".louti").each(function() {
					var divTop = parseInt($(this).offset().top) - 150;
					if(winScroll >= divTop) {
						index = $(this).index(".louti");
					}
				})
				//		$(".fixed-r-ul li").eq(index).find("a").addClass("Active").parent().siblings().find("a").removeClass("Active");
			$(".fixed-r-ul li").eq(index).find("span").addClass("active").parent().siblings().find("span").removeClass("active");
		}
	});
	//回到顶部
	$(".top-return").click(function() {
		$("body").animate({scrollTop: 0}, 500)
		return false;
	})

	/*轮播图*/
	$.get("json/banner-img.json", function(resObj) {
		var arr = resObj.data;
		for(var i = 0; i < arr.length; i++) {
			var obj = arr[i];
			//创建节点
			$("<li><img src=" + obj.img_main + "></li>").appendTo(".banner-img-ul1");
			$("<li>" + (i + 1) + "</li>").appendTo(".banner-img-ul2");
		}
		$(".banner-img-ul2 li").first().addClass("banner-img-ul2-li1");
		banner();
	})

	function banner() {
		var list1 = $(".banner-img-ul1");
		var list2 = $(".banner-img-ul2");
		var li1 = $(".banner-img-ul1 li");
		var li2 = $(".banner-img-ul2 li");
		//复制第一张图到最后
		li1.first().clone(true).appendTo(list1);
		var size = $(".banner-img-ul1 li").size();
		list1.width(size * 1030); /*设置宽度*/
		var i = 0;
		var timer = setInterval(function() {
			i++;
			move();
		}, 10000);

		function move() {
			//限制边界
			if(i < 0) {
				list1.css("left", -(size - 1) * 1030);
				i = size - 2;
			}
			if(i >= size) {
				list1.css("left", 0);
				i = 1;
			}
			list1.stop().animate({
				left: -i * 1030
			}, 2000);
			//改变选中状态
			li2.eq(i).addClass("banner-img-ul2-li1").siblings().removeClass("banner-img-ul2-li1");
			if(i == size - 1) {
				li1.eq(0).addClass("banner-img-ul2-li1").siblings().removeClass("banner-img-ul2-li1");
			};
		}
		li2.mouseenter(function() {
			i = $(this).index();
			move();
		})
		$(".banner-img-btnleft").click(function() {
			i--;
			move();
		})
		$(".banner-img-btnright").click(function() {
			i++;
			move();
		})
		$(".banner-img-hide").hover(function() {
				clearInterval(timer);
			},
			function() {
				timer = setInterval(function() {
					i++;
					move();
				}, 10000)
			})
	}
	/*banner下面的图片*/
	$.get("json/banner-img.json", function(resObj) {
		var arr = resObj.bottom;
		for(var i = 0; i < arr.length; i++) {
			var obj = arr[i];
			$("<img src=" + obj.img_bottom + ">").addClass("banner-bottom-a-img hover-img").appendTo(".banner-bottom-a1");

		}
	});
	/*全球海购  */
	$.get("json/globalImg.json", function(resObj) {
		var arrBig = resObj.data_big;
		for(var i = 0; i < arrBig.length; i++) {
			$(".global-a").eq(i).append("<img src=" + arrBig[i].global_big + ">");
			$(".global-a img").addClass("hover-img");
		};
		var arrRight = resObj.data_right;
		for(var i = 0; i < arrRight.length; i++) {
			$(".global-right-a").eq(i).append("<img src=" + arrRight[i].global_right + "><span>" + arrRight[i].count + "</span>");
		};
		var arrBottom = resObj.data_bottom;
		for(var i = 0; i < arrBottom.length; i++) {
			$(".global-bottom-ul-img").eq(i).append("<img src=" + arrBottom[i].global_bottom + ">");
			$(".global-bottom-ul-img img").addClass("hover-img");
			$(".global-bottom-ul-p").eq(i).append(arrBottom[i].content);
			$(".global-bottom-ul-span").eq(i).append(arrBottom[i].price);
		}
	});
	/* sale*/
	$.get("json/saleImg.json", function(resObj) {
		var arrCenter = resObj.sale_center;
		for(var i = 0; i < arrCenter.length; i++) {
			$(".sale-a").eq(i).append("<img src=" + arrCenter[i].center + ">");
			$(".sale-a img").addClass("hover-img");
		}
		var arrRight = resObj.right;
		for(var i = 0; i < arrRight.length; i++) {
			$(".sale-right-img").eq(i).append("<img src=" + arrRight[i].right + ">");
			$(".sale-right-img img").addClass("hover-img");
			$(".sale-right-img").eq(i).append("<a href='#' class='sale-center-conent'>" + arrRight[i].name + "</a>");
			$(".sale-right-img").eq(i).append("<p class='sale-center-price'>¥<span>" + arrRight[i].price + "</span></p>");
		}
		var arrBottom = resObj.bottom;
		for(var i = 0; i < arrBottom.length; i++) {
			$("<li><img src=" + arrBottom[i].bottom + "><a>" + arrBottom[i].name + "</a></li>").appendTo(".sale-ul");
		}

		var arrBig = resObj.auc_img;
		for(var i = 0; i < arrBig.length; i++) {
			$(".auc-img").eq(0).append("<img src=" + arrBig[i].img + ">");
			$(".auc-img img").addClass("hover-img");

		};
		var arrA = resObj.auc_a;
		for(var i = 0; i < arrA.length; i++) {
			$(".auc-right-top-con").eq(0).append("<li><a href='#'>" + arrA[i].a + "</a></li>")
		};
	});
	
	
	
	
})