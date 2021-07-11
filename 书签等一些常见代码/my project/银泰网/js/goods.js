$(function() {
	$(".left-nav").hover(function() {
		$(".sort").show();
	}, function() {
		$(".sort").hide();
	})
	$(".WeChat,.WeChatBox").hover(function() {
		$(".WeChat").css("border", "1px solid #ccc");
		$(".WeChatBox").show();
	}, function() {
		$(".WeChat").css("border", "none");
		$(".WeChatBox").hide();
	})
	$(".snsShare,.snsBox").hover(function() {
			$(".snsShare").css("border", "1px solid #ccc");
			$(".snsBox").show();
		}, function() {
			$(".snsShare").css("border", "none");
			$(".snsBox").hide();
		})
		//创建商品详情页右侧商品列表
	$.get("json/goods.json", function(responseObj) {
		//遍历从json获取的数据
		var arr = responseObj.data1;
		for(var i = 0; i < arr.length; i++) {
			var obj = arr[i];
			//2, 创建节点					
			$("<li><a href='#'><img src=" + obj.img + " alt=''/></a><p class='y-e-ptit'><a href='#'>" + obj.name + "</a></p><p class='p-listPrice'><strong>" + obj.sign + obj.price + "</strong><em>" + obj.num + "</em></p></li>").appendTo(".peson-tj")
		}
	})
	$.get("json/goods.json", function(responseObj) {
		//遍历从json获取的数据
		var arr = responseObj.data2;
		for(var i = 0; i < arr.length; i++) {
			var obj = arr[i];
			//2, 创建节点					
			$("<li><a href='#'><img src=" + obj.img + " alt=''/></a><p class='y-e-ptit'><a href='#'>" + obj.name + "</a></p><p class='p-listPrice'><strong>" + obj.sign + obj.price + "</strong><em>" + obj.num + "</em></p></li>").appendTo(".peson-buy")
		}
	})
	$.get("json/goods.json", function(responseObj) {
			//遍历从json获取的数据
			var arr = responseObj.data3;
			for(var i = 0; i < arr.length; i++) {
				var obj = arr[i];
				//2, 创建节点					
				$("<li><a href='#'><img src=" + obj.img + " alt=''/></a><p class='y-e-ptit'><a href='#'>" + obj.name + "</a></p><p class='p-listPrice'><strong>" + obj.sign + obj.price + "</strong><em>" + obj.num + "</em></p></li>").appendTo(".peson-see")
			}
		})
		//商品列表吸顶部分
	$(".mobileBuy a,.qrCode").hover(function() {
		$(".mobileBuy a").css("background", "url(img/icon.png) no-repeat -148px -70px");
		$(".qrCode").show();
	}, function() {
		$(".mobileBuy a").css("background", "url(img/icon.png) no-repeat 0 -70px");
		$(".qrCode").hide();
	})

	$(".y-desc-bar button").hover(function() {
			$(".miniShopCart").show();
		}, function() {
			$(".miniShopCart").hide();
		})
		//吸顶效果
	var top = $(".y-desc-bar").offset().top;
	$(window).scroll(function() {
			var disTop = $(window).scrollTop();
			if(disTop >= top) {
				$(".y-desc-bar").css({
					"position": "fixed",
					"top": 0
				});
			} else {
				$(".y-desc-bar").css("position", "static");
			}
		})
		//创建图片
		//从首页传入的商品id
	var goodsId = location.search.slice(1)
	$.get("json/store.json", function(responseObj) {
		//遍历从json获取的数据
		var arr = responseObj.data1;
		for(var i = 0; i < arr.length; i++) {
			var obj = arr[i];
			if(goodsId == obj.id) {
				showInfo(obj);//商品大图
				smallImg(obj);//商品小图
				size(obj);//商品的尺码
				color(obj);
				
			}
		}
	})
	function showInfo(obj) {
		$("<div class='magnifier-wrap smallImg'><img src=" + obj.img1 + " alt='' class='img-s' /><div class='smallArea'></div></div>").appendTo(".pic-show");
		$("<div class='bigArea'><img src=" + obj.img1 + " alt='' class='bigImg' /></div>").appendTo(".pic-show");
		$("<h2>"+obj.name+"</h2>").insertBefore(".priceInfo");
		$("<strong>"+obj.sign+obj.price+".<em>00</em></strong>").appendTo(".YTPrice");
		$("<span>"+obj.sign+obj.num+".<em>00</em></span>").appendTo(".marketPriceNum");		
		//放大镜
		var _smallImg = $(".smallImg"); //小图
		var _smallArea = $(".smallArea"); //小区域
		var _bigArea = $(".bigArea"); //大区域
		var _bigImg = $(".bigImg"); //大图
		//计算小区域的宽高
		// _smallArea.width/_bigArea.width = _smallImg.width/_bigImg.width
		// _smallArea.width = _smallImg.width/_bigImg.width * _bigArea.width
		_smallArea.width(_smallImg.width() / _bigImg.width() * _bigArea.width());
		_smallArea.height(_smallImg.height() / _bigImg.height() * _bigArea.height());
		//放大系数/放大倍数
		var scale = _bigImg.width() / _smallImg.width();
		//mousemove
		_smallImg.mousemove(function(e) {
				_smallArea.show(); //显示小区域
				_bigArea.show();
				var x = e.pageX - _smallImg.offset().left - _smallArea.width() / 2;
				var y = e.pageY - _smallImg.offset().top - _smallArea.height() / 2;
				//判断不超出左边界
				if(x <= 0) {
					x = 0;
				} else if(x >= _smallImg.width() - _smallArea.width()) { //不超出右边界
					x = _smallImg.width() - _smallArea.width();
				}
				//判断不超出上边界
				if(y <= 0) {
					y = 0;
				} else if(y >= _smallImg.height() - _smallArea.height()) {
					y = _smallImg.height() - _smallArea.height();
				}
				_smallArea.css({
					left: x,
					top: y
				}); //移动小区域
				//移动大图
				_bigImg.css({
					left: -scale * x,
					top: -scale * y
				});
			})
			//mouseleave
		_smallImg.mouseleave(function() {
			_smallArea.hide(); //隐藏小区域
			_bigArea.hide();
		})
	}
	function smallImg(obj) {
		var imgdate = obj.smallImg;
		for(var j = 0; j < imgdate.length; j++) {
			var obj1 = imgdate[j];
			$("<li><a href='#'><img src=" + obj1 + " alt='' /></a></li>").appendTo(".mouseenter-qh");
			$("<img src=" + obj1 + " alt='' />").appendTo(".yp-dobule-images");
		}
		
		
		
		$(".mouseenter-qh li").mouseenter(function() {
				$(".smallImg img").attr("src", $(this).find("img").attr("src"));
				$(".bigArea img").attr("src", $(this).find("img").attr("src"));
			})
			//点击小图中的翻页
		var size = $(".mouseenter-qh li").size();
		$(".mouseenter-qh").width(size * 72);
		var num = 0;
		$(".ew-c-pre").click(function() {
			num--;
			if(num < 0) {
				num = 0;
			}
			$(".mouseenter-qh").stop().animate({
				"left": -num * 72
			}, 500);
		})
		$(".ew-c-next").click(function() {
			num++;
			if(num > size - 1) {
				num = size - 1;
			}
			$(".mouseenter-qh").stop().animate({
				"left": -num * 72
			}, 500);
		})
	}

	function size(obj){
		var sizedate = obj.size;
		for(var j = 0; j < sizedate.length; j++) {
			var obj2 = sizedate[j];
			$("<div class='item1'><b></b><a href='#'><span>"+obj2+"</span></a></div>").appendTo(".store-size");
			$(".item1").first().addClass("selected");
			//点击选择尺码
			$(".item1").click(function(e) {
				e.preventDefault();
					$(this).addClass("selected").siblings().removeClass("selected");
			})						
		} 
	}		
	
	function color(obj){
		
		var colordate = obj.img;
		for(var j = 0; j < colordate.length; j++) {
			var obj3 = colordate[j];			
			$("<div class='item'><b></b><a href='#' class='color-lei'><img src="+obj3+" /></a></div>").appendTo(".goods-color");	
			$(".item").first().addClass("selected");
			
		} 
	}			
		//点击商品加减
	var n = 1;
	$(".p-num-red").click(function(e) {
		e.preventDefault();
		n--;
		if(n <= 1) {
			n = 1;
		}
		$(".p-num input").val(n);
	})
	$(".p-num-add").click(function(e) {
		e.preventDefault();
		n++;
		$(".p-num input").val(n);
	})
	//点击加入购物车
	$(".incart").click(function(){
		$("#mask").show();
		$(".info-box").show();
		 //加入购物车
       var index=location.search.slice(1);
       var numb=parseInt($(".p-num input").val());       
       $(".p-num input").blur(function(){
       	    numb=$(this).val();       	
       })                                                         					
		//使用cookie保存购物车商品信息
		var arr = $.cookie("cart") ? JSON.parse($.cookie("cart")) : [];		
		//判断购物车中是否存在该商品,  如果存在则把数量增加, 否则添加该新商品
		var isExist = false; //表示是否存在相同商品
		for (var i=0; i<arr.length; i++){
			if (index == arr[i].id) { //存在相同的商品
				arr[i].numb=arr[i].numb+numb; //将数量++
				isExist = true;
				break;
			}
		}		
		//如果不存在, 否则添加该新商品
		if (!isExist) {
			//将新商品加入数组中
			var obj={
					id:index,
					numb:numb
		        }
			arr.push(obj);
		}					
			//重新将数组arr保存到cookie中
			$.cookie("cart", JSON.stringify(arr), {expires:30, path:"/"} );
		//	console.log( $.cookie("cart") );																									
		$(".content-cart").click(function(){
			$("#mask").hide();
		    $(".info-box").hide();
		})
		$(".box-cart-pay").click(function(){
			location.href="cart.html";
			$(".p-num input").val(1);
		})
		$(".Y_CloCart").click(function(){
			location.reload();
		})
		
	})

})