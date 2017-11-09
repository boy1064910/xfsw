Ding.ready(function(){
    DingUploaderManager.loadSign(function() {
//        questionUploader = new Ding.FileUploader({
//    		'id':"questionDiv",
//    		'multiSelection':false,
//    		'selectorTitle':'上传题目',
//    		'limitType':'jpg,jpeg,png',
//    		'maxFileSize':'2m',
//    		'addedCallback':function(uploader,files,fileUploader){
//    			for(var f in files){
//    				var jpreviewDiv = $('<div class="preview-div"></div>');
//    				var jprocessDiv = $('<div class="progress" id="progress'+files[f].id+'"></div>');
//    				var jprocessBar = $('<div class="progress-bar" id="progressBar'+files[f].id+'"></div>');
//    				jprocessDiv.append(jprocessBar);
//    				jpreviewDiv.append(jprocessDiv);
//    				fileUploader.jcontainer.append(jpreviewDiv);
//    			}
//    		},
//    		'completeCallback':function(uploader,files,fileUploader){
//    			fileUploader.reset();
//    			fileUploader.jdom.find("img").remove();
//    			var url = DingUploaderManager.host+'/'+files[0].uploadPath;
//    			$("#questionDiv").append('<img src="'+url+'"></img>');
//    		}
//    	});
    });
    
    var rowDivLength = Math.floor($("#pointPanel").width()/350);
    
    //请求知识点详情
    Ding.ajax({
        'url' : '/acadamic-web-manager/manager/course/chapter/knowledge/getKnowledgeById.shtml',
        'params' : {
        	'knowledgeId' : $("#knowledgeId").val()
        },
        'method' : 'GET',
        'successCallback' : function(result){
        	var knowledge = result.data;
        	var knowledgeInfoList = knowledge.knowledgeInfoList;
        	if(!Ding.isEmpty(knowledgeInfoList)){
        		for(var i=0;i<knowledgeInfoList.length;i++){
            		switch(knowledgeInfoList[i].type){
    	        		case 'GAME':{
    	        			renderGamePoint(knowledgeInfoList[i]);
    	        			break;
    	        		}
    	        		default:{
    	        			renderPoint(knowledgeInfoList[i]);
    	        		}
            		}
            		if((i+1)%rowDivLength==0){
            			$("#pointPanel").append('<div class="point_panel_clear"></div>')
            		}
            	}
        	}
        }
    });
});

//添加发现、探索、总结、套路panel
function initAddPoint(type){
	Ding.ajax({
        'url' : '/acadamic-web-manager/manager/course/chapter/knowledge/initAddKnowledgeInfo.shtml',
        'params' : {
        	'type' : type,
        	'knowledgeId' : $("#knowledgeId").val()
        },
        'method' : 'POST',
        'successCallback' : function(result){
        	var knowledgeInfo = {
        		'id' : result.data,
        		'type' : type
        	};
        	renderPoint(knowledgeInfo);
        }
    });
	
//	var outerPanel = $('<div class="col-md-4"></div>');
//	var panel = $('<div class="panel"></div>');
//	var innerPanel = $('<div class="panel-body point-panel-body"></div>');
//	
//	var headerPanel = $('<header class="panel-heading"><label>'+type+'</label><input type="text" placeholder="标题" class="form-control form-control-60" /></header>');
//	innerPanel.append(headerPanel);
//	
//	var btnFormGroup = $('<div class="form-group btn-form-group"></form>');
//	var addBtnDiv = $('<div class="add_btn_panel"><i class="fa fa-plus-square-o" onclick="addPointInfo(this)"></i></div>');
//	btnFormGroup.append(addBtnDiv);
//	
//	var addTextBtn =$('<div><p><i class="fa fa-file-text-o"></i></p><p class="add_info_text">文字</p></div>');
//	addTextBtn.on("click",function(){
//		var randomId = Ding.randomId(8);
//		var textFormGroup = $('<div class="form-group"></div>');
//		var textDiv = $('<div id="'+randomId+'" class="point_div"><textarea class="form-control"></textarea></div>');
//		textFormGroup.append(textDiv);
//		btnFormGroup.before(textFormGroup);
//		
//		var deletePointDiv = $('<div class="delete_point_div"><i class="fa fa-trash-o"></i></div>');
//		textDiv.append(deletePointDiv);
//		deletePointDiv.on("click",function(){
//			textDiv.parent().remove();
//		});
//	});
//	
//	addInfoBtnDiv.append(addVideoBtn).append(addPicBtn).append(addTextBtn);
//	addInfoBtnDiv.mouseleave(function(){
//		$(this).removeClass("move_show");
//		$(this).prev().removeClass("move_hide");
//	});
//	btnFormGroup.append(addInfoBtnDiv);
//	
//	innerPanel.append(btnFormGroup);
//	
//	panel.append(innerPanel);
//	outerPanel.append(panel);
//	$("#pointPanel").append(outerPanel);
}

