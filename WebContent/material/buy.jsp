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
<form action="material/matSearch" method="post"> 
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 购买材料</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li> <a class="button border-main icon-plus-square-o" href="material/addInfo"> 新添材料</a> </li>
        
        <li style="float:right;">
          <input type="text" placeholder="请输入搜索关键字" name="searchName" class="input" style="width:250px; line-height:17px;display:inline-block" />
          <button type="submit" class="button border-main icon-search"> 搜索</button></li>
      </ul>
    </div>
    </form>
    <form method="post" action="material/pageNumber" id="listform">
    <table class="table table-hover text-center">
      <tr>
        <th>ID</th>
        <th>材料名称</th>
        <th>数量</th>
        <th>单位</th>
        <th>价格</th>
        <th>品牌</th>
        <th>项目</th>
        <th>流程</th>
        <th>购买人</th>
        <th width="10%">购买日期</th>
        <th width="310">操作</th>
      </tr>
     
      <c:forEach var="i" items="${matPageData}" varStatus="rowCount">
      <tr>
	      <td><c:out value="${matPage.startCode + rowCount.index+1}"/><p></td>	
	      <td><c:out value="${i.matName}"/><p></td>	
	      <td><c:out value="${i.matNum}"/><p></td>	
	      <td><c:out value="${i.matUnit}"/><p></td>	
	      <td><c:out value="${i.matPrice}"/><p></td>	
	      <td><c:out value="${i.matBrand}"/><p></td>	
	      <td><c:out value="${i.matProject.projectName}"/><p></td>	
	      <td><c:out value="${i.matFlow.flowName}"/><p></td>	
	      <td><c:out value="${i.matUser.userName}"/><p></td>	
	      <td><c:out value="${i.matBuyDate}"/><p></td>	
	      
	      <td>
	      <div class="button-group"> 
	       <a class="button border-main" href="material/updateInfo/${i.matId}"><span class="icon-edit"></span> 修改</a>
	       <a class="button border-red" href="material/delete/${i.matId}" ><span class="icon-trash-o"></span> 删除</a>
	      </div>
	      </td>
	  </tr>
	  </c:forEach>
		
		<tr>
			<td colspan="8">
				<div class="pagelist" style="display:inline-block;">
					<a href="<%=path%>/material?from=firstPage">首页</a> 
					<a href="<%=path%>/material?from=previousPage">上一页</a> 
					<a href="<%=path%>/material?from=nextPage">下一页</a> 
					<a href="<%=path%>/material?from=finalPage">尾页</a>
				</div>
				<div style="display:inline-block;">&nbsp;&nbsp;&nbsp;&nbsp;当前页数</div>
				
					<div style="display:inline-block;">
						<select name="currentPageCode" class="input" style="width: 100px; line-height: 15px;">
							<c:forEach var="i" begin="1" end="${matPage.totalPages }" varStatus="rowCount">
							<c:if test="${i == matPage.currentPageCode}">
								<option value="${i}" selected>${i}</option>
							</c:if>
							<c:if test="${i != matPage.currentPageCode}">
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