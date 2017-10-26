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
	
	var formGroup = $('<div class="form-group"></form>');
	var typeLable = $('<label>'+type+'</label>');
	var inputDiv = $('<input type="text" placeholder="标题" class="form-control" />');
	formGroup.append(typeLable).append(inputDiv);
	innerPanel.append(formGroup);
	
	var btnFormGroup = $('<div class="form-group btn-form-group"></form>');
	var addBtnDiv = $('<div class="add_btn_panel"><i class="fa fa-plus-square-o" onclick="addPointInfo(this)"></i></div>');
	btnFormGroup.append(addBtnDiv);
	
	var addInfoBtnDiv = $('<div class="add_info_btn_panel"></div>');
	var addVideoBtn =$('<div><p><i class="fa fa-video-camera"></i></p><p class="add_info_text">视频</p></div>');
	addVideoBtn.on("click",function(){
		var div = $('<div id="123" class="video_div"></div>');
		btnFormGroup.before(div);
		
		new Ding.FileUploader({
			'id':'123',
			'selectorTitle':'请选择视频文件',
			'limitType':'mp4',
			'maxFileSize':'20m'
		});
	});
	var addPicBtn =$('<div><p><i class="fa fa-picture-o" onclick="addPointInfo(this)"></i></p><p class="add_info_text">图片</p></div>');
	var addTextBtn =$('<div><p><i class="fa fa-file-text-o" onclick="addPointInfo(this)"></i></p><p class="add_info_text">段落</p></div>');
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

function addPointInfo(btn){
	$(btn).parent().addClass("move_hide");
	$(btn).parent().next().addClass("move_show");
}