//渲染发现、探索、总结、套路panel
function renderPoint(knowledgeInfo){
	var panel1 = $('<div class="panel panel_knowledge" data-id="'+knowledgeInfo.id+'"></div>');
	$("#pointPanel").append(panel1);
	var panelBody = $('<div class="panel-body point-panel-body"></div>');
	panel1.append(panelBody);
	var headerPanel = $('<header class="panel-heading"><label>'+knowledgeInfo.type+'</label></header>');
	panelBody.append(headerPanel);
	
	var btnFormGroup = $('<div class="form-group btn-form-group"></div>');
	panelBody.append(btnFormGroup);
	
	var addBtnDiv = $('<div class="add_btn_panel"><i class="fa fa-plus-square-o" onclick="addPointInfo(this)"></i></div>');
	btnFormGroup.append(addBtnDiv);
	
	var addInfoBtnDiv = $('<div class="add_info_btn_panel"></div>');
	var addVideoBtn =$('<div onclick="addVideo(this)"><p><i class="fa fa-video-camera"></i></p><p class="add_info_text">视频</p></div>');
	var addPicBtn =$('<div onclick="addPic(this)"><p><i class="fa fa-picture-o"></i></p><p class="add_info_text">图片</p></div>');
	var addTextBtn =$('<div><p><i class="fa fa-file-text-o"></i></p><p class="add_info_text">文字</p></div>');
	addInfoBtnDiv.append(addVideoBtn).append(addPicBtn).append(addTextBtn);
	
	addInfoBtnDiv.mouseleave(function(){
		$(this).removeClass("move_show");
		$(this).prev().removeClass("move_hide");
	});
	btnFormGroup.append(addInfoBtnDiv);
	
	var knowledgeInfoDetailList = knowledgeInfo.knowledgeInfoDetailList;
	if(!Ding.isEmpty(knowledgeInfoDetailList)){
		for(var i=0;i<knowledgeInfoDetailList.length;i++){
			var knowledgeDetailCol = $('<div class="knowledge_detail_col"></div>');
			btnFormGroup.before(knowledgeDetailCol);
			switch(knowledgeInfoDetailList[i].knowledgeInfoDetailType){
				case "video":{
					//渲染视频
					renderVideoDetail(knowledgeDetailCol,knowledgeInfoDetailList[i]);
					break;
				}
				case "pic":{
					renderPicDetail(knowledgeDetailCol,knowledgeInfoDetailList[i]);
					break;
				}
			}
		}
	}
}

//点击知识点面板添加按钮样式变化
function addPointInfo(btn){
	$(btn).parent().addClass("move_hide");
	$(btn).parent().next().addClass("move_show");
}

//添加视频
function addVideo(btn){
	var knowledgeInfo = $(btn).parents('[data-id]');
	var randomId = Ding.randomId(8);
	var videoKnowledgeDetailCol = $('<div id="'+randomId+'" class="knowledge_detail_col"></div>');
	$(btn).parent().parent().before(videoKnowledgeDetailCol);
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
			var url = files[0].uploadPath;
			Ding.ajax({
		        'url' : '/acadamic-web-manager/manager/course/chapter/knowledge/insertKnowledgeInfoDetail.shtml',
		        'params' : {
		        	'knowledgeInfoId' : knowledgeInfo.attr("data-id"),
		        	'knowledgeInfoDetailType' : 'video',
		        	'info' : url
		        },
		        'method' : 'POST',
		        'successCallback' : function(result){
		        	var knowledgeInfoDetail = result.data;
		        	renderVideoDetail(videoKnowledgeDetailCol,knowledgeInfoDetail);
		        }
		    });
		}
	});
}

//渲染知识点细节-视频
function renderVideoDetail(videoKnowledgeDetailCol,knowledgeInfoDetail){
	videoKnowledgeDetailCol.attr("data-id",knowledgeInfoDetail.id);
	videoKnowledgeDetailCol.empty();
	videoKnowledgeDetailCol.append('<video src="'+knowledgeInfoDetail.info+'" controls="controls"></video>');
	var deletePointDiv = $('<div class="delete_point_mask_div" onclick="deleteKnowledgeDetail(this)"><i class="fa fa-trash-o"></i></div>');
	videoKnowledgeDetailCol.append(deletePointDiv);
}

