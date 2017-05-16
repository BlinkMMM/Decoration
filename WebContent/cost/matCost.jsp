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
    <div class="panel-head"><strong class="icon-reorder">材料成本统计</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <form action="cost/mat2" method="post">
        
        <li>请选择项目
          <select name="projectName" class="input" style="width:200px; line-height:17px; display:inline-block">
            <c:forEach var="i" items="${chooseProject}">
            
            <c:if test="${i.projectName == matProjectSelected}">
			<tr>
	      		<td><option value="${i.projectName}" selected>${i.projectName}</option><p></td>	
	        </tr>	
			</c:if>
            <c:if test="${i.projectName != matProjectSelected}">
			<tr>
	      		<td><option value="${i.projectName}">${i.projectName}</option><p></td>	
	        </tr>	
			</c:if>		
	  		</c:forEach>
          </select>
          &nbsp;&nbsp;
          请选择流程
          <select name="flowName" class="input" style="width:200px; line-height:17px;display:inline-block">
             <option value=""></option>
            <c:forEach var="i" items="${chooseFlow}">
           
            <c:if test="${i.flowName == matFlowSelected}">
			<tr>
	      		<td><option value="${i.flowName}" selected>${i.flowName}</option><p></td>	
	        </tr>	
			</c:if>
            <c:if test="${i.flowName != matFlowSelected}">
			<tr>
	      		<td><option value="${i.flowName}">${i.flowName}</option><p></td>	
	        </tr>	
			</c:if>
	  		</c:forEach>
          </select>
          &nbsp;&nbsp;
        </li>
        
        <li> <button class="button border-main icon-search" type="submit">查看 </li>
        </form>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th>ID</th>
        <th>项目</th>
        <th>流程</th>
        <th>材料名称</th>
        <th>使用数量</th>
        <th>单位</th>
        <th>价格</th>
        <th>总价</th>

      </tr>
     
      <c:forEach var="i"  items="${matCostPageData}" varStatus="rowCount">
      <tr>
	      <td><c:out value="${matCostPage.startCode + rowCount.index+1}"/><p></td>	
	      <td><c:out value="${i.matBean.matProject.projectName}"/><p></td>	
	      <td><c:out value="${i.matBean.matFlow.flowName}"/><p></td>	
	      <td><c:out value="${i.matBean.matName}"/><p></td>	
	      <td><c:out value="${i.matBean.matNum}"/><p></td>	
	      <td><c:out value="${i.matBean.matUnit}"/><p></td>	
	      <td><c:out value="${i.matBean.matPrice}"/><p></td>	
	      <td><c:out value="${i.singleMatCost}"/><p></td>	
	      </tr>
	  </c:forEach>

		<tr>
		<form action="cost/matCostPageNumber" method="post">
			<td colspan="8">
				<div class="pagelist" style="display:inline-block;">
					<a href="<%=path%>/cost/?matCostFrom=firstPage">首页</a> 
					<a href="<%=path%>/cost/?matCostFrom=previousPage">上一页</a> 
					<a href="<%=path%>/cost/?matCostFrom=nextPage">下一页</a> 
					<a href="<%=path%>/cost/?matCostFrom=finalPage">尾页</a>
				</div>
				<div style="display:inline-block;">&nbsp;&nbsp;&nbsp;&nbsp;当前页数</div>
				
					<div style="display:inline-block;">
						<select name="currentPageCode" class="input" style="width: 100px; line-height: 15px;">
							<c:forEach var="i" begin="1" end="${matCostPage.totalPages }" varStatus="rowCount">
							<c:if test="${i == matCostPage.currentPageCode}">
								<option value="${i}" selected>${i}</option>
							</c:if>
							<c:if test="${i != matCostPage.currentPageCode}">
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