<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
	<base href="<%=basePath%>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    
    <meta http-equiv="pragma" content="no-cache">  
	<meta http-equiv="cache-control" content="no-cache">  
	<meta http-equiv="expires" content="0">   
    <title>后台管理中心</title>  
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>   
    <style type="text/css">
    	#loginuser{
    		color:yellow;
    		font-size:25px;
    		padding-top:15px;
    		/* padding-left:1000px; */
    		float:right;
    		font-weight:bold;
    	}
    
    </style>
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />后台管理中心</h1>
  </div>
  <div class="head-l">
  
  <a class="button button-little bg-green" href="user/reLogin">
  <span class="icon-home"></span> 登录
  </a> &nbsp;&nbsp;
  
  <a href="user/changePassInfo" class="button button-little bg-blue">
  <span class="icon-wrench"></span> 修改密码
  </a> &nbsp;&nbsp;
  
  <a class="button button-little bg-red" href="user/logout">
  <span class="icon-power-off"></span> 退出登录
  </a> </div>
  
  <div id="loginuser">Hello! ${loginUser.userName}&nbsp;&nbsp;职责：${loginUser.jobType}&nbsp;&nbsp;</div>
  
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
  <c:if test="${loginUser.jobType == '项目经理'}">
	  <h2><span class="icon-user"></span>项目管理</h2>
	  <ul style="display:block">
	    <li><a href="material/buy"><span class="icon-caret-right" ></span>购买材料登记</a></li>
	    <li><a href="material/enter"><span class="icon-caret-right"></span>材料进场登记</a></li>
	    <li><a href="material/use"><span class="icon-caret-right"></span>材料使用登记</a></li>  
	    <li><a href="schedule/schedule"><span class="icon-caret-right"></span>工程进度登记</a></li>   
	    <li><a href="work/record"><span class="icon-caret-right"></span>工勤统计</a></li>     
	  </ul>   
	  <h2><span class="icon-pencil-square-o"></span>成本管理</h2>
	  <ul style="display:block">
	    <li><a href="cost/mat"><span class="icon-caret-right"></span>材料成本</a></li>
	    <li><a href="cost/wage"><span class="icon-caret-right"></span>薪水成本</a></li>
	    <li><a href="cost/total"><span class="icon-caret-right"></span>总成本</a></li>        
	  </ul>  
  </c:if>
  <c:if test="${loginUser.jobType == '施工人员'}">
  	  <h2><span class="icon-user"></span>项目管理</h2>
	  <ul style="display:block">
	    <li><a href="material/enter"><span class="icon-caret-right"></span>材料进场登记</a></li>
	    <li><a href="material/use"><span class="icon-caret-right"></span>材料使用登记</a></li>  
	    <li><a href="schedule/schedule"><span class="icon-caret-right"></span>工程进度登记</a></li>   
	    <li><a href="work/record"><span class="icon-caret-right"></span>工勤统计</a></li>     
	  </ul>     
  </c:if>
  <c:if test="${loginUser.jobType == '采购员'}">
  	  <h2><span class="icon-user"></span>项目管理</h2>
	  <ul style="display:block">
	    <li><a href="material/buy"><span class="icon-caret-right" ></span>购买材料登记</a></li> 
	  </ul>   	
  </c:if>
  <c:if test="${loginUser.jobType == '财务'}"> 	  
	  <h2><span class="icon-pencil-square-o"></span>成本管理</h2>
	  <ul style="display:block">
	    <li><a href="cost/mat"><span class="icon-caret-right"></span>材料成本</a></li>
	    <li><a href="cost/wage"><span class="icon-caret-right"></span>薪水成本</a></li>
	    <li><a href="cost/total"><span class="icon-caret-right"></span>总成本</a></li>        
	  </ul>  
  </c:if>
  <!-- <h2><span class="icon-user"></span>项目管理</h2>
  <ul style="display:block">
    <li><a href="material/buy"><span class="icon-caret-right" ></span>购买材料登记</a></li>
    <li><a href="material/enter"><span class="icon-caret-right"></span>材料进场登记</a></li>
    <li><a href="material/use"><span class="icon-caret-right"></span>材料使用登记</a></li>  
    <li><a href="schedule/schedule"><span class="icon-caret-right"></span>工程进度登记</a></li>   
    <li><a href="work/record"><span class="icon-caret-right"></span>工勤统计</a></li>     
  </ul>   
  <h2><span class="icon-pencil-square-o"></span>成本管理</h2>
  <ul style="display:block">
    <li><a href="cost/mat"><span class="icon-caret-right"></span>材料成本</a></li>
    <li><a href="cost/wage"><span class="icon-caret-right"></span>薪水成本</a></li>
    <li><a href="cost/total"><span class="icon-caret-right"></span>总成本</a></li>        
  </ul>   -->
  
