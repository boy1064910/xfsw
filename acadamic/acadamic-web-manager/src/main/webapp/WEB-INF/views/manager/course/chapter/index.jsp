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

<script type="text/javascript" src="/acadamic-web-manager/js/manager/course/chapter/index.js"></script>
</head>
<body>
	<div class="panel">
		<header class="panel-heading">
			<input type="hidden" id="courseId" value="${courseId }" />
			<button class="btn btn-info " type="button" onclick="initAdd()"><i class="fa fa-plus"></i> 添加章节</button>
        </header>
		<div class="panel-body">
			<table id="dataTable"></table>
		</div>
	</div>
	
	<form class="Ding-Form modal-form form-horizontal" id="form" method="post"
		action="/acadamic-web-manager/manager/course/chapter/saveChapter.shtml" 
		successCallback="saveSuccess">
		<div class="form-group">
			<input type="hidden" id="id" name="id" />
	        	<input type="hidden" id="index" />
	        	<input type="hidden" name="courseCode" id="courseCode" value="${courseCode }" />
		</div>
		<div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">章节名称</label>
            <div class="col-lg-8">
            	<input class="form-control" type="text" id="name" name="name" validations="vComplexMaxLength=40" validationTips="长度不能超过20个中文字符或者40个英文字符"  />
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">价格</label>
            <div class="col-lg-8">
            	<input class="form-control" type="text" id="price" name="price" value="0" validations="vInteger" validationTips="价格只能填写整数"  />
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">章节介绍</label>
            <div class="col-lg-8">
            	<textarea id="info" name="info" rows="6" class="form-control" validations="vComplexMaxLength=100" validationTips="长度不能超过100个中文字符或者200个英文字符" ></textarea>
            </div>
        </div>
	</form>
	
</body>
</html>