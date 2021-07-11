//获取样式属性
function getStyle(obj,attr){
	if(window.getComputedStyle){
		return window.getComputedStyle(obj,null)[attr];
	}
	return obj.currentStyle[attr];
}

//(   加强版      )
//json字符串
//调用方式:animate(box,{left:100,top:300,width:400},function(){});
/*
 * obj:需要运动的元素
 * json:包含需要改变的css属性名和属性值,如:{let:200,top:500}
 * fn:运动结束后的回调函数
 */
function animate(obj,json,fn){
	//清除原来的定时器
	clearInterval(obj.timer);
	//开启新的定时器
	obj.timer=setInterval(function(){
		//定义一个变量bStop,表示所有属性都达到了目标值,可以停止定时器
		var bStop=true;
		
		//json={left:100,top:300,width:400}
		for(var attr in json){
			//属性名
			//itarget:目标值,属性值
			var iTarget=json[attr];
			
			//1.current
			var current=0;
			//区分透明度和宽高边距(透明度没有像素单位且算法也不同)
			if(attr=="opacity"){//透明度(opacity)
				current=getStyle(obj,attr)*100;
				current=Math.round(current);
			}
			else{//宽高和边距(left,top,width,height)
				current=parseFloat(getStyle(obj,attr));
				current=Math.round(current);
			}
			
			//2.speed
			var speed=(iTarget-current)/15;
			speed=speed>0?Math.ceil(speed):Math.floor(speed);
			
			//3.判断是否存在有属性还没有达到目标值
			if(current!=iTarget){
				bStop=false;//表示有一个或多个属性还没有到达目标值
			}
			
			//4.运动
			if(attr=="opacity"){//透明度
				obj.style[attr]=(current+speed)/100;
				obj.style.filter="alpha(opacity="+(current+speed)+")";
			}
			else{//宽高,边距(left,top,width,height)
				obj.style[attr]=current+speed+"px";
			}
		}
		
		//如果bStop为true,即所有属性都达到了目标值
		if(bStop){
			console.log("停止运动");
			clearInterval(obj.timer);//清除定时器
			if(fn) fn();//回调
		}
	},50);
}
			
		
			
			
			
		
	
	
	
	
