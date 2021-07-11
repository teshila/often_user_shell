define(['require', 'jquery', 'approvecommon','approve/approve' ], function(require, $, approvecommon,approve) {
	var getParameter = function(name){
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r!=null) { 
		   return unescape(r[2]); 
		} 
		return null; 
	};
	var torch = {
		shareholder_datarender : function(event, obj) {
		},
		noShareholder_datarender : function(event, obj) {
		},
		mainPerson_datarender : function(event, obj) {
		},
		gid : getParameter('gid'),
		query_fromNames : [ 'entLinkInfo_Form', 'processAIEntInfo_Form', 'processAIEntBasicInfo_Form', 'financeInfo_Form', 'entcontactInfo_Form', 'shareholder_Form', 'noShareholder_Form', 'mainPerson_Form' ],

		processAllInfoSure : function() {
			$("div[name='entLinkInfo_Form']").formpanel("option", "dataurl", "../../../torch/service.do?fid=processAIFuncId&wid=entLinkInfo&gid=" + torch.gid);
			$("div[name='entLinkInfo_Form']").formpanel("reload");
			$("div[name='processAIEntInfo_Form']").formpanel("option", "dataurl", "../../../torch/service.do?fid=processAIFuncId&wid=processAIEntInfo&gid=" + torch.gid);
			$("div[name='processAIEntInfo_Form']").formpanel("reload");
			$("div[name='processAIEntBasicInfo_Form']").formpanel("option", "dataurl", "../../../torch/service.do?fid=processAIFuncId&wid=processAIEntBasicInfo&gid=" + torch.gid);
			$("div[name='processAIEntBasicInfo_Form']").formpanel("reload");
			$("div[name='financeInfo_Form']").formpanel("option", "dataurl", "../../../torch/service.do?fid=processAIFuncId&wid=financeInfo&gid=" + torch.gid);
			$("div[name='financeInfo_Form']").formpanel("reload");
			$("div[name='entcontactInfo_Form']").formpanel("option", "dataurl", "../../../torch/service.do?fid=processAIFuncId&wid=entcontactInfo&gid=" + torch.gid);
			$("div[name='entcontactInfo_Form']").formpanel("reload");
			$("div[name='shareholder_Grid']").gridpanel("option", "dataurl", "../../../torch/service.do?fid=processAIFuncId&wid=shareholder&gid=" + torch.gid);
			$("div[name='shareholder_Grid']").gridpanel("query", [ "shareholder_Form" ]);
			$("div[name='noShareholder_Grid']").gridpanel("option", "dataurl", "../../../torch/service.do?fid=processAIFuncId&wid=noShareholder&gid=" + torch.gid);
			$("div[name='noShareholder_Grid']").gridpanel("query", [ "noShareholder_Form" ]);
			$("div[name='mainPerson_Grid']").gridpanel("option", "dataurl", "../../../torch/service.do?fid=processAIFuncId&wid=mainPerson&gid=" + torch.gid);
			$("div[name='mainPerson_Grid']").gridpanel("query", [ "mainPerson_Form" ]);
		},
		processAllInfoReset : function() {
			for (x in torch.query_fromNames) {
				$("div[name='" + torch.query_fromNames[x] + "']").formpanel("reset");
			}
		},
		
		_init : function() {
			require(['domReady'], function (domReady) {
				  domReady(function () {
					    $("div[name='_Form']").gridpanel("option", "datarender", torch.shareholder_datarender);
						$("div[name='_Form']").gridpanel("reload");
						$("div[name='_Form']").gridpanel("option", "datarender", torch.noShareholder_datarender);
						$("div[name='_Form']").gridpanel("reload");
						$("div[name='_Form']").gridpanel("option", "datarender", torch.mainPerson_datarender);
						$("div[name='_Form']").gridpanel("reload");

						$("div[name='processAllInfo_query_button']").off('click').click(torch.processAllInfoSure);
						$("div[name='processAllInfo_reset_button']").off('click').click(torch.processAllInfoReset);
						torch.processAllInfoSure();
						torch.showReqAllPic();
						
				  });
				});			
			
		},
		
		showReqAllPic:function(){
			var url = '../../../approve/reqhistory/getReqPicInfo.do';
			$.ajax({
				url:url,
				data:{
					gid:torch.gid
				},
				type:'post',
				dataType:'json',
				success:function(data){
					var fileId,refText;
					if(data!=null && data.length>0){
						for(var i=0;i<data.length;i++){
							fileId = data[i].fileId;
							refText = data[i].refText;
							var html = "";//先如下定义，这样子的拼接性能会比var html= "<div class='categoryPic'>"+refText+"<img name='upfile' width='100px' height='100px'  src='../../../upload/showPic.do?fileId="+fileId+"' /></div>";//性能要高40%
							html = "<div class='categoryPic'>"+refText+"<img name='upfile' width='100px' height='100px'  src='../../../upload/showPic.do?fileId="+fileId+"' /></div>";
							$("#reqAllPicInfo").append(html);
						}
					}
				},
				error:function(){
					
					alert(2);
				}
			});
		}
		
	};
	
	torch._init();
	return torch;

});
