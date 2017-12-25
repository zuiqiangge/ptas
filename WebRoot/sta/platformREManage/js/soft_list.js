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
						{"data": "name"},
						{
							"data": "macId",
							render: function(data, type, row, meta) {
					           if(data!=null||data!="")
					        	   return getHardWareName(data).name;
					           return data;
					        }
						},
						{"data": "version"},
						{"data": "createTime"},
						{"data": "finalUpdateTime"},
						{
							"data": "isNew",
							render: function(data, type, row, meta) {
								return getDictionaryName("gxzt",data);
							}
						}
      				],
         "ajax" :{
             'url':"soft_getList",
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
	// 单行选择
	$('#myTable tbody').on( 'click', 'tr', function () {
	        if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	        }
	        else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	        }
	});
	//$("#p").panel('collapse',true);
	$("#p").slideUp("slow");
	$('#myTable tbody').on( 'dblclick', 'tr', function () {
		$("#p").slideDown("slow");
		 var obj = table.rows(this).data();
		 //$("#p").panel('expand',true);
		 initLogTable(obj[0].id);
	});
	
});


// 关闭添加模态窗口时,清空里面的验证图标
$('#addModal').on('hide.bs.modal', function (){
	var form = $("#addForm");
	form.find(".glyphicon-remove").remove();
	form.find(".glyphicon-ok").remove();
});
// 关闭编辑模态窗口时,清空里面的验证图标
$('#editModal').on('hide.bs.modal', function (){
	var form = $("#editForm");
	form.find(".glyphicon-remove").remove();
	form.find(".glyphicon-ok").remove();
});


// 显示错误信息
function errorMsg(myMap){
	var count = 0;;
	myMap.forEach(function(value,key) {
		if(null!=value){
			count = count+1;
			$(key).after("<span style='color:red' class='glyphicon glyphicon-remove'></span>");
			layer.alert(value, {
				skin: 'layui-layer-lan'
				,offset: ['100px', '450px']
				,closeBtn: 0
				,time:1000
				,anim:6 // 动画类型
			});
			key.focus();
		}
		else{
			$(key).parent().after("<span style='color:green' class='glyphicon glyphicon-ok'></span>");
		  
		}
	});
	if(count>0)
		return false;
	else
		return true;
}
// 验证addForm
function yzAddForm(){
	var myMap = new Map();
	var form = $("#addForm");
	// 防止重复添加图标
	form.find(".glyphicon-remove").remove();
	form.find(".glyphicon-ok").remove();
	//验证ip(不能为空)
	var ip = form.find("[name='hardWare.ip']");
	if(ip.val()==null||ip.val()=="")
		myMap.set(ip,"ip不能为空!");
	var os = form.find("[name='hardWare.os']");
	if(os.val()==null||os.val()=="")
		myMap.set(os,"os不能为空!");
	return errorMsg(myMap);
}

// 验证editForm
function yzEditForm(){
	var myMap = new Map();
	var form = $("#editForm");
	// 防止重复添加图标
	form.find(".glyphicon-remove").remove();
	form.find(".glyphicon-ok").remove();
	//验证ip(不能为空)
	var ip = form.find("[name='hardWare.ip']");
	if(ip.val()==null||ip.val()=="")
		myMap.set(ip,"ip不能为空!");
	var os = form.find("[name='hardWare.os']");
	if(os.val()==null||os.val()=="")
		myMap.set(os,"os不能为空!");
	return errorMsg(myMap);
}

// 将表单参数弄成json
function getFormJson(frm) {
    var o = {};
    var a = $(frm).serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            if(this.value!="")
            	o[this.name].push(this.value || '');
        } else {
        	 if(this.value!="")
            	o[this.name] = this.value || '';
        }
    });
    return o;
}

$('.main-nav').on('click', 'li', function(){
	$(this).siblings().removeClass('current').end().addClass('current');
	$('#iframe').attr('src', $(this).attr('data-src'));
});


$(window).resize(function(e) {
    $("#bd").height($(window).height() - $("#hd").height() - $("#ft").height()-6);
	$(".wrap").height($("#bd").height()-6);
	$("#iframe").height($(window).height() - $("#hd").height() - $("#ft").height() - 6);
}).resize();

// 点击添加按钮
$('.add').click(function(){
	$('#addForm')[0].reset();
	$('#addModal').modal('show')
});