function addPic(btn){
	var knowledgeInfo = $(btn).parents('[data-id]');
	var randomId = Ding.randomId(8);
	var knowledgeDetailCol = $('<div id="'+randomId+'" class="knowledge_detail_col"></div>');
	$(btn).parent().parent().before(knowledgeDetailCol);
	
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
			var url = files[0].uploadPath;
			Ding.ajax({
		        'url' : '/acadamic-web-manager/manager/course/chapter/knowledge/insertKnowledgeInfoDetail.shtml',
		        'params' : {
		        	'knowledgeInfoId' : knowledgeInfo.attr("data-id"),
		        	'knowledgeInfoDetailType' : 'pic',
		        	'info' : url
		        },
		        'method' : 'POST',
		        'successCallback' : function(result){
		        	var knowledgeInfoDetail = result.data;
		        	renderPicDetail(knowledgeDetailCol,knowledgeInfoDetail);
		        }
		    });
		}
	});
}

//渲染知识点细节-视频
function renderPicDetail(knowledgeDetailCol,knowledgeInfoDetail){
	knowledgeDetailCol.attr("data-id",knowledgeInfoDetail.id);
	knowledgeDetailCol.empty();
	knowledgeDetailCol.append('<img src="'+knowledgeInfoDetail.info+'" />');
	var deletePointDiv = $('<div class="delete_point_mask_div" onclick="deleteKnowledgeDetail(this)"><i class="fa fa-trash-o"></i></div>');
	knowledgeDetailCol.append(deletePointDiv);
}

//删除知识点细节
function deleteKnowledgeDetail(btn){
	var knowledgeInfoDetailCol = $($(btn).parents("[data-id]")[0]);
	Ding.ajax({
        'url' : '/acadamic-web-manager/manager/course/chapter/knowledge/deleteKnowledgeInfoDetail.shtml',
        'params' : {
        	'knowledgeInfoDetailId' : knowledgeInfoDetailCol.attr("data-id")
        },
        'method' : 'POST',
        'successCallback' : function(result){
        	knowledgeInfoDetailCol.remove();
        }
    });
}

//初始化添加游戏题目窗口
function initAddGamePoint(){
	Ding.ajax({
        'url' : '/acadamic-web-manager/manager/course/chapter/knowledge/info/initAddKnowledgeInfo.shtml',
        'params' : {
        	'type' : 'GAME',
        	'knowledgeId' : $("#knowledgeId").val()
        },
        'method' : 'POST',
        'successCallback' : function(result){
        	renderGamePoint({
        		'id' : result.data,
        		'type' : 'GAME'
        	});
        }
    });
}

function renderGamePoint(knowledgeInfo){
	var panel1 = $('<div class="panel panel_knowledge" data-id="'+knowledgeInfo.id+'"></div>');
	$("#pointPanel").append(panel1);
	var panelBody = $('<div class="panel-body point-panel-body"></div>');
	panel1.append(panelBody);
	var addExerciseBtnRow = $('<div class="exercise_btn_row"><button class="btn btn-default" onclick="addExercise(this)"><i class="fa fa-plus-square"> 添加题目</i></button></div>');//添加题目按钮行
	panelBody.append(addExerciseBtnRow);
	
	var exerciseList = knowledgeInfo.exerciseList;
	if(!Ding.isEmpty(exerciseList)){
		for(var i=0;i<exerciseList.length;i++){
			var exerciseRow = $('<div class="exercise_row" data-id="'+exerciseList[i].id+'"></div>');
			addExerciseBtnRow.before(exerciseRow);
			renderExercise(exerciseRow,exerciseList[i]);
		}
	}
}

//初始化答案项
var answerSelector = $(
		'<select class="form-control" style="width:130px" onchange="changeAnswerInputType(this)">'
		+'<option value="NUMBER">数字输入框</option>'
		+'<option value="TEXT">文本输入框</option>'
		+'<option value="SELECTOR">选择器</option>'
		+'</select>');
