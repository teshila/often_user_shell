$(function() {
	$.get("json/mall.json", function(resObj) {
		var arrMore = resObj.more_left;
		var arrRight = resObj.more_right;
		var arrLi = resObj.Li;
		for(var i = 0; i < arrLi.length; i++) {
			mallObj = arrMore[i]
			liObj = arrLi[i];
			$("<div class='mall-con'></div>").addClass("louti").appendTo(".mall");
			$("<div class='mall-more'>" + mallObj.h4 + mallObj.a + arrRight[0].right + "</div>").appendTo(".mall-con");
			var mallImg = $("<div class='mall-img'><ul class='mall-img-ul'>" + liObj.mall_img1 + liObj.img1_h4 + liObj.img1_p + liObj.mall_img2 + liObj.img2_h4 + liObj.img2_p + liObj.mall_img3 + liObj.img3_h4 + liObj.img3_p +
				liObj.mall_img4 + liObj.img4_h4 + liObj.img4_p + liObj.mall_img5 + liObj.img5_h4 + liObj.mall_img6 + liObj.img6_h4 + liObj.img6_p + "</ul></div>");

			$(".mall-img-ul li").addClass("hover-img");
			$(".mall-con").eq(i).append(mallImg);
		}
	});

	/*底部轮播图*/
	$.get("json/bottomImg.json", function(a) {
		var arrBottomImg = a.bottomImg;
		for(var i = 0; i < arrBottomImg.length; i++) {
			var obj = arrBottomImg[i];
			$("<li><a href='#'><img src=" + obj.img + "></a></li>").appendTo(".bottomImg-ul");
			$(".bottomImg-ul li a img").addClass("hover-img");
		}
		bottomMove();
	});

	function bottomMove() {
		var list1 = $(".bottomImg-ul");
		var li1 = $(".bottomImg-ul li");
		li1.first().clone(true).appendTo(list1);
		var size = li1.size();
		list1.width(size * 250);
		var i = 0;
		var timer = setInterval(function() {
			i++;
			Botommove();
		}, 2000);

		function Botommove() {
			if(i < 0) {
				list1.css("left", -(size - 1) * 250);
				i = size - 2;
			}
			if(i >= size) {
				list1.css("left", 0);
				i = 1;
			}
			list1.stop().animate({
				left: -i * 250
			}, 500);

		}

		$(".bottomlef").click(function() {
			i--;
			Botommove();
		});
		$(".bottomright").click(function() {
			i++;
			Botommove();
		});
		$(".bottomImg-hide").hover(function() {
			console.log('claer');
				clearInterval(timer);
			},
			function() {
				timer = setInterval(function() {
					i++;
					Botommove();
			}, 2000)
		})
	}

})