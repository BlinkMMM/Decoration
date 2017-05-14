<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>仓库</title>
  <meta name="description" content="这是一个 table 页面">
  <meta name="keywords" content="table">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="/coal-gas-system/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="/coal-gas-system/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="/coal-gas-system/css/amazeui.min.css"/>
  <link rel="stylesheet" href="/coal-gas-system/css/admin.css">
</head>
<body>

<!-- header -->
<c:import url="../header.jsp"></c:import>

<div class="am-cf admin-main">
  <!-- sidebar -->
  <c:import url="../sidebar.jsp"></c:import>

  <!-- content start -->
  <div class="admin-content">
    <div class="admin-content-body">
      <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">仓库</strong> / <small>Depot</small></div>
        <button style="margin-left: 50px; height: 35px; font-size: 15px; padding-top: 6px; background: #3BB4F2; color: #FFF" type="button" class="am-btn am-btn-default" 
			onclick="location.href='/coal-gas-system/inbound/history?page=1'">入库记录</button>
        <button style="margin-left: 15px; height: 35px; font-size: 15px; padding-top: 6px; background: #3BB4F2; color: #FFF" type="button" class="am-btn am-btn-default" 
			onclick="location.href='/coal-gas-system/outbound/history?page=1'">出库记录</button>
      </div>

      <hr>

      <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6" style="width: 345px;">
          <div class="am-btn-toolbar">
            <div class="am-btn-group am-btn-group-xs">
              <button type="button" class="am-btn am-btn-default" onclick="location.href='/coal-gas-system/depot/add'">
              	<span class="am-icon-plus"></span> 新增 </button>
              <button type="button" class="am-btn am-btn-default" onclick="location.href='/coal-gas-system/depot/inbound'">
              	<span class="am-icon-save"></span> 入库 </button>
              <button type="button" class="am-btn am-btn-default" onclick="location.href='/coal-gas-system/depot/outbound'">
              	<span class="am-icon-trash-o"></span> 出库 </button>
              <button type="button" class="am-btn am-btn-default" onclick="location.href='#'">
              	<span class="am-icon-archive"></span> 审核 </button>
            </div>
          </div>
        </div>
        
        <div class="am-u-sm-12 am-u-md-3" style="width: 240px;">
          <div class="am-form-group">
            <select id="select1" data-am-selected="{btnSize: 'sm'}" onchange="selectChange()">
              <option <c:if test="${param.option1 == '所有类别' }"> selected="selected" </c:if> value="所有类别">所有类别</option>
              <option <c:if test="${param.option1 == '高等原煤' }"> selected="selected" </c:if> value="高等原煤">高等原煤</option>
              <option <c:if test="${param.option1 == '中等原煤' }"> selected="selected" </c:if> value="中等原煤">中等原煤</option>
              <option <c:if test="${param.option1 == '低等原煤' }"> selected="selected" </c:if> value="低等原煤">低等原煤</option>
            </select>
          </div>
        </div>
        
        <div class="am-u-sm-12 am-u-md-3" style="width: 240px;">
          <div class="am-form-group">
            <select id="select2"  data-am-selected="{btnSize: 'sm'}" onchange="selectChange()">
              <option <c:if test="${param.option2 == '所有状态' }"> selected="selected" </c:if> value="所有状态">所有状态</option>
              <option <c:if test="${param.option2 == '正常' }"> selected="selected" </c:if> value="正常">正常</option>
              <option <c:if test="${param.option2 == '空缺' }"> selected="selected" </c:if> value="空缺">空缺</option>
            </select>
          </div>
        </div>
        
        <div class="am-u-sm-12 am-u-md-3">
          <div class="am-input-group am-input-group-sm">
            <input id="searchText" type="text" class="am-form-field" value="${param.searchText }">
          <span class="am-input-group-btn">
            <button id="search" class="am-btn am-btn-default" type="button" onclick="search()">搜索</button>
          </span>
          </div>
        </div>
      </div>

      <div class="am-g">
        <div class="am-u-sm-12">
          <form class="am-form">
            <table class="am-table am-table-striped am-table-hover table-main">
              <thead>
              <tr>
                <th>&nbsp;&nbsp;管理</th>
                <th style="text-align:center;">仓库号</th>
                <th>名称</th>
                <th>产地</th>
                <th>发热量MJ/kg</th>
                <th>硫份%</th>
                <th>灰份%</th>
                <th>固定碳%</th>
                <th>挥发份%</th>
                <th>水份%</th>
                <th>存储量/t</th>
                <th>单价/¥</th>
                <th>管理人</th>
                <th>状态</th>
                <th>操作</th>
              </tr>
              </thead>
              <tbody>
              
              	<c:forEach begin="${(param.page - 1) * 6 }" end="${param.page * 6 - 1 }" var="i" varStatus="status">
              	<c:if test="${status.index<fn:length(depot_list)}">
				  <c:set var="depot" value="${depot_list[i]}" />
				  <tr>
	                <td>
		                <div class="am-dropdown" data-am-dropdown>
		                  <button class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" data-am-dropdown-toggle><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
		                  <ul class="am-dropdown-content">
		                    <li><a href="/coal-gas-system/depot/inbound?id=${depot.id }">1. 入库</a></li>
		                    <li><a href="/coal-gas-system/depot/outbound?id=${depot.id }&unitPrice=${depot.unitPrice }">2. 出库</a></li>
		                    <li><a href="/coal-gas-system/inbound/history?page=1&id=${depot.id }">3. 入库记录</a></li>
		                    <li><a href="/coal-gas-system/outbound/history?page=1&id=${depot.id }">4. 出库记录</a></li>
		                  </ul>
		                </div>
		           </td>
	                <td align="center">${depot.id }</td>
	                <td <c:if test="${depot.name == '' }">style="color: #AAA;"</c:if> >
	                	<c:if test="${depot.name == '' }">空仓库</c:if>
	                	<c:if test="${depot.name != '' }">${depot.name }</c:if>
	                </td>
	                <td>${depot.place }</td>
	                <td>${depot.heat }</td>
	                <td>${depot.sulphur }</td>
	                <td>${depot.ash }</td>
	                <td>${depot.carbon }</td>
	                <td>${depot.volatiles }</td>
	                <td>${depot.water }</td>
	                <td <c:if test="${depot.totalAmount == null }">style="color: #AAA;"</c:if> >
	                	<c:if test="${depot.totalAmount == null }">0.0</c:if>
	                	<c:if test="${depot.totalAmount != null }">${depot.totalAmount }</c:if>
	                </td>
	                <td>${depot.unitPrice }</td>
	                <td>${depot.confirmPerson }</td>
	                <td <c:if test="${depot.totalAmount < 10 }">style="color: red;"</c:if> >
	                	<c:if test="${depot.totalAmount < 10 && depot.totalAmount != 0 }">空缺</c:if>
	                	<c:if test="${depot.totalAmount >= 10 }">正常</c:if>
	                </td>
	                <td>
	                  <div class="am-btn-toolbar">
	                    <div class="am-btn-group am-btn-group-xs">
	                      <button type="button" class="am-btn am-btn-default am-btn-xs am-text-secondary" onclick="location.href='/coal-gas-system/depot/edit?id=${depot.id }'"><span class="am-icon-pencil-square-o"></span> 编辑</button>
	                      <button type="button" class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="deleteDepot(${depot.id })"><span class="am-icon-trash-o"></span> 删除</button>
	                    </div>
	                  </div>
	                </td>
	              </tr>
	            </c:if>
				</c:forEach>
				
              </tbody>
            </table>
            <div class="am-cf">
              &nbsp;&nbsp;共 <fmt:formatNumber type="number" value="${fn:length(depot_list) / 6 + 0.49 }" maxFractionDigits="0"/> 页 ${fn:length(depot_list) } 条记录 -- 第 ${param.page } 页
              <div class="am-fr">
                <ul class="am-pagination">
                  <li><a href="javascript:changePage(1)">«</a></li>
                  
				  <c:if test="${param.page == 1 }">
	                <c:forEach begin="0" end="4" var="i" varStatus="status">
					    <li <c:if test="${param.page == (param.page + status.index) }">class="am-active"</c:if> ><a href="javascript:changePage(${param.page + status.index })">${param.page + status.index }</a></li>
					</c:forEach>
                  </c:if>
                  
				  <c:if test="${param.page == 2 }">
	                <c:forEach begin="0" end="4" var="i" varStatus="status">
					    <li <c:if test="${param.page == (param.page + status.index - 1) }">class="am-active"</c:if> ><a href="javascript:changePage(${param.page + status.index -1 })">${param.page + status.index -1 }</a></li>
					</c:forEach>
                  </c:if>
                  
				  <c:if test="${param.page == 3 }">
	                <c:forEach begin="0" end="4" var="i" varStatus="status">
					    <li <c:if test="${param.page == (param.page + status.index - 2) }">class="am-active"</c:if> ><a href="javascript:changePage(${param.page + status.index -2 })">${param.page + status.index -2 }</a></li>
					</c:forEach>
                  </c:if>
                  
                  <c:if test="${param.page == 4 && (fn:length(depot_list) / 6 - param.page) <= 1 }">
	                <c:forEach begin="0" end="4" var="i" varStatus="status">
					    <li <c:if test="${param.page == (param.page + status.index - 3) }">class="am-active"</c:if> ><a href="javascript:changePage(${param.page + status.index -3 })">${param.page + status.index -3 }</a></li>
					</c:forEach>
                  </c:if>
                  
                  <c:if test="${param.page == 5 && (fn:length(depot_list) / 6 - param.page) <= 0 }">
	                <c:forEach begin="0" end="4" var="i" varStatus="status">
					    <li <c:if test="${param.page == (param.page + status.index - 4) }">class="am-active"</c:if> ><a href="javascript:changePage(${param.page + status.index -4 })">${param.page + status.index -4 }</a></li>
					</c:forEach>
                  </c:if>
                  
				  <c:if test="${param.page >= 4 && (fn:length(depot_list) / 6 - param.page) > 1 }">
	                <c:forEach begin="0" end="4" var="i" varStatus="status">
					    <li <c:if test="${param.page == (param.page + status.index - 2) }">class="am-active"</c:if> ><a href="javascript:changePage(${param.page + status.index - 2 })">${param.page + status.index - 2 }</a></li>
					</c:forEach>
                  </c:if>
                  
				  <c:if test="${fn:length(depot_list) / 6 > 5 && (fn:length(depot_list) / 6 - param.page) > 0 && (fn:length(depot_list) / 6 - param.page) <= 1 }">
	                <c:forEach begin="0" end="4" var="i" varStatus="status">
					    <li <c:if test="${param.page == (param.page + status.index - 3) }">class="am-active"</c:if> ><a href="javascript:changePage(${param.page + status.index - 3 })">${param.page + status.index - 3 }</a></li>
					</c:forEach>
                  </c:if>
                  
				  <c:if test="${fn:length(depot_list) / 6 > 5 && (fn:length(depot_list) / 6 - param.page) > -1 && (fn:length(depot_list) / 6 - param.page) <= 0 }">
	                <c:forEach begin="0" end="4" var="i" varStatus="status">
					    <li <c:if test="${param.page == (param.page + status.index - 4) }">class="am-active"</c:if> ><a href="javascript:changePage(${param.page + status.index - 4 })">${param.page + status.index - 4 }</a></li>
					</c:forEach>
                  </c:if>
                  
                  <li><a href="javascript:changePage(${fn:substringBefore(fn:length(depot_list) / 6 + 0.99, '.') })">»</a></li>
                </ul>
              </div>
            </div>
            <hr />
            
            <p><b>注：</b>
            
            <c:import url="./import/remark.jsp"></c:import>
            
          </form>
        </div>

      </div>
    </div>

    <c:import url="../footer.jsp"></c:import>

  </div>
  <!-- content end -->