//初始化正确答案框
var numberAnswerInput = $('<input type="number" class="form-control" style="width:100px" placeholder="正确答案" onblur="updateAnswerInfo(this)" />');
var textAnswerInput = $('<input type="text" class="form-control" style="width:100px" placeholder="正确答案" onblur="updateAnswerInfo(this)" />');
var imgAnswer = $('<div class="answer_img_div" onclick="configAnswerImg(this)">点击配置答案</div>');
var deleteAnswerBtn = $('<button type="button" class="btn btn-default" onclick="delteExerciseDetail(this)"><i class="fa fa-times-circle"></i></button>');

//添加题目
function addExercise(btn){
	var knowledgeInfo = $(btn).parents("[data-id]")[0];
	Ding.ajax({
        'url' : '/acadamic-web-manager/manager/course/chapter/exersice/initAddExercise.shtml',
        'params' : {
        	'type' : 'GAME',
        	'knowledgeInfoId' : $(knowledgeInfo).attr("data-id")
        },
        'method' : 'POST',
        'successCallback' : function(result){
        	var exerciseRow = $('<div class="exercise_row" data-id="'+result.data+'"></div>');
        	$(btn).parent().before(exerciseRow);
        	
        	renderExercise(exerciseRow,{
        		'id' : result.data
        	});
        }
	});
}

//添加练习题填空处
function addExerciseDetail(btn){
	var exerciseId = $(btn).parent().parent().parent().attr("data-id");
	Ding.ajax({
        'url' : '/acadamic-web-manager/manager/course/chapter/exersice/insertExerciseDetail.shtml',
        'params' : {
        	'exerciseId' : exerciseId
        },
        'successCallback':function(result){
        	var exerciseDetailRow = $(btn).parent().next();
        	var exerciseDetailCol = $('<div class="exercise_detail_col" data-id="'+result.data+'"></div>');
        	exerciseDetailRow.append(exerciseDetailCol);
        	exerciseDetailCol.append(answerSelector.clone()).append(numberAnswerInput.clone()).append(deleteAnswerBtn.clone());
        }
	});
}

//渲染练习题和练习题填空处
function renderExercise(exerciseRow,exercise){
	//开始添加单个题目信息
	var uploadExerciseBtnCol = $('<div class="exercise_col" id="exercise-'+exercise.id+'"></div>');
	exerciseRow.append(uploadExerciseBtnCol);
	
	var exerciseCol = $('<div class="exercise_col"></div>');
	exerciseRow.append(exerciseCol);
	
	var addAnswerBtnCol = $('<div><button class="btn btn-default" onclick="addExerciseDetail(this)"><i class="fa fa-plus-circle"> 新增填空处</i></button></div>');
	exerciseCol.append(addAnswerBtnCol);
	var exerciseDetailRow = $('<div class="exercise_detail_row"></div>');
	exerciseCol.append(exerciseDetailRow);
	//初始化题目上传控件
	new Ding.FileUploader({
		'id':"exercise-"+exercise.id,
		'multiSelection':false,
		'selectorTitle':'上传题目',
		'limitType':'jpg,jpeg,png',
		'maxFileSize':'2m',
		'addedCallback':function(uploader,files,fileUploader){
			var jpreviewDiv = $('<div class="preview-div"></div>');
			var jprocessDiv = $('<div class="progress" id="progress'+files[0].id+'"></div>');
			var jprocessBar = $('<div class="progress-bar" id="progressBar'+files[0].id+'"></div>');
			jprocessDiv.append(jprocessBar);
			jpreviewDiv.append(jprocessDiv);
			fileUploader.jcontainer.append(jpreviewDiv);
		},
		'completeCallback':function(uploader,files,fileUploader){
			var filePath = files[0].uploadPath;
			//上传练习题图片，并保存到练习题数据中
			Ding.ajax({
		        'url' : '/acadamic-web-manager/manager/course/chapter/exersice/uploadExercise.shtml',
		        'params' : {
		        	'exerciseId' : exercise.id,
		        	'filePath' : filePath
		        },
		        'successCallback':function(result){
		        	fileUploader.reset();
        			fileUploader.jdom.find("img").remove();
        			$("#exercise-"+exercise.id).append('<img src="'+result.data+'"></img>');
		        }
			});
		}
	});
	
	//添加删除按钮
	var deleteExerciseBtn = $('<button type="button" onclick="deleteExercise(this)" title="删除练习题" class="btn btn-default exercise_col_del_btn"><i class="fa fa-times"></i></button>');
	exerciseRow.append(deleteExerciseBtn);
	
	if(!Ding.isEmpty(exercise.exerciseUrl)){
		$("#exercise-"+exercise.id).append('<img src="'+exercise.exerciseUrl+'" />');
	}
}

