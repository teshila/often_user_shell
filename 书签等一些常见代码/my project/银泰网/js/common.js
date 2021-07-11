$(function() {
	//头部
	$(".wx").mouseenter(function() {
		$(".wx1").addClass("ys");
		$(".wxp").show();
	})
	$(".wx").mouseleave(function() {
		$(".wx1").removeClass("ys");
		$(".wxp").hide();
	})
	$(".phone").mouseenter(function() {
		$(".phone1").addClass("sm");
		$(".phoneP").show();
	})
	$(".phone").mouseleave(function() {
		$(".phone1").removeClass("sm");
		$(".phoneP").hide();
	})
	$(".change").mouseenter(function() {
		$(".yr").addClass("mou");
		$(".non").show();
	})
	$(".change").mouseleave(function() {
		$(".yr").removeClass("mou");
		$(".non").hide();
	})
	$(".sort dl").mouseenter(function() {
		$(this).find("dt").css("background", "#e5004f");
		$(this).find(".jt").css("background", "#e5004f");
		$(this).find("dd").show();
		$(this).find(".golden").css("background", " url(img/header-02.png) no-repeat 0 -129px");
		$(this).find(".girl-cloth").css("background", " url(img/header-02.png) no-repeat -17px -129px");
		$(this).find(".man-cloth").css("background", " url(img/header-02.png) no-repeat -34px -129px");
		$(this).find(".shoes").css("background", " url(img/header-02.png) no-repeat -51px -129px");
		$(this).find(".makeup").css("background", " url(img/header-02.png) no-repeat -68px -129px");
		$(this).find(".box").css("background", " url(img/header-02.png) no-repeat -85px -129px");
		$(this).find(".sports").css("background", " url(img/header-02.png) no-repeat -102px -129px");
		$(this).find(".ornament").css("background", " url(img/header-02.png) no-repeat -119px -129px");
		$(this).find(".childs").css("background", " url(img/header-02.png) no-repeat -136px -129px");
		$(this).find(".cloths").css("background", " url(img/header-02.png) no-repeat -153px -129px");
	})
	$(".sort dl").mouseleave(function() {
		$(this).find("dt").css("background", "#333");
		$(this).find("dd").hide();
		$(this).find(".jt").css("background", " url(img/header-02.png) no-repeat -209px -111px");
		$(this).find(".golden").css("background", " url(img/header-02.png) no-repeat 0 -111px");
		$(this).find(".girl-cloth").css("background", " url(img/header-02.png) no-repeat -17px -111px");
		$(this).find(".man-cloth").css("background", " url(img/header-02.png) no-repeat -34px -111px");
		$(this).find(".shoes").css("background", " url(img/header-02.png) no-repeat -51px -111px");
		$(this).find(".makeup").css("background", " url(img/header-02.png) no-repeat -68px -111px");
		$(this).find(".box").css("background", " url(img/header-02.png) no-repeat -85px -111px");
		$(this).find(".sports").css("background", " url(img/header-02.png) no-repeat -102px -111px");
		$(this).find(".ornament").css("background", " url(img/header-02.png) no-repeat -119px -111px");
		$(this).find(".childs").css("background", " url(img/header-02.png) no-repeat -136px -111px");
		$(this).find(".cloths").css("background", " url(img/header-02.png) no-repeat -153px -111px");
	})

	//登录用户名
	//获取cookie

	var _users = $.cookie("users");
	if(_users) {
		_users = JSON.parse(_users);
		for(var i = 0; i < _users.length; i++) {
			$(".rightL span").html("Hi");
			$(".login-index").html(_users[i]._username);
			$(".register-index").html("退出");
		}
	}
	//删除cookie
	$(".register-index").click(function() {
		$.cookie("users", "", {
			expires: 0,
			path: "/"
		});
		$(".rightL span").html("Hi，欢迎来到银泰网！");
		$(".login-index").html("登录");
		$(".register-index").html("注册");
	})

	//返回顶部
	$(window).scroll(function() {
		var top1 = $(window).scrollTop();
		if(top1 > 10) {
			$(".b-top").css("opacity", 1);
		} else {
			$(".b-top").css("opacity", 0);
		}
	})
	$(".b-top a").click(function() {
		$("html,body").animate({
			"scrollTop": 0
		}, 500)
	})
	//购物车
	var goodsArr = $.cookie("cart");
	//console.log($.cookie("cart"))
	if(goodsArr) {
		goodsArr = JSON.parse(goodsArr); //json解析
		$("<div class='J-ShopCart yt-bag'></div>").insertAfter(".index-cart");
		$("<p class='new-add'>最新加入的商品</p>").appendTo(".yt-bag");
		$("<div class='pu-goods'><a href='#'>普通商品</a></div>").appendTo(".yt-bag");
		//遍历goodsArr
		$.get("json/store.json", function(result) {
			var goods = result.data1;
			for(var i = 0; i < goodsArr.length; i++) {
				$.each(goods, function() {
					//console.log(this); 
					if(goodsArr[i].id == this.id) {
						var obj = this; //每个商品的数据
						//创建节点   	
						var div = $("<div class='goods-xx'></div>").appendTo(".yt-bag");
						$("<div class='fl goods-content'><img src=" + obj.img1 + " alt='' /></div>").appendTo(div);
						$("<div class='fl goods-title'><a href='#'>" + obj.name + "</a></div>").appendTo(div);
						$("<div class='goods-prices fr'><p><span class='obj-price'>" + obj.price + " </span>X<em class='num'>" + goodsArr[i].numb + "</em></p><p class='delet'><a href='#'>删除</a></p></div>").appendTo(div);
					}
				})
			}
			$("<div class='goods-total'><span>共 <em class='total-num'>0</em> 件商品</span><span>共计 <em class='total-price'>￥100</em> 元</span></div>").appendTo(".yt-bag");
			$("<div class='go-cart'><a href='cart.html'>去购物袋结算 </a></div>").appendTo(".yt-bag");

			total1();
			total2();
			//删除
			$(".delet").click(function() {
				var index = $(this).index(".delet");
				//获取cookie
				var arr = JSON.parse($.cookie("cart"));
				arr.splice(index, 1); //删除				
				//重新把数组arr保存到cookie中
				$.cookie("cart", JSON.stringify(arr), {
					expires: 30,
					path: "/"
				});
				$(this).closest(".goods-xx").remove();
				total1();
				total2();
			})
			function total1() {
				var totalnum = 0;
				$(".goods-xx").each(function(index, obj) {
					//console.log(obj);
					//console.log(index);
					totalnum += parseInt($(obj).find(".num").html());
				})
				$(".total-num").html(totalnum);
				$(".cart-num").html(totalnum);
			}
			function total2() {
				var totalprice = 0;
				$(".goods-xx").each(function(index, obj) {
					totalprice += parseInt($(obj).find(".num").html() * $(obj).find(".obj-price").html());
				})
				$(".total-price").html(totalprice);
			}
		})
	}
    $(".index-cart,.yt-bag").hover(function(){
    	$(".yt-bag").show();
    },function(){
    	$(".yt-bag").hide();
    })
    


})