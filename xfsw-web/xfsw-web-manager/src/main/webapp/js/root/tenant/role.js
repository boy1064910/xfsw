//该角色已存在的权限id数组
var exsitAuthorityIds = [];

var setting = {
	check: {
		enable: true
	},
	data: {
		simpleData: {
			enable: true
		}
	}
};
var zNodes = {};
var treeObj = {}
var code;


function loadAuthorityTab(pid){
	Ding.ajax({
		'url':'/xfsw-web-auth//manager/authority/selectAuthorityModelListByPid.shtml',
		'params':{
			'tenantId':$("#tenantId").val(),
			'pid':pid
		},
		'successCallback':function(result){
			$("#treeDemo"+pid).attr("rendered",true);
			zNodes[pid] = result.data;
			if(zNodes[pid].length!=0){
				var nodeMap = {};
				for(var i=0;i<zNodes[pid].length;i++){
					nodeMap[zNodes[pid][i].id] = i;
				}
				for(var i=0;i<exsitAuthorityIds.length;i++){
					//获取对应权限的下标,把这个tree节点设置未选中效果
					var index = nodeMap[exsitAuthorityIds[i]];
					if(!Ding.isEmpty(index))
						zNodes[pid][index].checked=true;
				}
				treeObj[pid] = $.fn.zTree.init($("#treeDemo"+pid), setting, zNodes[pid]);
				treeObj[pid].expandAll(true);
			}
		}
	});
}

function loadChildrenAuthorities(){
	var pAuthTabs = $("#authTab").children();
	for(var i=0;i<pAuthTabs.length;i++){
		var pid = $(pAuthTabs[i]).attr("id");
		loadAuthorityTab(pid);
	}
}

function submitValidation(){
	var nodes = [];
	for(var i in treeObj){
		//获取所有选中的树节点
		nodes = nodes.concat(treeObj[i].getCheckedNodes(true));
	}
	var ids = [];
	var types = [];
	for(var i=0; i<nodes.length; i++){
		ids.push(nodes[i].id);
		types.push(nodes[i].type);
	}
	D("#editRoleForm").submitParams['ids'] = ids;
	D("#editRoleForm").submitParams['types'] = types;
	return true;
}

function success(result){
	// this.location = projectName+"/system/role/index.shtml";
	Ding.tips("操作成功");
}