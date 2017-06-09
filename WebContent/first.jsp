<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<title></title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
</head>
<style type="text/css">
.first{
	width:100%; 
	height:100%; 
	position:relative; 
	background:url(/decoration/images/first.jpg) no-repeat;
	background-size:cover;
}
.title1{
	position:absolute;
	text-align:center;
	padding-top:50px;
	margin-left:300px; 
	font-family: 幼圆;
	font-size: 65px;
	font-style: italic;
	 
}
.title2{
	position:absolute;
	padding-top:150px;
	margin-left:300px; 
	font-family: 幼圆;
	font-size: 65px;
	font-style: italic;
	 
}
.text{
	padding-top:20px;
	font-family:宋体;
	font-size: 25px;
	line-height: 30px;
}
</style>
<body>

<div class="first">
<span class="title1"><b>欢迎使用</b></span>
<div class="title2"><b>豪盛家装公司成本与材料监控系统！</b></div>
</div>
	
</body>
</html>