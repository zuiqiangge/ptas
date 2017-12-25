var table;
//初始化表格
$(document).ready(function(){
	table = $('#xtUserTable').DataTable({
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
                     	{
							"data": "password",
							"visible": false
						},
						{"data": "loginid"},
						
						{"data": "username"},
      				],
         "ajax" :{
             'url':"xtUser_getXtUserData",
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
	//单行选择
	 $('#xtUserTable tbody').on( 'click', 'tr', function () {
	        if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	        }
	        else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	        }
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
//关闭改密模态窗口时,清空里面的验证图标
$('#editPwdModal').on('hide.bs.modal', function (){
	var form = $("#editPwdForm");
	form.find(".glyphicon-remove").remove();
	form.find(".glyphicon-ok").remove();
});


//验证addForm
function yzAddForm(){
	var myMap = new Map();
	var form = $("#addForm");
	//验证用户名(不能为空)
	var loginid = form.find("[name='xtUser.loginid']");
	if(null==loginid.val()||loginid.val()=="")
		myMap.set(loginid,"用户名不能为空!")
	else
		myMap.set(loginid,null);
	//验证昵称
	myMap.set(form.find("[name='xtUser.username']"),null);
	//验证密码
	myMap.set(form.find("[name='xtUser.password']"),null);
	//验证两次输入密码是否一样
	var pwd = form.find("[name='xtUser.password']");
	var repwd =form.find("[name='xtUser.repassword']");
	if(pwd.val()!=repwd.val())
		myMap.set(repwd,"两次输入密码不一样!")
	else
		myMap.set(repwd,null);
	
	//防止重复添加图标	
	form.find(".glyphicon-remove").remove();
	form.find(".glyphicon-ok").remove();
	
	return errorMsg(myMap);
	
}


//验证editForm
function yzEditForm(){
	var myMap = new Map();
	var form = $("#editForm");
	//验证用户名(不能为空)
	var loginid = form.find("[name='xtUser.loginid']");
	if(null==loginid.val()||loginid.val()=="")
		myMap.set(loginid,"用户名不能为空!")
	else
		myMap.set(loginid,null);
	//验证昵称(只是为了显示验证通过图标)
	myMap.set(form.find("[name='xtUser.username']"),null);
	//防止重复添加图标	
	form.find(".glyphicon-remove").remove();
	form.find(".glyphicon-ok").remove();
	return errorMsg(myMap);
	
}


//验证editPwdForm
function yzEditPwdForm(){
	var myMap = new Map();
	var form = $("#editPwdForm");
	//验证用户名(不能更改,理论上不会为空)
	var loginid = form.find("[name='xtUser.loginid']");
	myMap.set(loginid,null);
	//验证旧密码输入是否正确
	var pwd = form.find("[name='xtUser.password']");
	if(!checkPwd())
		myMap.set(pwd,"密码输入不正确");
	else
		myMap.set(pwd,null);
	//验证两次输入密码是否一样
	var newPwd = form.find("[name='xtUser.newPassWord']");
	var repwd =form.find("[name='xtUser.repassword']");
	if(newPwd.val()!=repwd.val())
		myMap.set(repwd,"两次输入密码不一样!")
	else
		myMap.set(repwd,null);
	//防止重复添加图标	
	form.find(".glyphicon-remove").remove();
	form.find(".glyphicon-ok").remove();
	return errorMsg(myMap);
}



//显示错误信息
function errorMsg(myMap){
	var count = 0;;
	myMap.forEach(function(value,key) {
		if(null!=value){
			count = count+1;
			$(key).parent().parent().after("<span style='color:red' class='glyphicon glyphicon-remove'></span>");
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
			$(key).parent().parent().after("<span style='color:green' class='glyphicon glyphicon-ok'></span>");
		  
		}
	});
	if(count>0)
		return false;
	else
		return true;
}

//验证密码是否正确
function checkPwd(){
	var flag = false;
	//将密码改成md5的值
	var formData = $("#editPwdForm").serializeArray();
	for(var i=0;i<formData.length;i++){
		if(formData[i].name=="xtUser.password")
			formData[i].value=hex_md5(formData[i].value);
	}
	$.ajax(
			{ 
				url: "xtUser_checkUser", 
				type:"POST",
				async:false, 
				data:formData,
				success: function(msg){
					if(msg>0)
						flag = true;
				}
		});
	return flag;
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
			,closeBtn: 0
			,time:1000
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

//点击改密按钮
$(".editpwd").click(function(){
	var objs = table.rows('.selected').data();
	if(objs.length==0){
		layer.alert('请选择!', {
			skin: 'layui-layer-lan'
			,offset: ['100px', '450px']
			,closeBtn: 0
			,time:1000
			,anim:6 //动画类型
		});
		return;
	}
	//清空数据
	$('#editPwdForm')[0].reset();
	$('#editPwdForm').find("[name='xtUser.loginid']").val(objs[0].loginid);
	$('#editPwdForm').find("[name='xtUser.id']").val(objs[0].id);
	$('#editPwdModal').modal('show');
})


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
		var li = "<li class='list-group-item' id='"+objs[i].id+"'>"+objs[i].username+"&nbsp;&nbsp;&nbsp;&nbsp;"+objs[i].loginid+"</li>";
		$('#delModal').find(".list-group").append(li);
	}
	$('#delModal').find(".list-group").after("<h3>确定删除这"+objs.length+"条记录吗？</h3>");
	$('#delModal').modal('show')
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

//点击设置角色按钮
$('.setRole').click(function(){
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
	$('#roleForm')[0].reset();
	$('#roleForm').find("[name='id']").val(objs[0].id);
	$('#roleModal').modal('show')
	
});

//添加
function add(){
	//验证addForm
	if(!yzAddForm())
		return;
	
	//将密码改成md5的值
	var formData = $("#addForm").serializeArray();
	for(var i=0;i<formData.length;i++){
		if(formData[i].name=="xtUser.password")
			formData[i].value=hex_md5(formData[i].value);
	}
	
	$.ajax(
		{ 
			url: "xtUser_insert", 
			type:"POST",
			data:formData,
			success: function(msg){
				if(msg>0){
					$('#addModal').modal('hide')
					layer.alert('添加成功!', {
						skin: 'layui-layer-lan'
						,offset: ['100px', '450px']
						,closeBtn: 0
						,anim: 1 //动画类型
					});
					table.ajax.reload().draw();
				}
			}
	});
}

//修改
function update(){
	if(!yzEditForm())
		return ;
	$.ajax(
		{ 
			url: "xtUser_update", 
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

//删除
function del(){
	var lis = $('#delModal').find(".list-group").find("li");
	var ids = [];
	for(var i=0;i<lis.length;i++){
		ids.push($(lis[i]).attr("id"));
	}
	$.ajax(
		{ 
			url: "xtUser_delete", 
			type:"POST",
			data:"ids="+ids,
			success: function(msg){
				if(msg>0){
					$('#delModal').modal('hide');
					layer.alert('删除成功!', {
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


//修改密码
function updatePwd(){
	//验证表单
	if(!yzEditPwdForm())
		return;
	
	//将密码改成md5的值
	var formData = $("#editPwdForm").serializeArray();
	for(var i=0;i<formData.length;i++){
		if(formData[i].name=="xtUser.newPassWord")
			formData[i].value=hex_md5(formData[i].value);
	}
	
	//异步修改
	$.ajax(
			{ 
				url: "xtUser_updatePwd", 
				type:"POST",
				data:formData,
				success: function(msg){
					if(msg>0){
						$('#editPwdModal').modal('hide');
						layer.alert('修改密码成功!', {
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
//点击设置角色按钮
$('.setRole').click(function(){
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
	$('#roleForm')[0].reset();
	$.ajax(
			{ 
				url: "xtUser_getRole", 
				type:"GET",
				data:"id="+objs[0].id,
				dataType:'json',
				success: function(msg){
					var roles = $('#roleForm').find("[name='role']");
					for(var i=0;i<roles.length;i++){
						for(var j=0;j<msg.length;j++){
							if($(roles[i]).val()==msg[j].id){
								roles[i].checked=true;
							}
						}
						
					}
				}
		});
	
	$('#roleForm').find("[name='id']").val(objs[0].id);
	$('#roleModal').modal('show')
	
});

//设置角色
function setRole(){
	var formData = $("#roleForm").serializeArray();
	var roles = $('#roleForm').find("[name='role']");
	if(roles.length==0){
		layer.alert('请选择!', {
			skin: 'layui-layer-lan'
			,offset: ['100px', '450px']
			,closeBtn: 0
			,time:1500
			,anim:6 //动画类型
		});
		return;
	}
	var jsIds=[];
	for(var i=0;i<roles.length;i++){
		if(roles[i].checked){
			jsIds.push(roles[i].value);
		}
	}
	var parameter = {
			roles:jsIds+"",
	        id:$('#roleForm').find("[name='id']").val()
	};
	$.ajax(
			{ 
				url: "xtUser_setRole", 
				type:"POST",
				data:parameter,
				success: function(msg){
					if(msg>0){
						$('#addModal').modal('hide')
						layer.alert('设置角色成功!', {
							skin: 'layui-layer-lan'
							,offset: ['100px', '450px']
							,closeBtn: 0
							,anim: 1 //动画类型
						});
						$('#roleModal').modal('hide')
					}
				}
		});
}