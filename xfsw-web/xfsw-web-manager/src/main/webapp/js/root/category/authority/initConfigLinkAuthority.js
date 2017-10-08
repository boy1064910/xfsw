var aRandomCode = 'AccountLinkAuthority';
var columns = [];
columns.push({
    field: 'id',
    title: 'ID',
    align: 'center'
});
columns.push({
    field: 'name',
    title: '功能权限名称',
    align: 'center'
});
columns.push({
    field: 'url',
    title: '链接地址',
    align: 'center'
});
columns.push({
    field: 'id',
    title: '操作',
    align: 'center',
    formatter:function(value,row,index){
        var result = '<a href="javascript:void(0)" onclick="initEdit('+row.id+','+index+')" title="编辑">编辑</a>';
        result+='<a href="javascript:void(0)" onclick="initDel('+row.id+')" title="删除">删除</a>';
        result+='<a href="javascript:void(0)" onclick="copyToOnline('+row.id+')" title="复制到线上">复制到线上</a>';
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
        // pageNumber:1,   
        // //每页的记录行数（*）   
        // pageSize: 50,  
        // //可供选择的每页的行数（*）    
        // pageList: [20, 50, 150, 300],
        showRefresh:false,
        // showToggle:true,
        singleSelect: false,
        //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据  
        // url: projectName+"/manager/data/pageInfo.shtml?aRandomCode="+aRandomCode,
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
        url: '',
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

    loadData();
});

function loadData(){
    Ding.ajax({
        'url' : projectName+"/manager/data/list.shtml",
        'params' : {
            'authorityId' : $("#categoryAuthorityId").val(),
            'aRandomCode' : aRandomCode
        },
        'successCallback' : function(result){
            var data = {};
            data.rows = result.data;
            $("#dataTable").bootstrapTable('load',data);
        }
    })
}

function initAdd(){
    $("#name").val('');
    $("#url").val('');
    openModal({
        'title':'添加功能权限',
        'targetId':'addLinkAuthorityForm',
        'sureBtnText':'保存'
    });
}

function insertSuccess(result){
    loadData();
}

function initEdit(id,index){
    Ding.ajax({
        'url' : projectName + '/manager/account/category/authority/initEditLinkAuthority.shtml',
        'params' : {
            'id' : id
        },
        'successCallback' : function(result){
            var data = result.data;
            $("#editId").val(data.id);
            $("#editName").val(data.name);
            $("#editUrl").val(data.url);
            $("#editIndex").val(index);
            openModal({
                'title':'编辑功能权限',
                'targetId':'editLinkAuthorityForm',
                'sureBtnText':'保存'
            });
        }
    })
}

function updateSuccess(result){
    var data = result.data;
    var index = $("#editIndex").val();
    $("#dataTable").bootstrapTable('updateRow',{
        'index' : index,
        'row' : data
    });
}

function initDel(id){
    $.confirm({
        backgroundDismiss: true,
        title:'删除功能权限',
        content: '删除功能权限将会同时清除角色权限关系和角色权限SQL配置信息，是否删除 !',
        confirmButton:'删除',
        cancelButton:'取消',
        confirmButtonClass:'btn-success',
        confirm:function(){
            Ding.ajax({
                'url':projectName+'/manager/account/category/authority/deleteLinkAuthority.shtml?id='+id,
                'successCallback':function(result){
                    $("#dataTable").bootstrapTable('removeByUniqueId',id);
                }
            });
        }
    });
}

function copyToOnline(id){
    $.alert({
        title:'学术葩提示',
        content: '【学术葩】尚未部署线上服务器',
        confirmButton:'确定'
    });
}