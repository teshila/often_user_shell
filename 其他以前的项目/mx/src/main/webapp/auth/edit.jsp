<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="https://cdn.bootcss.com/jquery/3.4.0/jquery.min.js"></script>
<script src="statics/editor/ckeditor.js" type="text/javascript"></script>
<script src="statics/editor/config.js" type="text/javascript"></script> 
</head>
<body>
	<form method="post" enctype="multipart/form-data">
		<textarea name="content" id="editor"></textarea>
	</form>
	<script type="text/javascript">
	
		$(function() {
			 var editor = CKEDITOR.replace('editor', {
				removePlugins : 'elementspath,resize',
				codeSnippet_theme : 'zenburn',
			}); 
	
	
		})
	</script>
</body>
</html>
