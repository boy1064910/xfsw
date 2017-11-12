var exerciseDetailAnswerUploader;
Ding.ready(function(){
    DingUploaderManager.loadSign(function() {
    	exerciseDetailAnswerUploader = new Ding.FileUploader({
    		'id':"exerciseDetailAnswerUploader",
    		'multiSelection':true,
    		'selectorTitle':'上传答案',
    		'limitType':'jpg,jpeg,png',
    		'maxFileSize':'2m',
    		'addedCallback':function(uploader,files,fileUploader){
    			
    		},
    		'completeCallback':function(uploader,files,fileUploader){
    			console.log(files);
    			var filePaths = [];
				for(var f in files){
					filePaths.push(files[f].uploadPath);
				}

    			//上传答案并且保存答案信息
    			Ding.ajax({
			        'url' : '/acadamic-web-manager/manager/answer/saveAnswers.shtml',
			        'method' : 'post',
			        'params' : {
			        	'answerFileNames':filePaths,
			        	'exerciseDetailId':$("#exerciseDetailId").val()
			        },
			        'successCallback' : function(result){
			        	var answerList = result.data;
			        	for(var i=0;i<answerList.length;i++){
			        		var answerImgLi = $('<div data-id="'+answerList[i].id+'" class="answer_img_li"></div>');
			        		$("#answerGroup").append(answerImgLi);

			        		var answerImgDiv = $('<div class="answer_un_choose" onclick="chooseAnswer(this)" data-id="'+dataList[i].id+'" answer="'+dataList[i].answer+'"></div>');
					        answerImgDiv.css({
								"background":"url("+answerList[i].answer+")",
								"background-repeat":"no-repeat",
								"background-position":"center",
								"background-size": "contain"
							});
							var deleteAnswerBtn = $('<button class="btn btn-info btn-xs" type="button" onclick="deleteAnswer(this)">删除</button>');
			        		answerImgLi.append(answerImgDiv).append(deleteAnswerBtn);
			        	}

			        	files.splice(0,files.length);
			        }
			    });
    		}
    	});
    });
    
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
    	        		case 'GAME':
    	        		case 'EXERCISE':{
    	        			renderGamePoint(knowledgeInfoList[i]);
    	        			break;
    	        		}
    	        		default:{
    	        			renderPoint(knowledgeInfoList[i]);
    	        		}
            		}
            	}
        	}
        }
    });
});

function resetPanelLayout(){
	$(".point_panel_clear").remove();
	var rowDivLength = Math.floor($("#pointPanel").width()/350);
	var knowledgeList = $("#pointPanel").children(".panel_knowledge");
	for(var i=0;i<knowledgeList.length;i++){
		if((i+1)%rowDivLength==0){
			$(knowledgeList[i]).after('<div class="point_panel_clear"></div>')
		}	
	}
}

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
}

//渲染发现、探索、总结、套路panel
function renderPoint(knowledgeInfo){
	var panel1 = $('<div class="panel panel_knowledge"></div>');
	$("#pointPanel").append(panel1);
	var panelBody = $('<div class="panel-body point-panel-body" data-id="'+knowledgeInfo.id+'"></div>');
	panel1.append(panelBody);
	var headerPanel = $('<header class="panel-heading"><label>'+knowledgeInfo.type+'</label><button title="删除知识点内容" class="btn btn-default" type="button" onclick="deleteKnowledgeInfo(this)"><i class="fa fa-times"><i></button></header>');
	panelBody.append(headerPanel);
	
	var btnFormGroup = $('<div class="form-group btn-form-group"></div>');
	panelBody.append(btnFormGroup);
	
	var addBtnDiv = $('<div class="add_btn_panel"><i class="fa fa-plus-square-o" onclick="addPointInfo(this)"></i></div>');
	btnFormGroup.append(addBtnDiv);
	
	var addInfoBtnDiv = $('<div class="add_info_btn_panel"></div>');
	var addVideoBtn =$('<div onclick="addVideo(this)"><p><i class="fa fa-video-camera"></i></p><p class="add_info_text">视频</p></div>');
	var addPicBtn =$('<div onclick="addPic(this)"><p><i class="fa fa-picture-o"></i></p><p class="add_info_text">图片</p></div>');
	var addTextBtn =$('<div onclick="addText(this)"><p><i class="fa fa-file-text-o"></i></p><p class="add_info_text">文字</p></div>');
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
				case "text":{
					renderTextDetail(knowledgeDetailCol,knowledgeInfoDetailList[i])
					break;
				}
			}
		}
	}

	resetPanelLayout();
}

