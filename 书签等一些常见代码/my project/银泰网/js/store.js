$(function() {
	$(".left-nav").hover(function() {
		$(".sort").show();
	}, function() {
		$(".sort").hide();
	})
	$(".mp-dj").hover(function() {
		$(this).find(".store-mpL").show();
		$(this).css("background", "#666");
		$(this).find("a").css("color", "#fff");
		$(this).find("b").css("background", "url(img/st.jpg) no-repeat 0 -5px");
	}, function() {
		$(this).find(".store-mpL").hide();
		$(this).css("background", "");
		$(this).find("a").css("color", "#666");
		$(this).find("b").css("background", "url(img/st.jpg) no-repeat");
	})
	$(".nz-dj").hover(function() {
		$(this).find(".store-mpL").show();
		$(this).css("background", "#666");
		$(this).find("a").css("color", "#fff");
		$(this).find("b").css("background", "url(img/st.jpg) no-repeat 0 -5px");
	}, function() {
		$(this).find(".store-mpL").hide();
		$(this).css("background", "");
		$(this).find("a").css("color", "#666");
		$(this).find("b").css("background", "url(img/st.jpg) no-repeat");
	})
	$(".zk-content").hover(function() {
		$(this).css({
			"color": "#E5004F",
			"background": "url(img/zk.gif) no-repeat 32px -178px"
		})
	}, function() {
		$(this).css({
			"color": "#000",
			"background": "url(img/zk.gif) no-repeat 32px -161px"
		})
	})
	$(".sq-content").hover(function() {
		$(this).css({
			"color": "#E5004F",
			"background": "url(img/zk.gif) no-repeat 32px -178px"
		})
	}, function() {
		$(this).css({
			"color": "#000",
			"background": "url(img/zk.gif) no-repeat 32px -161px"
		})
	})
	$(".goods-fl .zk-content").click(function() {
		$(".goods-fl .zk").hide();
		$(".goods-fl .sq").show();
		$(".p-filterMain").css("min-height", "260px");
		$(".goods-fl").css({
			"min-height": "90px"
		});
		$(".goods-fl ul").css({
			"min-height": "80px",
			"overflow-y": "scroll"
		});

	})
	$(".goods-fl .sq-content").click(function() {
		$(".goods-fl .sq").hide();
		$(".goods-fl .zk").show();
		$(".p-filterMain").css("min-height", "191px");
		$(".goods-fl").css({
			"min-height": "42px"
		});
		$(".goods-fl ul").css({
			"min-height": "28px",
			"overflow-y": "hidden"
		});
	})
	$(".goods-pp .zk-content").click(function() {
		$(".goods-pp .zk").hide();
		$(".goods-pp .sq").show();
		$(".xs-pp").hide();
		$(".search-pp").show();
		$(".goods-mc").show();
		$(".search-mc ul").show();
		$(".p-filterMain").css({
			"min-height": "360px"
		});
	})
	$(".goods-pp .sq-content").click(function() {
		$(".goods-pp .sq").hide();
		$(".goods-pp .zk").show();
		$(".xs-pp").show();
		$(".search-pp").hide();
		$(".goods-mc").hide();
		$(".search-mc ul").hide();
		$(".p-filterMain").css({
			"min-height": "192px"
		});
	})
	$(".dx-content").hover(function() {
		$(this).css({
			"color": "#E5004F",
			"background": "url(img/dx.jpg) no-repeat 32px -33px"
		})
	}, function() {
		$(this).css({
			"color": "#000",
			"background": "url(img/dx.jpg) no-repeat 32px 2px"
		})
	})
	$(".dax-content").hover(function() {
		$(this).css({
			"color": "#E5004F",
			"background": "url(img/dx.jpg) no-repeat 32px -52px"
		})
	}, function() {
		$(this).css({
			"color": "#000",
			"background": "url(img/dx.jpg) no-repeat 32px -15px"
		})
	})
	$(".goods-pp .dx-content").click(function() {
		$(".goods-mc ul li input").show();
		$(".goods-pp .zk").hide();
		$(".goods-pp .sq").show();
		$(".xs-pp").hide();
		$(".search-pp").show();
		$(".goods-mc").show();
		$(".search-mc ul").show();
		$(".p-filterMain").css({
			"min-height": "360px"
		});
		$(".goods-pp .dx-content").hide();
		$(".goods-pp .dax").show();
	})
	$(".goods-pp .dax-content").click(function() {
		$(".goods-mc ul li input").hide();
		$(".goods-pp .dax").hide();
		$(".goods-pp .dx-content").show();
	})
	$(".goods-ys .zk-content").click(function() {
		$(".goods-ys .zk").hide();
		$(".goods-ys .sq").show();
		$(".goods-ys").css({
			"min-height": "96px"
		})
		$(".hs-yc").show();
		$(".xy-hx").css({
			"height": "80px",
			"overflow-y": "scroll"
		})
		$(".p-filterMain").css({
			"min-height": "247px"
		});
	})
	$(".goods-ys .sq-content").click(function() {
		$(".goods-ys .zk").show();
		$(".goods-ys .sq").hide();
		$(".goods-ys").css({
			"min-height": "40px"
		})
		$(".hs-yc").hide();
		$(".xy-hx").css({
			"height": "28px",
			"overflow-y": "hidden"
		})
		$(".p-filterMain").css({
			"min-height": "192px"
		});
	})
	$(".goods-ys .dx-content").click(function() {
		$(".goods-ys .zk").hide();
		$(".goods-ys .sq").show();
		$(".goods-ys").css({
			"min-height": "96px"
		})
		$(".hs-yc").show();
		$(".xy-hx").css({
			"height": "80px",
			"overflow-y": "scroll"
		})
		$(".p-filterMain").css({
			"min-height": "247px"
		});
		$(".goods-ys ul li input").show();
		$(".goods-ys .dx-content").hide();
		$(".goods-ys .dax").show();
	})
	$(".goods-ys .dax-content").click(function() {
			$(".goods-ys ul li input").hide();
			$(".goods-ys .dax").hide();
			$(".goods-ys .dx-content").show();
		})
		//吸顶效果
	var top = $(".store-xd").offset().top;
	$(window).scroll(function() {
			var disTop = $(window).scrollTop();
			if(disTop >= top) {
				$(".store-xd").css({
					"position": "fixed",
					"top": 0,
					"margin-top":0
				});
			} else {
				$(".store-xd").css({"position":"static","margin-top":6});
			}
		})
		//商品列表
	$.get("json/store.json", function(responseObj) {
		//遍历从json获取的数据
		var arr = responseObj.data1;
		for(var i = 0; i < arr.length; i++) {
			var obj = arr[i];
			//2, 创建节点				
        var oDiv = $("<div class='p-listInfo'></div>").appendTo(".p-list");
            $("<div class='p-listImgBig'><img src=" +obj.img1+ " alt='' /></div>").appendTo(oDiv);     
        var div1 = $("<div class='p-listImgSmall'></div>").appendTo(oDiv);  
        var ul1=$("<ul></ul>").appendTo(div1);               
           var imgdate=obj.img;
           for(var j=0;j<imgdate.length;j++){
           	  var obj1=imgdate[j];   
           	  $("<li><a href='#'><img src=" + obj1 + " /></a></li>").appendTo(ul1);           	           	
           }                                                      
           $("<div class='p-listPrice'><strong>￥<i class='store-price'>"+obj.price+"</i></strong><em>"+obj.num+"</em></div>").appendTo(oDiv);
            $("<div class='p-listTxt'><p><a href='#'>"+obj.name+"</a></p></div>").appendTo(oDiv);
			$("<div class='p-listAddInfo'><p><a href='#'>"+obj.store+"</a></p></div>").appendTo(oDiv); 
		}	
		$(".p-listInfo").click(function(){
			var index=$(this).index();			
			var obj=arr[index];			
//		   var index=location.search.slice(1);
//		   console.log(index);
//			//使用cookie保存购物车商品信息
//		  var arr = $.cookie("lie") ? JSON.parse($.cookie("lie")) : [];		
//		    var isExist = false; //表示是否存在相同商品
//		for (var i=0; i<arr.length; i++){
//			if (index == arr[i].id) { //存在相同的商品				
//				isExist = true;
//				break;
//			}
//		}		
//		//如果不存在, 否则添加该新商品
//		if (!isExist) {
//			//将新商品加入数组中
//		   $.get("json/store.json",function(responseObj){
//		   	   var arr=responseObj.data1;
//		   	   for(var i=0;i<arr.length;i++){
//		   	   	 var obj=arr[i];
//		   	   	 if(index==obj.id){
//		   	   	 	var newObj={
//		   	   	 		img:obj.img1,
//		   	   	 		price:obj.price
//		   	   	 	}
//		   	   	 	arr.push(newObj);
//		   	   	 }
//		   	   	 
//		   	   }
//		   	//重新将数组arr保存到cookie中
//			$.cookie("lie", JSON.stringify(arr), {expires:30, path:"/"} );
//		   	
//		   })
//		
//		  console.log($.cookie("lie"));
//		
//			
//		}					
																
			//跳入详情页
			location.href="goods.html?"+obj.id; 				          						
		})
		//列表商品的移入移除
		$(".p-listInfo").hover(function() {
		$(this).css("border-color", "#f00");
		$(this).find(".p-listAddInfo").show();
		}, function() {
			$(this).css("border-color", "#f4f4f4");
			$(this).find(".p-listAddInfo").hide();
		})
		$(".p-listImgSmall ul li").mouseenter(function() {
			$(this).parents(".p-listInfo").find(".p-listImgBig img").attr("src", $(this).find("img").attr("src"));
		})						
	})									
	
	//列表中价格的筛选
	$(".low-price").click(function(){		
		 $('.store-price').each(function (index,obj) {		
            var price = parseInt($(obj).html()); 
            //你的比较逻辑，这里测试直接写死，选出大于200的记录，你自己依据你的条件写比较
            if (price >= 1000 && price <= 2000) {            	
            	$(obj).parents(".p-listInfo").show();
            }else{
            	$(obj).parents(".p-listInfo").hide();
            }
        });		
	})
	$(".middle-price").click(function(){		
		 $('.store-price').each(function (index,obj) {
            var price = parseInt($(obj).html()); 
            //你的比较逻辑，这里测试直接写死，选出大于200的记录，你自己依据你的条件写比较
            if (price > 2000 && price <= 4000) {            	
            	$(obj).parents(".p-listInfo").show();
            }else{
            	$(obj).parents(".p-listInfo").hide();
            }
        });		
	})
	
	$(".height-price").click(function(){		
		 $('.store-price').each(function (index,obj) {
            var price = parseInt($(obj).html()); 
            //你的比较逻辑，这里测试直接写死，选出大于200的记录，你自己依据你的条件写比较
            if (price > 5000) {            	
            	$(obj).parents(".p-listInfo").show();
            }else{
            	$(obj).parents(".p-listInfo").hide();
            }
        });		
	})
	
	$(".sure").click(function(){		
		 $('.store-price').each(function (index,obj) {
            var price = parseInt($(obj).html()); 
            var p1= parseInt($(".begin").val());
            var p2= parseInt($(".over").val());
            //你的比较逻辑，这里测试直接写死，选出大于200的记录，你自己依据你的条件写比较
            if (price >=p1 && price <=p2 ) {            	
            	$(obj).parents(".p-listInfo").show();
            }else{
            	$(obj).parents(".p-listInfo").hide();
            }
        });		
	})
	
	
	
	
})