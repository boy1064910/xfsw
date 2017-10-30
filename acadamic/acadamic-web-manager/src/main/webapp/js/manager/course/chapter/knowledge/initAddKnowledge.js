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
    DingUploaderManager.loadSign(function() {
        videoUploader = new Ding.FileUploader({
            'id' : 'video',
            'limitType' : 'mp4,rmvb,rm,hlv,flv',
            'preventDuplicate' : false,
            'needPreview' : false
        });
    });
    
    var questionUploader = new Ding.FileUploader({
		'id':"questionDiv",
		'multiSelection':false,
		'selectorTitle':'请上传题目',
		'limitType':'jpg,jpeg,png',
		'maxFileSize':'2m',
		'addedCallback':function(uploader,files,fileUploader){
			for(var f in files){
				var jpreviewDiv = $('<div class="preview-div"></div>');
				var jprocessDiv = $('<div class="progress" id="progress'+files[f].id+'"></div>');
				var jprocessBar = $('<div class="progress-bar" id="progressBar'+files[f].id+'"></div>');
				jprocessDiv.append(jprocessBar);
				jpreviewDiv.append(jprocessDiv);
				fileUploader.jcontainer.append(jpreviewDiv);
			}
		},
		'completeCallback':function(uploader,files,fileUploader){
			fileUploader.reset();
			fileUploader.jdom.find("img").remove();
			var url = DingUploaderManager.host+'/'+files[0].uploadPath;
			$("#questionDiv").append('<img src="'+url+'"></img>');
		}
	});
    
//    var answerUploader = new Ding.FileUploader({
//		'id':answerRandomId,
//		'multiSelection':true,
//		'selectorTitle':'上传答案选项',
//		'limitType':'jpg,jpeg,png',
//		'maxFileSize':'1m',
//		'addedCallback':function(uploader,files,fileUploader){
//			for(var f in files){
//				fileUploader.jcontainer.append('<div class="answer_div"><img id="answer'+files[f].id+'" /></div>');
//			}
//		},
//		'completeCallback':function(uploader,files,fileUploader){
//			for(var f in files){
//				var url = DingUploaderManager.host+'/'+files[f].uploadPath;
//				$("#answer"+files[f].id).attr("src",url);
//				$("#answer"+files[f].id).parent().css("width","auto");
//				$("#answer"+files[f].id).on("click",function(){
//					if(!Ding.isEmpty($(".answer_div_selected")[0])){
//						var imgUrl = $(this).attr("src");
//						var selectedAnswerDiv = $(".answer_div_selected");
//						selectedAnswerDiv.empty();
//						selectedAnswerDiv.append('<img src="'+imgUrl+'" />');
//						var nextSelectAnswerDiv = selectedAnswerDiv.next();
//						selectedAnswerDiv.parent().children().removeClass("answer_div_selected");
//						if(!Ding.isEmpty(nextSelectAnswerDiv[0])){
//							nextSelectAnswerDiv.addClass("answer_div_selected");
//						}
//					}
//				});
//			}
//		}
//	});
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
        'title':'添加知识点',
        'targetId':'form',
        'sureBtnText':'保存'
    });
    this.location = '/acadamic-web-manager/manager/course/chapter/knowledge/initAddKnowledge.shtml?chapterCode='+Ding.getQueryParameterByName("chapterCode");
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

