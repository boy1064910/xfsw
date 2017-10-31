var answerUploader;
Ding.ready(function(){
	DingUploaderManager.loadSign(function() {
		answerUploader = new Ding.FileUploader({
			'id':"answerUploader",
			'multiSelection':true,
			'selectorTitle':'请上传预设答案',
			'limitType':'jpg,jpeg,png',
			'maxFileSize':'2m',
			'addedCallback':function(uploader,files,fileUploader){
				for(var f in files){
					$("#answerDiv").append('<div class="pre_answer_div" id="answer-'+files[f].id+'" ></div>');
				}
			},
			'completeCallback':function(uploader,files,fileUploader){
				for(var f in files){
					var url = DingUploaderManager.host+'/'+files[f].uploadPath;
					$("#answer-"+files[f].id).css("background","url("+url+")");
					$("#answer-"+files[f].id).css({
						"background":"url("+url+")",
						"background-repeat":"no-repeat",
						"background-position":"center",
						"background-size": "contain"
					});
				}
				fileUploader.reset();
			}
		});
    });
	
	Ding.ajax({
        'url' : '/acadamic-web-manager/manager/pre/answer/list.shtml',
        'successCallback' : function(result){
            var data = result.data;
            console.log(data);
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