var columns = [];
columns.push({
    field: 'id',
    title: 'ID',
    align: 'center'
});
columns.push({
    field: 'code',
    title: 'code',
    align: 'center'
});
columns.push({
    field: 'name',
    title: '名称',
    align: 'center'
});
columns.push({
    field: 'lastUpdater',
    title: '最后操作者',
    align: 'center'
});
columns.push({
    field: 'lastUpdateTime',
    title: '最后操作时间',
    align: 'center',
    formatter: function(value,row,index){
        return Ding.formatDate(new Date(value),'%Y-%M-%d %H:%m');
    }
});
columns.push({
    field: 'id',
    title: '操作',
    align: 'center',
    formatter:function(value,row,index){
        var result = '<a href="javascript:void(0)" onclick="initEdit('+row.id+','+index+')" title="编辑">编辑</a>';
        result+='<a href="javascript:void(0)" onclick="initDelete('+row.id+')" title="删除">删除</a>';
        return result;
    }
});
Ding.ready(function(){
    //请求商品表格数据
    $("#dataTable").bootstrapTable({
        //请求方法
        method: 'get',
        toolbar:'#toolbar',
         //是否显示行间隔色
        striped: true,
        //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）     
        cache: false,    
        //是否显示分页（*）  
        pagination: false,   
        //是否启用排序  
        sortable: false,    
        //排序方式 
        sortOrder: "asc",    
        //初始化加载第一页，默认第一页
         pageNumber:1,   
        // //每页的记录行数（*）   
         pageSize: 50,  
        // //可供选择的每页的行数（*）    
         pageList: [20, 50, 150, 300],
        showRefresh:false,
        // showToggle:true,
        singleSelect: false,
        //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据  
         url: "/xfsw-web-manager/root/tenant/pageInfo.shtml",
        //查询参数,每次调用是会带上这个参数，可自定义
        // queryParams : function(params) {
        //     return {
        //         currentIndex : params.offset + 1,
        //         pageSize : params.limit,
        //         aRandomCode : aRandomCode,
        //         name:params.search,
        //         status:$("#status").val(),
        //         country:$("#country").val()
        //     };
        // },
        //分页方式：client客户端分页，server服务端分页（*）
        sidePagination: "server",
        //是否显示搜索
        search: false,  
        // formatSearch:function(){
        //     return '买手店名称';
        // },
        searchOnEnterKey:false,
        idField : "id",
        uniqueId: "id",
        columns: columns
        // detailFormatter:function(index, row){
        //     console.log(row);
        // },
        // onLoadSuccess:function(result){
        //     if(result.resultCode!=200){
        //         Ding.tips(result.msg);
        //     }
        // }
    });
});

function initAddCategoryAuthority(){
	if(Ding.isEmpty($("#tenantId").val())){
		$.alert({
            content: '请先选择空间信息',
            confirmButton:'确定'
        });
		return false;
	}
    $("#name").val('');
    $("#pid").val('');
    $("#url").val('');
    $("#remark").val('');
    $("#orderIndex").val('');
    $("#ico").val('');
    openModal({
        'title':'添加菜单权限',
        'targetId':'addCategoryAuthorityForm',
        'sureBtnText':'保存'
    });
}

function insertSubmitValidation(){
	if(Ding.isEmpty($("#tenantId").val())){
		$.alert({
            content: '空间信息不能为空',
            confirmButton:'确定'
        });
		return false;
	}
	D("#addCategoryAuthorityForm").submitParams['tenantId'] = $("#tenantId").val();
	return true;
}

//添加权限成功回调事件
function insertSuccess(result){
    Ding.tips("添加成功");
    loadData();
}

//配置权限
function initConfig(id){
    this.location = "/xfsw-web-manager/root/category/authority/initConfigLinkAuthority.shtml?id="+id;
}


function initDelete(id){
    $.confirm({
        backgroundDismiss: true,
        title:'删除菜单权限',
        content: '删除菜单权限将会同时清除角色权限关系，是否删除 !',
        confirmButton:'删除',
        cancelButton:'取消',
        confirmButtonClass:'btn-success',
        confirm:function(){
            Ding.ajax({
                'url':projectName+'/manager/account/category/authority/deleteAuthority.shtml?id='+id,
                'successCallback':function(result){
                    $("#dataTable").bootstrapTable('removeByUniqueId',id);
                }
            });
        }
    });
}

function initEdit(id,index){
    Ding.ajax({
        'url' : '/xfsw-web-manager/root/category/authority/initEditCategoryAuthority.shtml',
        'params' : {
            'id' : id
        },
        'successCallback' : function(result){
            var data = result.data;
            $("#editid").val(data.id);
            $("#editname").val(data.name);
            $("#editpid").val(data.pid);
            $("#editurl").val(data.url);
            $("#editremark").val(data.remark);
            $("#editorderIndex").val(data.orderIndex);
            $("#editico").val(data.ico);
            $("#editindex").val(index);
            openModal({
                'title':'编辑菜单权限',
                'targetId':'editCategoryAuthorityForm',
                'sureBtnText':'保存'
            });
        }
    })
}

function updateSuccess(result){
    var data = result.data;
    var index = $("#editindex").val();
    $("#dataTable").bootstrapTable('updateRow',{
        'index' : index,
        'row' : data
    });
}


function refreshAuthorityCache(){
    Ding.ajax({
        'url':projectName+'/manager/account/category/authority/refreshAuthorityCache.shtml',
        successCallback:function(result){
            Ding.tips("刷新成功");
        }
    });
}