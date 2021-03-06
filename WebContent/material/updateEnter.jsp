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
  <div class="panel-head" id="update"><strong><span class="icon-pencil-square-o"></span>修改进场材料</strong></div>
  <div class="body-content">
  		<c:if test="${result == false }">
			<div class="alert alert-danger" role="alert" style="margin-top:5px color:red" id="tip">${reason }</div>
		</c:if>
    <form method="post" class="form-x" action="enter/update/${enterUpdateId}">
      <div class="form-group">
        <div class="label">
          <label>项目名称：</label>
        </div>
        <div class="field">
          <select name="enterProject.projectName" class="input" onchange="changesearch()" style="width:200px; line-height:17px; display:inline-block">
            <c:forEach var="i" items="${chooseProject}">
      		<tr>
      		<c:if test="${i.projectName == matEnter.enterProject.projectName}">
      			<td><option value="${i.projectName}" selected>${i.projectName}</option><p></td>	
      		</c:if>
      		<c:if test="${i.projectName != matEnter.enterProject.projectName}">
      			<td><option value="${i.projectName}">${i.projectName}</option><p></td>	
      		</c:if>
	      		
	        </tr>
	  		</c:forEach>
          </select>
        </div>
      </div>  
      
      <div class="form-group">
        <div class="label">
          <label>材料名称：</label>
        </div>
        <div class="field">
          <select name="enterMat.matName" class="input" onchange="changesearch()" style="width:200px; line-height:17px; display:inline-block">
            <c:forEach var="i" items="${matData}">
      		<tr>
      		<c:if test="${i.matName == matEnter.enterMat.matName}">
      			<td><option value="${i.matName}" selected>${i.matName}</option><p></td>	
      		</c:if>
      		<c:if test="${i.matName != matEnter.enterMat.matName}">
      			<td><option value="${i.matName}">${i.matName}</option><p></td>	
      		</c:if>
	      		
	        </tr>
	  		</c:forEach>
          </select>
        </div>
      </div>  
      
      <%-- <div class="form-group">
        <div class="label">
          <label>材料名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50"  name="enterMat.matName" data-validate="required:请输入材料名称" value="${matEnter.enterMat.matName }"/>
          <div class="tips"></div>
        </div>
      </div> --%>
     
      <div class="clear"></div>
      <div class="form-group">
        <div class="label">
          <label>进场数量：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="enterNum"  data-validate="required:请输入材料数量" value="${matEnter.enterNum }"/>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>单位：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="enterMat.matUnit" data-validate="required:请输入材料单位" value="${matEnter.enterMat.matUnit }"/>
        </div>
      </div>

      <div class="form-group">
        <div class="label">
          <label>进场日期：</label>
        </div>
        <div class="field"> 
          <script src="js/laydate/laydate.js"></script>
          <input type="date" class="input w50" name="enterDate" value="${matEnter.enterDate }"/>
          <div class="tips"></div>
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