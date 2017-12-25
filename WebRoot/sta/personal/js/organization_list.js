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
                     		"data":"o_id",
                     		"visible": false
                     	},
						{"data": "o_name"},
						{"data": "o_introduce"},
						{
						"data": "o_call",
						},
						{"data": "o_email"},
						{"data": "o_num"},
						{"data": "o_create_time"}
      				],
         "ajax" :{
             'url':"user_getOrganizationListData",
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

});


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
	var data = getFormJson($("#conditionFrom"));
	table.settings()[0].ajax.data =data;
	table.ajax.reload();
})
//邮箱格式
function validateemailAdd(){
         var accemail = $("#addemail").val();    //获取email控件对象
         if (accemail != "") {
                   if (!/(\S)+[@]{1}(\S)+[.]{1}(\w)+/.test(accemail)) {
                           alert("邮箱格式错误，请重新输入！");
                           return;
                   }
         }    
}
//邮箱格式
function validateemailUpdate(){
         var accemail = $("#updateemail").val();    //获取email控件对象
         if (accemail != "") {
                   if (!/(\S)+[@]{1}(\S)+[.]{1}(\w)+/.test(accemail)) {
                           alert("邮箱格式错误，请重新输入！");
                           return;
                   }
         }    
}

//添加
function add(){
	//js验证
	if(!checkJsAdd()){
		return;
	}
	$.ajax(
		{ 
			url: "user_insertOrganization", 
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

//js判断
function checkJsAdd(){
	var flat=false;
	var addname=$("#addname").val();
	var addintroduce=$("#addintroduce").val();
	var addcall=$("#addcall").val();
	var addemail=$("#addemail").val();
	var addnum=$("#addnum").val();
	if(addname==''){
		alert('请输入单位名称');
		flat = false;
	}else if(addcall==''){
		alert('请输入单位电话');
		flat = false;
	}else{
		flat = true;
	}
	return flat;
}
function checkJsUpdate(){
	var flat=false;
	var updatename=$("#updatename").val();
	var updateintroduce=$("#updateintroduce").val();
	var updatecall=$("#updatecall").val();
	var updateemail=$("#updateemail").val();
	var updatenum=$("#updatenum").val();
	if(updatename==''){
		alert('请输入单位名称');
		flat = false;
	}else if(updatecall==''){
		alert('请输入单位电话');
		flat = false;
	}else{
		flat = true;
	}
	return flat;
}

//修改
function update(){
	//JS判断
	if(!checkJsUpdate()){
		return;
	}
	$.ajax(
		{ 
			url: "user_updateOrganization", 
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
		var li = "<li class='list-group-item' id='"+objs[i].o_id+"'>"+objs[i].o_name+"&nbsp;&nbsp;&nbsp;&nbsp;"+objs[i].o_create_time+"</li>";
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
			url: "user_deleteOrganization", 
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