function initAddPoint(type){
	var outerPanel = $('<div class="col-md-4"></div>');
	var panel = $('<div class="panel"></div>');
	var innerPanel = $('<div class="panel-body point-panel-body"></div>');
	
	var headerPanel = $('<header class="panel-heading"><label>'+type+'</label><input type="text" placeholder="标题" class="form-control form-control-60" /></header>');
	innerPanel.append(headerPanel);
	
	var btnFormGroup = $('<div class="form-group btn-form-group"></form>');
	var addBtnDiv = $('<div class="add_btn_panel"><i class="fa fa-plus-square-o" onclick="addPointInfo(this)"></i></div>');
	btnFormGroup.append(addBtnDiv);
	
	var addInfoBtnDiv = $('<div class="add_info_btn_panel"></div>');
	var addVideoBtn =$('<div><p><i class="fa fa-video-camera"></i></p><p class="add_info_text">视频</p></div>');
	addVideoBtn.on("click",function(){
		var randomId = Ding.randomId(8);
		var videoFormGroup = $('<div class="form-group"></div>');
		var videoDiv = $('<div id="'+randomId+'" class="point_div"></div>');
		videoFormGroup.append(videoDiv);
		btnFormGroup.before(videoFormGroup);
		new Ding.FileUploader({
			'id':randomId,
			'multiSelection':false,
			'selectorTitle':'请选择视频文件',
			'limitType':'mp4,ogg',
			'maxFileSize':'20m',
			'addedCallback':function(uploader,files,fileUploader){
				for(var f in files){
					var jpreviewDiv = $('<div class="preview-div"></div>');
					var jprocessDiv = $('<div class="progress" id="progress'+files[f].id+'"></div>');
					var jprocessBar = $('<div class="progress-bar" id="progressBar'+files[f].id+'"></div>');
					jprocessDiv.append(jprocessBar);
					
					jpreviewDiv.append(jprocessDiv);
					fileUploader.jcontainer.append(jpreviewDiv);
				}
			},
			'completeCallback':function(uploader,files){
				videoDiv.empty();
//				var url = DingUploaderManager.host+'/'+files[0].uploadPath+'?OSSAccessKeyId='+DingUploaderManager.uploadParams.OSSAccessKeyId+'&Expires='+DingUploaderManager.expire+'&Signature='+DingUploaderManager.uploadParams.signature;
				var url = DingUploaderManager.host+'/'+files[0].uploadPath;
				videoDiv.append('<video src="'+url+'" controls="controls"></video>');
				
				var deletePointDiv = $('<div class="delete_point_mask_div"><i class="fa fa-trash-o"></i></div>');
				videoDiv.append(deletePointDiv);
				deletePointDiv.on("click",function(){
					videoDiv.parent().remove();
				});
			}
		});
	});
	var addPicBtn =$('<div><p><i class="fa fa-picture-o"></i></p><p class="add_info_text">图片</p></div>');
	addPicBtn.on("click",function(){
		var randomId = Ding.randomId(8);
		var picFormGroup = $('<div class="form-group"></div>');
		var picDiv = $('<div id="'+randomId+'" class="point_div"></div>');
		picFormGroup.append(picDiv);
		btnFormGroup.before(picFormGroup);
		new Ding.FileUploader({
			'id':randomId,
			'multiSelection':false,
			'selectorTitle':'请选择图片文件',
			'limitType':'jpg,jpeg',
			'maxFileSize':'2m',
			'addedCallback':function(uploader,files,fileUploader){
				for(var f in files){
					var jpreviewDiv = $('<div class="preview-div"></div>');
					var jprocessDiv = $('<div class="progress" id="progress'+files[f].id+'"></div>');
					var jprocessBar = $('<div class="progress-bar" id="progressBar'+files[f].id+'"></div>');
					jprocessDiv.append(jprocessBar);
					jpreviewDiv.append(jprocessDiv);
					fileUploader.jcontainer.append(jpreviewDiv);
				}
			},
			'completeCallback':function(uploader,files){
				picDiv.empty();
//				var url = DingUploaderManager.host+'/'+files[0].uploadPath+'?OSSAccessKeyId='+DingUploaderManager.uploadParams.OSSAccessKeyId+'&Expires='+DingUploaderManager.expire+'&Signature='+DingUploaderManager.uploadParams.signature;
				var url = DingUploaderManager.host+'/'+files[0].uploadPath;
				picDiv.append('<img src="'+url+'"></img>');
				
				var deletePointDiv = $('<div class="delete_point_mask_div"><i class="fa fa-trash-o"></i></div>');
				picDiv.append(deletePointDiv);
				deletePointDiv.on("click",function(){
					picDiv.parent().remove();
				});
			}
		});
	});
	
	var addTextBtn =$('<div><p><i class="fa fa-file-text-o"></i></p><p class="add_info_text">文字</p></div>');
	addTextBtn.on("click",function(){
		var randomId = Ding.randomId(8);
		var textFormGroup = $('<div class="form-group"></div>');
		var textDiv = $('<div id="'+randomId+'" class="point_div"><textarea class="form-control"></textarea></div>');
		textFormGroup.append(textDiv);
		btnFormGroup.before(textFormGroup);
		
		var deletePointDiv = $('<div class="delete_point_div"><i class="fa fa-trash-o"></i></div>');
		textDiv.append(deletePointDiv);
		deletePointDiv.on("click",function(){
			textDiv.parent().remove();
		});
	});
	
	addInfoBtnDiv.append(addVideoBtn).append(addPicBtn).append(addTextBtn);
	addInfoBtnDiv.mouseleave(function(){
		$(this).removeClass("move_show");
		$(this).prev().removeClass("move_hide");
	});
	btnFormGroup.append(addInfoBtnDiv);
	
	innerPanel.append(btnFormGroup);
	
	panel.append(innerPanel);
	outerPanel.append(panel);
	$("#pointPanel").append(outerPanel);
}

