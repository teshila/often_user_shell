  ;(function($) {  
		
  		$.secooframe = function(opts) {openframe(opts); };  
  		
    	$.secooframe.defaults={
    			
    			frameurl:"http://www.secoo.com/loreg/page/tofasreg",
    			css: {
    				padding:	0,
    				margin:		0,
    				width:		'30%',
    				top:		'40%',
    				left:		'30%',
    				textAlign:	'center',
    				color:		'#000',
    				border:		'0px',
    				cursor:		'wait'
    			},
    			style:"width:700px;height:392px;border:0;"
    	}
	    
    	function openframe(opts)
    	{
    		
    		var frameurl = opts && opts.frameurl !== undefined ? opts.frameurl : undefined;
    		var style = opts && opts.style !== undefined ? opts.style : undefined;
    		var mycss = opts && opts.css !== undefined ? opts.css : undefined;
    		
    		opts = $.extend({}, $.secooframe.defaults, opts || {});
    		frameurl = frameurl === undefined ? opts.frameurl : frameurl;
    		mycss = mycss === undefined ? opts.mycss : mycss;
    		style = style === undefined ? opts.style : style;
    		
    		var $div = $("<div id='fastloreg' style='display:none;width:665px;'></div>");
    		$div.html("<span class='xxx'></span><iframe src="+frameurl+" marginwidth='0' marginheight='0' frameborder='0' scrolling='no' style='"+style+"'></iframe>");
    		
    		if($("body").find($("#fastloreg")))
    		{
    			$("#fastloreg").remove();
    		}
    		//alert($div.html());
    		$("body").append($div);
    		
    		$.blockUI( { // 当点击事件发生时调用弹出层
    				message : $div, // 要弹出的元素box
    				css : mycss
    		}); 
    		$('.blockOverlay').attr('title','点击此处关闭登录框').click($.unblockUI); 
    		$('.xxx').attr('title','点击此处关闭登录框').click($.unblockUI);
    		
    	}
    	  
  })(jQuery);
  
  function closeOpenBox()
  {
  	$.unblockUI();
  }