//删除知识点内容
function deleteKnowledgeInfo(btn){
	var knowledgeInfo = $(btn).parents("[data-id]");
	Ding.ajax({
        'url' : '/acadamic-web-manager/manager/course/chapter/knowledge/deleteKnowledgeInfo.shtml',
        'params' : {
        	'knowledgeInfoId' : knowledgeInfo.attr("data-id")
        },
        'method' : 'POST',
        'successCallback' : function(result){
        	knowledgeInfo.parent().remove();
        	resetPanelLayout();
        }
    });
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
	var deletePointDiv = $('<div class="delete_point_mask_div"><i class="fa fa-trash-o" onclick="deleteKnowledgeDetail(this)"></i></div>');
	knowledgeDetailCol.append(deletePointDiv);
}

//添加文本内容
function addText(btn){
	var knowledgeInfo = $(btn).parents('[data-id]');
	Ding.ajax({
        'url' : '/acadamic-web-manager/manager/course/chapter/knowledge/insertKnowledgeInfoDetail.shtml',
        'params' : {
        	'knowledgeInfoId' : knowledgeInfo.attr("data-id"),
        	'knowledgeInfoDetailType' : 'text'
        },
        'method' : 'POST',
        'successCallback' : function(result){
        	var knowledgeInfoDetail = result.data;
        	var knowledgeDetailCol = $('<div class="knowledge_detail_col"></div>');
        	knowledgeInfo.children().last().before(knowledgeDetailCol);
        	renderTextDetail(knowledgeDetailCol,knowledgeInfoDetail);
        }
    });
}

//渲染知识点文字模式
function renderTextDetail(knowledgeDetailCol,knowledgeInfoDetail){
	knowledgeDetailCol.attr("data-id",knowledgeInfoDetail.id);
	var textarea = $('<textarea class="form-control" onchange="updateKnowledgeInfoDetail(this)"></textarea>');
	textarea.val(knowledgeInfoDetail.info);
	knowledgeDetailCol.append(textarea);
	
	var deletePointDiv = $('<div class="delete_point_div"><i class="fa fa-trash-o" onclick="deleteKnowledgeDetail(this)"></i></div>');
	knowledgeDetailCol.append(deletePointDiv);
}

