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
					renderDiv(files[f].id);
				}
			},
			'completeCallback':function(uploader,files,fileUploader){
				var filePaths = [];
				for(var f in files){
					filePaths.push(files[f].uploadPath);
				}
				Ding.ajax({
			        'url' : '/acadamic-web-manager/manager/answer/saveAnswers.shtml',
			        'method' : 'post',
			        'params' : {
			        	'answerFileNames':filePaths
			        },
			        'successCallback' : function(result){
			            var dataList = result.data;
			            for(var f in files){
							var url = DingUploaderManager.host+'/'+files[f].uploadPath;
							renderDiv(files[f].id,dataList[f],url);
						}
						fileUploader.reset();
						fileUploader.uploader.files.splice(0,fileUploader.uploader.files.length);
			        }
			    });
			}
		});
    });
	
	Ding.ajax({
        'url' : '/acadamic-web-manager/manager/answer/list.shtml',
        'successCallback' : function(result){
            var dataList = result.data;
            for(var i=0;i<dataList.length;i++){
            	renderDiv(dataList[i].id,dataList[i].id,dataList[i].answer);
            }
        }
    });
});

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
		$("#answer-"+id).parent().append('<button class="btn btn-default" onclick="removeDiv(this)">删除</button>');
		$("#answer-"+id).parent().attr("data-id",dataId);
	}
}

/**
 * 删除
 * @param btn
 * @returns
 */
function removeDiv(btn){
	var dataId = $(btn).parent().attr("data-id");
	Ding.ajax({
        'url' : '/acadamic-web-manager/manager/answer/deleteAnswer.shtml',
        'method':'post',
        'params':{
        	'answerId':dataId
        },
        'successCallback' : function(result){
        	$(btn).parent().remove();
        }
    });
}

