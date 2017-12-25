var setting = {
		data: { 
			simpleData: { 
			enable: true 
			} 
		},
		async: {
			enable: true,
			url: "weiHu_getData",
			autoParam: ["id"]
		}
	};

	var tree;
	
	$(document).ready(function(){
		trees();
	});
var tree;


//加载树
function trees(){
	tree = $.fn.zTree.init($("#treeDemo"),setting);
}

//填充编辑表单数据
function setData(eidtForm,obj){
	//清空数据
	$(eidtForm)[0].reset();
	//填充数据
	var forms = $(eidtForm).serializeArray()
	for(var i=0;i<=forms.length;i++){
		var name = $(forms[i]).attr("name");
		var a = (name+"").split(".")[1];
		$(eidtForm).find("[name='"+name+"']").val($(obj).attr(a+""));
	}
}





//点击添加按钮
$('.add').click(function(){
	var nodes = tree.getSelectedNodes();
	//如果没有选择单一树,新建字典
	if(nodes.length==0){
		$('#dicAddForm')[0].reset();
		$('#dicAddModal').modal('show')
	//选择了,新建参数
	}else{
		$('#dicValueAddForm').find("[name='value.dicId']").val(nodes[0].id);
		$('#dicValueAddForm')[0].reset();
		$('#dicValueAddModal').modal('show')
	}
});

//添加字典
function addDic(){
	$.ajax({
		  type: "POST",
		  url: "weiHu_addDic",
		  data:$("#dicAddForm").serializeArray(),
		  success: function(msg){
			  if(msg>0){
				$('#dicAddModal').modal('hide');
				 layer.alert("添加成功!", {
						skin: 'layui-layer-lan'
						,offset: ['100px', '450px']
						,closeBtn: 0
						,time:1000
						,anim:1 //动画类型
				});
				trees();
			}}
	})
}


//添加参数
function addValue(){
	$.ajax({
		  type: "POST",
		  url: "weiHu_addValue",
		  data:$("#dicValueAddForm").serializeArray(),
		  success: function(msg){
			  if(msg>0){
				$('#dicValueAddModal').modal('hide');
				 layer.alert("添加成功!", {
						skin: 'layui-layer-lan'
						,offset: ['100px', '450px']
						,closeBtn: 0
						,time:1000
						,anim:1 //动画类型
				});
				trees();
			}}
	})
}

//点击编辑按钮
$('.edit').click(function(){
	var nodes = tree.getSelectedNodes();
	//如果没有选择单一树
	if(nodes.length==0){
		 layer.alert("请选择!", {
				skin: 'layui-layer-lan'
				,offset: ['100px', '450px']
				,closeBtn: 0
				,time:1000
				,anim:6 //动画类型
		});
		return;
	}
	//字典
	if(nodes[0].isParent){
		setData($("#dicEditForm"),nodes[0].dic);
		$('#dicEditModal').modal('show')
	//参数
	}else{
		setData($("#dicValueEditForm"),nodes[0].value);
		$('#dicValueEditForm').find("[name='value.id']").val(nodes[0].id);
		$('#dicValueEditModal').modal('show')
	}
});



//编辑字典
function editDic(){
	$.ajax({
		  type: "POST",
		  url: "weiHu_editDic",
		  data:$("#dicEditForm").serializeArray(),
		  success: function(msg){
			  if(msg>0){
				$('#dicEditModal').modal('hide');
				 layer.alert("修改成功!", {
						skin: 'layui-layer-lan'
						,offset: ['100px', '450px']
						,closeBtn: 0
						,time:1000
						,anim:1 //动画类型
				});
				trees();
			}}
	})
}


//编辑参数
function editValue(){
	$.ajax({
		  type: "POST",
		  url: "weiHu_editValue",
		  data:$("#dicValueEditForm").serializeArray(),
		  success: function(msg){
			  if(msg>0){
				$('#dicValueEditModal').modal('hide');
				 layer.alert("修改成功!", {
						skin: 'layui-layer-lan'
						,offset: ['100px', '450px']
						,closeBtn: 0
						,time:1000
						,anim:1 //动画类型
				});
				trees();
			}}
	})
}

//点击删除按钮
$('.del').click(function(){
	var nodes = tree.getSelectedNodes();
	//如果没有选择单一树
	if(nodes.length==0){
		 layer.alert("请选择!", {
				skin: 'layui-layer-lan'
				,offset: ['100px', '450px']
				,closeBtn: 0
				,time:1000
				,anim:6 //动画类型
		});
		return;
	}
	layer.confirm('是否删除？', {
		skin: 'layui-layer-lan'
		,offset: ['100px', '450px']
		,btn: ['是','否'] //按钮
		}, function(){
			del(nodes);
		}, function(){
		});
	
});

//删除
function del(nodes){
	if(nodes[0].isParent)
		var url = "weiHu_deleteDic";
	else
		var url = "weiHu_deleteDicValue";
	$.ajax({
		  type: "POST",
		  url: url,
		  data:"id="+nodes[0].id,
		  success: function(msg){
			  if(msg>0){
				 layer.alert("删除成功!", {
						skin: 'layui-layer-lan'
						,offset: ['100px', '450px']
						,closeBtn: 0
						,time:1000
						,anim:1 //动画类型
				});
				trees();
			}}
	})
}