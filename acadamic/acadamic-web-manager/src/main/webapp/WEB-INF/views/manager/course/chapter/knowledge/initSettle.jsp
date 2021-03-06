<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css" href="/widgets/dingui/Ding.FileUploader.css" />
<script type="text/javascript" src="/widgets/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="/widgets/dingui/Ding.FileUploader.js"></script>

<script type="text/javascript" src="/acadamic-web-manager/js/manager/course/chapter/knowledge/initSettle.js"></script>
<link href="/acadamic-web-manager/js/manager/course/chapter/knowledge/initSettle.css" rel="stylesheet">
</head>
<body>
	<input type="hidden" id="knowledgeId" value="${knowledgeId }" />
	<div class="row">
		<div class="col-md-12">
			<div class="panel">
				<div class="panel-body add_panel">
					<div class="point_item_div" id="pointItem">
						<div>
							<i class="fa fa-plus"></i>
						</div>
						<div onclick="initAddPoint('DISCOVER')">
							<i class="fa fa-lightbulb-o"></i>
							<p>发现</p>
						</div>
						<div onclick="initAddPoint('EXPLORE')">
							<i class="fa fa-magnet"></i>
							<p>探索</p>
						</div>
						<div onclick="initAddGamePoint('GAME')">
							<i class="fa fa-meh-o"></i>
							<p>游戏</p>
						</div>
						<div onclick="initAddPoint('SUMMARY')">
							<i class="fa fa-inbox"></i>
							<p>总结</p>
						</div>
						<div onclick="initAddPoint('STEP')">
							<i class="fa fa-puzzle-piece"></i>
							<p>套路</p>
						</div>
						<div onclick="initAddGamePoint('EXERCISE')">
							<i class="fa fa-road"></i>
							<p>练习</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id="pointPanel">
		
	</div>
	
	<!-- 配置答案弹窗表单 -->
	<form class="modal-form" id="configAnswerForm">
		<input type="hidden" id="exerciseDetailId" />
		<div class="form-group" id="answerGroup">
			
		</div>
		<div id="exerciseDetailAnswerUploader"></div>
	</form>
</body>
</html>