$(function(){
	var parameters = cartJS.getParameters(location.search);
	cartJS.ajaxCart($.toJSON(parameters), function(_cart){
	}, true);
});
var cartJS = {
	ajaxCart: function(params, callback, isRetry){
		var _this = this;
		if(!params || params === 'undefined' || params === 'null'){
			params = {};
		} else{
			try{params = $.parseJSON(params);} catch(e){}
		}
		$.ajax({
		    type: "POST",
		    url: "/cart/cart_shoppinglist.jsp",
		    data: params,
		    cache: false,
		    error: function(XMLHttpRequest, textStatus, errorThrown){
				var errorMsg = textStatus || errorThrown;
			},
			// timeout: 10000,
		    success: function(json){
		    	if(!json){return;}
		    	var _cart = json.cart;
		    	//对接代码
		    	if (!pushJS.isPush && _cart) {
			    	var cartItems = [];
			    	var productIds = [];
			    	var totalNum = 0;
			    	var pinyouItems = "";
					for (var i = 0; i < _cart.commonCartItems.length; i++) {
						var item = _cart.commonCartItems[i];
						if (item.isChecked == 1) {
							var cartItem = [];
							cartItem.push(item.productId + "");
							cartItem.push(item.secooPrice);
							cartItem.push(item.quantity);
							cartItems.push(cartItem);
							productIds.push(item.productId);
							totalNum += item.quantity;
							pinyouItems += item.productId + "," + item.quantity + ";";
						}
					}
					//pushJS.baifendian(cartItems, _cart.totalNowPrice);
					dwindowDataJS.getMayLikeData(productIds);
					pushJS.jinzan(productIds.join(","), _cart.totalNowPrice + "", totalNum + "");
					pushJS.pinyou(_cart.totalNowPrice + '', pinyouItems);
					pushJS.mediav(productIds.join(","));
					pushJS.isPush = true;
		    	}
		    	
	    		//////////////
				if(json.redirectUrl){window.location.href=json.redirectUrl;}
				
				// DOM控制
				var _htmlDom = _this.getCartHtmlDom(_cart);
				_this.insertHtmlDom(_htmlDom);
				
				// 显示错误弹出层
				_this.showErrorAlert(json.retMsg, '温馨提示',function(){
					$('[name="errorAlert"][clone]').find(".buttonOk").show();
				},null, true);
				
				// 保留当前场景cart数据
				$('html').data('_cart', _cart);
				
				if(typeof callback === 'function'){
					var isBreak = callback(_cart);
					if(isBreak){
						// 保留当前场景cart数据
						$('html').data('_cart', _cart);
						return;
					}
				}
				
				if(/^3\d{2}$/.test(json.retCode) && /^http[s]?:.*?$/.test(json.redirectUrl)){
					window.location.href = json.redirectUrl;
					return;
				}
		    }
		});
	},
	getCartHtmlDom: function(_cart){
		var _this = this;
		var _cart = _this.changeCart(_cart);
		var htmlDom = '';
		var data = {
			"_cart":_cart
		};
		if(!_cart || _cart.empty){
			htmlDom = template.render('cart_productList_template', data);
		} else{
			htmlDom = template.render('cart_productList_template', data);
		}
		return htmlDom;
	},
	//转换购物车对象，用于页面渲染
	changeCart : function(_cart){
		var _this = this;
		var commonMap = {}; //定义一个存储正常商品的map
		var presentMap = {}; //定义一个存储赠品的map
		var minusAmountMap = _cart.minusAmountMap;//满减对应的散列集合
		var minDiscountMap = _cart.minDiscountMap;//折扣对应的散列集合
		var fullQuantityDiscountMap = _cart.fullQuantityDiscountMap;//多件多折对应的散列集合
		var commonCartItems = _cart.commonCartItems;//商品集合
		var presentCartItems = _cart.presentCartItems;//赠品集合
		var userId = _cart.userId;//用户id
		commonMap = _this.toCommonObj(commonCartItems,userId).commonMap;//把商品转换成key为促销id的map
		presentMap = _this.toPresentMap(presentCartItems);//把赠品转换为key为促销id的map
		var realTotalReferencerice = _cart.totalNowPrice.add(_cart.totalPackagePrice);//商品金额总计
		var getRealTotalPrice = realTotalReferencerice
		realTotalReferencerice = _this.toCommonPrice(realTotalReferencerice,2,0); //自动补00
		_cart.mapCart = _this.combinedMap(commonMap,presentMap,minusAmountMap,minDiscountMap,fullQuantityDiscountMap);//添加过映射关系的商品赠品集合
		_cart.isHideChoseDel = _this.toCommonObj(commonCartItems,userId).isHideChoseDel;//是否隐藏“删除选择商品”按钮1:隐藏,0不隐藏
		_cart.realTotalReferencerice = realTotalReferencerice;//商品金额总计
		_cart.notNormalList=_this.toCommonObj(commonCartItems,userId).notNormalList;
		_cart.getRealTotalReferencerice = realTotalReferencerice;//金额总计
		_cart.getRealTotalPrice= getRealTotalPrice;//金额总计
		_cart.packageQuantity  = 0;
		for (var commonIndex in commonCartItems) {
		    if (commonCartItems[commonIndex].packageId > 0 && commonCartItems[commonIndex].isChecked>0) {
		        _cart.packageQuantity += commonCartItems[commonIndex].quantity;
		    }
		}
		return _cart;
	},
	toCommonPrice : function(s, n , o){
		 n = n > 0 && n <= 20 ? n : 2;  
			var sss = s.toString().split(".");
			if(sss.length > 1 || o == 0){
				s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + ""; 
			}else{
			    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")) + ""; 
			}
		    var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];  
		    t = "";  
		    for (i = 0; i < l.length; i++) {  
		        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");  
		    }
			var obj = '';
			if(o > 0){
				var xsd = s.toString().split(".");
				 if(xsd.length == 1){	
					 return t.split("").reverse().join("");
				 }
				 if(xsd.length>1){
					if(xsd[1].length<2){
						obj = xsd[1]
					}else{
						obj = xsd[1]
					}
				 }
				return t.split("").reverse().join("") + "." + obj;  
			}
		    return t.split("").reverse().join("") + "." + r;  
	},
	/**
	 * 把商品转换成key为促销id的对象
	 * @param commonCartItems 商品集合
	 * @param userId 用户id
	 * return Object（commonMap,notNormalList,isHideChoseDel）
	 */
	toCommonObj : function(commonCartItems,userId){
		var _this = this;
		var obj = new Object();//返回对象
		var commonMap = {}; //定义一个存储正常商品的map
		var notNormalList = new Array();//定义一个存储非正常商品的集合
		var isHideChoseDel = 1;//是否隐藏“删除选择商品”按钮1:隐藏,0不隐藏
		//根据促销id把正常商品转换为map集合
		for(var commonIndex in commonCartItems){//循环商品集合
			if (!commonCartItems.hasOwnProperty(commonIndex)) {//判定当前对象是否具有指定名称的属性
				continue;
			}
			var salePrice = 0;//应展示的商品售价
			var splitPrice = 0;
			var quantity = commonCartItems[commonIndex].quantity;//商品数量
			if(userId==0){
				salePrice = commonCartItems[commonIndex].secooPrice;
			}else{
				salePrice = commonCartItems[commonIndex].userDiscountPrice;
			}
			var totalSalePrice = salePrice.mul(quantity).toDecimal(2);//商品金额小计
			splitPrice = salePrice
			commonCartItems[commonIndex].salePrice = salePrice;
			
			totalSalePrice = _this.toCommonPrice(totalSalePrice,2,1); //转换
			commonCartItems[commonIndex].totalSalePrice = totalSalePrice;
			var promotionId = commonCartItems[commonIndex].promotionId;//促销id
			var promotionDisplays = commonCartItems[commonIndex].promotionDisplays;//获取商品的优惠方式
			var promotionFlag = _this.getPromotionType(promotionId,promotionDisplays[0]);//0为无促销，1为促销，2为促销预执行,3为单品买赠
			if(promotionId==0){//当商品对象不存在促销id时
				if(promotionDisplays && promotionDisplays.length>0){
					promotionId = promotionDisplays[0].id;
				}
			}
			var inventoryStatus = commonCartItems[commonIndex].inventoryStatus;//库存状态 2表示删除 0 表示正常 1表示库存不足
			var isChecked = commonCartItems[commonIndex].isChecked;//当前商品的选中状态
			if(isChecked==1){//如果有一个商品被选中则不隐藏“删除选中商品”按钮
				isHideChoseDel = 0;
			}
			if(inventoryStatus==2){//当库存状态标记为删除时，该商品加入到非正常商品中
				notNormalList.push(commonCartItems[commonIndex]);
				continue;
			}
			if (commonCartItems[commonIndex].isAuctionProduct) {
				notNormalList.push(commonCartItems[commonIndex]);
				continue;
			}
			var has = promotionId in commonMap;
			if(has){
				if(commonMap[promotionId].promotionCheckAll==1  && isChecked==0){//只有旧的“促销全选”属性为全选，当前明细未被选中时才更换“促销全选”属性为非全选
					commonMap[promotionId].promotionCheckAll = isChecked;
				}
				if(isChecked==1){//如果选中才统计显示金额
					//当前促销维度下的金额统计
					commonMap[promotionId].totalPromotionPrice = Number((commonMap[promotionId].totalPromotionPrice).add(salePrice.mul(quantity)));
					//返利总额统计
					commonMap[promotionId].returnTotalRateValue = Number((commonMap[promotionId].returnTotalRateValue).add(commonCartItems[commonIndex].returnRateValue).toDecimal(0,false));
				}
				commonMap[promotionId].commonArray.push(commonCartItems[commonIndex]);
			}else{
				var innd = 0
				var returnTotalRateValue = 0;//返利总额
				if(isChecked==1){
					returnTotalRateValue = commonCartItems[commonIndex].returnRateValue;//现在价格 
				}else{
					salePrice = 0;
				}
				var commonObj = new Object();
				var commonArray = new Array();
				commonArray.push(commonCartItems[commonIndex]);
				commonObj.totalPromotionPrice = new Number(salePrice).mul(quantity);//当前促销维度下的金额统计
				commonObj.returnTotalRateValue = returnTotalRateValue;
				commonObj.promotionFlag = promotionFlag;
				commonObj.promotionCheckAll = isChecked;
				commonObj.commonArray = commonArray;
				commonMap[promotionId] = commonObj;
			}
			totalSalePrice = _this.toCommonPrice(totalSalePrice,2,1); //转换
			commonCartItems[commonIndex].totalSalePrice = totalSalePrice;
			splitPrice = _this.toCommonPrice(splitPrice,2,1); //转换
			commonCartItems[commonIndex].salePrice = splitPrice;
		}
		obj.commonMap = commonMap;
		obj.notNormalList = notNormalList;
		obj.isHideChoseDel = isHideChoseDel;
		return obj;
	},
	/**
	 * 根据促销id，促销内容获取促销类型
	 * @param promotionId 促销id
	 * @param promotionDisplay 促销内容
	 * return int 0为无促销，1为促销，2为促销预执行,3为单品买赠
	 */
	getPromotionType : function(promotionId,promotionDisplay){
		if(promotionDisplay){//当促销内容存在时
			var type = promotionDisplay.type;//促销类型1:买2:满额
			var actionType = promotionDisplay.actionType;//行为类型5:最低价打折1：赠2：减
			var isSingleRangeId = promotionDisplay.isSingleRangeId;//商品是集合还是单品
			if(type==1 && actionType==1 && isSingleRangeId==1){
				return 3;
			}
		}
		if(promotionId==0){//当商品对象不存在促销id时
			if(promotionDisplay){//当促销存在时，其为预促销
				return 2;
			}
			return 0;
		}
		return 1;
	},
	/**
	 * 把赠品转换成key为促销id的map
	 * @param presentCartItems 赠品集合
	 * return presentMap
	 */
	toPresentMap : function(presentCartItems){
		//定义一个存储赠品的map
		var presentMap = {}; 
		//根据促销id把赠品转换为map集合
		for(var presentIndex in presentCartItems){
			if (!presentCartItems.hasOwnProperty(presentIndex)) {//判定当前对象是否具有指定名称的属性。
				continue;
			}
			var promotionId = presentCartItems[presentIndex].promotionId;//促销id
			var has = promotionId in presentMap;//判定当前map中是否存在这个key
			if(has){
				presentMap[promotionId].push(presentCartItems[presentIndex]);
			}else{
				var presentArray = new Array();
				presentArray.push(presentCartItems[presentIndex]);
				presentMap[promotionId] = presentArray;
			}
		}
		return presentMap;
	},
	/**
	 * 合并商品赠品的map集合
	 * @param commonMap 商品map key为促销id
	 * @param presentMap 赠品map key为促销id
	 * @param minusAmountMap 满减对应金额map
	 * @param minDiscountMap 打折对应金额map
	 * return listCart(Array)
	 */
	combinedMap : function(commonMap,presentMap,minusAmountMap,minDiscountMap,fullQuantityDiscountMap){
		var _this = this;
		//定义一个cart对象的集合
		var listCart = new Array();
		//循环map，组装cart集合
		for(var i in commonMap){
			if (!commonMap.hasOwnProperty(i)) {//判定当前对象是否具有指定名称的属性。
				continue;
			}
			var mapCart = new Object();//以促销id绑定的商品集合
			mapCart.promotionFlag = commonMap[i].promotionFlag;//促销类型0为无促销，1为促销，2为促销预执行,3为单品买赠
			mapCart.commonCartItems = commonMap[i].commonArray;
			mapCart.promotionCheckAll = commonMap[i].promotionCheckAll;
			mapCart.presentCartItems = presentMap[i];
			mapCart.promotionId = i;
			var minusAmount = 0;//满减对应金额
			if(minusAmountMap[i]){
					minusAmount = minusAmountMap[i];
			}
			var minDiscount = 0;//打折对应减去金额
			if(minDiscountMap[i]){
				minDiscount =  minDiscountMap[i];
			}
			var fullQuantityDiscount = 0;//
			if (fullQuantityDiscountMap[i]) {
			    fullQuantityDiscount = fullQuantityDiscountMap[i];
			}
			//当前促销维度下的商品总计金额
			mapCart.nowTotalPrice = (commonMap[i].totalPromotionPrice).subtract(minusAmount).subtract(minDiscount).subtract(fullQuantityDiscount).toDecimal(2);
			mapCart.nowTotalPrice = _this.toCommonPrice(mapCart.nowTotalPrice,2,1);
			mapCart.returnTotalRateValue = commonMap[i].returnTotalRateValue;
			listCart.push(mapCart);
		}
		return listCart;
	},	
	insertHtmlDom: function(_htmlDom){
		$(".center div.centerBox").html(_htmlDom);
	},
	modifyQuantity: function(_domEle){
		var _this = this;
		_domEle = $(_domEle);
		_cartRow = _domEle.closest('[name="cartRow"]');
		var action = _domEle.attr('action');
		var quantity = _cartRow.find('input[name="quantity"]').val();
		if(!quantity || !(/^\d+$/.test(quantity))){
			if(action == 'goto'){
				//_this.showLocalExceptionQuantity(_domEle, '商品数量输入错误', false);
				_cartRow.find('input[name="quantity"]').val(_cartRow.attr('quantity'));//还原数量
				_this.showErrorAlert('商品数量输入错误', '温馨提示',function(){
					$('[name="errorAlert"][clone]').find(".buttonOk").show();
				},null, true);
				return ;
			} else if(action == 'decrease' || action == 'increase'){
				quantity = 1;
			}
		}
		quantity = parseInt(quantity, 10);
		var num = 0;
		if(action == 'decrease'){
			num = quantity - 1;
		} else if (action == 'increase'){
			num = quantity + 1;
		} else if (action == 'goto'){
			if(quantity == _cartRow.attr('quantity')){
				return;
			}
			num = quantity;
		}
		if(num == 0){
			_cartRow.find('input[name="quantity"]').val(_cartRow.attr('quantity'));//还原数量
			_this.delItem(_domEle);
			return ;
		}
		var item = new Object();
		item.process = 5;
		item.quantity = num;
		item.itemKey = _cartRow.attr('itemKey');
		_this.ajaxCart($.toJSON(item), function(_cart){
		}, false);
		return false;
	},
	modifyPackage: function(_domEle) {
	    var _this = this;
        _domEle = $(_domEle);
        _cartRow = _domEle.closest('[name="cartRow"]');
        var packageId = 0;
        if (!_domEle.children(".item").hasClass("on")) {
            packageId = _domEle.attr("packageId");
        }
        var item = new Object();
        item.process = 10;
        item.packageId = packageId;
        item.itemKey = _cartRow.attr('itemKey');
        _this.ajaxCart($.toJSON(item), function(_cart){
        }, false);
        return false;
	},
	showErrorAlert: function(errMsg, errTitle,cssFun,callback, isClearTimeout){
		var _this = this;
		var errNoticeTimeout;
		// 清除错误提示
		_this.closeErrorAlert(errNoticeTimeout);
		// 错误提示
		if(errMsg){
			var errorAlert = $('[name="errorAlert"]').clone(true).attr("clone", true).appendTo("body");
			//控制样式
			if(typeof cssFun === 'function'){
				cssFun();
			}
			errorAlert.find('[name="errorMsg"]').html(errMsg);
			errorAlert.find('[name="errorTitle"]').html(errTitle?errTitle:"");
			//确认按钮
			errorAlert.find('a.buttonOk').removeAttr('onclick').unbind( "click" ).bind('click',function(){
				_this.closeErrorAlert(errNoticeTimeout);
				if(typeof callback === 'function'){
					callback();
				}
			});
			//关闭按钮
			errorAlert.find('[name="close"]').click(function(){
				_this.closeErrorAlert(errNoticeTimeout);
			});
			errorAlert.show();
			if(isClearTimeout){
				errNoticeTimeout = setTimeout(function(){
					_this.closeErrorAlert(errNoticeTimeout);
				}, 5000);
			}
		}
	},
	closeErrorAlert: function(errNoticeTimeout){
		try{
			if(errNoticeTimeout){
				window.clearTimeout(errNoticeTimeout);
			}
		} catch(e){}
		$('[name="errorAlert"][clone]').remove();
	},
	goConfirm: function(){
		var _this = this;
		if($('[noDisappear="true"]').length > 0){
			$("html,body").animate({scrollTop:$('[noDisappear="true"]:eq(0)').closest('[name="cartRow"]').offset().top});
			return ;
		}
		var item = new Object();
		item.process = 4;
		var tabName = "购物车结算";
		global_variables.analytical('购物车结算',tabName);
		_this.ajaxCart($.toJSON(item), function(_cart){
			if(_cart && _cart.userId==0){
				_this.user_login_reg('','cartJS.goConfirmLogin('+ _cart.totalCount +')','');
			}
		}, true);
	},
	//立即结算登录回调函数
	goConfirmLogin: function(totalCount){
		var _this = this;
		if($('[noDisappear="true"]').length > 0){
			$("html,body").animate({scrollTop:$('[noDisappear="true"]:eq(0)').closest('[name="cartRow"]').offset().top});
			return ;
		}
		var item = new Object();
		item.process = 4;
		item.totalCount = totalCount;
		_this.ajaxCart($.toJSON(item), function(_cart){
		}, true);
	},
	//选中当前tab下的所有checkbox
	choseAll : function (o){
		var _this = this;
		var item = new Object();
		item.process = 9;
		if($(o).is(":checked")){
			item.isChecked = 1;
		}else{
			item.isChecked = 0;
		}
		_this.ajaxCart($.toJSON(item), function(_cart){
		}, false);
		return false;
	},
	//选中商品
	selectItems : function (listKey,isChecked){
		var _this = this;
		var item = new Object();
		item.process = 8;
		item.isChecked = isChecked;
		item.listKey = listKey;
		_this.ajaxCart($.toJSON(item), function(_cart){
		}, false);
		return false;
	},
	//选中当前促销下的checkbox
	chosePromotion : function (o){
		var _this = this;
		_o = $(o);
		var promotionId = _o.val();
		var isChecked = 0;
		if(_o.is(":checked")){
			isChecked = 1;
		}
		var listKey = new Array();
		$('input[name="'+promotionId+'"]').each(function(){  
			listKey.push($(this).val());  
		});  
		_this.selectItems($.toJSON(listKey),isChecked);
		return false;
	},
	//选中当前商品的checkbox
	choseItem : function (o){
		var _this = this;
		_o = $(o);
		_cartRow = _o.closest('[name="cartRow"]');
		var isChecked = 0;
		if(_o.is(":checked")){
			isChecked = 1;
		}
		var listKey = new Array();
		listKey.push(_cartRow.attr('itemKey'));
		_this.selectItems($.toJSON(listKey),isChecked);
		return false;
	},
	// 删除商品记录
	deleteItem: function(_domEle,listKey){
		var _this = this;
		//_this.ajaxCartLog(null, 0);
		var item = new Object();
		item.process = 2;// deleteItem
		item.listKey =listKey;
		_this.ajaxCart($.toJSON(item), function(_cart){
		}, false);
		return false;
	},
	//单商品删除
	delItem : function(e){
		var _this = this;
		$(".cartPop01").css({
			left : $(e).offset().left -60,
			top : $(e).offset().top + 20
		}).show();
		$(".btn02").click(function(){
			$(this).parents(".cartPop,.cartPop01").hide();
		});
		$(".btn01").click(function(){
			$(this).parents(".cartPop,.cartPop01").hide();
			var listKey = new Array();
			var _cartRow = $(e).closest('[name="cartRow"]');
			listKey.push(_cartRow.attr('itemKey'));
			_this.deleteItem(e,$.toJSON(listKey));
		});
	},
	//删除选中商品
	delChose : function(e){
		var _this = this;
		$(".cartPop").css({
			left : $(e).offset().left,
			top : $(e).offset().top + 20
		}).show();
		$(".btn02").click(function(){
			$(this).parents(".cartPop,.cartPop01").hide();
		});
		$(".close").click(function(){
			$(this).parents(".cartPop,.cartPop01").hide();
		});
		$(".btn01").click(function(){
			$(this).parents(".cartPop,.cartPop01").hide();
			var listKey = new Array();
			$('input[id="choseItem"]:checked').each(function(){  
				listKey.push($(this).val());  
			}); 
			_this.deleteItem(e,$.toJSON(listKey));
		});
	},
	//登录
	user_login_reg: function(retUrl,callback, param) {
	    $.secooframe({
    		frameurl: "https://passport.secoo.com/login/fastRegLogin_new.jsp?returnUrl="+retUrl+"&parentLocal=yes&callback=" + callback + "",
    		css: {
            	 marginTop: '-190px',
     			marginLeft : '-335px',
                 cursor: 'pointer',
     			width:		'670px',
                 left: '50%',
                 background:'transparent',
                 border: '0px'
    		},
    		style: "width:670px;height:480px;border:0;"
	    });
	},
	//购物车登录回调函数
	setUserInfo : function(){
		var _this = this;
		$("#loginButton").html();
		$("#loginButton").html(cookieJS.getCookie('ume')+'<a href="###" onClick="cartJS.logout();return false;">退出</a>');
		var item = new Object();
		item.process = 0;// deleteItem
		_this.ajaxCart($.toJSON(item), function(_cart){
		}, false);
		return false;
	},
	//退出
	logout : function() {
		var _this = this;
		try {
			cookieJS.delCookie('UID');
			cookieJS.delCookie('nickname');
			cookieJS.delCookie('ume');
			cookieJS.delCookie('upk');
			cookieJS.delCookie('hiddentstr');
			cookieJS.delCookie('cps_source_QQCB');
			cookieJS.delCookie('dl_close');
			cookieJS.delCookie('qd');
			cookieJS.delCookie('qsm');
			cookieJS.delCookie('qhs');
			cookieJS.delCookie('jfu');
			cookieJS.delCookie('xunLeiLoginType');
			window.location.href="https://passport.secoo.com/";
		} catch (e) {
			_this.showErrorAlert('注销用户信息失败,请重新尝试', '温馨提示',function(){
				$('[name="errorAlert"][clone]').find(".buttonOk").show();
			},null, true);
		}
	},
	getParameters: function(queryString){
		var parameters = {};
		if(!queryString || queryString.charAt(0) != '?' || queryString.length < 2){
			return ;
		}
		queryString = queryString.slice(1, queryString.length);
		var queryArr = queryString.split('&', -1);
		if(!queryArr){
			return ;
		}
		for(var i in queryArr){
			var keyValue = queryArr[i];
			if(keyValue && typeof(keyValue) === 'string'){
				var querys = keyValue.split('=');
				parameters[querys[0]] = querys[1];
			}
		}
		return parameters;
	}
};

