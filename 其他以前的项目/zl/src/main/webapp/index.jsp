<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>M</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="statics/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="statics/ckeditor/ckeditor.js"></script>
<!-- https://blog.csdn.net/mmake1994/article/details/83781560 -->
<!-- https://www.jianshu.com/p/c836c7e1a98e -->
<!-- https://my.oschina.net/jast90/blog/295636?p=1
https://www.codepalace.xyz/article_show/34
https://www.cnblogs.com/MrSaver/p/6597278.html -->
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<form method="post">
		<textarea name="editor1"  rows="8" class="form-control"></textarea>
	</form>
	<script type="text/javascript">
		$(window).load(function(){
			CKEDITOR.replace('editor1', {
                filebrowserUploadUrl : "<%=basePath %>upload/ckeditImg.do?id=${param.id}",
                filebrowserUploadMethod : 'form',
                language : 'zh-cn',
                height: 800
            });
		});
	</script>
</body>
</html>