function initAddExcercise(type){
	var outerPanel = $('<div class="col-md-4"></div>');
	var panel = $('<div class="panel"></div>');
	var innerPanel = $('<div class="panel-body point-panel-body"></div>');
	panel.append(innerPanel);
	outerPanel.append(panel);
	$("#pointPanel").append(outerPanel);
	
	var headerPanel = $('<header class="panel-heading"><label>'+type+'</label><button class="btn btn-default" onclick="initAddGamePoint()"><i class="fa fa-plus"></i> 添加游戏题目</button></header>');
	innerPanel.append(headerPanel);
	
	var questionFormGroup = $('<div class="form-group form-group-exercise"></form>');
	innerPanel.append(questionFormGroup);
	var randomId = Ding.randomId(8);
	var questionDiv = $('<div id="'+randomId+'" class="point_div"></div>');
	questionFormGroup.append(questionDiv);
	
	new Ding.FileUploader({
		'id':randomId,
		'multiSelection':false,
		'selectorTitle':'请上传题目',
		'limitType':'jpg,jpeg',
		'maxFileSize':'2m',
		'addedCallback':function(uploader,files,fileUploader){
			fileUploader.reset();
			fileUploader.jdom.find("img").remove();
			for(var f in files){
				var jpreviewDiv = $('<div class="preview-div"></div>');
				var jprocessDiv = $('<div class="progress" id="progress'+files[f].id+'"></div>');
				var jprocessBar = $('<div class="progress-bar" id="progressBar'+files[f].id+'"></div>');
				jprocessDiv.append(jprocessBar);
				
				jpreviewDiv.append(jprocessDiv);
				fileUploader.jcontainer.append(jpreviewDiv);
			}
		},
		'completeCallback':function(uploader,files,fileUploader){
//			var url = DingUploaderManager.host+'/'+files[0].uploadPath+'?OSSAccessKeyId='+DingUploaderManager.uploadParams.OSSAccessKeyId+'&Expires='+DingUploaderManager.expire+'&Signature='+DingUploaderManager.uploadParams.signature;
			var url = DingUploaderManager.host+'/'+files[0].uploadPath;
			questionDiv.append('<img src="'+url+'"></img>');
		}
	});
	
	var answerFormGroup = $('<div class="form-group form-group-exercise"></form>');
	innerPanel.append(answerFormGroup);
	var answerRandomId = Ding.randomId(8);
	var answerDiv = $('<div id="'+answerRandomId+'" class="point_div"></div>');
	answerFormGroup.append(answerDiv);
	new Ding.FileUploader({
		'id':answerRandomId,
		'multiSelection':true,
		'selectorTitle':'请上传供选择的答案',
		'limitType':'jpg,jpeg,png',
		'maxFileSize':'1m',
		'addedCallback':function(uploader,files,fileUploader){
			for(var f in files){
				fileUploader.jcontainer.append('<div class="answer_div"><img id="answer'+files[f].id+'" /></div>');
			}
		},
		'completeCallback':function(uploader,files,fileUploader){
			for(var f in files){
				var url = DingUploaderManager.host+'/'+files[f].uploadPath;
				$("#answer"+files[f].id).attr("src",url);
				$("#answer"+files[f].id).parent().css("width","auto");
				$("#answer"+files[f].id).on("click",function(){
					if(!Ding.isEmpty($(".answer_div_selected")[0])){
						var imgUrl = $(this).attr("src");
						var selectedAnswerDiv = $(".answer_div_selected");
						selectedAnswerDiv.empty();
						selectedAnswerDiv.append('<img src="'+imgUrl+'" />');
						var nextSelectAnswerDiv = selectedAnswerDiv.next();
						selectedAnswerDiv.parent().children().removeClass("answer_div_selected");
						if(!Ding.isEmpty(nextSelectAnswerDiv[0])){
							nextSelectAnswerDiv.addClass("answer_div_selected");
						}
					}
				});
			}
		}
	});
	
	var rightAnswerFormGroup = $('<div class="form-group form-group-exercise"></form>');
	innerPanel.append(rightAnswerFormGroup);
//	var rightAnswerHeaderDiv = $('<div><input type="number" class="form-control" placeholder="请输入需要选择的答案数量" onchange="changeAnswerCount(this)" /></div>');
	var rightAnswerHeaderDiv = $('<div><button onclick="addAnswer(this)"><i class="fa fa-plus"></i> 添加答案</button></div>');
	rightAnswerFormGroup.append(rightAnswerHeaderDiv).append('<div></div>');
	
	var explainFormGroup = $('<div class="form-group form-group-exercise"></div>');
	innerPanel.append(explainFormGroup);
	var explainHeaderDiv = $('<div><label>请上传题目讲解</label></div>');
	explainFormGroup.append(explainHeaderDiv);
	var addExplainBtnDiv = $('<div class="add_info_btn_panel" style="transform: translateY(0px);"></div>');
	explainFormGroup.append(addExplainBtnDiv);
	var addExplainVideoBtn =$('<div><p><i class="fa fa-video-camera"></i></p><p class="add_info_text">视频</p></div>');
	addExplainVideoBtn.on("click",function(){
		var randomId = Ding.randomId(8);
		var explainVideoFormGroup = $('<div class="form-group"></div>');
		var explainVideoDiv = $('<div id="'+randomId+'" class="point_div"></div>');
		explainVideoFormGroup.append(explainVideoDiv);
		explainFormGroup.append(explainVideoFormGroup);
		new Ding.FileUploader({
			'id':randomId,
			'multiSelection':false,
			'selectorTitle':'请选择视频讲解文件',
			'limitType':'mp4,swf,ogg',
			'maxFileSize':'20m',
			'addedCallback':function(uploader,files,fileUploader){
				for(var f in files){
					var jpreviewDiv = $('<div class="preview-div"></div>');
					var jprocessDiv = $('<div class="progress" id="progress'+files[f].id+'"></div>');
					var jprocessBar = $('<div class="progress-bar" id="progressBar'+files[f].id+'"></div>');
					jprocessDiv.append(jprocessBar);
					
					jpreviewDiv.append(jprocessDiv);
					fileUploader.jcontainer.append(jpreviewDiv);
				}
			},
			'completeCallback':function(uploader,files){
				explainVideoDiv.empty();
//				var url = DingUploaderManager.host+'/'+files[0].uploadPath+'?OSSAccessKeyId='+DingUploaderManager.uploadParams.OSSAccessKeyId+'&Expires='+DingUploaderManager.expire+'&Signature='+DingUploaderManager.uploadParams.signature;
				var url = DingUploaderManager.host+'/'+files[0].uploadPath;
				explainVideoDiv.append('<video src="'+url+'" controls="controls"></video>');
				
				var deletePointDiv = $('<div class="delete_point_mask_div"><i class="fa fa-trash-o"></i></div>');
				explainVideoDiv.append(deletePointDiv);
				deletePointDiv.on("click",function(){
					explainVideoDiv.parent().remove();
				});
			}
		});
	});
	addExplainBtnDiv.append(addExplainVideoBtn);
	var addExplainPicBtn =$('<div><p><i class="fa fa-picture-o"></i></p><p class="add_info_text">图片</p></div>');
	addExplainPicBtn.on("click",function(){
		var randomId = Ding.randomId(8);
		var explainPicFormGroup = $('<div class="form-group"></div>');
		var explainPicDiv = $('<div id="'+randomId+'" class="point_div"></div>');
		explainPicFormGroup.append(explainPicDiv);
		explainFormGroup.append(explainPicFormGroup);
		new Ding.FileUploader({
			'id':randomId,
			'multiSelection':false,
			'selectorTitle':'请上传讲解图片文件',
			'limitType':'jpg,jpeg',
			'maxFileSize':'1m',
			'addedCallback':function(uploader,files,fileUploader){
				for(var f in files){
					var jpreviewDiv = $('<div class="preview-div"></div>');
					var jprocessDiv = $('<div class="progress" id="progress'+files[f].id+'"></div>');
					var jprocessBar = $('<div class="progress-bar" id="progressBar'+files[f].id+'"></div>');
					jprocessDiv.append(jprocessBar);
					jpreviewDiv.append(jprocessDiv);
					fileUploader.jcontainer.append(jpreviewDiv);
				}
			},
			'completeCallback':function(uploader,files){
				explainPicDiv.empty();
//				var url = DingUploaderManager.host+'/'+files[0].uploadPath+'?OSSAccessKeyId='+DingUploaderManager.uploadParams.OSSAccessKeyId+'&Expires='+DingUploaderManager.expire+'&Signature='+DingUploaderManager.uploadParams.signature;
				var url = DingUploaderManager.host+'/'+files[0].uploadPath;
				explainPicDiv.append('<img src="'+url+'"></img>');
				
				var deletePointDiv = $('<div class="delete_point_mask_div"><i class="fa fa-trash-o"></i></div>');
				explainPicDiv.append(deletePointDiv);
				deletePointDiv.on("click",function(){
					explainPicDiv.parent().remove();
				});
			}
		});
	});
	addExplainBtnDiv.append(addExplainPicBtn);
}

