var columns = [];
columns.push({
    field: 'id',
    title: 'ID',
    align: 'center'
});
columns.push({
    field: 'code',
    title: '习题编号',
    align: 'center'
});
columns.push({
    field: 'picUrl',
    title: '习题题目',
    align: 'center',
    formatter:function(value,row,index){
        return '<img src="'+value+'" width="50" />';
    }
});
columns.push({
    field: 'answerInfo',
    title: '习题答案',
    align: 'center',
    formatter:function(value,row,index){
        var answerList = row.answerList;
        var str = '';
        if(!Ding.isEmpty(answerList)){
            for(var i=0;i<answerList.length;i++){
                str += "[<font color='green' style='font-weight:bold;'>" + answerList[i].answer + "</font>] ";
            }
        }
        return str;
    }
});
columns.push({
    field: 'id',
    title: '操作',
    align: 'center',
    formatter:function(value,row,index){
        var result ='';
        result += '<a href="javascript:void(0)" onclick="initEdit('+row.id+','+index+')" title="编辑">编辑</a>';
        result +='<a href="javascript:void(0)" onclick="initDelete('+row.id+')" title="删除">删除</a>';
        return result;
    }
});

var picUploader;
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
        picUploader = new Ding.FileUploader({
            'id' : 'exercisePic',
            'limitType' : 'jpg,jpeg,png,bmp',
            'preventDuplicate' : false,
            'needPreview' : true
        });
    });

    Ding.ajax({
        'url' : projectName+"/manager/acadamic/course/chapter/knowledge/diff/level/exercise/list.shtml?diffLevelCode="+Ding.getQueryParameterByName("diffLevelCode"),
        'successCallback' : function(result){
            var data = {};
            data.rows = result.data;
            $("#dataTable").bootstrapTable('load',data);
        }
    })
});

function resetForm(){
    $("#id").val('');
    $("#index").val('');
    picUploader.reset();

    $("input[name='deleteAnswerIds']").remove();
    var children = $("#answerDiv").children();
    for(var i=1;i<children.length;i++){
        $(children[i]).remove();
    }
}

function initAdd(){
    openModal({
        'title':'添加练习题',
        'targetId':'form',
        'sureBtnText':'保存'
    });
}

function submitValidation(){
    if(Ding.isEmpty($("#id").val())){
        console.log(picUploader.uploader.files);
        if(picUploader.uploader.files.length<1){
            $.alert({
                title:'温馨提示',
                content: '请先上传习题题目！',
                confirmButton:'确定'
            });
            return false;
        }
        D("#form").submitParams['picUrl'] = picUploader.uploader.files[0].uploadPath;
    }

    if($("#answerDiv").children().length<=1){
        $.alert({
            title:'温馨提示',
            content: '请为习题配置答案！',
            confirmButton:'确定'
        });
        return false;
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
        title:'删除习题信息',
        content: '是否删除习题信息 ?',
        confirmButton:'删除',
        cancelButton:'取消',
        confirmButtonClass:'btn-success',
        confirm:function(){
            Ding.ajax({
                'url':projectName+'/manager/acadamic/course/chapter/knowledge/diff/level/exercise/deleteExercise.shtml',
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

function initEdit(id,index){
    $("#index").val(index);
    $("#id").val(id);
    Ding.ajax({
        'url' : projectName + '/manager/acadamic/course/chapter/knowledge/diff/level/exercise/getInfoById.shtml',
        'params' : {
            'id' : id
        },
        'successCallback' : function(result){
            var data = result.data;
            picUploader.append({
                'url' : data.picUrl
            });
            for(var i=0;i<data.answerList.length;i++){
                initAddAnswer(data.answerList[i]);
            }

            openModal({
                'title':'编辑习题信息',
                'targetId':'form',
                'sureBtnText':'保存'
            });
        }
    });
}

function initAddAnswer(answer){
    if(Ding.isEmpty(answer)){
        answer = {};
        answer.id = 0;
        answer.answer = '';
    }
    $("#answerDiv").append('<div style="margin-top:10px;">'
        +'<input type="text" name="answers" class="form-control" style="width:150px;display:inline-block" value="'+answer.answer+'" validations="required" validationTips="答案不能为空" />'
        +'<input type="hidden" name="answerIds" value="'+answer.id+'" />'
        +'<button class="btn btn-danger" style="margin-left:10px;" type="button" onclick="deleteAnswer(this)">删除</button>'
        +'</div>');
    resetPlaceHolder();
}

function deleteAnswer(btn){
    var answerId = $(btn).prev().val();
    if(answerId!=0){
        $("#id").after('<input type="hidden" name="deleteAnswerIds" value="'+answerId+'" />');    
    }
    $(btn).parent().remove();
    resetPlaceHolder();
}

function resetPlaceHolder(){
    var inputList = $("#answerDiv").children();
    if(inputList.length>1){
        for(var i=1;i<inputList.length;i++){
            $(inputList[i]).children('input[name="answers"]').attr("placeholder","答案"+i);
        }
    }
}