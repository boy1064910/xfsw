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

<script type="text/javascript" src="/js/manager/acadamic/course/chapter/knowledge/diff/level/index.js"></script>
</head>
<body>
	<div class="panel">
		<header class="panel-heading">
			<input type="hidden" id="knowledgeId" value="${knowledgeId }" />
			<button class="btn btn-info " type="button" onclick="initAdd()"><i class="fa fa-plus"></i> 添加习题难度级别</button>
        </header>
		<div class="panel-body">
			<table id="dataTable"></table>
		</div>
	</div>
	
	<form class="Ding-Form modal-form form-horizontal" id="form" 
		action="<%=request.getContextPath() %>/manager/acadamic/course/chapter/knowledge/diff/level/saveDiffLevel.shtml" 
		submitValidation="submitValidation"
		successCallback="saveSuccess">
		<div class="form-group">
			<input type="hidden" id="id" name="id" />
        	<input type="hidden" id="index" />
        	<input type="hidden" name="knowledgeCode" id="knowledgeCode" value="${knowledgeCode }" />
		</div>
        <div class="form-group">
			<label class="col-lg-3 col-sm-3 control-label">难度答疑资料</label>
			<div class="col-md-7">
				<div id="video"></div>
			</div>
		</div>
	</form>
	
</body>
</html>