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

  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 总成本</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <form action="cost/totalSearch" method="post" >
        <li style="float:right">
          <input type="text" placeholder="请输入搜索关键字" name="searchName" class="input" style="width:250px; line-height:17px;display:inline-block" />
          <button type="submit" class="button border-main icon-search" > 搜索</button></li>
      </form>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
      
        <th>ID</th>
        <th>项目名称</th>
        <th>户主</th>
        <th>电话</th>
        <th>地址</th>
        <th>材料成本</th>
        <th>人工成本</th>
        <th>总成本</th>
        
      </tr>
     
      <c:forEach var="i" items="${totalCostData}" varStatus="rowCount">
      <tr>
	      <td><c:out value="${i.project.projectId}"/><p></td>	
	      <td><c:out value="${i.project.projectName}"/><p></td>	
	      <td><c:out value="${i.project.customer}"/><p></td>	
	      <td><c:out value="${i.project.telephone}"/><p></td>	
	      <td><c:out value="${i.project.address}"/><p></td>	
	      <td><c:out value="${i.allMatCost}"/><p></td>	
	      <td><c:out value="${i.allWageCost}"/><p></td>	
	      <td><c:out value="${i.totalCost}"/><p></td>	
	      <td>

	      </td>
	      </tr>
	  </c:forEach>

<tr>
		<form action="cost/totalCostPageNumber" method="post">
			<td colspan="8">
				<div class="pagelist" style="display:inline-block;">
					<a href="<%=path%>/cost/total/?totalCostFrom=firstPage">首页</a> 
					<a href="<%=path%>/cost/total/?totalCostFrom=previousPage">上一页</a> 
					<a href="<%=path%>/cost/total/?totalCostFrom=nextPage">下一页</a> 
					<a href="<%=path%>/cost/total/?totalCostFrom=finalPage">尾页</a>
				</div>
				<div style="display:inline-block;">&nbsp;&nbsp;&nbsp;&nbsp;当前页数</div>
				
					<div style="display:inline-block;">
						<select name="currentPageCode" class="input" style="width: 100px; line-height: 15px;">
							<c:forEach var="i" begin="1" end="${wageCostPage.totalPages }" varStatus="rowCount">
							<c:if test="${i == wageCostPage.currentPageCode}">
								<option value="${i}" selected>${i}</option>
							</c:if>
							<c:if test="${i != wageCostPage.currentPageCode}">
								<option value="${i}">${i}</option>
							</c:if>
							</c:forEach>
						</select>
					</div>
					<div style="display:inline-block;height:10px;">
						<input type="submit" value="查询" class="button border-main icon-search">
					</div>
			</td>
			</form>
		</tr>
    </table>
  </div>

</body>
</html>