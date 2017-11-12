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

<link rel="stylesheet" type="text/css" href="/widgets/dingui/Ding.FileUploader.css" />
<script type="text/javascript" src="/widgets/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="/widgets/dingui/Ding.FileUploader.js"></script>

<script type="text/javascript" src="/acadamic-web-manager/js/manager/course/chapter/knowledge/index.js"></script>
</head>
<body>
	<div class="panel">
		<header class="panel-heading">
			<input type="hidden" id="chapterId" value="${chapterId }" />
			<button class="btn btn-info " type="button" onclick="initAdd()"><i class="fa fa-plus"></i> 添加知识点</button>
        </header>
		<div class="panel-body">
			<table id="dataTable"></table>
		</div>
	</div>
	
	<form class="Ding-Form modal-form" id="form" 
		action="/acadamic-web-manager/manager/course/chapter/knowledge/saveKnowledge.shtml" 
		successCallback="saveSuccess">
		<div class="form-group">
			<input type="hidden" id="id" name="id" />
	        	<input type="hidden" id="index" />
	        	<input type="hidden" name="chapterCode" id="chapterCode" value="${chapterCode }" />
		</div>
		<div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">标题</label>
            <div class="col-lg-8">
            	<input class="form-control" type="text" id="name" name="name" validations="required&&vComplexMaxLength=40" validationTips="名称不能为空&&长度不能超过20个中文字符或者40个英文字符"  />
            </div>
        </div>
	</form>
	
</body>
</html>