$(function() {
	$(".left-nav").hover(function() {
			$(".sort").show();
		}, function() {
			$(".sort").hide();
		})
		//轮播图
		//1,先获取接口数据
		//json文件不要写错, 不能在json中写注释
	$.get("json/sale.json", function(responseObj) {
			//遍历从后台获取的数据
			var arr = responseObj.data;
			for(var i = 0; i < arr.length; i++) {
				var obj = arr[i];
				//2, 创建节点
				//添加图片
				$("<li><a href='#'><img src=" + obj.img + " /></a></li>").appendTo(".sliderPanels");
				$("<li></li>").appendTo(".sliderTrigger"); //添加按钮
			}
			$(".sliderTrigger li").first().addClass("active1");
			//轮播
			lunbo();
		})
		//轮播
	function lunbo() {
		//把第1张图复制到最后
		$(".sliderPanels li").first().clone(true).appendTo(".sliderPanels");
		var size = $(".sliderPanels li").size(); //图片的总数量
		//console.log(size);
		$(".sliderPanels").width(size * 1190); //设置宽度
		var i = 0; //即将显示的图片下标
		var timer = setInterval(function() {
			i++;
			move();
		}, 2000);
		function move() {
			//限制不超出左边界
			if(i < 0) {
				$(".sliderPanels").css("left", -(size - 1) * 1190);
				i = size - 2;
			}
			//限制不超出右边界
			if(i >= size) {
				$(".sliderPanels").css("left", 0);
				i = 1;
			}
			//移动
			$(".sliderPanels").stop().animate({
				left: -i * 1190
			}, 500);
			//改变按钮的选中状态
			$(".sliderTrigger li").eq(i).addClass("active1").siblings().removeClass("active1");
			if(i == size - 1) {
				$(".sliderTrigger li").eq(0).addClass("active1").siblings().removeClass("active1");
			}
		}
		//移入按钮
		$(".sliderTrigger li").mouseenter(function() {
				i = $(this).index();
				move();
			})
			//移入/移出box
		$(".topBanner").hover(function() { //移入box
				clearInterval(timer);
			},
			function() { //移出box
				timer = setInterval(function() {
					i++;
					move();
				}, 2000);
			})
	}
	$(".category dl dd").hover(function() {
			var index = $(this).index();
			
			$(this).find(".cat1").css("background", "url(img/cat_1.png) no-repeat 0 -108px");
			$(this).find(".cat2").css("background", "url(img/cat_2.png) no-repeat 0 -108px");
			$(this).find(".cat3").css("background", "url(img/cat_3.png) no-repeat 0 -108px");
			$(this).find(".cat4").css("background", "url(img/cat_4.png) no-repeat 0 -108px");
			$(this).find(".cat5").css("background", "url(img/cat_5.png) no-repeat 0 -108px");
			$(this).find(".cat6").css("background", "url(img/cat_6.png) no-repeat 0 -108px");
			$(this).find(".cat7").css("background", "url(img/cat_7.png) no-repeat 0 -108px");
			$(this).find(".cat8").css("background", "url(img/cat_8.png) no-repeat 0 -108px");
			$(this).find(".cat9").css("background", "url(img/cat_9.png) no-repeat 0 -108px");
		}, function() {
			
			$(this).find(".cat1").css("background", "url(img/cat_1.png) no-repeat 0 0");
			$(this).find(".cat2").css("background", "url(img/cat_2.png) no-repeat 0 0");
			$(this).find(".cat3").css("background", "url(img/cat_3.png) no-repeat 0 0");
			$(this).find(".cat4").css("background", "url(img/cat_4.png) no-repeat 0 0");
			$(this).find(".cat5").css("background", "url(img/cat_5.png) no-repeat 0 0");
			$(this).find(".cat6").css("background", "url(img/cat_6.png) no-repeat 0 0");
			$(this).find(".cat7").css("background", "url(img/cat_7.png) no-repeat 0 0");
			$(this).find(".cat8").css("background", "url(img/cat_8.png) no-repeat 0 0");
			$(this).find(".cat9").css("background", "url(img/cat_9.png) no-repeat 0 0");
		})
		//特卖主推		

	$.get("json/sale.json", function(responseObj) {
			//遍历从json获取的数据		
			for(var i = 0; i < responseObj.date1.length; i++) {
				var obj = responseObj.date1[i];
				//2, 创建节点									
				var li = $("<li></li>").appendTo(".mainRecomList");
				$("<div class='imgBox'><img src=" + obj.img + " alt='' /></div>").appendTo(li);
				$("<div class='eventInfo1'><div class='fl'><a href='#'>" + obj.name + "</a><p class='count'>距结束还有<span class='day'></span>天<span class='hours'></span>时<span class='minute'></span>分</p></div><div class='discountInfo fr'><strong>" + obj.price + "</strong><span>折</span></div></div>").appendTo(li);
				//添加倒计时
				var obj1 = $(".count")
				countDown(obj1);
				$(".mainRecomList li").hover(function() {
					$(this).css("border-color", "#d00");
				}, function() {
					$(this).css("border-color", "#fff");
				})
			}
		})
		//倒计时
	function countDown(obj) {
		var timer = 0;
		timer = setInterval(function() {
			var count1 = obj.find(".day");
			var count2 = obj.find(".hours");
			var count3 = obj.find(".minute");
			var iNow = new Date();
			//console.log(iNow);
			var iNew = new Date("2017/2/26 00:00:00");
			var t = Math.floor((iNew - iNow) / 1000);
			if(t >= 0) {
				var str = Math.floor(t / 86400);
				var str1 = Math.floor(t % 86400 / 3600);
				var str2 = Math.floor(t % 86400 % 3600 / 60);
				count1.html(str);
				count2.html(str1);
				count3.html(str2);
			} else {
				clearInterval(timer);
			}
		}, 1000);
	}

	$.get("json/sale.json", function(responseObj) {
		//遍历从json获取的数据		
		for(var i = 0; i < responseObj.data2.length; i++) {
			var obj = responseObj.data2[i];
			//2, 创建节点												
			var li = $("<li></li>").appendTo(".mainCon1");
			$("<a href='#'><img src=" + obj.img + " alt='' /></a>").appendTo(li);
			$("<div class='mainRecomGoodsInfoBox'><div class='timerBox'><span class='fr'>已售" + obj.sign + "件</span><span class='fl'>已结束</span></div><p class='goodsTitle'>" + obj.name + "</p><div class='goodsPriceBox'><strong class='salesPrice fl'>￥" + obj.price + "</strong><span class='marketPrice fr'>￥" + obj.num + "</span></div></div>").appendTo(li);

		}
		for(var i = 0; i < responseObj.date3.length; i++) {
			var obj = responseObj.date3[i];
			//2, 创建节点												
			var li = $("<li></li>").appendTo(".mainCon2");
			$("<a href='#'><img src=" + obj.img + " alt='' /></a>").appendTo(li);
			$("<div class='mainRecomGoodsInfoBox'><div class='timerBox'><span class='fr'>已售" + obj.sign + "件</span><span class='fl'>已结束</span></div><p class='goodsTitle'>" + obj.name + "</p><div class='goodsPriceBox'><strong class='salesPrice fl'>￥" + obj.price + "</strong><span class='marketPrice fr'>￥" + obj.num + "</span></div></div>").appendTo(li);
		}
		for(var i = 0; i < responseObj.date4.length; i++) {
			var obj = responseObj.date4[i];
			//2, 创建节点												
			var li = $("<li></li>").appendTo(".mainCon3");
			$("<a href='#'><img src=" + obj.img + " alt='' /></a>").appendTo(li);
			$("<div class='mainRecomGoodsInfoBox'><div class='timerBox'><span class='fr'>已售" + obj.sign + "件</span><span class='fl'>已结束</span></div><p class='goodsTitle'>" + obj.name + "</p><div class='goodsPriceBox'><strong class='salesPrice fl'>￥" + obj.price + "</strong><span class='marketPrice fr'>￥" + obj.num + "</span></div></div>").appendTo(li);
		}
	})
	$(".mainCon1").clone(true).appendTo(".eva-switchable-panels");
	var size = $(".mainRecomGoodsList").size(); //图片的总数量    
	$(".eva-switchable-panels").width(size * 1190); //设置宽度
	var i = 0; //即将显示的图片下标
	function move() {
		//限制不超出左边界
		if(i < 0) {
			$(".eva-switchable-panels").css("left", -(size - 1) * 1190);
			i = size - 2;
		}
		//限制不超出右边界
		if(i >= size) {
			$(".eva-switchable-panels").css("left", 0);
			i = 1;
		}
		//移动
		$(".eva-switchable-panels").stop().animate({
			left: -i * 1190
		}, 500);
	}
	$(".eva-switchable-prev").click(function(e) {
		e.preventDefault();
		i--;
		move();
	})
	$(".eva-switchable-next").click(function(e) {
			e.preventDefault();
			i++;
			move();
		})
		//最新推荐
	$.get("json/sale.json", function(responseObj) {
		//遍历从json获取的数据		
		for(var i = 0; i < responseObj.date5.length; i++) {
			var obj = responseObj.date5[i];
			//2, 创建节点												
			var li = $("<li></li>").appendTo(".newRecomBox");
			$("<div class='newRecomBrand fl'><div class='newRecomBrandBox'><img src=" + obj.img1 + " alt='' /><p class='newRecomBrandContent'><a href='#'>" + obj.name + "</a></p><div class='discountInfo'><strong>" + obj.sign + "</strong><span>折</span></div></div><p class='count1'>距结束还有<span class='day'>3</span>天<span class='hours'>13</span>时<span class='minute'>51</span>分</p></div>").appendTo(li);
			$("<div class='newRecomEvent fl'><img src=" + obj.img2 + " alt='' /></div>").appendTo(li);
			var obj1 = $(".count1");
			countDown(obj1);
			$(".newRecomBox li").hover(function() {
				$(this).css("border-color", "#d00");
			}, function() {
				$(this).css("border-color", "#fff")
			})
			$(".newRecomEvent img").hover(function() {
				$(this).stop().animate({
					"width": "810px",
					"height": "308px"
				}, 500);
			}, function() {
				$(this).stop().animate({
					"width": "792px",
					"height": "294px"
				}, 500);
			})

		}
	})
	$(".serveTrigger li").hover(function() {
		var index = $(this).index();
		$(this).addClass("changes1").siblings().removeClass("changes1");
		$(".servePanels li").eq(index).show().siblings().hide();
	});
	//抢购预告
	$.get("json/sale.json", function(responseObj) {
		var arr = responseObj.date6;
		for(var i = 0; i < arr.length; i++) {
			var obj = arr[i];
			//创建节点			
			$("<li><img src=" + obj.img1 + " alt='' /><div class='eventInfo'><h4>" + obj.name + "</h4><span class='fr'><strong>" + obj.sign + "</strong>折</span></div></li>").appendTo(".foretellList1 ul");
		}		
	})
	$.get("json/sale.json", function(responseObj) {
		var arr = responseObj.date7;
		for(var i = 0; i < arr.length; i++) {
			var obj = arr[i];
			//创建节点			
			$("<li><img src=" + obj.img1 + " alt='' /><div class='eventInfo'><h4>" + obj.name + "</h4><span class='fr'><strong>" + obj.sign + "</strong>折</span></div></li>").appendTo(".foretellList2 ul" );
		}
	})
	
	$(".foretellList").on("mouseenter","li",function(){
		$(this).css("border-color","#d00");
	})
	$(".foretellList").on("mouseleave","li",function(){
		$(this).css("border-color","#fff");
	})
	
	$(".foretellSwitch li").mouseenter(function() {
			$(this).addClass("active").siblings().removeClass("active");
			var index = $(this).index();
			$(".foretellList").eq(index).show().siblings().hide();
		})
	
		//吸顶效果
	var top = $(".asideBox").offset().top;
	console.log(top);
	$(window).scroll(function() {
			var disTop = $(window).scrollTop();
			if(disTop >= top) {
				$(".asideBox").css({
					"position": "fixed",
					"top": 0,
					"left":"994.5px"
				})			
			}else{
				$(".asideBox").css("position","static");
			}
		})		  
		//楼梯
	$(".sideNavBox li").hover(function() {
		$(this).addClass("changes");
	}, function() {
		$(this).removeClass("changes");
	})
	$(".sideNavBox li").not(".sideNavBox li.last").click(function() {
			var index = $(this).index();
			var top = $(".caption").eq(index).offset().top;
			//移动					  
			$("html,body").stop().animate({
				"scrollTop": top
			}, 500)
		})
		//滚动事件
	var top1 = $(".sideNavBox").offset().top;
	$(window).scroll(function() {
		var disTop = $(window).scrollTop();
		if(disTop >= top1) {
			$(".sideNavBox").css({
				"position": "fixed",
				"top": 0
			})
		} else {
			$(".sideNavBox").css("position", "static");
		}
	})
	$(".sideNavBox li.last").click(function() {
		$("html,body").animate({
			"scrollTop": 0
		}, 500)
	})
})