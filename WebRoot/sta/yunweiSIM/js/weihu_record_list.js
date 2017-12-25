var table;
$(document).ready(function(){
	var type=$("#sType").val();;
	if(type ==''){
		type = '1';
	}
	if(type =='1'){
		//显示维护报表
		whObject();
	}
	if(type == '2'){
		//显示操作日志
		sxPersonal();
	}
	
});
//显示维护报表
function whObject(){
	var th=" <th>序号</th>"+
    "  <th>姓名</th>"+
     " <th>身份证</th>"+
      "<th>内容</th>"+
      "<th>类型</th>"+
      "<th>结果</th>"+
      "<th>对象</th>"+
      "<th>进入时间</th>";
	$("#tabhead").html(th);
	table = $('#myTable').DataTable({
	 "serverSide": true,
	  "dom": 'tflip',
	  'searching':false,
	  "lengthMenu":[10,20,30],
	  "ordering": false,
	  "columns": [
	              	{
	              		"data":"md_id",
	              		"visible": false
	              	},
					{"data": "m_name"},
					{"data": "m_card"},
					{
					"data": "md_context",
					},
					{"data": "md_species"},
					{"data": "md_results"},
					{"data": "md_object"},
					{"data": "md_time"}
				],
	  "ajax" :{
	      'url':"yunWei_getMaintenanceRecordListData",
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
		$(this).toggleClass('selected').addClass("");
	} );
}

//显示工作人员信息
function sxPersonal(){
	
}


//关闭添加模态窗口时,清空里面的验证图标
$('#addModal').on('hide.bs.modal', function (){
	var form = $("#addForm");
	form.find(".glyphicon-remove").remove();
	form.find(".glyphicon-ok").remove();
});
//关闭编辑模态窗口时,清空里面的验证图标
$('#editModal').on('hide.bs.modal', function (){
	var form = $("#editForm");
	form.find(".glyphicon-remove").remove();
	form.find(".glyphicon-ok").remove();
});

//显示错误信息
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
				,anim:6 //动画类型
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

//将表单参数弄成json
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

//点击添加按钮
$('.add').click(function(){
	$('#addForm')[0].reset();
	$('#addModal').modal('show')
});

//点击编辑按钮
$('.edit').click(function(){
	var objs = table.rows('.selected').data();
	if(objs.length==0){
		layer.alert('请选择!', {
			skin: 'layui-layer-lan'
			,offset: ['100px', '450px']
			,closeBtn:0
			,time:1500
			,anim:6 //动画类型
		});
		return;
	}
	//清空数据
	$('#editForm')[0].reset();
	//填充数据
	var obj = objs[0];
	var form = $("#editForm").serializeArray()
	for(var i=0;i<=form.length;i++){
		var name = $(form[i]).attr("name");
		var a = (name+"").split(".")[1];
		$("#editForm").find("[name='"+name+"']").val($(obj).attr(a+""));
	}
	$('#editModal').modal('show');
});

//点击选择本页
$(".selected").click(function(){
	var rows = table.page(table.page()).rows().nodes();
	$(rows).toggleClass('selected').addClass("");
})


//点击取消本页选择
$(".unselected").click(function(){
	$(table.rows('.selected').nodes()).removeClass("selected");
})


//查询
$(".query-btn").click(function(){
	var sType = $("#selecttype").val();
	location.href = "yunWei_preJiLu?sType="+sType;
})

//添加
function add(){
	//js验证
	if(!checkJsAdd()){
		return;
	}
	$.ajax(
		{ 
			url: "yunWei_insertMaintenanceRecord", 
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
function checkJsAdd(){
	var flag = false;
	var md_mid = $("#addmd_mid").val();
	var md_context = $("#addmd_context").val();
	var md_species = $("#addmd_species").val();
	var md_results = $("#addmd_results").val();
	var md_object = $("#addmd_object").val();
	var md_time = $("#addmd_time").val();
	if(md_mid == ''){
		alert('选择维护人员');
	}else if(md_context == ''){
		alert('请填写内容详情');
	}
	else if(md_species == ''){
		alert('请填写类型');
	}
	else if(md_results == ''){
		alert('请填写结果');
	}
	else if(md_object == ''){
		alert('请填写维护对象');
	}
	else if(md_time == ''){
		alert('请填写时间');
	}else{
		flag = true;
	}
	return flag;
}
function checkJsUpdate(){
	var flag = false;
	var md_context = $("#updatemd_context").val();
	var md_species = $("#updatemd_species").val();
	var md_results = $("#updatemd_results").val();
	var md_object = $("#updatemd_object").val();
	var md_time = $("#updatemd_time").val();
	if(md_context == ''){
		alert('请填写内容详情');
	}
	else if(md_species == ''){
		alert('请填写类型');
	}
	else if(md_results == ''){
		alert('请填写结果');
	}
	else if(md_object == ''){
		alert('请填写维护对象');
	}
	else if(md_time == ''){
		alert('请填写时间');
	}else{
		flag = true;
	}
	return flag;
}
//修改
function update(){
	//JS判断
	if(!checkJsUpdate()){
		return;
	}
	$.ajax(
			{ 
			url: "yunWei_updateMaintenanceRecord", 
			type:"POST",
			data:$("#editForm").serializeArray(),
			success: function(msg){
				if(msg>0){
					$('#editModal').modal('hide');
					//alert("修改成功");
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
//点击删除按钮
$('.del').click(function(){
	var objs = table.rows('.selected').data();
	if(objs.length==0){
		layer.alert('请选择!', {
			skin: 'layui-layer-lan'
			,offset: ['100px', '450px']
			,closeBtn: 0
			,time:1500
			,anim:6 //动画类型
		});
		return;
	}
	//清空数据
	$('#delModal').find(".list-group").empty();
	$('#delModal').find("h3").remove();
	//填充数据
	for(var i=0;i<objs.length;i++){
		var li = "<li class='list-group-item' id='"+objs[i].md_id+"'>"+objs[i].m_name+"&nbsp;&nbsp;&nbsp;&nbsp;"+objs[i].md_time+"</li>";
		$('#delModal').find(".list-group").append(li);
	}
	$('#delModal').find(".list-group").after("<h3>确定删除这"+objs.length+"条记录吗？</h3>");
	$('#delModal').modal('show')
});

//删除
function del(){
	var lis = $('#delModal').find(".list-group").find("li");
	var ids = [];
	for(var i=0;i<lis.length;i++){
		ids.push($(lis[i]).attr("id"));
	}
	$.ajax(
		{ 
			url: "yunWei_deleteMaintenanceRecord", 
			type:"POST",
			data:"ids="+ids,
			success: function(msg){
				if(msg>0){
					$('#delModal').modal('hide');
					layer.alert('删除成功!', {
						skin: 'layui-layer-lan'
						,offset: ['100px', '450px']
						,closeBtn: 0
						,anim:1 //动画类型
					});
					table.draw();
				}
			}
	});
}



