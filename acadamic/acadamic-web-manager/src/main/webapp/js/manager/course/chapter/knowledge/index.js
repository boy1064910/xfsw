var columns = [];
columns.push({
    field: 'id',
    title: 'ID',
    align: 'center'
});
columns.push({
    field: 'code',
    title: '知识点编号',
    align: 'center'
});
columns.push({
    field: 'name',
    title: '知识点标题',
    align: 'center'
});
columns.push({
    field: 'id',
    title: '操作',
    align: 'center',
    formatter:function(value,row,index){
        var result ;
        result = '<a href="javascript:void(0)" onclick="initEdit('+row.id+','+index+')" title="编辑">编辑</a>';
        result += '<a href="javascript:void(0)" onclick="initSettle('+row.id+',\''+row.code+'\')" title="设置知识点内容">内容设置</a>';
        result +='<a href="javascript:void(0)" onclick="initDelete('+row.id+')" title="删除">删除</a>';
        return result;
    }
});

Ding.ready(function(){
    $("#dataTable").bootstrapTable({
        method: 'get',
        striped: true,
        cache: false,    
        pagination: false,   
        sortable: false,    
        sortOrder: "asc",    
        showRefresh:false,
        singleSelect: false,
        sidePagination: "server",
        search: false,
        searchOnEnterKey:false,
        idField : "id",
        uniqueId: "id",
        url: '',
        columns: columns
    });

    Ding.ajax({
        'url' : "/acadamic-web-manager/manager/course/chapter/knowledge/list.shtml?chapterCode="+Ding.getQueryParameterByName("chapterCode"),
        'successCallback' : function(result){
            var data = {};
            data.rows = result.data;
            $("#dataTable").bootstrapTable('load',data);
        }
    });
});
//弹出窗口，添加知识点
function initAdd(){
	openModal({
		'title' : '添加知识点',
		'targetId' : 'form',
		'sureBtnText' : '保存'
	});
}
//重置弹出窗口表单内容
function resetForm(){
    $("#name").val('');
    $("#id").val('');
    $("#index").val('');
}

function initEdit(id,index){
    $("#index").val(index);
    $("#id").val(id);
    Ding.ajax({
        'url' : '/acadamic-web-manager/manager/course/chapter/knowledge/getKnowledgeById.shtml',
        'params' : {
            'knowledgeId' : id
        },
        'successCallback' : function(result){
            var data = result.data;
            $("#name").val(data.name);
            openModal({
                'title':'编辑知识点',
                'targetId':'form',
                'sureBtnText':'保存'
            });
        }
    });
}   

function saveSuccess(result){
    if($.isEmpty($("#id").val())){
        $("#dataTable").bootstrapTable('append',result.data);
    }
    else{
        var index = $("#index").val();
        $("#dataTable").bootstrapTable('updateRow',{
            'index' : index,
            'row' : result.data
        });
    }
    resetForm();
}

function initDelete(id){
    $.confirm({
        backgroundDismiss: true,
        title:'删除知识点',
        content: '是否删除知识点 ?',
        confirmButton:'删除',
        cancelButton:'取消',
        confirmButtonClass:'btn-success',
        confirm:function(){
            Ding.ajax({
                'url':'/acadamic-web-manager/manager/course/chapter/knowledge/deleteKnowledgeById.shtml',
                'params':{
                    'knowledgeId' : id
                },
                'method':'post',
                'successCallback':function(result){
                    $("#dataTable").bootstrapTable('removeByUniqueId',id);
                }
            });
        }
    });
}

function initSettle(id,code){
    this.location = '/acadamic-web-manager/manager/course/chapter/knowledge/initSettle.shtml?knowledgeId='+id+'&breadSequence=1';
}