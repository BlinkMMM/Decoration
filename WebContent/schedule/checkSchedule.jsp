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
<title></title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head" id="update"><strong><span class="icon-pencil-square-o"></span>审核项目进度</strong></div>
  <div class="body-content">	
    <form method="post" class="form-x" action="schedule/check/${checkScheduleId}">  

      <div class="form-group">
        <div class="label">
          <label>审核状态：</label>
        </div>
        <div class="field">
			<select class="input w50" name="checkState">
				<c:if test="${checkSchedule.checkState==0}">
					<option value="0" selected="selected">未审核</option>
					<option value="1">通过</option>
					<option value="2">不通过</option>
				</c:if>
				<c:if test="${checkSchedule.checkState==1}">
					<option value="0" >未审核</option>
					<option value="1" selected="selected">通过</option>
					<option value="2">不通过</option>
				</c:if>
				<c:if test="${checkSchedule.checkState==2 }">
					<option value="0" >未审核</option>
					<option value="1">通过</option>
					<option value="2" selected="selected">不通过</option>
				</c:if>
			</select>
		</div>
      </div>
     
      <div class="clear"></div>
      <div class="form-group">
        <div class="label">
          <label>不通过原因：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="reason" value="${checkSchedule.reason }"/>
        </div>
      </div>
      <div class="clear"></div>
      <div class="form-group">
        <div class="label">
          <label>责任方：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="responsibleParty" value="${checkSchedule.responsibleParty }"/>
        </div>
      </div>    
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>

</body></html>