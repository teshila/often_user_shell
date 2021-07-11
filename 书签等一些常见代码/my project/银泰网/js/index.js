$(function() {
	$(".product-sort li").hover(function() {
			$(this).find("span").css("background", "url(img/yt_ico.png) no-repeat 0 -16px").parent().siblings().find("span").css("background", "#fff");
			$(this).addClass("border-b").siblings().removeClass("border-b");
			var index = $(this).index();
			$(".goods-lie").eq(index).show().siblings().hide();
		})
		//轮播图
		//1,先获取接口数据			
	$.get("json/index-lunbo.json", function(responseObj) {
				console.log("success");					
			//遍历从json获取的数据
			var arr = responseObj.data;
			for(var i = 0; i < arr.length; i++) {
				var obj = arr[i];		
				var div = $("<div class='pannel1' style='background-image:url("+obj.img1+")'></div>").appendTo(".pannels");							
				$("<div class='content-img'><img src=" + obj.img + " alt='' /></div>").appendTo(div); //添加按钮
				$("<li></li>").appendTo(".nav_trriger");												
			}
			
			
			$(".nav_trriger li").first().addClass("active");
			//透明度轮播
			lunbo();
		})
		//轮播
	function lunbo() {
		//初始化,显示第一张大图
		$(".pannels .pannel1").first().css({
				"opacity": 1,
				"filter": "alpha(opacity=100)"
			})
			.siblings().css({
				"opacity": 0,
				"filter": "alpha(opacity=0)"
			})
		var size = $(".pannels .pannel1").size(); //图片的总数量
		//console.log(size);          
		//自动透明度轮播
		var i = 0; //表示即将显示的图片下标
		var timer = setInterval(function() {
			i++;
			move();
		}, 3000);
		function move() {
			//判断不超出右边界
			if(i >= size) {
				i = 0;
			}
			$(".pannels .pannel1").eq(i).stop().animate({
					"opacity": 1
				}, 1000)
				.siblings().stop().animate({
					"opacity": 0
				}, 1000);
			$(".nav_trriger li").eq(i).addClass("active").siblings().removeClass("active");
		}
		//点击按钮切换
		$(".nav_trriger li").mouseenter(function() {
				i = $(this).index();
				move();
			})
			//移入,移出
		$("#wrap-banner").hover(function() {
//			$(".eva-switchable-prev").show();
//			$(".eva-switchable-next").show();
				clearInterval(timer);
			}, function() {
				//clearInterval(timer);
//			$(".eva-switchable-prev").hide();
//			$(".eva-switchable-next").hide();
				timer = setInterval(function(){
					i++;
					move();
				}, 2000);
			})
			//点击上一页
		$(".eva-switchable-prev").click(function() {
				i--;
				move();
			})
			//点击下一页
		$(".eva-switchable-next").click(function() {
			  i++;
			  move();
		})
	}
	//超值特卖
	$.get("json/index.json", function(responseObj) {
			//遍历从json获取的数据
			var arr = responseObj.data;
			for(var i = 0; i < arr.length; i++) {
				var obj = arr[i];
				//2, 创建节点
				var Odiv = $("<div class='border-anmiate'></div>").appendTo(".goods1");
				$("<div class='b1'></div>").appendTo(Odiv);
				$("<div class='b2'></div>").appendTo(Odiv);
				$("<div class='b3'></div>").appendTo(Odiv);
				$("<div class='b4'></div>").appendTo(Odiv);
				$("<div><img src=" + obj.img + "/></div>").appendTo(Odiv);
			}
			$(".border-anmiate").mouseenter(function() {
				$(this).find(".b1").stop().animate({
					width: 220
				}, 500);
				$(this).find(".b2").stop().animate({
					height: 260
				}, 500);
				$(this).find(".b3").stop().animate({
					width: 220
				}, 500);
				$(this).find(".b4").stop().animate({
					height: 260
				}, 500);
			})
			$(".border-anmiate").mouseleave(function() {
				$(this).find(".b1").stop().animate({
					width: 0
				}, 500);
				$(this).find(".b2").stop().animate({
					height: 0
				}, 500);
				$(this).find(".b3").stop().animate({
					width: 0
				}, 500);
				$(this).find(".b4").stop().animate({
					height: 0
				}, 500);
			})
		})
		//手机专享
	$.get("json/index.json", function(responseObj) {
			//遍历从json获取的数据
			var arr = responseObj.data1;
			for(var i = 0; i < arr.length; i++) {
				var obj = arr[i];
				//2, 创建节点			
				var Oul = $("<ul></ul>").appendTo(".goods2");
				var Oli = $("<li class='border-anmiate'></li>").appendTo(Oul);
				$("<div class='b1'></div>").appendTo(Oli);
				$("<div class='b2'></div>").appendTo(Oli);
				$("<div class='b3'></div>").appendTo(Oli);
				$("<div class='b4'></div>").appendTo(Oli);
				$("<div class='p1'><div><img src=" + obj.img + "/></div><p class='p2'><a href='#'>" + obj.name + "</a></p>   <p class='p2'><strong>银泰价：" + obj.sign + obj.price + "</strong><span>参考价：" + obj.sign + obj.num + "</span></p></div>").appendTo(Oli);
			}

			$(".border-anmiate").mouseenter(function() {
				$(this).find(".b1").stop().animate({
					width: 220
				}, 500);
				$(this).find(".b2").stop().animate({
					height: 260
				}, 500);
				$(this).find(".b3").stop().animate({
					width: 220
				}, 500);
				$(this).find(".b4").stop().animate({
					height: 260
				}, 500);
			})

			$(".border-anmiate").mouseleave(function() {
				$(this).find(".b1").stop().animate({
					width: 0
				}, 500);
				$(this).find(".b2").stop().animate({
					height: 0
				}, 500);
				$(this).find(".b3").stop().animate({
					width: 0
				}, 500);
				$(this).find(".b4").stop().animate({
					height: 0
				}, 500);
			})

		})
		//品牌活动
	$.get("json/index.json", function(responseObj) {
		//遍历从json获取的数据
		var arr = responseObj.data2;
		for(var i = 0; i < arr.length; i++) {
			var obj = arr[i];
			//2, 创建节点			
			$("<a href='#' class='border-anmiate'><img src=" + obj.img + " alt=''/></a>").appendTo(".goods3");
		}
		$(".goods3 img").mouseenter(function() {
			$(this).css("opacity", "0.7");
		})
		$(".goods3 img").mouseleave(function() {
			$(this).css("opacity", "1");
		})
	})
	$(".product-right img").mouseenter(function() {
		$(this).css("opacity", "0.7");
	})
	$(".product-right img").mouseleave(function() {
			$(this).css("opacity", 1);
		})
		//银泰百货
	$(".news-left img").hover(function() {
			$(this).css("opacity", "0.7");
		},
		function() {
			$(this).css("opacity", 1);
		})
	$(".newsT li").mouseenter(function() {
			$(this).find("span").css("background", "url(img/yt_ico.png) no-repeat 0 -16px").parent().siblings().find("span").css("background", "#fff");
			$(this).addClass("border-bm").siblings().removeClass("border-bm");
			var index = $(this).index();
			$(".newsR").eq(index).show().siblings().hide();
		})
		//热门品牌
	$.get("json/index.json", function(responseObj) {
			//遍历从json获取的数据
			var arr = responseObj.data3;
			for(var i = 0; i < arr.length; i++) {
				var obj = arr[i];
				//2, 创建节点	
				//			<a href="#"><img src="img/d1.jpg" alt="" /></a>
				$("<a href='#'><img src=" + obj.img + " alt=''/></a>").appendTo(".newsR1");
			}
			$(".newsR1 img").mouseenter(function() {
				$(this).css("opacity", "0.7");
			})
			$(".newsR1 img").mouseleave(function() {
				$(this).css("opacity", "1");
			})
		})
		//本周推荐
	$.get("json/index.json", function(responseObj) {
			//遍历从json获取的数据
			var arr = responseObj.data4;
			for(var i = 0; i < arr.length; i++) {
				var obj = arr[i];
				//2, 创建节点											
				var Oul = $("<ul class='nev'></ul>").appendTo(".newsR2");
				var Oli = $("<li></li>").appendTo(Oul);
				$("<div class='b1'></div>").appendTo(Oli);
				$("<div class='b2'></div>").appendTo(Oli);
				$("<div class='b3'></div>").appendTo(Oli);
				$("<div class='b4'></div>").appendTo(Oli);
				$("<div class='photo'><img src=" + obj.img + "/></div>").appendTo(Oli);
				$("<div class='text'><p class='txt'><a href='#'>" + obj.name + "</a></p>   <p class='price'><span class='price1 fl'>" + obj.sign + obj.price + "</span><span class='price2 fr'>参考价：" + obj.sign + obj.num + "</span></p></div>").appendTo(Oli);
			}

			$(".nev li").mouseenter(function() {
				$(this).find(".b1").stop().animate({
					width: 198
				}, 500);
				$(this).find(".b2").stop().animate({
					height: 248
				}, 500);
				$(this).find(".b3").stop().animate({
					width: 198
				}, 500);
				$(this).find(".b4").stop().animate({
					height: 248
				}, 500);
			})

			$(".nev li").mouseleave(function() {
				$(this).find(".b1").stop().animate({
					width: 0
				}, 500);
				$(this).find(".b2").stop().animate({
					height: 0
				}, 500);
				$(this).find(".b3").stop().animate({
					width: 0
				}, 500);
				$(this).find(".b4").stop().animate({
					height: 0
				}, 500);
			})
		})
		//时尚名品轮播
	$.get("json/index.json", function(responseObj) {
			//遍历从json获取的数据
			var arr = responseObj.data5;
			for(var i = 0; i < arr.length; i++) {
				var obj = arr[i];
				//2, 创建节点	
				var oDiv = $("<div class='img1'></div>").appendTo(".lie");
				$("<img src=" + obj.img + " alt=''/><img src=" + obj.img1 + " alt=''/><img src=" + obj.img2 + " alt=''/><img src=" + obj.img3 + " alt=''/>").appendTo(oDiv);
			}
			$(".lie div").first().clone(true).appendTo(".lie");
			var size = $(".lie div").size(); //图片的总数量			
			$(".lie").width(size * 170); //设置宽度               
			var i = 0; //即将显示的图片下标			
			//上一页
			$(".mp-store .pre").click(function(e) {
					e.preventDefault();
					i--;
					//限制不超出左边界
					if(i < 0) {
						$(".lie").css("left", -(size - 1) * 170);
						i = size - 2;
					}
					//移动
					$(".mp-store .lie").stop().animate({
						"left": -i * 170
					}, 500);
				})
				//下一页
			$(".mp-store .next").click(function(e) {
				e.preventDefault();
				i++;
				//限制不超出右边界
				if(i >= size) {
					$(".lie").css("left", 0);
					i = 1;
				}
				//移动
				$(".mp-store .lie").stop().animate({
					"left": -i * 170
				}, 500);
			})
		})
		//1,先获取接口数据
	$.get("json/index.json", function(responseObj) {
		//遍历从后台获取的数据
		var arr = responseObj.data6;
		for(var i = 0; i < arr.length; i++) {
			var obj = arr[i];
			//2, 创建节点
			//添加图片
			$("<li><img src=" + obj.img + " /></li>").appendTo(".list");
			$("<li></li>").appendTo(".list1");
		}
		$(".list1 li").first().addClass("active");
		var list1 = $(".list");
		var list2 = $(".list1");
		var li1 = $(".list li");
		var li2 = $(".list1 li");
		var size = $(".list li").size(); //图片的总数量			
		list1.width(size * 390); //设置宽度
		list2.width(size * 18); //设置宽度
		var i = 0; //即将显示的图片下标				
		function move() {
			//限制不超出左边界
			if(i < 0) {
				i = 0;
			}
			//限制不超出右边界
			if(i >= size) {
				i = size - 1;
			}
			//移动
			list1.stop().animate({
				left: -i * 390
			}, 500);
			//改变按钮的选中状态					
			li2.eq(i).addClass("active").siblings().removeClass("active");
		}
		//点击按钮
		li2.click(function() {
			i = $(this).index();
			move();
		})
		$(".mp-content").mouseenter(function() {
			$(".prev").animate({
				left: 0
			}, 500);
			$(".nextP").animate({
				right: 0
			}, 500);
			$(".list li").css("opacity", "0.7");
		})
		$(".mp-content").mouseleave(function() {
				$(".prev").animate({
					left: -30
				}, 500);
				$(".nextP").animate({
					right: -30
				}, 500);
				$(".list li").css("opacity", 1);
			})
			//上一页
		$(".mp-content .prev").click(function(e) {
				e.preventDefault();
				i--;
				move();
			})
			//下一页
		$(".mp-content .nextP").click(function(e) {
			e.preventDefault();
			i++;
			move();
		})
	})
	$.get("json/index.json", function(responseObj) {
			//遍历从json获取的数据
			var arr = responseObj.data7;
			for(var i = 0; i < arr.length; i++) {
				var obj = arr[i];
				//2, 创建节点				
				var ODiv = $("<div class='produnct-block'></div>").appendTo(".mp-goods");
				var aDiv = $("<div class='mp-s'></div>").appendTo(ODiv);
				$("<div class='b1'></div>").appendTo(aDiv);
				$("<div class='b2'></div>").appendTo(aDiv);
				$("<div class='b3'></div>").appendTo(aDiv);
				$("<div class='b4'></div>").appendTo(aDiv);
				$("<div class='mp-p'><img src=" + obj.img + "/></div>").appendTo(aDiv);
			}

			$(".mp-s").mouseenter(function() {
				$(this).find(".b1").stop().animate({
					width: 270
				}, 500);
				$(this).find(".b2").stop().animate({
					height: 180
				}, 500);
				$(this).find(".b3").stop().animate({
					width: 270
				}, 500);
				$(this).find(".b4").stop().animate({
					height: 180
				}, 500);
			})
			$(".mp-s").mouseleave(function() {
				$(this).find(".b1").stop().animate({
					width: 0
				}, 500);
				$(this).find(".b2").stop().animate({
					height: 0
				}, 500);
				$(this).find(".b3").stop().animate({
					width: 0
				}, 500);
				$(this).find(".b4").stop().animate({
					height: 0
				}, 500);
			})
		})
		//潮流女装
	$.get("json/index.json", function(responseObj) {
			//遍历从json获取的数据
			var arr = responseObj.data8;
			for(var i = 0; i < arr.length; i++) {
				var obj = arr[i];
				//2, 创建节点	
				var oDiv = $("<div class='img1'></div>").appendTo(".nz-lie");
				$("<img src=" + obj.img + " alt=''/><img src=" + obj.img1 + " alt=''/><img src=" + obj.img2 + " alt=''/><img src=" + obj.img3 + " alt=''/>").appendTo(oDiv);
			}
			$(".nz-lie div").first().clone(true).appendTo(".lie");
			var size = $(".nz-lie div").size(); //图片的总数量			
			$(".nz").width(size * 170); //设置宽度               
			var i = 0; //即将显示的图片下标			
			//上一页
			$(".nz-store .pre").click(function(e) {
					e.preventDefault();
					i--;
					//限制不超出左边界
					if(i < 0) {
						$(".nz-lie").css("left", -(size - 1) * 170);
						i = size - 2;
					}
					//移动
					$(".nz-lie").stop().animate({
						"left": -i * 170
					}, 500);
				})
				//下一页
			$(".nz-store .next").click(function(e) {
				e.preventDefault();
				i++;
				//限制不超出右边界
				if(i >= size) {
					$(".nz-lie").css("left", 0);
					i = 1;
				}
				//移动
				$(".nz-lie").stop().animate({
					"left": -i * 170
				}, 500);
			})
		})
		//1,先获取接口数据
	$.get("json/index.json", function(responseObj) {
		//遍历从后台获取的数据
		var arr = responseObj.data9;
		for(var i = 0; i < arr.length; i++) {
			var obj = arr[i];
			//2, 创建节点
			//添加图片
			$("<li><img src=" + obj.img + " /></li>").appendTo(".nz-list");
		}
		$(".nz-content").mouseenter(function() {
			$(".nz-list li").css("opacity", "0.7");
		})
		$(".nz-content").mouseleave(function() {
			$(".nz-list li").css("opacity", 1);
		})
	})
	$.get("json/index.json", function(responseObj) {
			//遍历从json获取的数据
			var arr = responseObj.data10;
			for(var i = 0; i < arr.length; i++) {
				var obj = arr[i];
				//2, 创建节点				
				var ODiv = $("<div class='produnct-block'></div>").appendTo(".nz-goods");
				var aDiv = $("<div class='mp-s'></div>").appendTo(ODiv);
				$("<div class='b1'></div>").appendTo(aDiv);
				$("<div class='b2'></div>").appendTo(aDiv);
				$("<div class='b3'></div>").appendTo(aDiv);
				$("<div class='b4'></div>").appendTo(aDiv);
				$("<div class='mp-p'><img src=" + obj.img + "/></div>").appendTo(aDiv);
			}

			$(".mp-s").mouseenter(function() {
				$(this).find(".b1").stop().animate({
					width: 270
				}, 500);
				$(this).find(".b2").stop().animate({
					height: 180
				}, 500);
				$(this).find(".b3").stop().animate({
					width: 270
				}, 500);
				$(this).find(".b4").stop().animate({
					height: 180
				}, 500);
			})
			$(".mp-s").mouseleave(function() {
				$(this).find(".b1").stop().animate({
					width: 0
				}, 500);
				$(this).find(".b2").stop().animate({
					height: 0
				}, 500);
				$(this).find(".b3").stop().animate({
					width: 0
				}, 500);
				$(this).find(".b4").stop().animate({
					height: 0
				}, 500);
			})
		})
		//精品男装
	$.get("json/index.json", function(responseObj) {
			//遍历从json获取的数据
			var arr = responseObj.data11;
			for(var i = 0; i < arr.length; i++) {
				var obj = arr[i];
				//2, 创建节点	
				var oDiv = $("<div class='img1'></div>").appendTo(".jp-lie");
				$("<img src=" + obj.img + " alt=''/><img src=" + obj.img1 + " alt=''/><img src=" + obj.img2 + " alt=''/><img src=" + obj.img3 + " alt=''/>").appendTo(oDiv);
			}
			$(".jp-lie div").first().clone(true).appendTo(".jp-lie");
			var size = $(".jp-lie div").size(); //图片的总数量			
			$(".jp").width(size * 170); //设置宽度               
			var i = 0; //即将显示的图片下标			
			//上一页
			$(".jp-store .pre").click(function(e) {
					e.preventDefault();
					i--;
					//限制不超出左边界
					if(i < 0) {
						$(".jp-lie").css("left", -(size - 1) * 170);
						i = size - 2;
					}
					//移动
					$(".jp-lie").stop().animate({
						"left": -i * 170
					}, 500);
				})
				//下一页
			$(".jp-store .next").click(function(e) {
				e.preventDefault();
				i++;
				//限制不超出右边界
				if(i >= size) {
					$(".jp-lie").css("left", 0);
					i = 1;
				}
				//移动
				$(".jp-lie").stop().animate({
					"left": -i * 170
				}, 500);
			})
		})
		//1,先获取接口数据
	$.get("json/index.json", function(responseObj) {
		//遍历从后台获取的数据
		var arr = responseObj.data12;
		for(var i = 0; i < arr.length; i++) {
			var obj = arr[i];
			//2, 创建节点
			//添加图片
			$("<li><img src=" + obj.img + " /></li>").appendTo(".jp-list");
		}
		$(".jp-content").mouseenter(function() {
			$(".jp-list li").css("opacity", "0.7");
		})
		$(".jp-content").mouseleave(function() {
			$(".jp-list li").css("opacity", 1);
		})
	})
	$.get("json/index.json", function(responseObj) {
			//遍历从json获取的数据
			var arr = responseObj.data13;
			for(var i = 0; i < arr.length; i++) {
				var obj = arr[i];
				//2, 创建节点				
				var ODiv = $("<div class='produnct-block'></div>").appendTo(".jp-goods");
				var aDiv = $("<div class='mp-s'></div>").appendTo(ODiv);
				$("<div class='b1'></div>").appendTo(aDiv);
				$("<div class='b2'></div>").appendTo(aDiv);
				$("<div class='b3'></div>").appendTo(aDiv);
				$("<div class='b4'></div>").appendTo(aDiv);
				$("<div class='mp-p'><img src=" + obj.img + "/></div>").appendTo(aDiv);
			}

			$(".mp-s").mouseenter(function() {
				$(this).find(".b1").stop().animate({
					width: 270
				}, 500);
				$(this).find(".b2").stop().animate({
					height: 180
				}, 500);
				$(this).find(".b3").stop().animate({
					width: 270
				}, 500);
				$(this).find(".b4").stop().animate({
					height: 180
				}, 500);
			})
			$(".mp-s").mouseleave(function() {
				$(this).find(".b1").stop().animate({
					width: 0
				}, 500);
				$(this).find(".b2").stop().animate({
					height: 0
				}, 500);
				$(this).find(".b3").stop().animate({
					width: 0
				}, 500);
				$(this).find(".b4").stop().animate({
					height: 0
				}, 500);
			})
		})
		//时尚鞋靴
	$.get("json/index.json", function(responseObj) {
			//遍历从json获取的数据
			var arr = responseObj.data14;
			for(var i = 0; i < arr.length; i++) {
				var obj = arr[i];
				//2, 创建节点	
				var oDiv = $("<div class='img1'></div>").appendTo(".xx-lie");
				$("<img src=" + obj.img + " alt=''/><img src=" + obj.img1 + " alt=''/><img src=" + obj.img2 + " alt=''/><img src=" + obj.img3 + " alt=''/>").appendTo(oDiv);
			}
			$(".xx-lie div").first().clone(true).appendTo(".xx-lie");
			var size = $(".xx-lie div").size(); //图片的总数量			
			$(".xx").width(size * 170); //设置宽度               
			var i = 0; //即将显示的图片下标			
			//上一页
			$(".xx-store .pre").click(function(e) {
					e.preventDefault();
					i--;
					//限制不超出左边界
					if(i < 0) {
						$(".xx-lie").css("left", -(size - 1) * 170);
						i = size - 2;
					}
					//移动
					$(".xx-lie").stop().animate({
						"left": -i * 170
					}, 500);
				})
				//下一页
			$(".xx-store .next").click(function(e) {
				e.preventDefault();
				i++;
				//限制不超出右边界
				if(i >= size) {
					$(".xx-lie").css("left", 0);
					i = 1;
				}
				//移动
				$(".xx-lie").stop().animate({
					"left": -i * 170
				}, 500);
			})
		})
		//1,先获取接口数据
	$.get("json/index.json", function(responseObj) {
		//遍历从后台获取的数据
		var arr = responseObj.data15;
		for(var i = 0; i < arr.length; i++) {
			var obj = arr[i];
			//2, 创建节点
			//添加图片
			$("<li><img src=" + obj.img + " /></li>").appendTo(".xx-list");
		}
		$(".xx-content").mouseenter(function() {
			$(".xx-list li").css("opacity", "0.7");
		})
		$(".xx-content").mouseleave(function() {
			$(".xx-list li").css("opacity", 1);
		})
	})
	$.get("json/index.json", function(responseObj) {
			//遍历从json获取的数据
			var arr = responseObj.data16;
			for(var i = 0; i < arr.length; i++) {
				var obj = arr[i];
				//2, 创建节点				
				var ODiv = $("<div class='produnct-block'></div>").appendTo(".xx-goods");
				var aDiv = $("<div class='mp-s'></div>").appendTo(ODiv);
				$("<div class='b1'></div>").appendTo(aDiv);
				$("<div class='b2'></div>").appendTo(aDiv);
				$("<div class='b3'></div>").appendTo(aDiv);
				$("<div class='b4'></div>").appendTo(aDiv);
				$("<div class='mp-p'><img src=" + obj.img + "/></div>").appendTo(aDiv);
			}

			$(".mp-s").mouseenter(function() {
				$(this).find(".b1").stop().animate({
					width: 270
				}, 500);
				$(this).find(".b2").stop().animate({
					height: 180
				}, 500);
				$(this).find(".b3").stop().animate({
					width: 270
				}, 500);
				$(this).find(".b4").stop().animate({
					height: 180
				}, 500);
			})
			$(".mp-s").mouseleave(function() {
				$(this).find(".b1").stop().animate({
					width: 0
				}, 500);
				$(this).find(".b2").stop().animate({
					height: 0
				}, 500);
				$(this).find(".b3").stop().animate({
					width: 0
				}, 500);
				$(this).find(".b4").stop().animate({
					height: 0
				}, 500);
			})
		})
		//流行箱包
	$.get("json/index.json", function(responseObj) {
			//遍历从json获取的数据
			var arr = responseObj.data17;
			for(var i = 0; i < arr.length; i++) {
				var obj = arr[i];
				//2, 创建节点	
				var oDiv = $("<div class='img1'></div>").appendTo(".bag-lie");
				$("<img src=" + obj.img + " alt=''/><img src=" + obj.img1 + " alt=''/><img src=" + obj.img2 + " alt=''/><img src=" + obj.img3 + " alt=''/>").appendTo(oDiv);
			}
			$(".bag-lie div").first().clone(true).appendTo(".xx-lie");
			var size = $(".bag-lie div").size(); //图片的总数量			
			$(".bag-lie").width(size * 170); //设置宽度               
			var i = 0; //即将显示的图片下标			
			//上一页
			$(".bag-store .pre").click(function(e) {
					e.preventDefault();
					i--;
					//限制不超出左边界
					if(i < 0) {
						$(".bag-lie").css("left", -(size - 1) * 170);
						i = size - 2;
					}
					//移动
					$(".bag-lie").stop().animate({
						"left": -i * 170
					}, 500);
				})
				//下一页
			$(".bag-store .next").click(function(e) {
				e.preventDefault();
				i++;
				//限制不超出右边界
				if(i >= size) {
					$(".bag-lie").css("left", 0);
					i = 1;
				}
				//移动
				$(".bag-lie").stop().animate({
					"left": -i * 170
				}, 500);
			})
		})
		//1,先获取接口数据
	$.get("json/index.json", function(responseObj) {
		//遍历从后台获取的数据
		var arr = responseObj.data18;
		for(var i = 0; i < arr.length; i++) {
			var obj = arr[i];
			//2, 创建节点
			//添加图片
			$("<li><img src=" + obj.img + " /></li>").appendTo(".bag-list");
			$("<li></li>").appendTo(".bag-list1");
		}
		$(".bag-list1 li").first().addClass("active");
		var list1 = $(".bag-list");
		var list2 = $(".bag-list1");
		var li1 = $(".bag-list li");
		var li2 = $(".bag-list1 li");
		var size = $(".bag-list li").size(); //图片的总数量			
		list1.width(size * 390); //设置宽度
		list2.width(size * 18); //设置宽度
		var i = 0; //即将显示的图片下标				
		function move() {
			//限制不超出左边界
			if(i < 0) {
				i = 0;
			}
			//限制不超出右边界
			if(i >= size) {
				i = size - 1;
			}
			//移动
			list1.stop().animate({
				left: -i * 390
			}, 500);
			//改变按钮的选中状态					
			li2.eq(i).addClass("active").siblings().removeClass("active");
		}
		//点击按钮
		li2.click(function() {
			i = $(this).index();
			move();
		})
		$(".bag-content").mouseenter(function() {
			$(".prev").animate({
				left: 0
			}, 500);
			$(".nextP").animate({
				right: 0
			}, 500);
			$(".list li").css("opacity", "0.7");
		})
		$(".bag-content").mouseleave(function() {
				$(".prev").animate({
					left: -30
				}, 500);
				$(".nextP").animate({
					right: -30
				}, 500);
				$(".list li").css("opacity", 1);
			})
			//上一页
		$(".bag-content .prev").click(function(e) {
				e.preventDefault();
				i--;
				move();
			})
			//下一页
		$(".bag-content .nextP").click(function(e) {
			e.preventDefault();
			i++;
			move();
		})
	})
	$.get("json/index.json", function(responseObj) {
			//遍历从json获取的数据
			var arr = responseObj.data19;
			for(var i = 0; i < arr.length; i++) {
				var obj = arr[i];
				//2, 创建节点				
				var ODiv = $("<div class='produnct-block'></div>").appendTo(".bag-goods");
				var aDiv = $("<div class='mp-s'></div>").appendTo(ODiv);
				$("<div class='b1'></div>").appendTo(aDiv);
				$("<div class='b2'></div>").appendTo(aDiv);
				$("<div class='b3'></div>").appendTo(aDiv);
				$("<div class='b4'></div>").appendTo(aDiv);
				$("<div class='mp-p'><img src=" + obj.img + "/></div>").appendTo(aDiv);
			}

			$(".mp-s").mouseenter(function() {
				$(this).find(".b1").stop().animate({
					width: 270
				}, 500);
				$(this).find(".b2").stop().animate({
					height: 180
				}, 500);
				$(this).find(".b3").stop().animate({
					width: 270
				}, 500);
				$(this).find(".b4").stop().animate({
					height: 180
				}, 500);
			})
			$(".mp-s").mouseleave(function() {
				$(this).find(".b1").stop().animate({
					width: 0
				}, 500);
				$(this).find(".b2").stop().animate({
					height: 0
				}, 500);
				$(this).find(".b3").stop().animate({
					width: 0
				}, 500);
				$(this).find(".b4").stop().animate({
					height: 0
				}, 500);
			})
		})
		//美容护肤
	$.get("json/index.json", function(responseObj) {
			//遍历从json获取的数据
			var arr = responseObj.data20;
			for(var i = 0; i < arr.length; i++) {
				var obj = arr[i];
				//2, 创建节点	
				var oDiv = $("<div class='img1'></div>").appendTo(".mr-lie");
				$("<img src=" + obj.img + " alt=''/><img src=" + obj.img1 + " alt=''/><img src=" + obj.img2 + " alt=''/><img src=" + obj.img3 + " alt=''/>").appendTo(oDiv);
			}
			$(".mr-lie div").first().clone(true).appendTo(".xx-lie");
			var size = $(".mr-lie div").size(); //图片的总数量			
			$(".mr").width(size * 170); //设置宽度               
			var i = 0; //即将显示的图片下标			
			//上一页
			$(".mr-store .pre").click(function(e) {
					e.preventDefault();
					i--;
					//限制不超出左边界
					if(i < 0) {
						$(".mr-lie").css("left", -(size - 1) * 170);
						i = size - 2;
					}
					//移动
					$(".mr-lie").stop().animate({
						"left": -i * 170
					}, 500);
				})
				//下一页
			$(".mr-store .next").click(function(e) {
				e.preventDefault();
				i++;
				//限制不超出右边界
				if(i >= size) {
					$(".mr-lie").css("left", 0);
					i = 1;
				}
				//移动
				$(".mr-lie").stop().animate({
					"left": -i * 170
				}, 500);
			})
		})
		//1,先获取接口数据
	$.get("json/index.json", function(responseObj) {
		//遍历从后台获取的数据
		var arr = responseObj.data21;
		for(var i = 0; i < arr.length; i++) {
			var obj = arr[i];
			//2, 创建节点
			//添加图片
			$("<li><img src=" + obj.img + " /></li>").appendTo(".mr-list");
		}
		$(".mr-content").mouseenter(function() {
			$(".mr-list li").css("opacity", "0.7");
		})
		$(".mr-content").mouseleave(function() {
			$(".mr-list li").css("opacity", 1);
		})
	})
	$.get("json/index.json", function(responseObj) {
			//遍历从json获取的数据
			var arr = responseObj.data22;
			for(var i = 0; i < arr.length; i++) {
				var obj = arr[i];
				//2, 创建节点				
				var ODiv = $("<div class='produnct-block'></div>").appendTo(".mr-goods");
				var aDiv = $("<div class='mp-s'></div>").appendTo(ODiv);
				$("<div class='b1'></div>").appendTo(aDiv);
				$("<div class='b2'></div>").appendTo(aDiv);
				$("<div class='b3'></div>").appendTo(aDiv);
				$("<div class='b4'></div>").appendTo(aDiv);
				$("<div class='mp-p'><img src=" + obj.img + "/></div>").appendTo(aDiv);
			}

			$(".mp-s").mouseenter(function() {
				$(this).find(".b1").stop().animate({
					width: 270
				}, 500);
				$(this).find(".b2").stop().animate({
					height: 180
				}, 500);
				$(this).find(".b3").stop().animate({
					width: 270
				}, 500);
				$(this).find(".b4").stop().animate({
					height: 180
				}, 500);
			})
			$(".mp-s").mouseleave(function() {
				$(this).find(".b1").stop().animate({
					width: 0
				}, 500);
				$(this).find(".b2").stop().animate({
					height: 0
				}, 500);
				$(this).find(".b3").stop().animate({
					width: 0
				}, 500);
				$(this).find(".b4").stop().animate({
					height: 0
				}, 500);
			})
		})
		//运动户外
	$.get("json/index.json", function(responseObj) {
			//遍历从json获取的数据
			var arr = responseObj.data23;
			for(var i = 0; i < arr.length; i++) {
				var obj = arr[i];
				//2, 创建节点	
				var oDiv = $("<div class='img1'></div>").appendTo(".yy-lie");
				$("<img src=" + obj.img + " alt=''/><img src=" + obj.img1 + " alt=''/><img src=" + obj.img2 + " alt=''/><img src=" + obj.img3 + " alt=''/>").appendTo(oDiv);
			}
			$(".yy-lie div").first().clone(true).appendTo(".xx-lie");
			var size = $(".yy-lie div").size(); //图片的总数量			
			$(".yy-lie").width(size * 170); //设置宽度               
			var i = 0; //即将显示的图片下标			
			//上一页
			$(".yy-store .pre").click(function(e) {
					e.preventDefault();
					i--;
					//限制不超出左边界
					if(i < 0) {
						$(".yy-lie").css("left", -(size - 1) * 170);
						i = size - 2;
					}
					//移动
					$(".yy-lie").stop().animate({
						"left": -i * 170
					}, 500);
				})
				//下一页
			$(".yy-store .next").click(function(e) {
				e.preventDefault();
				i++;
				//限制不超出右边界
				if(i >= size) {
					$(".yy-lie").css("left", 0);
					i = 1;
				}
				//移动
				$(".yy-lie").stop().animate({
					"left": -i * 170
				}, 500);
			})
		})
		//1,先获取接口数据
	$.get("json/index.json", function(responseObj) {
		//遍历从后台获取的数据
		var arr = responseObj.data24;
		for(var i = 0; i < arr.length; i++) {
			var obj = arr[i];
			//2, 创建节点
			//添加图片
			$("<li><img src=" + obj.img + " /></li>").appendTo(".yy-list");
			$("<li></li>").appendTo(".yy-list1");
		}
		$(".yy-list1 li").first().addClass("active");
		var list1 = $(".yy-list");
		var list2 = $(".yy-list1");
		var li1 = $(".yy-list li");
		var li2 = $(".yy-list1 li");
		var size = $(".yy-list li").size(); //图片的总数量			
		list1.width(size * 390); //设置宽度
		list2.width(size * 18); //设置宽度
		var i = 0; //即将显示的图片下标				
		function move() {
			//限制不超出左边界
			if(i < 0) {
				i = 0;
			}
			//限制不超出右边界
			if(i >= size) {
				i = size - 1;
			}
			//移动
			list1.stop().animate({
				left: -i * 390
			}, 500);
			//改变按钮的选中状态					
			li2.eq(i).addClass("active").siblings().removeClass("active");
		}
		//点击按钮
		li2.click(function() {
			i = $(this).index();
			move();
		})
		$(".yy-content").mouseenter(function() {
			$(".prev").animate({
				left: 0
			}, 500);
			$(".nextP").animate({
				right: 0
			}, 500);
			$(".list li").css("opacity", "0.7");
		})
		$(".yy-content").mouseleave(function() {
				$(".prev").animate({
					left: -30
				}, 500);
				$(".nextP").animate({
					right: -30
				}, 500);
				$(".list li").css("opacity", 1);
			})
			//上一页
		$(".yy-content .prev").click(function(e) {
				e.preventDefault();
				i--;
				move();
			})
			//下一页
		$(".yy-content .nextP").click(function(e) {
			e.preventDefault();
			i++;
			move();
		})
	})
	$.get("json/index.json", function(responseObj) {
			//遍历从json获取的数据
			var arr = responseObj.data25;
			for(var i = 0; i < arr.length; i++) {
				var obj = arr[i];
				//2, 创建节点				
				var ODiv = $("<div class='produnct-block'></div>").appendTo(".yy-goods");
				var aDiv = $("<div class='mp-s'></div>").appendTo(ODiv);
				$("<div class='b1'></div>").appendTo(aDiv);
				$("<div class='b2'></div>").appendTo(aDiv);
				$("<div class='b3'></div>").appendTo(aDiv);
				$("<div class='b4'></div>").appendTo(aDiv);
				$("<div class='mp-p'><img src=" + obj.img + "/></div>").appendTo(aDiv);
			}

			$(".mp-s").mouseenter(function() {
				$(this).find(".b1").stop().animate({
					width: 270
				}, 500);
				$(this).find(".b2").stop().animate({
					height: 180
				}, 500);
				$(this).find(".b3").stop().animate({
					width: 270
				}, 500);
				$(this).find(".b4").stop().animate({
					height: 180
				}, 500);
			})
			$(".mp-s").mouseleave(function() {
				$(this).find(".b1").stop().animate({
					width: 0
				}, 500);
				$(this).find(".b2").stop().animate({
					height: 0
				}, 500);
				$(this).find(".b3").stop().animate({
					width: 0
				}, 500);
				$(this).find(".b4").stop().animate({
					height: 0
				}, 500);
			})
		})
		//内衣配饰
	$.get("json/index.json", function(responseObj) {
			//遍历从json获取的数据
			var arr = responseObj.data26;
			for(var i = 0; i < arr.length; i++) {
				var obj = arr[i];
				//2, 创建节点	
				var oDiv = $("<div class='img1'></div>").appendTo(".ny-lie");
				$("<img src=" + obj.img + " alt=''/><img src=" + obj.img1 + " alt=''/><img src=" + obj.img2 + " alt=''/><img src=" + obj.img3 + " alt=''/>").appendTo(oDiv);
			}
			$(".ny-lie div").first().clone(true).appendTo(".xx-lie");
			var size = $(".ny-lie div").size(); //图片的总数量			
			$(".ny").width(size * 170); //设置宽度               
			var i = 0; //即将显示的图片下标			
			//上一页
			$(".ny-store .pre").click(function(e) {
					e.preventDefault();
					i--;
					//限制不超出左边界
					if(i < 0) {
						$(".ny-lie").css("left", -(size - 1) * 170);
						i = size - 2;
					}
					//移动
					$(".ny-lie").stop().animate({
						"left": -i * 170
					}, 500);
				})
				//下一页
			$(".ny-store .next").click(function(e) {
				e.preventDefault();
				i++;
				//限制不超出右边界
				if(i >= size) {
					$(".ny-lie").css("left", 0);
					i = 1;
				}
				//移动
				$(".ny-lie").stop().animate({
					"left": -i * 170
				}, 500);
			})
		})
		//1,先获取接口数据
	$.get("json/index.json", function(responseObj) {
		//遍历从后台获取的数据
		var arr = responseObj.data27;
		for(var i = 0; i < arr.length; i++) {
			var obj = arr[i];
			//2, 创建节点
			//添加图片
			$("<li><img src=" + obj.img + " /></li>").appendTo(".ny-list");
		}
		$(".ny-content").mouseenter(function() {
			$(".ny-list li").css("opacity", "0.7");
		})
		$(".ny-content").mouseleave(function() {
			$(".ny-list li").css("opacity", 1);
		})
	})
	$.get("json/index.json", function(responseObj) {
			//遍历从json获取的数据
			var arr = responseObj.data28;
			for(var i = 0; i < arr.length; i++) {
				var obj = arr[i];
				//2, 创建节点				
				var ODiv = $("<div class='produnct-block'></div>").appendTo(".ny-goods");
				var aDiv = $("<div class='mp-s'></div>").appendTo(ODiv);
				$("<div class='b1'></div>").appendTo(aDiv);
				$("<div class='b2'></div>").appendTo(aDiv);
				$("<div class='b3'></div>").appendTo(aDiv);
				$("<div class='b4'></div>").appendTo(aDiv);
				$("<div class='mp-p'><img src=" + obj.img + "/></div>").appendTo(aDiv);
			}

			$(".mp-s").mouseenter(function() {
				$(this).find(".b1").stop().animate({
					width: 270
				}, 500);
				$(this).find(".b2").stop().animate({
					height: 180
				}, 500);
				$(this).find(".b3").stop().animate({
					width: 270
				}, 500);
				$(this).find(".b4").stop().animate({
					height: 180
				}, 500);
			})
			$(".mp-s").mouseleave(function() {
				$(this).find(".b1").stop().animate({
					width: 0
				}, 500);
				$(this).find(".b2").stop().animate({
					height: 0
				}, 500);
				$(this).find(".b3").stop().animate({
					width: 0
				}, 500);
				$(this).find(".b4").stop().animate({
					height: 0
				}, 500);
			})
		})
		//可爱婴童
	$.get("json/index.json", function(responseObj) {
			//遍历从json获取的数据
			var arr = responseObj.data29;
			for(var i = 0; i < arr.length; i++) {
				var obj = arr[i];
				//2, 创建节点	
				var oDiv = $("<div class='img1'></div>").appendTo(".ka-lie");
				$("<img src=" + obj.img + " alt=''/><img src=" + obj.img1 + " alt=''/><img src=" + obj.img2 + " alt=''/><img src=" + obj.img3 + " alt=''/>").appendTo(oDiv);
			}
			$(".ka-lie div").first().clone(true).appendTo(".xx-lie");
			var size = $(".ka-lie div").size(); //图片的总数量			
			$(".ka").width(size * 170); //设置宽度               
			var i = 0; //即将显示的图片下标			
			//上一页
			$(".ka-store .pre").click(function(e) {
					e.preventDefault();
					i--;
					//限制不超出左边界
					if(i < 0) {
						$(".ka-lie").css("left", -(size - 1) * 170);
						i = size - 2;
					}
					//移动
					$(".ka-lie").stop().animate({
						"left": -i * 170
					}, 500);
				})
				//下一页
			$(".ka-store .next").click(function(e) {
				e.preventDefault();
				i++;
				//限制不超出右边界
				if(i >= size) {
					$(".ka-lie").css("left", 0);
					i = 1;
				}
				//移动
				$(".ka-lie").stop().animate({
					"left": -i * 170
				}, 500);
			})
		})
		//1,先获取接口数据
	$.get("json/index.json", function(responseObj) {
		//遍历从后台获取的数据
		var arr = responseObj.data30;
		for(var i = 0; i < arr.length; i++) {
			var obj = arr[i];
			//2, 创建节点
			//添加图片
			$("<li><img src=" + obj.img + " /></li>").appendTo(".ka-list");
		}
		$(".ka-content").mouseenter(function() {
			$(".ka-list li").css("opacity", "0.7");
		})
		$(".ka-content").mouseleave(function() {
			$(".ka-list li").css("opacity", 1);
		})
	})
	$.get("json/index.json", function(responseObj) {
			//遍历从json获取的数据
			var arr = responseObj.data31;
			for(var i = 0; i < arr.length; i++) {
				var obj = arr[i];
				//2, 创建节点				
				var ODiv = $("<div class='produnct-block'></div>").appendTo(".ka-goods");
				var aDiv = $("<div class='mp-s'></div>").appendTo(ODiv);
				$("<div class='b1'></div>").appendTo(aDiv);
				$("<div class='b2'></div>").appendTo(aDiv);
				$("<div class='b3'></div>").appendTo(aDiv);
				$("<div class='b4'></div>").appendTo(aDiv);
				$("<div class='mp-p'><img src=" + obj.img + "/></div>").appendTo(aDiv);
			}

			$(".mp-s").mouseenter(function() {
				$(this).find(".b1").stop().animate({
					width: 270
				}, 500);
				$(this).find(".b2").stop().animate({
					height: 180
				}, 500);
				$(this).find(".b3").stop().animate({
					width: 270
				}, 500);
				$(this).find(".b4").stop().animate({
					height: 180
				}, 500);
			})
			$(".mp-s").mouseleave(function() {
				$(this).find(".b1").stop().animate({
					width: 0
				}, 500);
				$(this).find(".b2").stop().animate({
					height: 0
				}, 500);
				$(this).find(".b3").stop().animate({
					width: 0
				}, 500);
				$(this).find(".b4").stop().animate({
					height: 0
				}, 500);
			})
		})
		//楼梯	
	var isMoving = false; //表示点击按钮是否正在动画运动过程中
	//点击楼梯导航栏上的楼层按钮
	$(".louTi-nav a").not(".fh1").click(function() {
		//将点击的按钮变成选中状态
		$(this).addClass("louti-bg").siblings().removeClass("louti-bg");
		$(this).find("span").show().parent().siblings().find("span").hide();
		//移动滚动条(移动页面)
		var index = $(this).index();
		var top = $(".louTi").eq(index).offset().top;
		isMoving = true; //正要开始动画运动
		$("html,body").stop().animate({
			"scrollTop": top
		}, 500, function() {
			isMoving = false; //动画运动结束
		});
	});
	//返回顶部
	$(".fh1").click(function() {
		$(".fh1").css({
			"background": "#E5004F",
			"color": "#fff"
		})
		$(".fh1").html("返回顶部");
		$("html,body").animate({
			"scrollTop": 0
		}, 500);
	})
	$(".fh1").mouseenter(function() {
		$(".fh1").css({
			"background": "#E5004F",
			"color": "#fff"
		})
		$(".fh1").html("返回顶部");
	})
	$(".fh1").mouseleave(function() {
			$(".fh1").css("background", "url(img/floor_nav.png) no-repeat 0 -495px");
			$(".fh1").html("");
			$(".fh1 span").html("");
		})
		//滚动事件
	$(window).scroll(function() {
		//如果没有正在移动
		if(isMoving == false) {
			var _scrollTop = $(window).scrollTop();
			var top1 = $(".product").offset().top;
			if(_scrollTop > top1) {
				$(".louTi-nav").show();
			} else {
				$(".louTi-nav").hide();
			}
			var index = 0; //楼层的下标
			$(".louTi").each(function() {
				var _top = $(this).offset().top;
				if(_scrollTop >= _top) {
					index = $(this).index();
				}
			})
			$(".louTi-nav a").eq(index).find("span").show().parent().siblings().find("span").hide();
			$(".louTi-nav a").eq(index).addClass("louti-bg").siblings().removeClass("louti-bg");
		}
	})

    $(".f_pic").hover(function(){
    	$(this).find("img").css("margin-left","-10px");   	    	
    },function(){
    	$(this).find("img").css("margin-left",0);
    })

})