</div>
<div>
</div>

<div class="admin">
<jsp:include page="/first.jsp"></jsp:include>
<!--========显示购买材料界面  ==========-->
<c:if test="${page == 'buy'}">
<jsp:include page="material/buy.jsp"></jsp:include>
</c:if>
<!--========显示材料进场界面  ==========-->
<c:if test="${page == 'enter'}">
<jsp:include page="material/enter.jsp"></jsp:include>
</c:if>
<!--========显示材料使用界面  ==========-->
<c:if test="${page == 'use'}">
<jsp:include page="material/use.jsp"></jsp:include>
</c:if>
<!--========显示员工考勤界面  ==========-->
<c:if test="${page == 'record'}">
<jsp:include page="workrecord/record.jsp"></jsp:include>
</c:if>
<!--========显示工程进度界面  ==========-->
<c:if test="${page == 'schedule'}">
<jsp:include page="schedule/schedule.jsp"></jsp:include>
</c:if>
<!--========显示材料成本界面  ==========-->
<c:if test="${page == 'matCost'}">
<jsp:include page="cost/matCost.jsp"></jsp:include>
</c:if>
<!--========显示员工工资成本界面  ==========-->
<c:if test="${page == 'wageCost'}">
<jsp:include page="cost/wageCost.jsp"></jsp:include>
</c:if>
<!--========显示总成本界面  ==========-->
<c:if test="${page == 'totalCost'}">
<jsp:include page="cost/totalCost.jsp"></jsp:include>
</c:if>
<!--========显示购买界面  ==========-->
<c:if test="${page == 'addInfo'}">
<jsp:include page="material/addBuy.jsp"></jsp:include>
</c:if>
<!--========显示修改购买界面  ==========-->
<c:if test="${page == 'updateInfo'}">
<jsp:include page="material/updateBuy.jsp"></jsp:include>
</c:if>
<!--========显示进场界面  ==========-->
<c:if test="${page == 'enterAddInfo'}">
<jsp:include page="material/addEnter.jsp"></jsp:include>
</c:if>
<!--========显示修改进场界面  ==========-->
<c:if test="${page == 'enterUpdateInfo'}">
<jsp:include page="material/updateEnter.jsp"></jsp:include>
</c:if>
<!--========显示材料使用界面  ==========-->
<c:if test="${page == 'useAddInfo'}">
<jsp:include page="material/addUse.jsp"></jsp:include>
</c:if>
<!--========显示修改材料使用界面  ==========-->
<c:if test="${page == 'useUpdateInfo'}">
<jsp:include page="material/updateUse.jsp"></jsp:include>
</c:if>
<!--========显示修改密码界面  ==========-->
<c:if test="${page == 'changePass'}">
<jsp:include page="user/changePass.jsp"></jsp:include>
</c:if>
<!--========显示添加考勤界面  ==========-->
<c:if test="${page == 'recordAddInfo'}">
<jsp:include page="workrecord/addRecord.jsp"></jsp:include>
</c:if>
<!--========显示添加工程进度界面  ==========-->
<c:if test="${page == 'scheduleAddInfo'}">
<jsp:include page="schedule/addSchedule.jsp"></jsp:include>
</c:if>
<!--========显示更新工程进度界面  ==========-->
<c:if test="${page == 'scheduleUpdateInfo'}">
<jsp:include page="schedule/updateSchedule.jsp"></jsp:include>
</c:if>
<!--========显示审核工程进度界面  ==========-->
<c:if test="${page == 'checkScheduleInfo'}">
<jsp:include page="schedule/checkSchedule.jsp"></jsp:include>
</c:if>
<!--========显示错误信息显示界面  ==========-->
<c:if test="${page == 'errorInfo'}">
<jsp:include page="errorInfo.jsp"></jsp:include>
</c:if>

</div>

<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>

</body>
</html>