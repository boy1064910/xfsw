var columns = [];
columns.push({
    field: 'id',
    title: 'ID',
    align: 'center'
});
columns.push({
    field: 'sequence',
    title: '章节序号',
    align: 'center'
});
columns.push({
    field: 'code',
    title: '章节编号',
    align: 'center'
});
columns.push({
    field: 'name',
    title: '章节名称',
    align: 'center'
});
columns.push({
    field: 'info',
    title: '章节介绍',
    align: 'center'
});
columns.push({
    field: 'id',
    title: '操作',
    align: 'center',
    formatter:function(value,row,index){
        var result ;
        result = '<a href="javascript:void(0)" onclick="initEdit('+row.id+','+index+')" title="编辑">编辑</a>';
        result += '<a href="javascript:void(0)" onclick="initSettle('+row.id+',\''+row.code+'\')" title="设置知识点">设置知识点</a>';
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
        'url' : "/acadamic-web-manager/manager/course/chapter/list.shtml?courseCode="+Ding.getQueryParameterByName("code"),
        'successCallback' : function(result){
            var data = {};
            data.rows = result.data;
            $("#dataTable").bootstrapTable('load',data);
        }
    })
});

function resetForm(){
    $("#name").val('');
    $("#info").val('');
    $("#id").val('');
    $("#index").val('');
    $("#value").val('');
}

function initAdd(){
    openModal({
        'title':'添加章节信息',
        'targetId':'form',
        'sureBtnText':'保存'
    });
}

function initEdit(id,index){
    $("#index").val(index);
    $("#id").val(id);
    Ding.ajax({
        'url' : projectName + '/manager/acadamic/course/chapter/getById.shtml',
        'params' : {
            'id' : id
        },
        'successCallback' : function(result){
            var data = result.data;
            $("#name").val(data.name);
            $("#price").val(data.price);
            openModal({
                'title':'编辑章节信息',
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
        title:'删除章节信息',
        content: '是否删除章节信息 ?',
        confirmButton:'删除',
        cancelButton:'取消',
        confirmButtonClass:'btn-success',
        confirm:function(){
            Ding.ajax({
                'url':projectName+'/manager/acadamic/course/chapter/deleteChapter.shtml',
                'params':{
                    'id' : id
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
    this.location = '/acadamic-web-manager/manager/course/chapter/knowledge/index.shtml?chapterId='+id+'&chapterCode='+code+'&breadSequence=1';
}