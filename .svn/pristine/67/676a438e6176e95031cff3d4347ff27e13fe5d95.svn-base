var setting = {
		data: { 
			simpleData: { 
			enable: true 
			} 
		},
		edit: {
			enable: true,
			removeTitle:"删除节点",
			renameTitle:"重命名节点"
		},
		view: {
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			selectedMulti: false
		},
		callback: {
			beforeRemove: beforeRemove,
			onRename: onRename
		},
		async: {
			enable: true,
			url: "xtQx_getQx",
			autoParam: ["id"]
		}
	};
var zNodes;

	var tree;
	
	var zTree,rMenu;
	$(document).ready(function(){
		trees();
		rMenu = $("#rMenu");
	});

//异步加载树数据
function ajaxGetTree(){
	var nodes;
	$.ajax({
		  type: "POST",
		  url: "xtQx_getQxList",
		  data:"jsId="+$("#js").val(),
		  async:false,
		  dataType: "json",
		  success: function(msg){
			 nodes = msg;
		}
	});
	return nodes;
}

//加载树
function trees(){
	tree = $.fn.zTree.init($("#treeDemo"),setting);
	//disableCheck();
}



//修改节点名字
function onRename(e,treeId,treeNode) {
	if(null!=treeNode){
		var node = tree.getNodeByTId(treeNode.tId);
		var parameter = {
	            node:JSON.stringify(node),
	    };
			$.ajax({
			  type: "POST",
			  url: "xtQx_rename",
			  data:parameter,
			  success: function(msg){
				  if(msg>0){
					  layer.alert("改名成功!", {
							skin: 'layui-layer-lan'
							,offset: ['100px', '450px']
							,closeBtn: 0
							,time:1000
							,anim:1 //动画类型
						});
				}}
			});
	}
}

//删除节点
function beforeRemove(treeId, treeNode){
	if(null!=treeNode){
		var node = tree.getNodeByTId(treeNode.tId);
		var parameter = {
	        node:JSON.stringify(node)
	    };
			$.ajax({
			  type: "POST",
			  url: "xtQx_del",
			  data:parameter,
			  success: function(msg){
				  if(msg>0){
					  layer.alert("删除成功!", {
							skin: 'layui-layer-lan'
							,offset: ['100px', '450px']
							,closeBtn: 0
							,time:1000
							,anim:1 //动画类型
						});
				}}
			});
	}
}


//添加节点
var newCount = 1;
function addHoverDom(treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
		+ "' title='增加节点' onfocus='this.blur();'></span>";
	sObj.after(addStr);
	var btn = $("#addBtn_"+treeNode.tId);
	if (btn) btn.bind("click", function(){
		$('#addModal').modal('show');
		$("#addForm").find("[name='xtQx.parentId']").val(treeNode.id);
		//alert(treeNode);
		//tree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
		return false;
	});
};
 function removeHoverDom(treeId, treeNode) {
	$("#addBtn_"+treeNode.tId).unbind().remove();
};

function add(){
	var form = $("#addForm");
	$.ajax({
		  type: "POST",
		  url: "xtQx_add",
		  data:$("#addForm").serializeArray(),
		  success: function(msg){
			  if(msg>0){
				$('#addModal').modal('hide');
				 layer.alert("添加成功!", {
						skin: 'layui-layer-lan'
						,offset: ['100px', '450px']
						,closeBtn: 0
						,time:1000
						,anim:1 //动画类型
				});
				trees();
			}}
	});
}
