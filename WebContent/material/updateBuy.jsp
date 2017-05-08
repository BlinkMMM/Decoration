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
  <div class="panel-head" id="update"><strong><span class="icon-pencil-square-o"></span>修改材料</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="material/update/${updateId}">  
      <div class="form-group">
        <div class="label">
          <label>材料名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50"  name="matName" data-validate="required:请输入材料名称" />
          <div class="tips"></div>
        </div>
      </div>
     
      <div class="clear"></div>
      <div class="form-group">
        <div class="label">
          <label>购买数量：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="matNum"  data-validate="required:请输入材料数量"/>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>单位：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="matUnit" data-validate="required:请输入材料单位"/>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>材料价格：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="matPrice"  data-validate="required:请输入材料价格"/>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>材料品牌：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="matBrand"  data-validate="required:请输入材料品牌名称"/>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>项目名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="matProject.projectName"  data-validate="required:请输入项目名称"/>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>流程名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="matFlow.flowName"  data-validate="required:请输入流程名称"/>
        </div>
      </div>
     
      
      <div class="form-group">
        <div class="label">
          <label>购买时间：</label>
        </div>
        <div class="field"> 
          <script src="js/laydate/laydate.js"></script>
          <input type="date" class="input w50" name="matBuyDate" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>购买人：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="matUser.userName"  data-validate="required:请输入购买人姓名"/>
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