//删除练习题
function deleteExercise(btn){
	var exerciseRow = $($(btn).parents("[data-id]")[0]);
	Ding.ajax({
        'url' : '/acadamic-web-manager/manager/course/chapter/exersice/deleteExercise.shtml',
        'method' : 'post',
        'params' : {
        	'exerciseId' : exerciseRow.attr("data-id")
        },
        'successCallback':function(result){
        	exerciseRow.remove();
        }
	});
}

//更改答案输入框类型
function changeAnswerInputType(selector){
	var exerciseDetailId = $(selector).parent().attr("data-id");
	var value = $(selector).val();
	Ding.ajax({
        'url' : '/acadamic-web-manager/manager/course/chapter/exersice/updateExerciseDetail.shtml',
        'params' : {
        	'exerciseDetailId' : exerciseDetailId,
        	'type' : value
        },
        'successCallback':function(result){
        	$(selector).nextAll().remove();
        	switch(value){
        		case "NUMBER":{
        			$(selector).after(numberAnswerInput.clone());
        			break;
        		}
        		case "TEXT":{
        			$(selector).after(textAnswerInput.clone());
        			break;
        		}
        		case "SELECTOR":{
        			$(selector).after(imgAnswer.clone());
        			break;
        		}
        	}
        }
	});
}

//更新答案信息
function updateAnswerInfo(input){
	var exerciseDetailId = $(input).parent().attr("data-id");
	var type = $(input).prev().val();
	var value = $(input).val();
	Ding.ajax({
        'url' : '/acadamic-web-manager/manager/course/chapter/exersice/updateExerciseDetail.shtml',
        'params' : {
        	'exerciseDetailId' : exerciseDetailId,
        	'type' : type,
        	'answer' : value
        },
        'successCallback':function(result){
        	
        }
	});
}

//删除习题填空
function delteExerciseDetail(btn){
	var exerciseDetailId = $(btn).parent().attr("data-id");
	Ding.ajax({
        'url' : '/acadamic-web-manager/manager/course/chapter/exersice/delteExerciseDetail.shtml',
        'params' : {
        	'exerciseDetailId' : exerciseDetailId
        },
        'successCallback':function(result){
        	$(btn).parent().remove();
        }
	});
}

//添加答案行
function addAnswerRow(btn){
	var formGroup = $('<div class="form-group answer_row"></div>');
	formGroup.append('<label class="col-lg-1 col-sm-1 control-label add_answer_btn_label"><i class="fa fa-plus-square-o"></i></label>');
	var answerRowDiv = $('<div class="col-md-11 answer_col"></div>');
	answerRowDiv.append(answerSelector.clone()).append(numberAnswerInput.clone());
	formGroup.append(answerRowDiv);
	$("#addAnswerRowBtnDiv").after(formGroup);
}



//配置答案图片
function configAnswerImg(imgDiv){
	$("#answerGroup").empty();
	//读取预设答案
	Ding.ajax({
        'url' : '/acadamic-web-manager/manager/answer/list.shtml',
        'successCallback' : function(result){
            var dataList = result.data;
            for(var i=0;i<dataList.length;i++){
            	var answerImgUl
            	var answerImgLi = $('<div class="answer_img_li"></div>');
            	var answerImg = $('<div></div>');
            	answerImg.css({
        			"background":"url("+dataList[i].answer+")",
        			"background-repeat":"no-repeat",
        			"background-position":"center",
        			"background-size": "contain"
        		});
            	answerImgLi.append(answerImgLi);
                $("#answerGroup").append(answerImgLi);
            	renderDiv(dataList[i].id,dataList[i].id,dataList[i].answer);
            }
        }
    });
	openModal({
        'title':'配置答案',
        'targetId':'configAnswerForm',
        'sureBtnText':'保存'
    });
}

function renderDiv(id,dataId,url){
	if($.isEmpty($("#answer-"+id)[0])){
		$("#answerDiv").append('<div class="pre_answer_div"><div id="answer-'+id+'" ></div></div>');
	}
	if(!Ding.isEmpty(dataId)){
		$("#answer-"+id).css("background","url("+url+")");
		$("#answer-"+id).css({
			"background":"url("+url+")",
			"background-repeat":"no-repeat",
			"background-position":"center",
			"background-size": "contain"
		});
		$("#answer-"+id).parent().append('<button class="btn btn-default" type="button" onclick="removeDiv(this)">删除</button>');
		$("#answer-"+id).parent().attr("data-id",dataId);
	}
}

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



