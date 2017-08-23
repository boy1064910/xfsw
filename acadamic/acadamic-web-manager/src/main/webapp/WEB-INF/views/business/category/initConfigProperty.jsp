<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="/widgets/bootstrap/bootstrap-switch-master/css/bootstrap3/bootstrap-switch.min.css" rel="stylesheet">
<script src="/widgets/bootstrap/bootstrap-switch-master/js/bootstrap-switch.min.js"></script>
<link href="/css/business/property.css" rel="stylesheet">
<script type="text/javascript" src="/js/business/property.js"></script>
<style>
.property-value-input{
	float:left;
	margin-right:10px;
	width:50%;
}
.property-value-input-div:after{
	content:" ";
	display:block;
	clear:both;
}
</style>
<script type="text/javascript">
	var aRandomCode = "businessCategoryPropertyConfig";

	var saveBtn = '<a class="saveBtn" onclick="saveProperty(this)">保存</a>';
	var delBtn = '<a class="delBtn" onclick="deleteProperty(this)">删除</a>';
	var newValueBtn = '<a class="newValueBtn" onclick="newPropertyValue(this)">新增字段值</a>';
	var vInput = '<input type="text" style="float:left;width:70%;"/>';
	var deleteBtn = '<span class="ding-ico-cancel" onclick="deletePropertyValue(this)" style="float:left;height:30px;"></span>';
	var editBtn = '<a onclick="editProperty(this)">编辑</a>';
	
	Ding.ready(function(){
		
	});
	
	function newPropertyValue(btn){
		$($(btn).parent().parent().children()[2]).append('<div style="margin:7px;height:30px;width:95%;"><input type="text" value="${p.value }" style="float:left;width:70%;"/><a class="ding-ico ding-ico-cancel" onclick="deletePropertyValue(this)" style="float:left;height:30px;"></a></p>');
	}
	
	function deletePropertyValue(btn){
		var id = $(btn).parent().attr("id");
		if(Ding.isEmpty(id)){
			$(btn).parent().remove();
		}
		else{
			Ding.ajax({
				'url':'<%=request.getContextPath() %>/category/deletePropertyValue.shtml',
				'params':{'id':id},
				'successCallback':function(result){
					$(btn).parent().remove();
				}
			});
		}
	}
	
	function removePropertyType(btn){
		$(btn).parent().parent().remove();
	}
