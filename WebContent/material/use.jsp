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
    <div class="panel-head"><strong class="icon-reorder"> 材料使用</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li> <a class="button border-main icon-plus-square-o" href="use/addInfo"> 新添材料使用</a> </li>
        <form method="post" action="use/matSearch">
        <li style="float:right;">
          <input type="text" placeholder="请输入搜索关键字" name="searchName" class="input" style="width:250px; line-height:17px;display:inline-block" />
          <button type="submit" class="button border-main icon-search"> 搜索</button></li>
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
        <th>品牌</th>
        <th>余量</th>
        <th>登记人</th>
        <th width="10%">使用日期</th>
        <th width="310">操作</th>
      </tr>
     <form method="post" action="use/pageNumber" id="listform">
      <c:forEach var="i" items="${usePageData}" varStatus="rowCount">
      <tr>
	      <td><c:out value="${usePage.startCode + rowCount.index+1}"/><p></td>	
	      <td><c:out value="${i.useMat.matProject.projectName}"/><p></td>	
	      <td><c:out value="${i.useMat.matFlow.flowName}"/><p></td>	
	      <td><c:out value="${i.useMat.matName}"/><p></td>	
	      <td><c:out value="${i.useNum}"/><p></td>	
	      <td><c:out value="${i.useMat.matUnit}"/><p></td>	
	      <td><c:out value="${i.useMat.matBrand}"/><p></td>	
	      <td><c:out value="${i.restRate}"/><p></td>	
	      <td><c:out value="${i.useUser.userName}"/><p></td>	
	      <td><c:out value="${i.useDate}"/><p></td>	
	      <td>
	      <div class="button-group"> 
	       <a class="button border-main" href="use/updateInfo/${i.useId}"><span class="icon-edit"></span> 修改</a>
	       <a class="button border-red" onclick="del(${i.useId})" ><span class="icon-trash-o"></span> 删除</a>
<%-- 	       <a class="button border-red" href="use/delete/${i.useId}" ><span class="icon-trash-o"></span> 删除</a> --%>
	      </div>
	      </td>
	      </tr>
	  </c:forEach>

       <tr>
			<td colspan="8">
				<div class="pagelist" style="display:inline-block;">
					<a href="<%=path%>/use/?useFrom=firstPage">首页</a> 
					<a href="<%=path%>/use/?useFrom=previousPage">上一页</a> 
					<a href="<%=path%>/use/?useFrom=nextPage">下一页</a> 
					<a href="<%=path%>/use/?useFrom=finalPage">尾页</a>
				</div>
				<div style="display:inline-block;">&nbsp;&nbsp;&nbsp;&nbsp;当前页数</div>
				
					<div style="display:inline-block;">
						<select name="currentPageCode" class="input" style="width: 100px; line-height: 15px;">
							<c:forEach var="i" begin="1" end="${usePage.totalPages }" varStatus="rowCount">
							<c:if test="${i == usePage.currentPageCode}">
								<option value="${i}" selected>${i}</option>
							</c:if>
							<c:if test="${i != usePage.currentPageCode}">
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
<script type="text/javascript">
//单个删除
function del(obj){
	if(confirm("您确定要删除吗?")){
		 location.href="use/delete/"+obj;
	}
	else{
		
	}
}
</script>
</body>
</html>