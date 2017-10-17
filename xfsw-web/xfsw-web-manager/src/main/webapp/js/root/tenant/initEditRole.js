Ding.ready(function(){
	Ding.ajax({
		'url':'/xfsw-web-manager/root/tenant/selectAuthorityListByRoleId.shtml',
		'params':{
			'roleId':$("#roleId").val()
		},
		'successCallback':function(result){
			exsitAuthorityIds = result.data;
			loadChildrenAuthorities();
		}
	});
});