</script>
<style>
.system table{
	width:700px;
	margin:0;
}
</style>
</head>
<body>
	<div class="panel" id="propertyPanel">
		<header class="panel-heading">品类：${category.name }</header>
		<div id="addDiv" class="panel-body">
			<button class="btn btn-default" type="button" id="addPropertyTypeBtn" onclick="initAddPropertyType()">添加品类属性类别</button>
			<input type="hidden" id="categoryId" value="${category.id }" />
		</div>
		<c:forEach var="propertyType" items="${propertyTypeList}" varStatus="status">
		<div class="panel-body" propertyTypeId="${propertyType.id }">
			<p>
				属性类型：
				<span id="propertyTypeName${propertyType.id }" style="color:#000;">${propertyType.name }
					<c:if test="${propertyType.ename!=null }">
					(${propertyType.ename })
					</c:if>
				</span>
				<c:if test="${propertyType.isSaleProperty==0 }">
					<button class="btn btn-default" style="margin-left:20px;" type="button" onclick="editPropertyType('${propertyType.id }','${propertyType.name }')"><i class="fa fa-pencil"></i> 编辑</button>
					<button id="delTypeBtn" class="btn btn-default" type="button" onclick="delPropertyType('${propertyType.id }',this)"><i class="fa fa-times"></i> 删除</button>
					<c:if test="${status.index!=0 }">
						<button class="btn btn-default" id="upBtn" type="button" onclick="up(this)"><i class="fa fa-arrow-circle-up"></i> 上移</button>
					</c:if>
					<c:if test="${fn:length(propertyTypeList)-2!=status.index}">
						<button id="downBtn" class="btn btn-default" type="button" onclick="down(this)"><i class="fa fa-arrow-circle-down"></i> 下移</button>
					</c:if>
				</c:if>
				<c:if test="${propertyType.isSaleProperty==0 }">
				<button class="btn btn-default" type="button" onclick="addProperty('${propertyType.id }')">添加属性字段</button>
				</c:if>
				<c:if test="${propertyType.isSaleProperty==1 }">
				<button class="btn btn-default" type="button" onclick="addSaleProperty('${propertyType.id }',this)">添加属性字段</button>
				</c:if>
				<c:if test="${propertyType.isSaleProperty==0 }">
					<button class="btn btn-default" type="button" onclick="initCopyPropertyType('${propertyType.id }')">Copy to others</button>
				</c:if>
			</p>
			<table class="table table-bordered table-striped" id="${propertyType.id }" is-sale-property="${propertyType.isSaleProperty }">
				<tr>
					<th width="5%">ID</th>
					<th width="8%">字段名称</th>
					<th width="8%">字段类型</th>
					<th width="10%">内容示例</th>
					<th width="200px">Selection内容</th>
					<th width="300px">字段校验</th>
					<th width="10%">校验提示语</th>
					<th width="5%">字段单位</th>
					<th width="100px">添加多个?</th>
					<th width="10%">操作</th>
				</tr>
				<c:forEach var="property" items="${propertyList}" varStatus="status2" begin="0" step="1">
				<c:if test="${property.propertyTypeId==propertyType.id }"> 
					<tr>
					<td id="idTd">${property.id }</td>
					<td id="nameTd">${property.name }</td>
					<td id="typeTd">
						<c:if test="${property.textBoxType=='input' }">
							单行文本框
						</c:if>
						<c:if test="${property.textBoxType=='textarea' }">
							多行文本框
						</c:if>
						<c:if test="${property.textBoxType=='select' }">
							下拉列表
						</c:if>
					</td>
					<td id="exampleTd">${property.placeholder }</td>
					<c:if test="${property.textBoxType=='select' }">
					<td id="valueTd" onclick="operatePropertySelector(this,'${property.id}')" class="cursor-pointer" td-id="propertyValueTd${property.id }">
						<c:if test="${fn:length(property.propertyValueList)==0 }">
							点击配置Selection
						</c:if>
						<c:if test="${property.propertyValueList!=null }">
							<c:forEach items="${property.propertyValueList }" var="p">
							<c:if test="${p.propertyId==property.id }">
							<div data-property-id="${p.id}">${p.value }</div>
							</c:if>
							</c:forEach>
						</c:if>
					</td>
					</c:if>
					<c:if test="${property.textBoxType!='select' }">
					<td id="valueTd">
					</td>
					</c:if>
					<td id="validationTd">
						<c:forEach items="${property.propertyValidationList }" var="p">
						<c:if test="${p.propertyId==property.id }">
						<div>${p.name }</div>
						</c:if>
						</c:forEach>
					</td>
					<td id="validationTipTd">
						<c:forEach items="${property.propertyValidationList }" var="p">
						<c:if test="${p.propertyId==property.id }">
						<div>${p.validationTip }</div>
						</c:if>
						</c:forEach>
					</td>
					<td id="unitTd">
						${property.propertyUnit }
					</td>
					<td id="moreTd">
						<c:if test="${property.isAllowMoreValue==0 }">
						不允许
						</c:if>
						<c:if test="${property.isAllowMoreValue==1 }">
						允许
						</c:if>
					</td>
					<td id="opTd">
						<c:if test="${propertyType.isSaleProperty==0 }">
						<a onclick="editProperty(this)">编辑</a>
						<a class="delete" onclick="deleteProperty(this)">删除</a>
						</c:if>
						<c:if test="${propertyType.isSaleProperty==1 }">
						<a onclick="editSaleProperty(this)">编辑</a>
						</c:if>
					</td>
				</tr>
				</c:if>
				</c:forEach>
			</table>
		</div>
		</c:forEach>
		
		<div class="panel-body">
			<p>
				属性类型：<span style="color:#000;">测量属性</span>
				<button class="btn btn-default" type="button" onclick="addMeasureProperty()">添加属性字段</button>
			</p>
			<table class="table table-bordered table-striped" id="measurePropertyTable" style="width:60%;">
				<tr>
					<th width="5%">ID</th>
					<th width="8%">字段名称</th>
					<th width="10%">内容示例</th>
					<th width="5%">字段单位</th>
					<th width="5%">操作</th>
				</tr>
				<c:forEach var="property" items="${measurePropertyList}" begin="0" step="1">
				<tr>
					<td id="idTd">${property.id }</td>
					<td id="nameTd">${property.name }</td>
					<td id="exampleTd">${property.placeholder }</td>
					<td id="unitTd">
						${property.propertyUnit }
					</td>
					<td id="opTd">
						<a onclick="editMeasureProperty(this)">编辑</a>
						<a class="delete" onclick="delMeasureProperty(this)">删除</a>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
	<form class="Ding-Form modal-form form-horizontal" id="form" action="<%=request.getContextPath() %>/business/category/updatePropertyType.shtml" successCallback="updatePropertyTypeSuccess">
		<div class="form-group">
            <label class="col-lg-3 col-sm-3 control-label">属性类型名称</label>
            <div class="col-lg-9">
            	<input type="hidden" id="propertyTypeId" name="id"/>
            	<input id="propertyTypeName" class="form-control" type="text" name="name" validations="required&&vComplexMaxLength=20" validationTips="名称不能为空&&名称长度不能超过20个字符"  />
            </div>
        </div>
        <div class="form-group">
        	<label class="col-lg-3 col-sm-3 control-label">英文名称</label>
            <div class="col-lg-9">
            	<input class="form-control" type="text" id="ename" name="ename" validations="vComplexMaxLength=20" validationTips="英文名称长度不能超过20个字符"  />
            </div>
        </div>
	</form>
	
	<form class="Ding-Form modal-form form-horizontal" id="addform" action="<%=request.getContextPath() %>/business/category/insertPropertyType.shtml" successCallback="insertPropertyTypeSuccess">
		<div class="form-group">
            <label class="col-lg-3 col-sm-3 control-label">属性类型名称</label>
            <div class="col-lg-9">
            	<input type="hidden" name="categoryId" value="${category.id }" />
            	<input id="addPropertyTypeName" class="form-control" type="text" name="name" validations="required&&vComplexMaxLength=20" validationTips="名称不能为空&&名称长度不能超过20个字符"  />
            </div>
        </div>
        <div class="form-group">
        	<label class="col-lg-3 col-sm-3 control-label">英文名称</label>
            <div class="col-lg-9">
            	<input class="form-control" type="text" name="ename" validations="required&&vComplexMaxLength=20" validationTips="英文名称不能为空&&英文名称长度不能超过20个字符"  />
            </div>
        </div>
	</form>
	
	<form class="Ding-Form modal-form form-horizontal" id="copyPropertyTypeForm" action="<%=request.getContextPath() %>/business/category/copyPropertyType.shtml" successCallback="copyPropertyTypeSuccess">
		<div class="form-group">
            <label class="col-lg-3 col-sm-3 control-label">复制的目标品类</label>
            <div class="col-lg-9">
            	<select class="Ding-Selector" name="categoryId" id="copyTargetCategory" url="<%=request.getContextPath()%>/business/category/list.shtml?pid=0"
					valueProperty="id" htmlProperty="name" validations="required" validationTips="1级品类不能为空"></select>
            	<input type="hidden" name="propertyTypeId" id="copyPropertyTypeId" />
            </div>
        </div>
	</form>
	
	<form class="Ding-Form modal-form" id="propertyValueForm" action="<%=request.getContextPath() %>/business/category/updatePropertyValues.shtml" 
		successCallback="savePropertyValueSuccess" submitValidation="propertyValueSubmitValidation">
		<div class="form-group">
			<div class="button-group" style="margin:5px 0;">
				<button class="btn btn-info" type="button" onclick="addPropertyValue()"><i class="fa fa-plus"> 添加</i></button>
				<input type="hidden" id="valueEditPropertyId" name="propertyId" />
			</div>
            <div id="propertyValueList">
            	
            </div>
        </div>
	</form>
</body>
</html>