</div>

<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>

<script type="text/javascript">

	var pathname = window.location.pathname;
	var pathnamePage1 = window.location.pathname + "?page=1";

	document.onkeydown = function(e) {
	   	if((e.keyCode || e.which) == 13) {
	   		if("searchText" == document.activeElement.id) {
		   		document.getElementById("search").click();
	   		}
	   	}
	}

	function deleteDepot(id) {
		if ('A' == "${user.role}" || 'B' == "${user.role}" || 'C' == "${user.role}") {
			if (window.confirm("确定删除此仓库记录？")) {
				var xhr = getXhr();
				xhr.open("get", "/coal-gas-system/depot/delete?id=" + id, true);
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						window.location.reload();
					}
				}
				xhr.send();
			}
		} else {
			alert("无删除权限");
		}
	}
	
	function changePage(page) {
		var pOption1 = "&option1=" + $('#select1 option:selected').val();
		var pOption2 = "&option2=" + $('#select2 option:selected').val();
		var pSearchText = "&searchText=" + $('#searchText').val();
		if ((page - 1) * 6 < "${fn:length(depot_list) }") {
			var search = window.location.search;
			if (search.indexOf("searchText") == -1) {
				if (search.indexOf("option1") == -1) {
					if (search.indexOf("option2") == -1) {
						location.href = pathname + "?page=" + page;
					} else {
						location.href = pathname + "?page=" + page + pOption2;
					}
				} else {
					if (search.indexOf("option2") == -1) {
						location.href = pathname + "?page=" + page + pOption1;
					} else {
						location.href = pathname + "?page=" + page + pOption1 + pOption2;
					}
				}
			} else {
				if (search.indexOf("option1") == -1) {
					if (search.indexOf("option2") == -1) {
						location.href = pathname + "?page=" + page + pSearchText;
					} else {
						location.href = pathname + "?page=" + page + pOption2 + pSearchText;
					}
				} else {
					if (search.indexOf("option2") == -1) {
						location.href = pathname + "?page=" + page + pOption1 + pSearchText;
					} else {
						location.href = pathname + "?page=" + page + pOption1 + pOption2 + pSearchText;
					}
				}
			}
		} else {
			location.href = "#"
		}
	}
 
	function selectChange() {
		var pOption1 = "&option1=" + $('#select1 option:selected').val();
		var pOption2 = "&option2=" + $('#select2 option:selected').val();
		location.href = pathnamePage1 + pOption1 + pOption2;
	}
	
	function search(){
		var pOption1 = "&option1=" + $('#select1 option:selected').val();
		var pOption2 = "&option2=" + $('#select2 option:selected').val();
		var pSearchText = "&searchText=" + $('#searchText').val();
		if ("" == $('#searchText').val()) {
			location.href = pathnamePage1;
		} else {
			if ("" != "${param.option1 }") {
				if ("" != "${param.option2 }")
					location.href = pathnamePage1 + pOption1 + pOption2 + pSearchText;
				else
					location.href = pathnamePage1 + pOption1 + pSearchText;
			} else if ("" != "${param.option2 }")
				location.href = pathnamePage1 + pOption2 + pSearchText;
			else
				location.href = pathnamePage1 + pSearchText;
		}
	}

</script>
<script src="/coal-gas-system/js/ajax.js"></script>
<script src="/coal-gas-system/js/jquery.min.js"></script>
<script src="/coal-gas-system/js/amazeui.min.js"></script>
<script src="/coal-gas-system/js/app.js"></script>

</body>
</html>
