var table;
var objectTable;
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
						{
                     		"data": "sxProject",
                     		render: function(data, type, row, meta) {
                     			return getSxProjectName(data).name;
                     			//alert(JSON.stringify(data));
                     		}
						},
						{"data": "keepTime"},
						{
							"data": "place"
						/*	render: function(data, type, row, meta) {
					            if (data='0') {
					                return "不在编"
					            }
					            return "在编";
					        }*/
						},
						{
							"data": "teacher",
							render: function(data, type, row, meta) {
                     			return getSxTeacherName(data).name;
                     		}
						},
						{
							"data": "sxdwId",
							render: function(data, type, row, meta) {
                     			return getSxdwName(data).o_name;
                     		}
						
						},
						{
							"data": "sxdxId","visible": false,
							render: function(data, type, row, meta) {
                     			return getSxdxName(data).t_name;
                     		}
						},
						{"data": "time"}
      				],
         "ajax" :{
             'url':"sxPlan_getData",
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
	 $('#myTable tbody').on( 'click', 'tr', function () {
	        if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	        }
	        else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	        }
	} );
	
	//初始化表格
	 objectTable = initObjectTable();
	
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
//验证addForm
function yzAddForm(){
	var myMap = new Map();
	var form = $("#addForm");
	//防止重复添加图标	
	form.find(".glyphicon-remove").remove();
	form.find(".glyphicon-ok").remove();
	//验证姓名(不用验证,值为null)
	var name = form.find("[name='yyGzryxxb.name']");
	myMap.set(name,null);
	//验证性别(不用验证,值为null)
	var xb = form.find("[name='yyGzryxxb.xb']");
	myMap.set(xb,null);
	//验证在编情况(只能是1或0)
	var zbflag = form.find("[name='yyGzryxxb.zbflag']");
	if(zbflag.val()!='1'&&zbflag.val()!='0'){
		myMap.set(zbflag,"只能输入1或0(1:在编;0:不在编)");
		return errorMsg(myMap);
	}
	else
		myMap.set(zbflag,null);
	//验证擅长领域(不用验证,值为null)
	var scly = form.find("[name='yyGzryxxb.scly']");
	myMap.set(scly,null);
	//验证联系方式(只能是数字且长度不超过15)
	var lxfs = form.find("[name='yyGzryxxb.lxfs']");
	if(isNaN(lxfs.val())||lxfs.val().length>15){
		myMap.set(lxfs,"联系方式只能是数字,长度不能超过15!");
		return errorMsg(myMap);
	}
	else
		myMap.set(lxfs,null);
	//验证年龄(只能是数组且长度不超过3)
	var age = form.find("[name='yyGzryxxb.age']");
	if(isNaN(age.val())||age.val().length>3){
		myMap.set(age,"年龄只能是数字,长度不能超过3位!");
		return errorMsg(myMap);
	}
	else
		myMap.set(age,null);
	//验证部门代码(不用验证)
	var bm = form.find("[name='yyGzryxxb.bm']");
	myMap.set(bm,null);
	//验证身份证号(不用验证)
	var sfzh = form.find("[name='yyGzryxxb.sfzh']");
	myMap.set(sfzh,null);
	return errorMsg(myMap);
}

