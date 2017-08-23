<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/widgets/bootstrap/bootstrap-table/bootstrap-table.css">
<!-- Latest compiled and minified JavaScript -->
<script src="/widgets/bootstrap/bootstrap-table/bootstrap-table.js"></script>
<!-- Latest compiled and minified Locales -->
<script src="/widgets/bootstrap/bootstrap-table/bootstrap-table-zh-CN.js"></script>

<script type="text/javascript" src="/js/manager/business/category/index.js"></script>
</head>
<body>
	<div class="row">
		<div class="col-lg-6">
			<section class="panel">
				<header class="panel-heading">一级类别</header>
				<div class="panel-body">
					<div id="ftoolbar" class="btn-group">
						<button class="btn btn-default" type="button" onclick="initAddFirstCategory()">添加一级品类</button>
					</div>
					<table id="firstCategoryTable"></table>
				</div>
			</section>
		</div>
		<div class="col-lg-6">
			<section class="panel">
				<header class="panel-heading">二级类别（上级品类：<span id="fCategoryName" style="color:red;font-size:bolder;">尚未选择</span>）</header>
				<div class="panel-body">
					<div id="toolbar" class="btn-group">
						<button class="btn btn-default" type="button" class="margin-left:10px;" onclick="initAddSecondCategory()">添加二级品类</button>
					</div>
					<table id="secondCategoryTable"></table>
				</div>
			</section>
		</div>
	</div>
	
	<!-- 添加一级品类 -->
	<form class="Ding-Form modal-form form-horizontal" id="addCategoryForm" successCallback="insertFirstSuccess" 
	action="<%=request.getContextPath()%>/manager/business/category/insertCategory.shtml">
		<div class="form-group">
			<label class="col-lg-3 control-label">类别名称</label>
            <div class="col-lg-8">
            	<input class="form-control" type="text" id="name" name="name" validations="required&&vComplexMaxLength=50" validationTips="不能为空&&不能超过50个字符" />
            	<input type="hidden" name="pid" id="pid" value="0" /> 
            </div>
        </div>
	</form>
	
	<!-- 添加二级品类 -->
	<form class="Ding-Form modal-form form-horizontal" id="editCategoryForm" successCallback="updateFirstSuccess" 
	action="<%=request.getContextPath()%>/manager/business/category/updateCategory.shtml">
		<div class="form-group">
			<label class="col-lg-3 control-label">类别名称</label>
            <div class="col-lg-8">
            	<input class="form-control" type="text" id="editName" name="name" validations="required&&vComplexMaxLength=50" validationTips="不能为空&&不能超过50个字符" />
            	<input type="hidden" name="id" id="editId" />
            	<input type="hidden" id="editIndex" />
            </div>
        </div>
	</form>
</body>
</html>
