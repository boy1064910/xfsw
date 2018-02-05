Ding.ready(function(){
	Ding.ajax({
		'url':'/xfsw-web-auth/manager/authority/getUserAuthorityList.shtml',
		successCallback:function(result){
			var data = result.data;
			var firstMenuList = [];
			var secondMenuList = {};
			var firstIndex = 0;
			var currentSelectMenuId = 0;//当前选中的菜单ID和父级菜单ID

			for(var index in data){//处理菜单分类（一级、二级）
				if(data[index].pid==0){
					firstMenuList.push(data[index]);
				}
				else{
					if(Ding.isEmpty(secondMenuList[data[index].pid]))
						secondMenuList[data[index].pid] = [];
					secondMenuList[data[index].pid].push(data[index]);
				}
				if(!Ding.isEmpty(data[index].url)){
					var menuCatalog = window.location.pathname;//获取菜单目录
					var centerMenuCatalog = menuCatalog.substring(0,menuCatalog.lastIndexOf("/"));//倒数截取第一个分隔符前面的字段，获取菜单目录
					var dataUrl = data[index].url.substring(0,data[index].url.lastIndexOf("/"));//倒数截取最后一个分隔符之前的字段，获取配置的菜单目录
					// 正斜线个数大于1并且路径前缀一致
					if(data[index].url.match(/(\/)/g).length>1&&centerMenuCatalog.indexOf(dataUrl)!=-1){
						//当前选中菜单
						currentSelectMenuId = data[index].id;
					}
				}
			}

			var selectedMenu = null;
			for(var index in firstMenuList){
				var a = $('<a href="#"></a>');
				//菜单图标
				var ico = $('<i class="fa"></i>');
				if(Ding.isEmpty(firstMenuList[index].ico)){
					ico.addClass("fa-laptop");
				}
				else{
					ico.addClass("fa-"+firstMenuList[index].ico);
				}
				//菜单栏名称
				var span = $('<span>'+firstMenuList[index].name+'</span>');
				a.append(ico).append(span);
				var li = $('<li class="menu-list"></li>');
				li.append(a);
				$("#mNavMenu").append(li);
				//二级菜单
				var ul = $('<ul class="sub-menu-list"></ul>');
				for(var secondIndex in secondMenuList[firstMenuList[index].id]){
					var sli = $('<li><a href="'+secondMenuList[firstMenuList[index].id][secondIndex].url+'" >'+secondMenuList[firstMenuList[index].id][secondIndex].name+'</a></li>');
					ul.append(sli);
					if(currentSelectMenuId==secondMenuList[firstMenuList[index].id][secondIndex].id){
						selectedMenu = sli;
					}
				}
				li.append(ul);
			}
			
			if(Ding.isEmpty(selectedMenu)){
				$($("#mNavMenu").children()[0]).addClass("active");
				$("#mMenuText").html("HOME");
				$("#mBreadCrumb").remove();
			}
			else{
				$(selectedMenu).addClass("active");
				$(selectedMenu).parent().parent().addClass("nav-active");

				var currentMenuName = $(selectedMenu).children()[0].innerHTML;
				var currentParentMenuName = $(selectedMenu).parent().prev().children()[1].innerHTML;
				$("#mMenuText").html(currentMenuName);
				$("#mMenuText").attr("href",$($(selectedMenu).children()[0]).attr("href"));
				$("#pMenuText").html(currentParentMenuName);
			}
			
			//TODO 面包屑接龙逻辑,链接中携带参数代码 breadSequence=1
			var breadSequence = Ding.getQueryParameterByName('breadSequence');
			var breadSequences = [];
			if(breadSequence=='1'){
				breadSequences = $.cookie('breadSequences');
				if(Ding.isEmpty(breadSequences)){
					breadSequences = [];
					breadSequences.push({
						'name' : $("#requestMenuName").html(),
						'url' : window.location.href
					});
					pushBreadSequences(breadSequences);
				}
				else{
					breadSequences = JSON.parse(breadSequences);
					var isExsit = false;
					var breadIndex = 0;
					var windowLocationHrefs = window.location.href.split("?");
					for(var i=0;i<breadSequences.length;i++){
						var breadSequenceUrls = breadSequences[i].url.split("?");
						if(breadSequenceUrls[0]==windowLocationHrefs[0]){
							isExsit = true;
							breadIndex = i;
							break;
						}
					}
					if(!isExsit){
						breadSequences.push({
							'name' : $("#requestMenuName").html(),
							'url' : window.location.href
						});
						pushBreadSequences(breadSequences);
						//将数组中的面包屑全部显示
						showBreadSequences(breadSequences);
					}
					else{
						//删除定位索引之后的数据
						var deleteLength = breadSequences.length - (breadIndex + 1);
						breadSequences.splice(breadIndex,deleteLength);
						//将定位之前的数据显示在面包屑中
						showBreadSequences(breadSequences);
					}
				}
			}
			else{
				pushBreadSequences(breadSequences);
			}
			controlMenu();
		}
	});
});

function pushBreadSequences(breadSequences){
	if(breadSequences.length!=0)
		$.cookie('breadSequences',JSON.stringify(breadSequences),{
			'path':'/',
			'domain':window.domainName
		});
}

function showBreadSequences(breadSequences){
	if(breadSequences.length<1) return;
	for(var i=breadSequences.length-2;i>=0;i--){
		$("#mMenuText").parent().after('<li class="active"><a href="'+breadSequences[i].url+'">'+breadSequences[i].name+'</a></li>');
	}
}