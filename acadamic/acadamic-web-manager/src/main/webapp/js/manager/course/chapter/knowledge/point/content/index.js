var columns = [];
columns.push({
    field: 'id',
    title: 'ID',
    align: 'center'
});
columns.push({
    field: 'code',
    title: '知识内容编号',
    align: 'center'
});
columns.push({
    field: 'type',
    title: '知识内容类型',
    align: 'center'
});
columns.push({
    field: 'id',
    title: '操作',
    align: 'center',
    formatter:function(value,row,index){
        var result ;
        result = '<a href="javascript:void(0)" onclick="initEdit('+row.id+','+index+')" title="编辑">编辑</a>';
        result += '<a href="javascript:void(0)" onclick="initSettle('+row.id+',\''+row.code+'\')" title="问题设置">问题设置</a>';
        result +='<a href="javascript:void(0)" onclick="initDelete('+row.id+')" title="删除">删除</a>';
        return result;
    }
});

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
            		questionDiv.append(previewDiv);
            		
            		var textarea = $('<textarea class="editor"></textarea>');
            		textareaDiv.append(textarea);
            		
            		textarea.html(questionList[j].content);
            		previewDiv.html(questionList[j].content);
            		
            		CKEDITOR.replace( textarea[0],{
            			toolbar: [
            				{ name: 'document', items: [ 'Source' ] },
            				{ name: 'clipboard', items: [ 'Undo', 'Redo' ] },
            				{ name: 'styles', items: [ 'Format', 'Font', 'FontSize' ] },
            				{ name: 'basicstyles', items: [ 'Bold', 'Italic', 'Underline', 'Strike', 'RemoveFormat', 'CopyFormatting' ] },
            				{ name: 'colors', items: [ 'TextColor', 'BGColor' ] },
            				{ name: 'align', items: [ 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock' ] },
            				{ name: 'links', items: [ 'Link', 'Unlink' ] },
            				{ name: 'paragraph', items: [ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote' ] },
            				{ name: 'insert', items: [ 'Image', 'Table' ] },
            				{ name: 'tools', items: [ 'Maximize' ] },
            				{ name: 'editing', items: [ 'Scayt' ] }
            			],
            			bodyClass: 'document-editor',
            			height: 400,
            			width:500
            		} );
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