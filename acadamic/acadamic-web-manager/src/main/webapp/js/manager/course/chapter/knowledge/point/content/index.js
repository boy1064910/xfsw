var answerCodeMap = {};

Ding.ready(function(){
    Ding.ajax({
        'url' : "/acadamic-web-manager/manager/course/chapter/knowledge/point/content/list.shtml?knowledgePointCode="+Ding.getQueryParameterByName("knowledgePointCode"),
        'successCallback' : function(result){
            for(var i=0;i<result.data.length;i++){
            	var content = result.data[i];
            	var div = $('<div><div class="content-title">'+content.type+'</div></div>');
            	$("#panelBody").append(div);
            	
            	var questionListDiv = $('<div class="question-list-div"></div>');
            	div.append(questionListDiv);
            	
            	var questionList = content.questionList;
            	for(var j=0;j<questionList.length;j++){
            		var questionDiv = $('<div class="question-div"></div>');
            		questionListDiv.append(questionDiv);
            		
            		var textareaDiv = $('<div class="textarea-div"></div>');
            		questionDiv.append(textareaDiv);
            		
            		var previewDiv = $('<div class="preview-div"></div>');
            		previewDiv.attr("id","preview-"+questionList[j].id);
            		questionDiv.append(previewDiv);
            		
            		var textarea = $('<textarea class="editor"></textarea>');
            		textarea.attr("name","editor-"+questionList[j].id);
            		textarea.attr('questionId',questionList[j].id);
            		textareaDiv.append(textarea);
            		
            		textarea.html(questionList[j].content);
            		previewDiv.html(questionList[j].content);
            		
            		var editor = CKEDITOR.replace( textarea[0],{
//            			toolbar: [
//            				{ name: 'styles', items: [ 'Source','Undo', 'Redo','Format', 'Font', 'FontSize' ] },
//            				{ name: 'basicstyles', items: [ 'Bold', 'Italic', 'Underline', 'Strike', 'RemoveFormat', 'CopyFormatting' ] },
//            				{ name: 'colors', items: [ 'TextColor', 'BGColor' ] },
//            				{ name: 'align', items: [ 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock' ] },
//            				{ name: 'paragraph', items: [ 'NumberedList', 'BulletedList', 'Outdent', 'Indent' ] },
//            				{ name: 'insert', items: [ 'Image', 'Table', 'TextField' ] }
//            			],
            			bodyClass: 'document-editor',
            			height: 400,
            			width:500
            		} );
            		
            		//编辑器on change 事件
            		editor.on( 'change', function( event ) {    
            		    var data = this.getData();//内容          		    
//            		    var tmpDiv = $('<div></div>');
//            		    tmpDiv.append(data);
//            		    console.log(tmpDiv[0]);
//            		    
//            		    //TODO 修改Input样式
//            		    var inputList = tmpDiv.find('input[type="text"]');
//            		    console.log(inputList);
//            		    for(var i=0;i<inputList.length;i++){
//            		    	$(inputList[i]).addClass("weui-input");
//            		    }
//            		    
//            		    this.setData(tmpDiv.html());
            			//TODO 预览更新内容
            			$('#preview-'+$('textarea[name="'+this.name+'"]').attr("questionId")).html(data);
            			//TODO mathjax刷新
            			
            			//TODO 解析答案，查找answerType的标签，normalInput为普通输入，numberInput为输入数字，fomulaSelector为公式选择器
            			
            		});
            	}
            }
            MathJax.Hub.Configured();
        }
    });
});
//弹出窗口，添加知识点
function initAdd(){
	openModal({
		'title' : '添加知识点',
		'targetId' : 'form',
		'sureBtnText' : '保存'
	});
}
//重置弹出窗口表单内容
function resetForm(){
    $("#name").val('');
    $("#id").val('');
    $("#index").val('');
}

function initEdit(id,index){
    $("#index").val(index);
    $("#id").val(id);
    Ding.ajax({
        'url' : '/acadamic-web-manager/manager/course/chapter/knowledge/getKnowledgeById.shtml',
        'params' : {
            'knowledgeId' : id
        },
        'successCallback' : function(result){
            var data = result.data;
            $("#name").val(data.name);
            openModal({
                'title':'编辑知识点',
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
        title:'删除知识点',
        content: '是否删除知识点 ?',
        confirmButton:'删除',
        cancelButton:'取消',
        confirmButtonClass:'btn-success',
        confirm:function(){
            Ding.ajax({
                'url':'/acadamic-web-manager/manager/course/chapter/knowledge/deleteKnowledgeById.shtml',
                'params':{
                    'knowledgeId' : id
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
    this.location = '/acadamic-web-manager/manager/course/chapter/knowledge/point/index.shtml?knowledgeId='+id+'&knowledgeCode='+code+'&breadSequence=1';
}