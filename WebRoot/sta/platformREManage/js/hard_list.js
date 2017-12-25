var table,leftTable;
$(document).ready(function(){
	initLeftTable();
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
						{"data": "mac"},
						{"data": "name"},
						{"data": "ip"},
						{
							"data": "os",
							render: function(data, type, row, meta) {
								return getDictionaryName("os",data);
                     		}
						},
						{
							"data": "pinpai",
							render: function(data, type, row, meta) {
								return getDictionaryName("pinpai",data);
                     		}
						},
						{
							"data": "runState",
							render: function(data, type, row, meta) {
								//return getDictionaryName("pinpai",data);
								return data;
                     		}
						}
      				],
         "ajax" :{
             'url':"hard_getList",
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

	//$('#leftDown').DataTable();
	draws();
	
	
});

//表格
function initLeftTable(){
	leftTable = $('#leftDown').DataTable({
		 "serverSide": true,
        "dom": 'tflip',
        'searching':false,
        "lengthMenu":[10,20,30],
        "ordering": false,
        "columns": [
                    	{
                    		"data":"id"
                    	},
						{"data": "attackIp"},
						{"data": "port"},
						{"data": "protocol"},
						{
							"data": "sourceHost",
							render: function(data, type, row, meta) {
								return data.name;
                    		}
						},
						{
							"data": "sourcePort"
						},
						{
							"data": "attckType",
							"visible": false
						}
     				],
        "ajax" :{
            'url':"hard_getLogList",
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
        },fnRowCallback : function(nRow, aData, iDisplayIndex){  
            jQuery("td:first", nRow).html(iDisplayIndex +1);  
            return nRow;  
         }
	});
	
}
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



