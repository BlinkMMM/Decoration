<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>记录考勤</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="work/saveRecord">  
      <div class="form-group">
        <div class="label">
          <label>项目：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50"  name="recordProject.projectName" data-validate="required:请输入项目名" />
          <div class="tips"></div>
        </div>
      </div>
     
      <div class="form-group">
        <div class="label">
          <label>签到日期：</label>
        </div>
        <div class="field"> 
          <script src="js/laydate/laydate.js"></script>
          <input type="date" class="input w50" name="checkDate" data-validate="required:请输入签到日期"/>
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label>是否满勤：</label>
        </div>
        <div class="field">
			<select class="input w50" name="isFullWork">
				<option value="1">是</option>
				<option value="0">否</option>
			</select>
		</div>
      </div>
      
		<div class="form-group">
			<div class="label">
				<label>备注：</label>
			</div>
			<div class="field">
				<input type="text" class="input w50" name="remark" />
			</div>
		</div>

		<div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit"> 签到</button>
        </div>
      </div>
    </form>
  </div>
</div>

</body></html>