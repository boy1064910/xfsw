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

<script type="text/javascript" src="/xfsw-web-manager/js/root/default/authority/index.js"></script>
</head>
<body>
	<div class="panel">
		<header class="panel-heading">
			<button class="btn btn-info " type="button" onclick="initAdd()"><i class="fa fa-plus"></i> 添加权限</button>
        </header>
		<div class="panel-body">
			<table id="dataTable"></table>
		</div>
	</div>
	<form class="Ding-Form modal-form form-horizontal" id="addForm" 
		action="/xfsw-web-manager/root/default/authority/insertDefaultAuthority.shtml" 
		submitValidation="insertSubmitValidation"
		successCallback="insertSuccess">
		<div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">权限名称</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" id="name" name="name" validations="required&&vComplexMaxLength=20" validationTips="权限名称不能为空&&权限名称长度不能超过20个字符"  />
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">上级菜单ID</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" id="pid" name="pid" validations="vComplexMaxLength=11" validationTips="长度不能超过11个整数" /> 
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">链接地址</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" id="url" name="url" validations="vComplexMaxLength=100" validationTips="链接地址长度不能超过100个字符" /> 
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">备注</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" id="remark" name="remark" validations="vComplexMaxLength=50" validationTips="备注长度不能超过50个字符" /> 
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">显示顺序</label>
            <div class="col-lg-10">
           		<input class="form-control" type="text" id="orderIndex" name="orderIndex" />
            </div>
        </div>
         <div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">图标</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" id="ico" name="ico" />
            </div>
        </div>
	</form>
	
	<form class="Ding-Form modal-form form-horizontal" id="editForm" 
		action="/xfsw-web-manager/root/default/authority/updateCategoryAuthority.shtml" 
		successCallback="updateSuccess">
		<div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">权限名称</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" id="editname" name="name" validations="required&&vComplexMaxLength=20" validationTips="权限名称不能为空&&权限名称长度不能超过20个字符"  />
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">上级菜单ID</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" id="editpid" name="pid" validations="vComplexMaxLength=11" validationTips="长度不能超过11个整数" /> 
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">链接地址</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" id="editurl" name="url" validations="vComplexMaxLength=100" validationTips="链接地址长度不能超过100个字符" /> 
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">备注</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" id="editremark" name="remark" validations="vComplexMaxLength=50" validationTips="备注长度不能超过50个字符" /> 
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">显示顺序</label>
            <div class="col-lg-10">
           		<input class="form-control" type="text" id="editorderIndex" name="orderIndex" />
            </div>
        </div>
         <div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">图标</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" id="editico" name="ico" />
            </div>
        </div>
        <input type="hidden" name="id" id="editid" />
        <input type="hidden" id="editindex" />
	</form>
</body>
</html>