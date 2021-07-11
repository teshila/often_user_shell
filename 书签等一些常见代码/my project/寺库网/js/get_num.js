$(function(){
	
	total
	
	
	
	
		var num=0;
		if (num>0) {
	var arr= $.cookie("cart");
	
	for (var i=0;i<arr.length;i++) {
			var obj=arr[i];
			num+=obj.num;
		}
		$(".num").html(num)
	}
	
	
})
