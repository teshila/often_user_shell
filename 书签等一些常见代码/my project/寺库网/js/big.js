$(function(){
	
	var mdiv=$(".middle");
	var smove=$(".smallDiv");
	var bdiv=$(".big");
	var bimg=$(".bimg");
	var simg=$(".simg");
//		famove.width()/bdiv.width()=bimg1.width()/simg.width();
//		smove.width()/bdiv.width()=simg.width()/bimg.width();
		/*改变小图宽高*/
//		smove.width(simg.width()/bimg.width()*bdiv.width());
//		smove.height(simg.height()/bimg.height()*bdiv.height());
		var sam=bimg.width()/simg.width();
		
		mdiv.mousemove(function(e){
		
			smove.show();
			bdiv.show();
			var x=e.pageX - simg.offset().left - smove.width()/2;
			var y=e.pageY - simg.offset().top - smove.height()/2;
			
			if(x<0){
				x=0;
			}
			else if(x>mdiv.width()-smove.width()){
				x=mdiv.width()-smove.width();
			}
			if(y<0){
				y=0
			}else if(y>mdiv.height()-smove.height()){
				y=mdiv.height()-smove.height();
			}
			//移动小图
			smove.css({left:x,top:y});
			//移动大图
			bimg.css({left:-sam*x,top:-sam*y});
		})
		mdiv.mouseleave(function(){
			smove.hide();
			bdiv.hide();
		})
   
})