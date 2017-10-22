var columns = [];
columns.push({
    field: 'id',
    title: 'ID',
    align: 'center'
});
columns.push({
    field: 'code',
    title: '课程编号',
    align: 'center'
});
columns.push({
    field: 'name',
    title: '课程名称',
    align: 'center'
});
columns.push({
    field: 'price',
    title: '价格',
    align: 'center'
});
columns.push({
    field: 'state',
    title: '状态',
    align: 'center',
    formatter:function(value,row,index){
        if(value==1){
            return '已上线';
        }
        else{
            return '未上线';
        }
    }
});
columns.push({
    field: 'id',
    title: '操作',
    align: 'center',
    formatter:function(value,row,index){
        var result ;
        result = '<a href="javascript:void(0)" onclick="initEdit('+row.id+','+index+')" title="编辑">编辑</a>';
        result += '<a href="javascript:void(0)" onclick="initSettle('+row.id+',\''+row.code+'\')" title="设置章节">设置章节</a>';
        result +='<a href="javascript:void(0)" onclick="initDelete('+row.id+')" title="删除">删除</a>';
        return result;
    }
});

Ding.ready(function(){
    $("#dataTable").bootstrapTable({
        method: 'get',
        striped: true,  
        cache: false,    
        pagination: true,   
        sortable: false,    
        sortOrder: "asc",    
        pageNumber:1,   
        pageSize: 50,  
        pageList: [20, 50, 150, 300],
        showRefresh:false,
        showToggle:false,
        singleSelect: false,
        url: "/acadamic-web-manager/manager/course/pageInfo.shtml",
        queryParams : function(params) {
            return {
                currentIndex : params.offset + 1,
                pageSize : params.limit
            };
        },
        sidePagination: "server",
        search: false,  
        idField : "id",
        uniqueId: "id",
        columns: columns,
        onLoadSuccess:function(result){
            commonResultCallback(result);
        }
    });
});

function resetForm(){
    $("#name").val('');
    $("#id").val('');
    $("#index").val('');
    $("#value").val('');
}

function initAdd(){
    openModal({
        'title':'添加课程信息',
        'targetId':'form',
        'sureBtnText':'保存'
    });
}

function initEdit(id,index){
    $("#index").val(index);
    $("#id").val(id);
    Ding.ajax({
        'url' : projectName + '/manager/acadamic/course/getById.shtml',
        'params' : {
            'id' : id
        },
        'successCallback' : function(result){
            var data = result.data;
            $("#name").val(data.name);
            $("#price").val(data.price);
            openModal({
                'title':'编辑课程信息',
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
        title:'删除课程信息',
        content: '是否删除课程信息 ?',
        confirmButton:'删除',
        cancelButton:'取消',
        confirmButtonClass:'btn-success',
        confirm:function(){
            Ding.ajax({
                'url':projectName+'/manager/acadamic/course/deleteCourse.shtml',
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
    this.location = '/acadamic-web-manager/manager/course/chapter/index.shtml?courseId='+id+"&code="+code+'&breadSequence=1';
}