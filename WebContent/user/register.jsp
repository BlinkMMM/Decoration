<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<base href="<%=basePath%>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>注册</title>  
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>  
</head>
 
<body>
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:80px;"></div>
            <div class="media media-y margin-big-bottom">           
            </div>         
            <form action="user/register" method="post">  
            <div class="panel loginbox">
                <div class="text-center margin-big padding-big-top"><h1>用户注册</h1></div>
                <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="text" class="input input-big"  name="userName" placeholder="注册账号  2~8位" data-validate="required:请输入用户名">
                            <span class="icon icon-user margin-small"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="password"  class="input input-big" name="password" placeholder="输入密码  6~12位" data-validate="required:请输入密码"/>
                            <span class="icon icon-key margin-small"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <select  class="input input-big" name="jobType">
                            	<option value="施工人员">施工人员</option>
                            	<option value="采购员">采购员</option>
                            	<option value="财务">财务</option>
                            	<option value="项目经理">项目经理</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="text" class="input input-big" name="telephone" placeholder="手机号码" />
                            <span class="icon icon-key margin-small"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="text" class="input input-big" name="email" placeholder="邮箱" />
                            <span class="icon icon-key margin-small"></span>
                        </div>
                    </div>
                    
                </div>
                <div style="padding:30px;"><input type="submit" class="button button-block bg-main text-big input-big" value="注册"></div>
            </div>
            </form>  
            <c:if test="${result == false }">
				<div class="alert alert-danger" role="alert" style="margin-top:5px" id="tip">${reason }</div>
			</c:if>        
        </div>
    </div>
</div>

</body>
</html>