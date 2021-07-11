$(function() {
	//数组checkArr: 保存每个商品的选中状态
	var checkArr = []; //元素如果是true则表示选中, false则表示未选中
	//初始化checkArr
	var arr2 = $.cookie("cart");
	if(arr2) {
		arr2 = JSON.parse(arr2);
		$.each(arr2, function() {
			checkArr.push(true); //默认都是选中状态                                                    
		});
	}
	refresh();
	//刷新购物车数据
	function refresh() {
		//获取cookie中购物车的商品
		var goodsArr = $.cookie("cart");
		if(goodsArr) {
			goodsArr = JSON.parse(goodsArr); //json解析			
			//遍历goodsArr
			$.get("json/store.json", function(result) {
				var goods = result.data1;
				for(var i = 0; i < goodsArr.length; i++) {
					//console.log(goodsArr[i].numb);
					$.each(goods, function() {
						//console.log(this); 
						if(goodsArr[i].id == this.id) {
							var obj = this; //每个商品的数据
							//创建节点   	 			    
                    var div1=$("<div class='CommodityList'></div>").appendTo("#CommodityBox");
                    var div2=$("<div class='AProduct'></div>").appendTo(div1);
                    if(checkArr[i]){
                    	 $("<div class='category fl'><input type='checkbox' checked='checked' class='check' /></div>").appendTo(div2);
                    }else{
                    	$("<div class='category fl'><input type='checkbox' class='check' /></div>").appendTo(div2);
                    }                                                                                
                    $("<div class='goods-photo fl'><div class='property'><div class='pro-img fl'><a href='#'><img src="+obj.img1+" alt='' /></a></div><p class='pro-title fl'><a href='#'>"+obj.name+"</a></p><div class='pro-pro fl'><p class='pro-check'>颜色："+obj.color[0]+"</p><div class='pro-tip'><span class='pro-tip-txt'>特例</span></div></div></div></div>").appendTo(div2);
                    $("<div class='goods-sale fl'><em class='fl'>¥</em><div class='pro-price fl'>"+obj.price+".00</div></div>").appendTo(div2);
                    $("<div class='pro-number fl'><a href='#' class='nums-red fl'>-</a><input class='pro-nums fl' type='text' value="+ goodsArr[i].numb+" /><a href='#' class='nums-add fl'>+</a></div>").appendTo(div2);
                    $("<div class='pro-sort fl'>"+parseInt(obj.price * goodsArr[i].numb)+"</div>").appendTo(div2);
                    $("<div class='pro-del fl'><div class='decidetime'><a class='pro-collect' href='#'>移入收藏</a></div><div class='decidetime'><a class='pro-remove' href='#'>删除商品</a></div></div>").appendTo(div2);                                                                                                                                                                                                                        
						}
					})
				}
				//计算价格
				total();
			})
		}
	}	
	//勾选
	$("#CommodityBox").on("click", ".check", function() {
		//判断是否全选
		updateAllCheked();
		//改变价格
		total();
	})
	//更改全选状态
	function updateAllCheked() {
		if($('.CommodityList').find(':checkbox:checked').length == $('.CommodityList').find(':checkbox').length) {
			$("#allChecked,#allChecked1").prop('checked', true)
		} else {
			$("#allChecked,#allChecked1").prop('checked', false)
		}
	}
	               //计算价格
	function total() {
		var totalnum = 0;
		$('.CommodityList').find(':checkbox:checked').each(function(index, obj) {
			totalnum += parseInt($(obj).parents('.CommodityList').find('.pro-sort').html());						
		})
		$('.pay-total').html(totalnum);
		$(".total").html(totalnum);
		$(".pay").html(totalnum);
	}	                  	
	//全选
	$("#allChecked,#allChecked1").click(function() {		
		$('.cart-con').find(':checkbox').prop('checked', this.checked);
		total(); //改变价格
	})
	//-	
	$("#CommodityBox").on("click", ".nums-red", function(e) {
		
		e.preventDefault();
		//对应'-'按钮的下标
		var index = $(this).index(".nums-red");		
		//console.log("- :" + index); 
		//获取cookie
		var arr = JSON.parse($.cookie("cart"));
		arr[index].numb--; //把数组中的第index个商品的numb--
		//如果数量小于等于0, 只能等于1
		if(arr[index].numb <= 0) {
			arr[index].numb = 1;
		}
		//重新把数组arr保存到cookie中
		$.cookie("cart", JSON.stringify(arr), {
			expires: 30,
			path: "/"
		});
		$(this).closest(".CommodityList").find(".pro-nums").val(arr[index].numb);
		$(this).closest(".CommodityList").find(".pro-sort").html(parseInt($(this).closest(".CommodityList").find(".pro-price").html() * arr[index].numb));
		total();
	})
    //				//+ 
	$("#CommodityBox").on("click", ".nums-add", function(e) {
		
		e.preventDefault();
		var index = $(this).index(".nums-add");				
		//获取cookie
		var arr = JSON.parse($.cookie("cart"));
		arr[index].numb++;				
		//重新把数组arr保存到cookie中
		$.cookie("cart", JSON.stringify(arr), {
			expires: 30,
			path: "/"
		});
		//console.log($.cookie("cart"));				
		$(this).closest(".CommodityList").find(".pro-nums").val(arr[index].numb);
		$(this).closest(".CommodityList").find(".pro-sort").html(parseInt($(this).closest(".CommodityList").find(".pro-price").html() * arr[index].numb));
		total();
	});
	//删除
	$("#CommodityBox").on("click", ".pro-remove", function() {
		var index = $(this).index(".pro-remove");
		//获取cookie
		var arr = JSON.parse($.cookie("cart"));
		arr.splice(index, 1); //删除
		checkArr.splice(index, 1); //删除checkArr中对应的数据
		updateAllCheked(); //更改全选按钮的状态
		//重新把数组arr保存到cookie中
		$.cookie("cart", JSON.stringify(arr), {
			expires: 30,
			path: "/"
		});
		$(this).closest(".CommodityList").remove();
		total();
	})
	//失去焦点时
	$("#CommodityBox").on("blur", ".pro-nums", function() {
		var index = $(this).index(".pro-nums");
		//获取cookie
		var arr = JSON.parse($.cookie("cart"));
		arr[index].numb = Number($(this).val());
		//重新把数组arr保存到cookie中
		$.cookie("cart", JSON.stringify(arr), {
			expires: 30,
			path: "/"
		});
		$(this).closest(".CommodityList").find(".pro-nums").val(arr[index].numb);
		$(this).closest(".CommodityList").find(".pro-sort").html(parseInt($(this).closest(".CommodityList").find(".pro-price").html() * arr[index].numb));
		total();
	})
    //点击删除选中的
    $("#ClearCartBtn").click(function(){
    	$('.CommodityList').find(':checkbox:checked').each(function(index, obj){
       var index = $(this).index(".pro-remove");
		//获取cookie
		var arr = JSON.parse($.cookie("cart"));
		arr.splice(index, 1); //删除
		checkArr.splice(index, 1); //删除checkArr中对应的数据
		updateAllCheked(); //更改全选按钮的状态
		//重新把数组arr保存到cookie中
		$.cookie("cart", JSON.stringify(arr), {
			expires: 30,
			path: "/"
		});
		$(this).closest(".CommodityList").remove();
		total();
    	})
    })																																					
					
	//创建购物车下面的商品列表
	$.get("json/cart.json", function(responseObj) {
		//遍历从json获取的数据
		var arr = responseObj.data1;
		var arr2 = responseObj.data2;
		var arr3 = responseObj.data3;
		for(var i = 0; i < arr.length; i++) {
			var obj = arr[i];			
				//2, 创建节点								
			var li = $("<li></li>").appendTo(".bfd_d");
			$("<div class='inter-pro-pic'><a href='#'><img alt='' src=" + obj.img + "></a></div>").appendTo(li);
			$("<p class='inter-pro-txt'><a href='#'>" + obj.name + "</a></p>").appendTo(li);
			$("<p class='inter-pro-dis'>" + obj.sign + obj.price + "</p>").appendTo(li);
			$("<div class='inter-pro-add'><a href='#' class='c-lbtn'>放入购物袋</a></div>").appendTo(li);
		}
		for(var j = 0; j < arr2.length; j++) {
			var obj1 = arr2[j];
				//2, 创建节点								
			var li = $("<li></li>").appendTo(".bfd_a");
			$("<div class='inter-pro-pic'><a href='#'><img alt='' src=" + obj1.img + "></a></div>").appendTo(li);
			$("<p class='inter-pro-txt'><a href='#'>" + obj1.name + "</a></p>").appendTo(li);
			$("<p class='inter-pro-dis'>" + obj1.sign + obj1.price + "</p>").appendTo(li);
			$("<div class='inter-pro-add'><a href='#' class='c-lbtn'>放入购物袋</a></div>").appendTo(li);
		}
		for(var j = 0; j < arr3.length; j++) {
			var obj2 = arr3[j];
				//2, 创建节点								
			var li = $("<li></li>").appendTo(".bfd_c");
			$("<div class='inter-pro-pic'><a href='#'><img alt='' src=" + obj2.img + "></a></div>").appendTo(li);
			$("<p class='inter-pro-txt'><a href='#'>" + obj2.name + "</a></p>").appendTo(li);
			$("<p class='inter-pro-dis'>" + obj2.sign + obj2.price + "</p>").appendTo(li);
			$("<div class='inter-pro-add'><a href='#' class='c-lbtn'>放入购物袋</a></div>").appendTo(li);
		}
		$(".bfd_d li").clone(true).appendTo(".bfd_d");
		$(".bfd_a li").clone(true).appendTo(".bfd_a");
		$(".bfd_c li").clone(true).appendTo(".bfd_c");
		var size = $(".bfd_d li").size();	
		$(".inter-list-mian").css("width", size * 174);
		var num = 0;
		//上一页
		$(".inter-prev").click(function() {
			num--;
			if(num < 0) {
				num = (size / 5) - 1;				
			}
			$(".inter-list-mian").stop().animate({
				"left": -num * 5*174
			}, 500);
		});
		//下一页
		$(".inter-next").click(function() {
			num++;
			if(num > (size / 5) - 1) {
				num = 0;			
			}
			$(".inter-list-mian").stop().animate({
				"left": -num * 5*174
			}, 500);
		})
	})
	$(".inter-title span").mouseenter(function(){
		var index=$(this).index();
		$(this).addClass("cursor").siblings().removeClass("cursor");
		$(".inter-list-mian").eq(index).show().siblings().hide();
	})
				
})