$(function() {
	var checkArr = [];

	var cartArr = $.cookie("cart");
	//如果如果有值就解析
	if(cartArr) {
		cartArr = JSON.parse(cartArr);
		$.each(cartArr, function() {    
		 	checkArr.push(true); //默认都是选中状态                                                    
		});
	}
	$(".tbody").empty();
	
	var total = 0;
	
	$("<thead><tr class='tr1'><th><input type='checkbox' checked='checked' class='allbox'><i class='all'>全选</i></th><th>商品名称</th><th>发货站</th><th>价格</th><th>数量</th><th>金额小计</th><th>操作</th></tr></thead>")
		.appendTo(".table");
	$("<tbody class='tbody'></tbody>").appendTo(".table");
	addtr();

	function addtr() {
		//总价
		var cartArr = $.cookie("cart");
			if (cartArr) {
				cartArr = JSON.parse(cartArr);
			} //json解析
						
			//清除旧的节点
				$(".tbody").empty();
				var total = 0; //总价
				var allnum=0;
		for(var i = 0; i < cartArr.length; i++) {
		var imgArr = cartArr[i].liimg;
		var price=cartArr[i].price
		var num = cartArr[i].num;
		//小总价
		var ssam=0;
		ssam=price*num;
		console.log(ssam)
		var tr = $("<tr class='tr'></tr>");
		tr.appendTo(".tbody");
		
		//根据checkArr来判断当前的勾选状态
			if (checkArr[i]) {
				$("<td><input type='checkbox'checked='checked'  class='check'></td>").appendTo(tr);
			}
			else {
				$("<td><input type='checkbox'  class='check'></td>").appendTo(tr);
			}
		
//		$("<td><input type='checkbox' class='onebox'></td>").appendTo(tr);
		$("<td class='td2'><img class='tdimg' src="+imgArr[0]+"><span class='spancon'>"+cartArr[i].content+"</span></td>").appendTo(tr);
		$("<td>澳大利亚</td>").appendTo(tr);
		$("<td><i style='font-size: 18px;'>¥</i><i class='price num'>"+price+"</i></td>").appendTo(tr);
		$("<td class='trsam'><button class='btndown'>-</button><input type='text' name='' id='' value="+num+" class='input_text'/><button class='btnup'>+</button></td>").appendTo(tr);
		$("<td><i style='font-size: 18px;'>¥</i><i class='sam num'>"+ssam+"</i></td>").appendTo(tr);
		$("<td class='tddel'><a class='del'>删除</a></td>").appendTo(tr);
	
	//单货总计
	
	//计算总价
			if (checkArr[i]){ //如果是选中的
				total += price * num-0;
				allnum +=num;
			}
	
	}
		//显示总价
		$(".allbuy").text(total+".00");
		$(".allnum").text(allnum);
		
		
	}

	/*加减*/
	$(".table").on("click", ".btndown", function() {
		console.log(111);
		//对应"-"下标
		var index = $(this).index(".btndown");
		//			console.log(index);
		//获取cookie
		var arr = JSON.parse($.cookie("cart"));
		console.log(arr[index].num);
		arr[index].num--; //把数组中的第indedx个商品的num--
		console.log(arr[index].num)
		
		
		//如果数组的数量小于0;则删除该商品
		if(arr[index].num <= 0) {
			arr.splice(index, 1); //删除数组
			checkArr.splice(index, 1); //删除check中的对应的数据
		}
		$.cookie("cart", JSON.stringify(arr), {expries: 7,path: "/"});

		//console.log($.cookie("cart"))
		//刷新函数
		addtr();
	});
	/*+*/
	$(".table").on("click", ".btnup", function() {
		var index = $(this).index(".btnup");
		var arr = JSON.parse($.cookie("cart"));
		arr[index].num++; //把数组中的第indedx个商品的num--
		console.log(arr[index].num)
			//如果数组的数量小于0;则删除该商品
		$.cookie("cart", JSON.stringify(arr), {expries: 7,path: "/"});

		//			console.log($.cookie("cart"))
		//刷新函数
		addtr();
	});
	//删除
	$(".table").on("click", ".del", function() {
		var index = $(this).index(".del");
		//获取cookie
		var arr = JSON.parse($.cookie("cart"));
		arr.splice(index, 1);
		checkArr.splice(index, 1);
		
		//更新全选
		eachCheck();
		$.cookie("cart", JSON.stringify(arr), {expries: 7,path: "/"});

		//			console.log($.cookie("cart"))
		//刷新
		addtr();
	});
	
	
	//判断全选
//	
	function eachCheck(){
		var sum=0;//
		$.each(checkArr,function(index,value){
			sum+=value;
		})
		
			if (sum == checkArr.length) {
						$(".allbox").prop("checked", true); //全选
					}
					else {
						$(".allbox").prop("checked", false); //不全选
					}
		}
			
		
	  
	
	
	
				//勾选
				$(".table").on("click", ".check", function(){
					var index = $(this).index(".check");
//					console.log(index);
					
					//切换选中状态
					checkArr[index] = !checkArr[index];
					//console.log(checkArr);
					
					//判断是否全选
					eachCheck();
					
					//刷新
					addtr();
				})
				
				//全选
				$(".allbox").click(function(){
					
					console.log( $(this).prop("checked") );
					
					//全选
					if ( $(this).prop("checked") ){
						$.each(checkArr, function(i) {    
							 checkArr[i] = true;                          
						});
					}
					else {
						$.each(checkArr, function(i) {    
							 checkArr[i] = false;                          
						});
					}
					
					//刷新
					addtr();
				})
				
				//删除选中
				$(".alldel").click(function(){
					
					//获取cookie
					var arr = JSON.parse( $.cookie("cart") );
					
					var newArr = []; //保存不删除的商品
					var newCheckArr = []; //保存不删除的商品对应的选中状态
					for (var i=0; i<arr.length; i++){
						if (checkArr[i]) { //如果不选中
							newArr.push(arr[i]);
							newCheckArr.push(checkArr[i]);
						}
					}
					checkArr = newCheckArr;
					
					//重新存储到cookie
					$.cookie("cart", JSON.stringify(newArr), {expires:30, path:"/"});
					eachCheck();
					//刷新
					addtr();
				})


})