function changeAnswerCount(input){
	var exsitCount = $(input).parent().next().children().length;
	var count = $(input).val();
	
	if(count>exsitCount){
		var leaveCount = count - exsitCount;
		var index = exsitCount;
		if(exsitCount==0){
			index = 1;
		}
		for(var i=0;i<leaveCount;i++){
			$(input).parent().next().append('<div class="answer_div" onclick="changeSelectedAnswer(this)">'+index+'</div>');
			index++;
		}
	}
	else{
		$($(input).parent().next().children()[count-1]).nextAll().remove();
	}
	if($(input).parent().next().children(".answer_div_selected").length==0){
		$($(input).parent().next().children()[0]).addClass("answer_div_selected");
	}
}

//添加答案
function addAnswer(btn){
	
}

function changeSelectedAnswer(answer){
	$(answer).parent().children().removeClass("answer_div_selected");
	$(answer).addClass("answer_div_selected");
}

//点击知识点面板添加按钮样式变化
function addPointInfo(btn){
	$(btn).parent().addClass("move_hide");
	$(btn).parent().next().addClass("move_show");
}

//初始化添加游戏题目窗口
function initAddGamePoint(){
	openModal({
        'title':'添加游戏题目',
        'targetId':'form',
        'sureBtnText':'保存'
    });
}