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

<script type="text/javascript" src="/xfsw-web-manager/js/root/category/authority/initConfigLinkAuthority.js"></script>
</head>
<body>
	<div class="panel">
		<header class="panel-heading">
			<h4>功能权限：${categoryAuthority.name }</h4>
			<input type="hidden" id="categoryAuthorityId" value="${categoryAuthority.id }" />
			<button class="btn btn-info " type="button" onclick="initAdd()"><i class="fa fa-plus"></i> 添加权限</button>
        </header>
		<div class="panel-body">
			<table id="dataTable"></table>
		</div>
	</div>
	<form class="Ding-Form modal-form form-horizontal" id="addLinkAuthorityForm" action="<%=request.getContextPath() %>/manager/account/category/authority/insertLinkAuthority.shtml" successCallback="insertSuccess">
		<div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">权限名称</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" id="name" name="name" validations="required&&vComplexMaxLength=30" validationTips="权限名称不能为空&&权限名称长度不能超过15个中文字符或者30个英文字符"  />
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">链接地址</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" id="url" name="url" validations="vComplexMaxLength=300" validationTips="链接地址长度不能超过300个字符" />
            </div>
        </div>
		<div>
			<input type="hidden" name="authorityId" value="${authority.id }" />
		</div>
	</form>
	<form class="Ding-Form modal-form form-horizontal" id="editLinkAuthorityForm" action="<%=request.getContextPath() %>/manager/account/category/authority/updateLinkAuthority.shtml" successCallback="updateSuccess">
		<div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">权限名称</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" id="editName" name="name" validations="required&&vComplexMaxLength=30" validationTips="权限名称不能为空&&权限名称长度不能超过15个中文字符或者30个英文字符"  />
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">链接地址</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" id="editUrl" name="url" validations="vComplexMaxLength=300" validationTips="链接地址长度不能超过300个字符" />
            </div>
        </div>
		<div>
			<input type="hidden" name="id" id="editId" />
			<input type="hidden" id="editIndex" />
		</div>
	</form>
</body>
</html>