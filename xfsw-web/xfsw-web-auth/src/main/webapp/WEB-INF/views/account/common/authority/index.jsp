<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
	/**
	*	提交数据服务器成功执行后回调函数
	*/
	function success(result){
		Ding.tips("添加成功");
		D("#AuthorityDatagrid").request();
	}
	
	function refreshAuthorityCache(){
		Ding.ajax({
			'url':'<%=request.getContextPath() %>/system/authority/refreshAuthorityList.shtml',
			successCallback:function(result){
				Ding.tips("刷新成功");
				D("#AuthorityDatagrid").request();
			}
		});
	}
	
	function initEdit(event){
		var btn = event.data.btn;
		$(btn).off("click",initEdit);
		var tr = $(btn).parent().parent();
		
		var idTd = tr.children()[0];
		var idValue = idTd.innerHTML;
		
		var nameTd = tr.children()[1];
		var nameValue = nameTd.innerHTML;
		
		var urlTd = tr.children()[2];
		var urlValue = urlTd.innerHTML;
		
		nameTd.innerHTML = '<input type="text" value="'+nameValue+'" style="width:90%;" />';
		urlTd.innerHTML = '<input type="text" value="'+urlValue+'" style="width:90%;" />';
		
		$(btn).html("保存");
		$(btn).next().html('取消');//移除刪除按鈕
		
		var delBtn = $(btn).next();
		$(delBtn).off("click",del);
		$(delBtn).on("click",{
			'nameTd':nameTd,
			'urlTd':urlTd,
			'nameValue':nameValue,
			'urlValue':urlValue,
			'btn':btn,
			'delBtn':delBtn,
			'id':idValue
		},cancel);
		
		$(btn).on("click",{
			'id':idValue,
			'name':nameValue,
			'url':urlValue,
			'btn':btn
		},update);
	}
	
	function update(event){
		var btn = event.data.btn;
		$(btn).off("click",update);
		delete event.data.btn;
		var tr = $(btn).parent().parent();
		var nameTd = tr.children()[1];
		var nameValue = $(nameTd).children()[0].value;
		
		var urlTd = tr.children()[2];
		var urlValue = $(urlTd).children()[0].value;
		
		nameTd.innerHTML = nameValue;
		urlTd.innerHTML = urlValue;
		
		event.data.name = nameValue;
		event.data.url = urlValue;
		
		$(btn).off("click",update);
		
		Ding.ajax({
			'url':'<%=request.getContextPath() %>/system/common/authority/updateCommonAuthority.shtml',
			'params':event.data,
			'successCallback':function(result){
				var idTd = tr.children()[0];
				$(idTd).html(result.data);
				
				$(btn).html("编辑");
				$(btn).on("click",initEdit);
			}
		});
	}
	
	function stateRender(rowData,td){
		var a = $('<a>编辑</a>');
		a.on("click",{'btn':a[0]},initEdit);
		var b = $('<a>删除</a>');
		b.on("click",{'id':rowData.id},del);
		$(td).append(a).append(b);
	}
	
	function save(){
		D("#addSystemAuthorityForm").submit();
	}
	
	function del(event){
		Ding.ajax({
			'url':'<%=request.getContextPath() %>/system/common/authority/deleteCommonAuthority.shtml?id='+event.data.id,
			'successCallback':function(result){
				Ding.tips("删除成功");
				D("#AuthorityDatagrid").request();
			}
		});
	}
	
	function cancel(event){
		$(event.data.nameTd).html(event.data.nameValue);
		$(event.data.urlTd).html(event.data.urlValue);
		$(event.data.btn).off("click",update);
		$(event.data.btn).html("编辑");
		$(event.data.btn).on("click",{'btn':event.data.btn},initEdit);
		$(event.data.delBtn).off("click",cancel);
		$(event.data.delBtn).html('刪除');
		$(event.data.delBtn).on("click",{'id':event.data.id},del);
	}
	
	Ding.ready(function(){
		$("#addAuthorityBtn").on("click",function(){
			openModal({
				'title':'添加公共权限',
				'targetId':'addSystemAuthorityForm',
				'sureCallback':save
			});
		});
	});
</script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/widgets/bootstrap/bootstrap-table/bootstrap-table.css">
<!-- Latest compiled and minified JavaScript -->
<script src="/widgets/bootstrap/bootstrap-table/bootstrap-table.js"></script>
<!-- Latest compiled and minified Locales -->
<script src="/widgets/bootstrap/bootstrap-table/bootstrap-table-zh-CN.js"></script>

<script type="text/javascript" src="/js/manager/account/common/authority/index.js"></script>
</head>
<body>
	<div class="panel">
		<header class="panel-heading">
			<button class="btn btn-info " type="button" onclick="initAddCommonAuthority()"><i class="fa fa-plus"></i> 添加权限</button>
			<button class="btn btn-info " type="button" onclick="refreshAuthorityCache()"><i class="fa fa-refresh"></i> 刷新权限缓存</button>
        </header>
		<div class="panel-body">
			<table id="dataTable"></table>
		</div>
	</div>
	
	<form class="Ding-Form modal-form form-horizontal" id="addForm" action="<%=request.getContextPath() %>/manager/account/common/authority/insertCommonAuthority.shtml" successCallback="insertSuccess">
		<div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">权限名称</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" id="name" name="name" validations="required&&vComplexMaxLength=15" validationTips="权限名称不能为空&&权限名称长度不能超过15个中文字符"  />
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">链接地址</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" id="url" name="url" validations="vComplexMaxLength=100" validationTips="链接地址长度不能超过100个字符" /> 
            </div>
        </div>
	</form>
	
	<form class="Ding-Form modal-form form-horizontal" id="editForm" action="<%=request.getContextPath() %>/manager/account/common/authority/updateCommonAuthority.shtml" successCallback="updateSuccess">
		<div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">权限名称</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" id="editname" name="name" validations="required&&vComplexMaxLength=15" validationTips="权限名称不能为空&&权限名称长度不能超过15个中文字符"  />
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-2 col-sm-2 control-label">链接地址</label>
            <div class="col-lg-10">
            	<input class="form-control" type="text" id="editurl" name="url" validations="vComplexMaxLength=100" validationTips="链接地址长度不能超过100个字符" /> 
            </div>
        </div>
        <input type="hidden" name="id" id="editid" />
        <input type="hidden" id="editindex" />
	</form>
</body>
</html>