// 添加
function add(){
	if(!yzAddForm())
		return;
	$.ajax(
		{ 
			url: "hard_insert", 
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
	if(!yzEditForm())
		return;
	$.ajax(
		{ 
			url: "hard_update", 
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
			url: "hard_delete", 
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

function draws(){
	var canvas = document.getElementById('canvas');
	var stage = new JTopo.Stage(canvas);
	//显示工具栏
	//showJTopoToobar(stage);

	var scene = new JTopo.Scene();	
	scene.background = './sta/platformREManage/images/bg.jpg';
	
	function node(x, y, img, name){
		var node = new JTopo.Node(name);
		node.setImage('./sta/platformREManage/images/' + img, true);				
		node.setLocation(x, y);
		scene.add(node);
		return node;
	}				
	
	function linkNode(nodeA, nodeZ){
		var link = new JTopo.FoldLink(nodeA, nodeZ);
		link.lineWidth = 3;
		link.strokeColor = '255,255,0';
		scene.add(link);
		return link;
	}
	
	var s1 = node(49, 41, 'satellite_antenna.png', 'Satellitte Feed');
	var s2 = node(57, 136, 'antenna.png', 'Off air');
	var s3 = node(57, 251, 'msc.png', 'Programing');
	
	var r1 = node(143, 43, 'router.png');
	var r2 = node(143, 63, 'router.png');
	r2.alarm = '2 W';
	var r3 = node(143, 83, 'router.png');
	var r4 = node(143, 103, 'router.png');
	var r5 = node(143, 123, 'router.png', 'Encoder');
	
	var r6 = node(243, 123, 'router.png', 'Scrambler');
	linkNode(r1, r6);
	linkNode(r2, r6);
	linkNode(r3, r6);
	linkNode(r4, r6);
	linkNode(r5, r6);
	
	var r7 = node(143, 180, 'router.png');
	var r8 = node(143, 200, 'router.png');
	linkNode(r7, r6);
	linkNode(r8, r6);
	
	var dataCloud = node(316, 113, 'cloud.png');
	scene.add(new JTopo.Link(dataCloud, r6));
	
	var tw130 = node(436, 107, 'tw130.png');
	scene.add(new JTopo.Link(tw130, dataCloud));
	
	var pstn = node(316, 176, 'cloud.png');
	linkNode(pstn, tw130);
	
	var wdm = node(525, 114, 'wdm.png', 'WDM');
	scene.add(new JTopo.Link(wdm, tw130));
	
	var testing = node(568, 128, 'testing.png');
	testing.alarm = '1 M';
	scene.add(new JTopo.Link(testing,wdm));
	
	var wdm2 = node(607, 114, 'wdm.png', 'WDM');
	scene.add(new JTopo.Link(wdm2, testing));
	
	var mainframe = node(654, 152, 'mainframe.png');
	linkNode(mainframe, wdm2);			
	
	var phone = node(738, 173, 'phone.png', 'Phone');
	linkNode(phone, mainframe);
	
	var host = node(730, 225, 'host.png', 'Pc');
	linkNode(host, mainframe);
	
	var router2 = node(706, 282, 'router2.png', 'STB');
	router2.alarm = "1 W";
	router2.alarmColor = '0,255,0';
	linkNode(router2, mainframe);
	
	var terminal = node(669, 326, 'terminal.png', 'IPTV/SDV');
	linkNode(terminal, router2);
	
	var modem = node(623, 49, 'modem.png', 'Modem');
	var pc = node(742, 7, 'host.png');
	var router3 = node(671, 73, 'router2.png');
	var terminal3 = node(736, 100, 'terminal.png');		
	
	linkNode(pc, modem);
	linkNode(router3, modem);
	linkNode(terminal3, router3);
				
	stage.add(scene);
}

//饼图(平台安全情况)
var pie = echarts.init(document.getElementById('pie'));
option1 = {
	    title: {
	        text: '平台安全情况',
	        subtext: '',
	        left: 'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        // orient: 'vertical',
	        // top: 'middle',
	        bottom: 10,
	        left: 'right',
	        data: getAttackType()
	    },
	    series : [
	        {
	            type: 'pie',
	            radius : '65%',
	            center: ['50%', '50%'],
	            selectedMode: 'single',
	            data:getAttackTypes(),
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};
pie.setOption(option1);

//饼图(硬件运行情况)
var pie1 = echarts.init(document.getElementById('pie1'));
option2 = {
	    title: {
	        text: '网络整体运行情况',
	        subtext: '',
	        left: 'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        // orient: 'vertical',
	        // top: 'middle',
	        bottom: 10,
	        left: 'right',
	        data: getRunStates()
	    },
	    series : [
	        {
	            type: 'pie',
	            radius : '65%',
	            center: ['50%', '50%'],
	            selectedMode: 'single',
	            data:getRunStatesAndValues(),
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};
pie1.setOption(option2);


//柱状图
var bar = echarts.init(document.getElementById('bar'));

// 指定图表的配置项和数据
var option = {
	title : {
		text :'系统访问情况'
	},
	tooltip : {},
	legend : {
		data : ['数量']
	},
	xAxis : {
		data :getHardIps().names
	},
	yAxis : {},
	series : [ {
		name : '销量',
		type : 'bar',
		data :getHardIps().values
	} ]
};
// 使用刚指定的配置项和数据显示图表。
bar.setOption(option);



//获取攻击类型
function getAttackType(){
	var data;
	$.ajax(
			{ 
				url: "hard_getAttackType", 
				type:"GET",
				async:false,
				dataType:'json',
				success: function(msg){
					data=msg;
				}
		});
	return data;
}

//获取攻击类型
function getAttackTypes(){
	var data;
	$.ajax(
			{ 
				url: "hard_getAttackTypes", 
				type:"GET",
				async:false,
				dataType:'json',
				success: function(msg){
					data=msg;
				}
		});
	return data;
}


//获取ip类型
function getHardIps(){
	var data;
	$.ajax(
			{ 
				url: "hard_getHardIps", 
				type:"GET",
				async:false,
				dataType:'json',
				success: function(msg){
					data=msg;
				}
		});
	return data;
}
//获取运行状态
function getRunStates(){
	var data;
	$.ajax(
			{ 
				url: "hard_getRunStates", 
				type:"GET",
				async:false,
				dataType:'json',
				success: function(msg){
					data=msg;
				}
			});
	return data;
}
//获取运行状态
function getRunStatesAndValues(){
	var data;
	$.ajax(
			{ 
				url: "hard_getRunStatesAndValues", 
				type:"GET",
				async:false,
				dataType:'json',
				success: function(msg){
					data=msg;
				}
			});
	return data;
}