//验证editForm
function yzEditForm(){
	var myMap = new Map();
	var form = $("#editForm");
	//防止重复添加图标	
	form.find(".glyphicon-remove").remove();
	form.find(".glyphicon-ok").remove();
	//验证姓名(不用验证,值为null)
	var name = form.find("[name='yyGzryxxb.name']");
	myMap.set(name,null);
	//验证性别(不用验证,值为null)
	var xb = form.find("[name='yyGzryxxb.xb']");
	myMap.set(xb,null);
	//验证在编情况(只能是1或0)
	var zbflag = form.find("[name='yyGzryxxb.zbflag']");
	if(zbflag.val()!='1'&&zbflag.val()!='0'){
		myMap.set(zbflag,"只能输入1或0(1:在编;0:不在编)");
		return errorMsg(myMap);
	}
	else
		myMap.set(zbflag,null);
	//验证擅长领域(不用验证,值为null)
	var scly = form.find("[name='yyGzryxxb.scly']");
	myMap.set(scly,null);
	//验证联系方式(只能是数字且长度不超过15)
	var lxfs = form.find("[name='yyGzryxxb.lxfs']");
	if(isNaN(lxfs.val())||lxfs.val().length>15){
		myMap.set(lxfs,"联系方式只能是数字,长度不能超过15!");
		return errorMsg(myMap);
	}
	else
		myMap.set(lxfs,null);
	//验证部门代码(不用验证)
	var bm = form.find("[name='yyGzryxxb.bm']");
	myMap.set(bm,null);
	//验证年龄(只能是数组且长度不超过3)
	var age = form.find("[name='yyGzryxxb.age']");
	if(isNaN(age.val())||age.val().length>3){
		myMap.set(age,"年龄只能是数字,长度不能超过3位!");
		return errorMsg(myMap);
	}
	else
		myMap.set(age,null);
	
	//验证身份证号(不用验证)
	var sfzh = form.find("[name='yyGzryxxb.sfzh']");
	myMap.set(sfzh,null);
	return errorMsg(myMap);
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
		name = name+"";
		var a =name.substring(name.indexOf(".")+1,name.length);
		$("#editForm").find("[name='"+name+"']").val($(obj).attr(a+""));
	}
	$('#editModal').modal('show');
});

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
		var li = "<li class='list-group-item' id='"+objs[i].id+"'>"+objs[i].name+"&nbsp;&nbsp;&nbsp;&nbsp;"+objs[i].sfzh+"</li>";
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



//查询
$(".query-btn").click(function(){
	var data = getFormJson($("#conditionFrom"));
	table.settings()[0].ajax.data =data;
	table.ajax.reload();
})

//点击添加按钮
$('.add').click(function(){
	$('#addForm')[0].reset();
	$('#addModal').modal('show');
});

var planId = null;
//点击设置受训对象
$(".setObject").click(function(){
	var objs = table.rows('.selected').data();
	//未选择时,提醒
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
	planId = objs[0].id;
	
	//重新加载
	objectTable.ajax.reload();
	//
	var trs = $(objectTable.rows().nodes());
	
	
	//根据选择,获取配置信息并填充
	getObjected(objs[0].id);
	
	$('#setObjectModal').modal('show');
})

