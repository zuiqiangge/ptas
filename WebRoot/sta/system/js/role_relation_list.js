var table;
$(document).ready(function(){
	table = $('#myTable').DataTable({
		 "serverSide": true,
         "dom": 'tflip',
         'searching':false,
         "lengthMenu":[10,20,30],
         "ordering": false,
         "columns": [
                     	{
                     		"data":"id",
                     		"visible": false
                     	},
						{"data": "jsmc"}
						
      				],
         "ajax" :{
             'url':"xtJs_getData",
             "type": "POST"
         },
         'language': {  
             'emptyTable': '没有数据',  
             'loadingRecords': '加载中...',  
             'processing': '查询中...',  
             'lengthMenu': '每页 _MENU_行',  
             'zeroRecords': '没有数据',  
             'paginate': {  
                 'first':      '第一页',  
                 'last':       '最后一页',  
                 'next':       '下一页',  
                 'previous':   '上一页'  
             }, 
             "info": "显示页 _PAGE__of__PAGES_页   共 _TOTAL_ 项",
             'infoEmpty': '没有数据'
         }
	});
	
	//多行选择
	$('#myTable tbody').on( 'click', 'tr', function () {
		//$(this).toggleClass('selected').addClass("");
		 if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	        }
	        else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	        }
		aa();
	} );

});
var setting = {
		check: {
			enable: true,
			chkboxType: {"Y" : "p", "N" : "p"}
		},
		data: { 
			simpleData: { 
			enable: true 
			} 
		}
		
		
	};
var zNodes;
	var tree;
	$(document).ready(function(){
		//trees();
	});

//异步加载树数据
function ajaxGetTree(jsId){
	var nodes;
	$.ajax({
		  type: "POST",
		  url: "xtQx_getQxList",
		  data:"jsId="+jsId,
		  async:false,
		  dataType: "json",
		  success: function(msg){
			 nodes = msg;
		}
	});
	return nodes;
}

//加载树
function trees(jsId){
	tree = $.fn.zTree.init($("#treeDemo"),setting,ajaxGetTree(jsId));
}
//选择表格里的角色时
function aa(){
	$("#treeDemo").empty();
	var objs = table.rows('.selected').data();
	trees(objs[0].id);
}

//赋权按钮
function getChecked(jsId){
	//选中的
	var nodes = tree.getChangeCheckedNodes();
		var parameter = {
            nodes:JSON.stringify(nodes),
            jsid:jsId
        };
		$.ajax({
		  type: "POST",
		  url: "xtQx_setQx",
		  data:parameter,
		  success: function(msg){
			  if(msg>0){
				  layer.alert("赋权成功!", {
						skin: 'layui-layer-lan'
						,offset: ['100px', '450px']
						,closeBtn: 0
						,time:1000
						,anim:1 //动画类型
					});
			}}
		});
}



//如果是管理员,禁用所有勾选
function disableCheck(){
	alert($("#js").html());
	if($("#js").text()=="系统管理员"){
		 var nodes = tree.getNodes;
		for (var i=0, l=nodes.length; i < l; i++) {
			tree.setChkDisabled(nodes[i], true);
		} 
	}
}





//点击添加按钮
$('.add').click(function(){
	$('#addForm')[0].reset();
	$('#addModal').modal('show')
});

//点击编辑按钮
$('.edit').click(function(){
	$('#editForm')[0].reset();
	var form = $('#editForm');
	var objs = table.rows('.selected').data();
	alert(objs[0].jsmc);
	form.find("[name='xtJs.id']").val(objs[0].id);
	form.find("[name='xtJs.jsmc']").val(objs[0].jsmc);
	$('#editModal').modal('show')
});

//点击赋权按钮
$('.fq').click(function(){
	var objs = table.rows('.selected').data();
	getChecked(objs[0].id);
});

//添加
function add(){
	$.ajax(
			{ 
				url: "xtJs_insert", 
				type:"POST",
				data:$("#addForm").serializeArray(),
				success: function(msg){
					if(msg>0){
						$('#addModal').modal('hide')
						layer.alert('添加成功!', {
							skin: 'layui-layer-lan'
							,offset: ['100px', '450px']
							,closeBtn: 0
							,anim:1 //动画类型
						});
						table.ajax.reload().draw();
					}
				}
		});
}

//修改
function update(){
	$.ajax(
		{ 
			url: "xtJs_update", 
			type:"POST",
			data:$("#editForm").serializeArray(),
			success: function(msg){
				if(msg>0){
					$('#editModal').modal('hide');
					layer.alert('修改成功!', {
						skin: 'layui-layer-lan'
						,offset: ['100px', '450px']
						,closeBtn: 0
						,anim: 1 //动画类型
					});
					table.draw();
				}
			}
	});
}