// 点击编辑按钮
$('.edit').click(function(){
	var objs = table.rows('.selected').data();
	if(objs.length==0){
		layer.alert('请选择!', {
			skin: 'layui-layer-lan'
			,offset: ['100px', '450px']
			,closeBtn:0
			,time:1500
			,anim:6 // 动画类型
		});
		return;
	}
	// 清空数据
	$('#editForm')[0].reset();
	// 填充数据
	var obj = objs[0];
	var form = $("#editForm").serializeArray()
	for(var i=0;i<=form.length;i++){
		var name = $(form[i]).attr("name");
		var a = (name+"").split(".")[1];
		$("#editForm").find("[name='"+name+"']").val($(obj).attr(a+""));
	}
	$('#editModal').modal('show');
});

// 点击删除按钮
$('.del').click(function(){
	var objs = table.rows('.selected').data();
	if(objs.length==0){
		layer.alert('请选择!', {
			skin: 'layui-layer-lan'
			,offset: ['100px', '450px']
			,closeBtn: 0
			,time:1500
			,anim:6 // 动画类型
		});
		return;
	}
	// 清空数据
	$('#delModal').find(".list-group").empty();
	$('#delModal').find("h3").remove();
	// 填充数据
	for(var i=0;i<objs.length;i++){
		var li = "<li class='list-group-item' id='"+objs[i].id+"'>"+objs[i].name+"&nbsp;&nbsp;&nbsp;&nbsp;"+objs[i].ip+"</li>";
		$('#delModal').find(".list-group").append(li);
	}
	$('#delModal').find(".list-group").after("<h3>确定删除这"+objs.length+"条记录吗？</h3>");
	$('#delModal').modal('show')
});

//点击日志按钮
$('.log').click(function(){
	var objs = table.rows('.selected').data();
	if(objs.length==0){
		layer.alert('请选择!', {
			skin: 'layui-layer-lan'
			,offset: ['100px', '450px']
			,closeBtn: 0
			,time:1500
			,anim:6 // 动画类型
		});
		return;
	}
	$('#p').panel('expand',true);
	initLogTable(objs[0].id);
});

// 添加
function add(){
	/*if(!yzAddForm())
		return;*/
	$.ajax(
		{ 
			url: "soft_insert", 
			type:"POST",
			data:$("#addForm").serializeArray(),
			success: function(msg){
				if(msg>0){
					$('#addModal').modal('hide')
					layer.alert('添加成功!', {
						skin: 'layui-layer-lan'
						,offset: ['100px', '450px']
						,closeBtn: 0
						,anim:1 // 动画类型
					});
					table.ajax.reload().draw();
				}
			}
	});
}

// 修改
function update(){
	/*if(!yzEditForm())
		return;*/
	$.ajax(
		{ 
			url: "soft_update", 
			type:"POST",
			data:$("#editForm").serializeArray(),
			success: function(msg){
				if(msg>0){
					$('#editModal').modal('hide');
					layer.alert('修改成功!', {
						skin: 'layui-layer-lan'
						,offset: ['100px', '450px']
						,closeBtn: 0
						,anim: 1 // 动画类型
					});
					table.draw();
				}
			}
	});
}

// 删除
function del(){
	var lis = $('#delModal').find(".list-group").find("li");
	var ids = [];
	for(var i=0;i<lis.length;i++){
		ids.push($(lis[i]).attr("id"));
	}
	$.ajax(
		{ 
			url: "soft_delete", 
			type:"POST",
			data:"ids="+ids,
			success: function(msg){
				if(msg>0){
					$('#delModal').modal('hide');
					layer.alert('删除成功!', {
						skin: 'layui-layer-lan'
						,offset: ['100px', '450px']
						,closeBtn: 0
						,anim:1 // 动画类型
					});
					table.draw();
				}
			}
	});
}

//获取硬件名称
function getHardWareName(id){
	var data;
	$.ajax(
			{ 
				url: "soft_getHardWareName", 
				type:"POST",
				data:"id="+id,
				dataType:"json",
				async:false,
				success: function(msg){
					data = msg;
				}
		});
	return data;
}


function initLogTable(softId){
	var data;
	$.ajax(
		{ 
				url: "soft_getLogList", 
				type:"POST",
				data:"softId="+softId,
				dataType:"json",
				async:false,
				success: function(msg){
					data = msg;
		}
	});
	$("#logBody").empty();
	var html = "";
	for(var i=0;i<data.length;i++){
		html=html+"<tr><td>"+data[i].oName+"</td><td>"+data[i].xtUser.username+"</td><td>"+data[i].createTime+"</td></tr>";
	}
	$("#logBody").append(html);
}

