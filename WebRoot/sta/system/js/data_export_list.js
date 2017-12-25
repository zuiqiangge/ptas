var table;
$(document).ready(function(){
	
	var type=$("#sType").val();;
	var date = "";
	if(type ==''){
		type = '1';
	}
	if(type =='1'){
		//显示受训对象信息
		sxObject();
	}
	if(type == '2'){
		//显示工作人员信息
		sxPersonal();
	}
	if(type == '3'){
		//显示实训信息
		sxTraning();
	}
	
});
//显示受训对象信息
function sxObject(){
	var th=" <th>序号</th>"+
    "  <th>单位名称</th>"+
     " <th>姓名</th>"+
      "<th>性别</th>"+
      "<th>身份证</th>"+
      "<th>人员身份</th>"+
      "<th>进入模式</th>"+
      "<th>编制类型</th>"+
      "<th>人员状态</th>"+
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
	              		"data":"t_id",
	              		"visible": false
	              	},
					{"data": "o_name"},
					{"data": "t_name"},
					{
					"data": "t_sex",
					},
					{"data": "t_card"},
					{"data": "t_identity"},
					{"data": "t_enter"},
					{"data": "t_compile"},
					{"data": "t_status"},
					{"data": "t_time"}
				],
	  "ajax" :{
	      'url':"user_getTrainedListData",
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
}

//显示工作人员信息
function sxPersonal(){
	var th="<th>序号</th>"+
    "<th>姓名</th>"+
    "<th>性别</th>"+
    "<th>在编情况</th>"+
    "<th>擅长领域</th>"+
    "<th>联系方式</th>"+
    "<th>部门代码</th>"+
    "<th>年龄</th>"+
    "<th>身份证号</th>";
	$("#tabhead").html(th);
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
				{"data": "xb"},
				{
					"data": "zbflag",
					render: function(data, type, row, meta) {
			            if (data='0') {
			                return "不在编"
			            }
			            return "在编";
			        }
				},
				{"data": "scly"},
				{"data": "lxfs"},
				{"data": "bm"},
				{"data": "age"},
				{"data": "sfzh"}
				],
	 "ajax" :{
	     'url':"user_getPersionalListData",
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
}
//显示实训信息
function sxTraning(){
	var th="<th>序号</th>"+
	"<th>时间</th>"+
    "<th>姓名</th>"+
    "<th>保留时间</th>"+
    "<th>地点</th>"+
    "<th>教员</th>"+
    "<th>受训对象名称</th>"+
    "<th>受训单位名称</th>";
	$("#tabhead").html(th);
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
				{"data": "time"},
				{"data": "name"},
				{
				"data": "keepTime",
				},
				{"data": "place"},
				{"data": "teacher"},
				{"data": "sxdwName"},
				{"data": "sxdxName"}
				],
	 "ajax" :{
	     'url':"export_getSXData",
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


//查询
$(".query-btn").click(function(){
	var sType = $("#selecttype").val();
	location.href = "export_list?sType="+sType;
})

//导出
function exportMes(){
	var exportType = $("#sType").val();
	var exPath = $("#sysPath").val();
	$.ajax({
		url:"export_mesDetails",
		type:"POST",
		data:{"sType":exportType,"exportPath":exPath},
		dataType: "json",   
		success:function(data){
			if(data.mes==0){
				alert('导出成功！');
			}else{
				alert('导出错误！');
			}
		}
	});
}