var _zpq = _zpq || [];
var _mvq = _mvq || [];
var pushJS = {
	isPush: false,
	/**
	 * 百分点
	 * @param cartItems
	 * @param totalNowPrice
	 */
	//baifendian: function(cartItems, totalNowPrice) {
	//	bfdCart(cartItems, totalNowPrice);
	//},
	/**
	 * 晶赞 
	 * @param productIds
	 * @param totalPrice
	 * @param totalNum
	 */
	jinzan: function(productIds, totalNowPrice, totalNum) {
		_zpq.push(['_setPageID','760']);
		_zpq.push(['_setPageType','mycartPage']);
		_zpq.push(['_setParams',productIds, totalNowPrice, totalNum]);
		_zpq.push(['_setAccount','219']);
		
		var zp = document.createElement('script'); zp.type = 'text/javascript'; zp.async = true;
		zp.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'cdn.zampda.net/s.js';
		var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(zp, s);
	},
	/**
	 * 品友
	 * @param totalNowPrice
	 * @param productIds
	 */
	pinyou: function(totalNowPrice, productIds) {
		!function(w,d,e){
		var _money = totalNowPrice;
		var _productList = productIds;
		var b=location.href,c=d.referrer,f,s,g=d.cookie,h=g.match(/(^|;)\s*ipycookie=([^;]*)/),i=g.match(/(^|;)\s*ipysession=([^;]*)/);if (w.parent!=w){f=b;b=c;c=f;};u='//stats.ipinyou.com/cvt?a='+e('AL.zL.K1ekQX1ZqkN0ZEBnFEayA0')+'&c='+e(h?h[2]:'')+'&s='+e(i?i[2].match(/jump\%3D(\d+)/)[1]:'')+'&u='+e(b)+'&r='+e(c)+'&rd='+(new Date()).getTime()+'&Money='+e(_money)+'&ProductList='+e(_productList)+'&e=';function _(){if(!d.body){setTimeout(_(),100);}else{s= d.createElement('script');s.src = u;d.body.insertBefore(s,d.body.firstChild);}}_();
		}(window,document,encodeURIComponent);
	},
	/**
	 * mediav
	 * @param productIds
	 */
	mediav: function(productIds) {
		_mvq.push(['$setAccount', 'm-20592-0']);
		_mvq.push(['$setGeneral', 'cartview', '',  '',  '']);
		_mvq.push(['$logConversion']);
		_mvq.push(['$addItem', '', productIds, '','']);
		_mvq.push(['$logData']);
	}
};

(function(){
	//回到顶部
	var $backToTopTxt = "回到顶部",
		$backToTopEle = $('<div class="fixBack"></div>').appendTo($("body")),
		goTop = $('<a href="###" onclick="$(\'html,body\').animate({ scrollTop: 0 }, 700); " class="fbtn go-top">返回顶部</a>'),
		sur = $('<a href="http://sa.secoo.com/activity/survey/cart_surveys.jsp" class="fbtn survey" target="_blank">有奖调查</a>'),
		$backToTopFun = function() {
			var st = $(document).scrollTop(), winh = $(window).height();
			(st > 50)? goTop.css({display:"block"}): goTop.css({display:"none"});
			//Fix position in IE6
			if (!window.XMLHttpRequest) {
				$backToTopEle.css("top", st + winh - 96);
			}
		};
		//sur.appendTo($backToTopEle);
		goTop.appendTo($backToTopEle);
		$(window).bind("scroll", $backToTopFun);
		$(function() { $backToTopFun(); });
})();