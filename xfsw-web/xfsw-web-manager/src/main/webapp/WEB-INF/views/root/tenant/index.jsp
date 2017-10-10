<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/widgets/bootstrap/bootstrap-table/bootstrap-table.css">
<!-- Latest compiled and minified JavaScript -->
<script src="/widgets/bootstrap/bootstrap-table/bootstrap-table.js"></script>
<!-- Latest compiled and minified Locales -->
<script src="/widgets/bootstrap/bootstrap-table/bootstrap-table-zh-CN.js"></script>

<script type="text/javascript" src="/xfsw-web-manager/js/root/tenant/index.js"></script>
</head>
<body>
	<div class="panel">
		<header class="panel-heading">
			<button class="btn btn-info " type="button" onclick="initAdd()"><i class="fa fa-plus"></i> 添加空间</button>
        </header>
		<div class="panel-body">
			<table id="dataTable"></table>
		</div>
	</div>
	<form class="Ding-Form modal-form form-horizontal" id="addForm" 
		action="/xfsw-web-manager/root/tenant/insertTenant.shtml" 
		successCallback="insertSuccess">
		<div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">空间名称</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" id="name" name="name" validations="required&&vComplexMaxLength=20" validationTips="名称不能为空&&名称长度不能超过20个字符"  />
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">空间code</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" id="code" name="code" validations="required&&vComplexMaxLength=20" validationTips="code不能为空&&code不能超过20个字符" /> 
            </div>
        </div>
	</form>
	
	<form class="Ding-Form modal-form form-horizontal" id="editForm" 
		action="/xfsw-web-manager/root/tenant/updateTenant.shtml" 
		successCallback="updateSuccess">
		<div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">空间名称</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" id="editname" name="name" validations="required&&vComplexMaxLength=20" validationTips="名称不能为空&&名称长度不能超过20个字符"  />
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">空间code</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" id="editcode" name="code" validations="required&&vComplexMaxLength=20" validationTips="code不能为空&&code不能超过20个字符" /> 
            </div>
        </div>
        <input type="hidden" name="id" id="editid" />
        <input type="hidden" id="editindex" />
	</form>
</body>
</html>