//添加
function add(){
	$.ajax(
		{ 
			url: "sxPlan_insert", 
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

//修改
function update(){
	$.ajax(
		{ 
			url: "sxPlan_update", 
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
		var li = "<li class='list-group-item' id='"+objs[i].id+"'>"+getSxProjectName(objs[i].name).name+"&nbsp;&nbsp;&nbsp;&nbsp;</li>";
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
			url: "sxPlan_delete", 
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


//异步根据获取正面字段
function getSxProjectName(id){
	var sxxm;
	$.ajax(
			{ 
				url: "sxPlan_getSxProjectName", 
				type:"POST",
				data:"id="+id,
				async:false,
				dataType:'json',
				success: function(msg){
					
					sxxm = msg;
				}
		});
	return sxxm;
}


function getSxdwName(id){
	var sxdw;
	$.ajax(
			{ 
				url: "sxPlan_getSxdwName", 
				type:"POST",
				data:"id="+id,
				async:false,
				dataType:'json',
				success: function(msg){
					sxdw = msg;
				}
		});
	return sxdw;
}

function getSxdxName(id){
	var sxdx;
	$.ajax(
			{ 
				url: "sxPlan_getSxdxName", 
				type:"POST",
				data:"id="+id,
				async:false,
				dataType:'json',
				success: function(msg){
					sxdx = msg;
				}
		});
	return sxdx;
}

function getSxTeacherName(id){
	var teacher;
	$.ajax(
			{ 
				url: "sxPlan_getTeacherName", 
				type:"POST",
				data:"id="+id,
				async:false,
				dataType:'json',
				success: function(msg){
					teacher = msg;
				}
		});
	return teacher;
}


function initObjectTable(){
	 var table =  $('#objectTable').DataTable({
	   "serverSide": true,
       "dom": 'tpfli',
       'searching':false,
       "lengthMenu":[3,10,15],
       "ordering": false,
       "info": false,
       "lengthChange": false,
       "columns": [
                   	{
                     		"data":"t_id",
                     		"visible": false
                     	},
                     	{"data": "t_name","title":"受训对象名称",
                     		render: function(data, type, row, meta) {
                     			return getsign(row);
                     		}
                     	}
						
    				],
       "ajax" :{
           'url':"user_getTrainedListData",
           "type": "POST",
           "async":false
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
          // "info": "显示页 _PAGE__of__PAGES_页   共 _TOTAL_ 项",
           'infoEmpty': '没有数据'
       }
	});
	
	//多行选择
	$('#objectTable tbody').on( 'click', 'tr', function () {
			var tr = $(this).has("span");
			if(tr.html()==null||tr.html()=="")
				$(this).toggleClass('selected');
	});
	
	
	
	return table;
}

//获取配置的受训对象
function getObjected(planId){
	$("#ul").empty();
	$.ajax(
			{ 
				url: "sxPlan_getObjected", 
				type:"GET",
				data:"planId="+planId,
				async:false,
				dataType:'json',
				success: function(msg){
					if(msg.length==0){
						//$("#ul").append("<li class='list-group-item'>什么都没有</li>")
					}else{
						var lis = "";
						for(var i=0;i<msg.length;i++){
							lis= lis+"<li class='list-group-item' name='"+msg[i].objectId+"'>"+msg[i].t_name+"<a href='#' onclick='removeLi(this)'><span class='glyphicon glyphicon-remove pull-right'></span></a></li>"
						}
						$("#ul").append(lis);
					}
				}
		});
}



function getObjects(name){
	//alert($(name).val());
	var data={
		"trained.t_name":$(name).val()
	}
	if($(name).val()!=""&&$(name).val()!=null)
		objectTable.settings()[0].ajax.data =data;
	else{
		var data={
				"trained.t_name":"%%"
		}
		objectTable.settings()[0].ajax.data =data;
	}
	objectTable.ajax.reload();
}

//获取是否再次计划里的标识
function getsign(row){
	var par={
		"planObject.planId":planId,
		"planObject.objectId":row.t_id
	}
	var result;
	$.ajax({ 
					url: "sxPlan_getPlanObjectByProperty", 
					type:"GET",
					data:par,
					async:false,
					dataType:'json',
					success: function(msg){
						if(msg.length>0)
							result =row.t_name+"<span class='glyphicon glyphicon-ok'></span>";
						else
							result =row.t_name;
					}
	});
	
	return result;
}

//点击添加到配置列表组
function addObject(){
	var objs = objectTable.rows('.selected').data();
	//未选择时,提醒
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
	//拼成带值得列表项,并插入
	var lis = "";
	for(var i=0;i<objs.length;i++){
		var f = $("#ul").find("[name='"+objs[i].t_id+"']");
		if(f.html()!=null&&f.html()!=""){
			alert(objs[i].t_name+"-已被添加!");
			continue;
		}
			
		lis= lis+"<li class='list-group-item' name='"+objs[i].t_id+"'>"+objs[i].t_name+"<a href='#' onclick='removeLi(this)'><span class='glyphicon glyphicon-remove pull-right'></span></a></li>"
	}
	$("#ul").prepend(lis);
	//取消已选中的表格
	$(objectTable.rows('.selected').nodes()).removeClass("selected");
}

//点击已配置列表组的项,移除该项
function removeLi(a){
	$(a).parent("li").remove();
}

//点击提交修改,提交配置
function setObjects(){
	var objectIds=[];
	$("#ul li").each(function (){
		objectIds.push($(this).attr("name"));
	})
	
	var par = {
		"objectIds":objectIds+"",
		"planId":planId
	}
	$.ajax({ 
		url: "sxPlan_setObject", 
		type:"POST",
		data:par,
		dataType:'text',
		success: function(msg){
			if(msg>0)
				$('#setObjectModal').modal('hide');
		}
});
}