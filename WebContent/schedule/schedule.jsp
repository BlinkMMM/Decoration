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
<body>
<form method="post" action="schedule/pageNumber" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder">工程进度</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li> <a class="button border-main icon-plus-square-o" href="schedule/addScheduleInfo">添加工程进度</a> </li>
        
        <li style="float:right;">
          <input type="text" placeholder="请输入搜索关键字" name="keywords" class="input" style="width:250px; line-height:17px;display:inline-block" />
          <a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()" > 搜索</a></li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th>ID</th>
        <th>预计工时</th>
        <th>已完成工时</th>
        <th>进度百分比</th>
        <th>项目</th>
        <th>流程</th>
        <th>登记人</th>
        <th width="10%">登记日期</th>
        <th width="310">操作</th>
      </tr>
     
      <c:forEach var="i" items="${schedulePageData}" varStatus="rowCount">
      <tr>
	      <td><c:out value="${schedulePage.startCode + rowCount.index+1}"/><p></td>	
	      <td><c:out value="${i.expectedDays}"/><p></td>	
	      <td><c:out value="${i.finishedDays}"/><p></td>	
	      <td><c:out value="${i.scheduleRate}"/><p></td>	
	      <td><c:out value="${i.scheduleProject.projectName}"/><p></td>	
	      <td><c:out value="${i.scheduleFlow.flowName}"/><p></td>	
	      <td><c:out value="${i.scheduleUser.userName}"/><p></td>	
	      <td><c:out value="${i.recordDate}"/><p></td>	
	      <td>
	      <div class="button-group"> 
	       <a class="button border-main" href="use/updateInfo/${i.scheduleId}"><span class="icon-edit"></span> 更新</a>
	       <a class="button border-red" href="use/delete/${i.scheduleId}" ><span class="icon-trash-o"></span> 删除</a>
	      </div>
	      </td>
	      </tr>
	  </c:forEach>

      <tr>
			<td colspan="8">
				<div class="pagelist" style="display:inline-block;">
					<a href="<%=path%>/schedule/?scheduleFrom=firstPage">首页</a> 
					<a href="<%=path%>/schedule/?scheduleFrom=previousPage">上一页</a> 
					<a href="<%=path%>/schedule/?scheduleFrom=nextPage">下一页</a> 
					<a href="<%=path%>/schedule/?scheduleFrom=finalPage">尾页</a>
				</div>
				<div style="display:inline-block;">&nbsp;&nbsp;&nbsp;&nbsp;当前页数</div>
				
					<div style="display:inline-block;">
						<select name="currentPageCode" class="input" style="width: 100px; line-height: 15px;">
							<c:forEach var="i" begin="1" end="${schedulePage.totalPages }" varStatus="rowCount">
							<c:if test="${i == schedulePage.currentPageCode}">
								<option value="${i}" selected>${i}</option>
							</c:if>
							<c:if test="${i != schedulePage.currentPageCode}">
								<option value="${i}">${i}</option>
							</c:if>
							</c:forEach>
						</select>
					</div>
					<div style="display:inline-block;height:10px;">
						<input type="submit" value="查询" class="button border-main icon-search">
					</div>
			</td>
		</tr>
    </table>
  </div>
</form>

</body>
</html>