//更新知识点细节信息
function updateKnowledgeInfoDetail(txt){
	var knowledgeInfoDetailId = $(txt).parents("[data-id]").attr("data-id");
	Ding.ajax({
        'url' : '/acadamic-web-manager/manager/course/chapter/knowledge/updateKnowledgeInfoDetail.shtml',
        'params' : {
        	'id' : knowledgeInfoDetailId,
        	'info' : $(txt).val()
        },
        'method' : 'POST',
        'successCallback' : function(result){
        	
        }
    });
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
function initAddGamePoint(type){
	Ding.ajax({
        'url' : '/acadamic-web-manager/manager/course/chapter/knowledge/initAddKnowledgeInfo.shtml',
        'params' : {
        	'type' : type,
        	'knowledgeId' : $("#knowledgeId").val()
        },
        'method' : 'POST',
        'successCallback' : function(result){
        	renderGamePoint({
        		'id' : result.data,
        		'type' : type
        	});
        }
    });
}

function renderGamePoint(knowledgeInfo){
	var panel1 = $('<div class="panel panel_knowledge"></div>');
	$("#pointPanel").append(panel1);
	var panelBody = $('<div class="panel-body point-panel-body" data-id="'+knowledgeInfo.id+'"></div>');
	panel1.append(panelBody);
	var headerPanel = $('<header class="panel-heading"><label>'+knowledgeInfo.type+'</label><button title="删除知识点内容" class="btn btn-default" type="button" onclick="deleteKnowledgeInfo(this)"><i class="fa fa-times"><i></button></header>');
	panelBody.append(headerPanel);
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

	resetPanelLayout();
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
	if(!Ding.isEmpty(exercise.exerciseDetailModelList)){
		for(var i=0;i<exercise.exerciseDetailModelList.length;i++){
			renderExerciseDetail(exerciseDetailRow,exercise.exerciseDetailModelList[i]);
		}
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

function renderExerciseDetail(exerciseDetailRow,exerciseDetail){
	var exerciseDetailCol = $('<div class="exercise_detail_col" data-id="'+exerciseDetail.id+'"></div>');
	exerciseDetailRow.append(exerciseDetailCol);
	var newAnswerSelector = answerSelector.clone();
	var newNumberAnswerInput = numberAnswerInput.clone();
	exerciseDetailCol.append(newAnswerSelector);
	
	newAnswerSelector.val(exerciseDetail.type);
	switch(exerciseDetail.type){
		case "NUMBER":{
			var newNumberAnswerInput = numberAnswerInput.clone();
			if(!Ding.isEmpty(exerciseDetail.answer)){
				newNumberAnswerInput.val(exerciseDetail.answer);
			}
			exerciseDetailCol.append(newNumberAnswerInput);
			break;
		}
		case "TEXT":{
			var newTextAnswerInput = textAnswerInput.clone();
			if(!Ding.isEmpty(exerciseDetail.answer)){
				newTextAnswerInput.val(exerciseDetail.answer);
			}
			exerciseDetailCol.append(newTextAnswerInput);
			break;
		}
		case "SELECTOR":{
			var newImgAnswer = imgAnswer.clone();
			if(!Ding.isEmpty(exerciseDetail.answer)){
				newImgAnswer.empty();
				newImgAnswer.css({
					"background":"url("+exerciseDetail.answerUrl+")",
					"background-repeat":"no-repeat",
					"background-position":"center",
					"background-size": "contain"
				});
			}
			exerciseDetailCol.append(newImgAnswer);
			break;
		}
	}
	
	exerciseDetailCol.append(deleteAnswerBtn.clone());//拼接删除按钮
}

//添加练习题填空处
function addExerciseDetail(btn){
	var exerciseRow = $($(btn).parents("[data-id]")[0]);
	Ding.ajax({
        'url' : '/acadamic-web-manager/manager/course/chapter/exersice/insertExerciseDetail.shtml',
        'params' : {
        	'exerciseId' : exerciseRow.attr("data-id")
        },
        'successCallback':function(result){
        	var exerciseDetailRow = $(btn).parent().next();
        	renderExerciseDetail(exerciseDetailRow,{
        		'id' : result.data,
        		'type' : 'NUMBER'//初始化默认为数字输入框
        	});
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
        	$(selector).next().remove();
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


var currentExerciseDetailImg;
//配置答案图片
function configAnswerImg(imgDiv){
	currentExerciseDetailImg = imgDiv;
	var exerciseDetailId = $(imgDiv).parents('[data-id]').attr("data-id");
	$("#answerGroup").empty();
	$("#exerciseDetailId").val(exerciseDetailId);
	//读取预设答案
	Ding.ajax({
        'url' : '/acadamic-web-manager/manager/answer/exercise/detail/list.shtml',
        'params' : {
        	'exerciseDetailId' : exerciseDetailId
        },
        'successCallback' : function(result){
            var dataList = result.data;
            for(var i=0;i<dataList.length;i++){
            	var answerImgLi = $('<div class="answer_img_li" data-id="'+dataList[i].id+'"></div>');
            	var answerImgDiv = $('<div class="answer_un_choose" onclick="chooseAnswer(this)" data-id="'+dataList[i].id+'" answer="'+dataList[i].answer+'"></div>');
		        answerImgDiv.css({
					"background":"url("+dataList[i].answer+")",
					"background-repeat":"no-repeat",
					"background-position":"center",
					"background-size": "contain"
				});
        		answerImgLi.append(answerImgDiv);
        		if(!Ding.isEmpty(dataList[i].exerciseDetailId)){
        			var deleteAnswerBtn = $('<button class="btn btn-info btn-xs" type="button" onclick="deleteAnswer(this)">删除</button>');
        			answerImgLi.append(deleteAnswerBtn);
        		}
                $("#answerGroup").append(answerImgLi);
            }
        }
    });
	openModal({
        'title':'配置答案（点击选中正确答案）',
        'targetId':'configAnswerForm',
        'sureBtnText':'确定',
        'sureCallback': chooseRightAnswer
    });
}

//删除答案
function deleteAnswer(btn){
	var answerLi = $(btn).parents("[data-id]");
	Ding.ajax({
        'url' : '/acadamic-web-manager/manager/answer/deleteAnswer.shtml',
        'method':'post',
        'params':{
        	'answerId':answerLi.attr("data-id")
        },
        'successCallback' : function(result){
        	answerLi.remove();
        }
    });
}

//选中正确答案
function chooseAnswer(answer){
	$(".answer_choose").removeClass("answer_choose");
	$(answer).toggleClass("answer_choose");
}

//选择并保存正确答案
function chooseRightAnswer(){
	var chooseAnswer = $(".answer_choose");
	if(chooseAnswer.length==0){

	}
	var answerId = chooseAnswer.attr("data-id");
	var answer = chooseAnswer.attr("answer");
	Ding.ajax({
        'url' : '/acadamic-web-manager/manager/course/chapter/exersice/updateExerciseDetail.shtml',
        'params' : {
        	'exerciseDetailId' : $("#exerciseDetailId").val(),
        	'answer' : answerId,
        	'type' : 'SELECTOR'
        },
        'successCallback':function(result){
        	$(currentExerciseDetailImg).empty();
        	$(currentExerciseDetailImg).css({
				"background":"url("+answer+")",
				"background-repeat":"no-repeat",
				"background-position":"center",
				"background-size": "contain"
			});
        }
	});
}
