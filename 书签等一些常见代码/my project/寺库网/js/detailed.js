$(function(){
	/*先给一个全局变量,保持获取的所有商品*/
	var myArr = [];
	$(".global-bottom-ul").on("click", ".global-bottom-ul-img", function() {
		var index = $(this).index(".global-bottom-ul-img");
		//		console.log(index, myArr);
		var obj = myArr[index];
		//		console.log(obj)
		location.href = "detailed.html?" + obj.id;
		//		console.log(obj.id);
	})
	var goodsId = location.search.slice(1);
	$.get("json/bigImg.json", function(data) {
			//		console.log(data)
			myArr = data.imgsrc;
			var arr = myArr;
			for(var i = 0; i < arr.length; i++) {
				/*每个商品的数据*/
				var obj = arr[i];
				
				if(obj.id == goodsId) {
					console.log(obj)
//					var obj = arr[i];
					mo(obj);
					clickimg(obj);
					carta(obj);
					ahref(obj);
				}

			}
		})
					function mo(obj) {
					$(".id").append(obj.id);
					$(".p1").append(obj.content);
					$(".simg").attr("src", obj.bigImg[0]);
					$(".bimg").attr("src", obj.bigImg[0]);
					$(".price").append(obj.price);
					$("<a class='cart'>加入购物车<a>").appendTo(".pcart");
					$("<a class='buy'>立即结算<a>").appendTo(".pcart");
					var arrImg = obj.liimg;
					//					console.log(arrImg);
					for(var i = 0; i < arrImg.length; i++) {
						var li = $("<li></li>");
						$("<img class='clickimg' src='" + arrImg[i] + "'/>").appendTo(li);
						li.appendTo(".list");
					}
				}
				function clickimg(obj) {
					$(".clickimg").click(function() {
					var index = $(this).index(".clickimg");
					//					console.log(index)
					$(".simg").attr("src", obj.bigImg[index]);
					$(".bimg").attr("src", obj.bigImg[index]);
					})
				}
//				var num=1;
				function carta(obj) {
					$(".pcart").on("click", ".cart", function(e) {
						e.preventDefault();
						var arr = $.cookie("cart") ? JSON.parse($.cookie("cart")) : [];/*获取cookie如果不为空则解析 */
						//			console.log($.cookie("cart"));
						var isundefind = false;//是否有相同的商品
						//遍历cookie中的数组,获取每个对象ID,与加载id匹配,
						for(var i = 0; i < arr.length; i++) {
							//Cookie获取的数组num++,arr[i]是保存的cook取出的才能自增
							console.log(obj.id,arr[i].id)
							if(obj.id == arr[i].id) {
								console.log(arr[i].num)
							/*如果存在相同id 则num++*/
								arr[i].num++;
								isundefind=true;//返回true;
								break;
	//							$(".num").text(obj.num);
							}
						}
						if(!isundefind){
							obj.num=1;/*obj添加一个num=1的数据*/
							arr.push(obj);/*添加新对象到数组*/
						}
						//stringify()转换字符串
						$(".num").text(arr[i].num);
						console.log(arr)
						$.cookie("cart",JSON.stringify(arr),{expires:7,path:"/"});/*创建新的cart名称的数组转换字符串存入*/	
						console.log($.cookie("cart"));
					})
				
				}
			ahref()
		function ahref(){
		$(".pcart").on("click",".buy",function(){
				location.href="cart.html"
		});
	}
})