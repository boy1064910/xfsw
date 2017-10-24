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
    title: '知识点名称',
    align: 'center'
});
columns.push({
    field: 'videoUrl',
    title: '知识点视频链接',
    align: 'center',
    formatter:function(value,row,index){
        return '<a href="'+value+'" target="_blank">'+value+'</a>';
    }
});
columns.push({
    field: 'id',
    title: '操作',
    align: 'center',
    formatter:function(value,row,index){
        var result ;
        result = '<a href="javascript:void(0)" onclick="initEdit('+row.id+','+index+')" title="编辑">编辑</a>';
        result += '<a href="javascript:void(0)" onclick="initSettle('+row.id+',\''+row.code+'\')" title="设置习题难度">设置习题难度</a>';
        result +='<a href="javascript:void(0)" onclick="initDelete('+row.id+')" title="删除">删除</a>';
        return result;
    }
});

var videoUploader;
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

    DingUploaderManager.loadSign(function() {
        videoUploader = new Ding.FileUploader({
            'id' : 'video',
            'limitType' : 'mp4,rmvb,rm,hlv,flv',
            'preventDuplicate' : false,
            'needPreview' : false
        });
    });

    Ding.ajax({
        'url' : "/acadamic-web-manager/manager/course/chapter/knowledge/list.shtml?chapterCode="+Ding.getQueryParameterByName("chapterCode"),
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
    this.location = '/acadamic-web-manager/manager/course/chapter/knowledge/initAddKnowledge.shtml?breadSequence=1&chapterCode='+Ding.getQueryParameterByName("chapterCode");
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

function submitValidation(){
    if(Ding.isEmpty($("#id").val())){
        if(videoUploader.uploader.files.length<1){
            $.alert({
                title:'温馨提示',
                content: '请先上传知识点教学视频！',
                confirmButton:'确定'
            });
            return false;
        }
        D("#form").submitParams['videoUrl'] = videoUploader.uploader.files[0].uploadPath;
    }
    return true;
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
    this.location = projectName + '/manager/acadamic/course/chapter/knowledge/diff/level/index.shtml?knowledgeId='+id+'&knowledgeCode='+code+'&breadSequence=1';
}