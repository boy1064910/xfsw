<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="/widgets/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="/widgets/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="/widgets/ztree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="/js/manager/account/role/role.js"></script>
<script type="text/javascript" src="/js/manager/account/role/initEdit.js"></script>
</head>
<body>
	<input type="hidden" id="roleId" value="${role.id }" />
	<form class="Ding-Form form" id="editRoleForm" action="<%=request.getContextPath()%>/manager/account/role/editRole.shtml" submitValidation="submitValidation" successCallback="success">
		<div class="panel">
			<div class="panel-body">
				<div class="form-group">
		            <label class="col-lg-2 col-sm-2 control-label">角色名称</label>
	            	<input class="form-control" type="text" id="name" name="name" value="${role.name }" validations="required&&vComplexMaxLength=20" validationTips="角色名称不能为空&&名称长度不能超过20个字符"  />
		        </div>
		        <div class="form-group">
		            <label class="col-lg-2 col-sm-2 control-label">备注</label>
					<input class="form-control" type="text" name="remark" value="${role.remark }" validations="vComplexMaxLength=100" validationTips="备注长度不能超过100个字符"  /> 
		        </div>
			</div>
		</div>
		<div class="panel">
			<header class="panel-heading custom-tab turquoise-tab">
			<ul class="nav nav-tabs" id="authTab">
				<c:forEach items="${firstAuthorityList }" var="f" varStatus="c">
					<c:if test="${c.index==0 }">
						<li class="active" id="${f.id }">
							<a data-toggle="tab" href="#tree${f.id }"> <i class="fa">${f.name }</i></a>
						</li>
					</c:if>
					<c:if test="${c.index!=0 }">
						<li id="${f.id }">
							<a data-toggle="tab" href="#tree${f.id }"> <i class="fa">${f.name }</i></a>
						</li>
					</c:if>
				</c:forEach>
			</ul>
			</header>
			<div class="panel-body">
				<div class="tab-content">
					<c:forEach items="${firstAuthorityList }" var="f" varStatus="c">
						<c:if test="${c.index==0 }">
							<div id="tree${f.id }" class="tab-pane active">
								<button class="btn btn-success" type="button" onclick="reloadAuthority(${f.id })">刷新</button>
								<ul id="treeDemo${f.id }" class="ztree"></ul>
							</div>
						</c:if>
						<c:if test="${c.index!=0 }">
							<div id="tree${f.id }" class="tab-pane">
								<button class="btn btn-success" type="button" onclick="reloadAuthority(${f.id })">刷新</button>
								<ul id="treeDemo${f.id }" class="ztree"></ul>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
			<div class="panel-body">
				<input type="hidden" name="id" value="${role.id }" />
				<button class="btn btn-success" type="button" onclick="save()"><i class="fa fa-save"></i> 保存</button>
				<button class="btn " type="button" onclick="back()"><i class="fa fa-arrow-left"></i> 返回</button>
			</div>
		</div>
	</form>

</body>
</html>