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
<form method="post" action="work/pageNumber" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder">显示考勤</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li> <a class="button border-main icon-plus-square-o" href="work/recordInfo"> 签到</a> </li>
        <li>搜索：</li>
        <li>首页
          <select name="s_ishome" class="input" onchange="changesearch()" style="width:60px; line-height:17px; display:inline-block">
            <option value="">选择</option>
            <option value="1">是</option>
            <option value="0">否</option>
          </select>
          &nbsp;&nbsp;
          推荐
          <select name="s_isvouch" class="input" onchange="changesearch()"  style="width:60px; line-height:17px;display:inline-block">
            <option value="">选择</option>
            <option value="1">是</option>
            <option value="0">否</option>
          </select>
          &nbsp;&nbsp;
          置顶
          <select name="s_istop" class="input" onchange="changesearch()"  style="width:60px; line-height:17px;display:inline-block">
            <option value="">选择</option>
            <option value="1">是</option>
            <option value="0">否</option>
          </select>
        </li>
        <if condition="$iscid eq 1">
          <li>
            <select name="cid" class="input" style="width:200px; line-height:17px;" onchange="changesearch()">
              <option value="">请选择分类</option>
              <option value="">产品分类</option>
              <option value="">产品分类</option>
              <option value="">产品分类</option>
              <option value="">产品分类</option>
            </select>
          </li>
        </if>
        <li>
          <input type="text" placeholder="请输入搜索关键字" name="keywords" class="input" style="width:250px; line-height:17px;display:inline-block" />
          <a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()" > 搜索</a></li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th>ID</th>
        <th>项目</th>
        <th>姓名</th>
        <th width="10%">签到日期</th>
        <th>是否满勤</th>
        <th>备注</th>
      </tr>
     
      <c:forEach var="i" items="${recordPageData}" varStatus="rowCount">
      <tr>
	      <td><c:out value="${recordPage.startCode + rowCount.index+1}"/><p></td>	
	      <td><c:out value="${i.recordProject.projectName}"/><p></td>	
	      <td><c:out value="${i.recordUser.userName}"/><p></td>	
	      <td><c:out value="${i.checkDate}"/><p></td>	
	      
	      <td>
	      <c:if test="${i.isFullWork == 1}">
	      	<c:out value="是"/><p>
	      </c:if>
	      <c:if test="${i.isFullWork == 0}">
	      	<c:out value="否"/><p>
	      </c:if>
	      
	      </td>	
	      
	      <td><c:out value="${i.remark}"/><p></td>	
	      
	      <td>
	      </td>
	  </tr>
	  </c:forEach>
	  
      <tr>
			<td colspan="8">
				<div class="pagelist" style="display:inline-block;">
					<a href="<%=path%>/work/?recordFrom=firstPage">首页</a> 
					<a href="<%=path%>/work/?recordFrom=previousPage">上一页</a> 
					<a href="<%=path%>/work/?recordFrom=nextPage">下一页</a> 
					<a href="<%=path%>/work/?recordFrom=finalPage">尾页</a>
				</div>
				<div style="display:inline-block;">&nbsp;&nbsp;&nbsp;&nbsp;当前页数</div>
				
					<div style="display:inline-block;">
						<select name="currentPageCode" class="input" style="width: 100px; line-height: 15px;">
							<c:forEach var="i" begin="1" end="${recordPage.totalPages }" varStatus="rowCount">
							<c:if test="${i == recordPage.currentPageCode}">
								<option value="${i}" selected>${i}</option>
							</c:if>
							<c:if test="${i != recordPage.currentPageCode}">
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