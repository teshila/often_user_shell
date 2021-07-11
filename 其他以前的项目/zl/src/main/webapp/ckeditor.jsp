<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>/admin/ckeditor/">
    
    <title>在线编辑器</title>
    <script type="text/javascript" src="ckeditor/ckeditor.js"></script>
	 <link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.min.css">
	<style type="text/css">
	#btn{
		margin-top: 10px;
	}
	</style>
  </head>
  
  <body>
  	<div  class="panel panel-default"> 
	  	<div class="panel-body">
		  	<form action=""  class="form-horizontal" onsubmit="return save();" method="post" id="myform" >
			  		<label for="firstname" class="control-label">标题</label>
			  		<input type="text" class="form-control" id="title" placeholder="请输入标题,在网站首页显示">
			  		 <label for="firstname" class="control-label">内容</label>
			    	<textarea id="content" name="content" cols="20" rows="2" class="ckeditor"></textarea>
			    	<div id="btn"> 
			    		<input type="button" class="btn btn-default" value="取消"/>
			    		
		    			<input type="submit" class="btn btn-default" value="保存"/>
			    		 
			    	</div>
			    
		    </form>
	    </div>
     </div>
  </body>
  <script type="text/javascript">
    var editor = CKEDITOR.replace('content', {
	"filebrowserUploadUrl" : "<%=basePath %>upload/ckeditImg.do?id=${param.id}",
	 uiColor: '#ffffff', //工具栏默认白色
	 enterMode :CKEDITOR.ENTER_BR //换行直接加br
	});
	
	function save(){
        editor.updateElement(); //非常重要的一句代码
        //前台验证工作
        //提交到后台
        return true ;
    }
     
</script>
</html>
