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

<script type="text/javascript" src="/js/manager/business/dictionary/index.js"></script>
</head>
<body>
	<div class="panel">
		<header class="panel-heading">
			<button class="btn btn-info " type="button" onclick="initAdd()"><i class="fa fa-plus"></i> 添加字典信息</button>
        </header>
		<div class="panel-body">
			<table id="dataTable"></table>
		</div>
	</div>
	
	<form class="Ding-Form modal-form form-horizontal" id="addForm" action="<%=request.getContextPath() %>/manager/business/dictionary/insertDictionary.shtml" successCallback="insertSuccess">
		<div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">字典代码</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" id="keyCode" name="keyCode" validations="required&&vComplexMaxLength=20" validationTips="不能为空&&长度不能超过20个中文字符或者40个英文字符"  />
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">描述</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" id="remark" name="remark" validations="required&&vComplexMaxLength=50" validationTips="不能为空&&不能超过50个字符" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">字典值</label>
            <div class="col-lg-10">
            	<div class="common-after">
            		<div class="col-md-8 form-group">
            			<input class="form-control" type="text" id="values" name="values" validations="required&&vComplexMaxLength=50" validationTips="不能为空&&不能超过50个字符" />
            		</div>
            		<div class="col-md-2" id="btnDiv">
            			<button class="btn btn-info " type="button" onclick="initAddDetail(this)"><i class="fa fa-plus"></i></button>
            		</div>
            	</div>
            </div>
        </div>
	</form>
	
	<form class="Ding-Form modal-form form-horizontal" id="editForm" action="<%=request.getContextPath() %>/manager/business/dictionary/updateDictionary.shtml" successCallback="updateSuccess">
		<div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">字典代码</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" name="keyCode" validations="required&&vComplexMaxLength=20" validationTips="不能为空&&长度不能超过20个中文字符或者40个英文字符"  />
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">描述</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" name="remark" validations="required&&vComplexMaxLength=50" validationTips="不能为空&&不能超过50个字符" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">字典值</label>
            <div class="col-lg-10">
            	<div class="common-after">
            		<div class="col-md-8 form-group">
            			<input class="form-control" type="text" name="values" validations="required&&vComplexMaxLength=50" validationTips="不能为空&&不能超过50个字符" />
            			<input type="hidden" name="ids" />
            		</div>
            		<div class="col-md-2" id="btnDiv">
            			<button class="btn btn-info" type="button" onclick="initAddDetail(this)"><i class="fa fa-plus"></i></button>
            		</div>
            	</div>
            </div>
        </div>
        <div>
        	<input type="hidden" name="id" />
        	<input type="hidden" id="deleteIds" name="deleteIds" />
        	<input type="hidden" id="index" />
        </div>
	</form>
</body>
</html>