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
                   		"data":"t_id",
                   		"visible": false
                   	},
						{"data": "o_name","title":"受训单位名称"},
						{"data": "t_name","title":"名称"},
						{
						"data": "t_sex","title":"性别"
						},
						{"data": "t_card","title":"身份证"},
						{"data": "t_identity","title":"人员身份"},
						{"data": "t_enter","title":"进入模式","visible": false},
						{"data": "t_compile","title":"编制模式"},
						{"data": "t_status","title":"人员状态"},
						{"data": "t_time","title":"进入时间"}
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




$('.main-nav').on('click', 'li', function(){
	$(this).siblings().removeClass('current').end().addClass('current');
	$('#iframe').attr('src', $(this).attr('data-src'));
});


$(window).resize(function(e) {
    $("#bd").height($(window).height() - $("#hd").height() - $("#ft").height()-6);
	$(".wrap").height($("#bd").height()-6);
	$("#iframe").height($(window).height() - $("#hd").height() - $("#ft").height() - 6);
}).resize();

//选择选项卡
$('.nav').find("li").click(function(){
	$('.nav').find("li").removeClass("active");
	$(this).addClass("active");
	
	if($(this).text()=="受训组织"){
		$("#queryName").attr("name","organization.o_name");
		sxdwTable();
	}
	else if($(this).text()=="受训对象"){
		$("#queryName").attr("name","trained.t_name");
		sxdxTable();
	}
	else if($(this).text()=="讲解员"){
		$("#queryName").attr("name","teacher.name");
		sxTeacherTable();
	}
	else{
		$("#queryName").attr("name","sxPlan.sxProject");
		sxPlanTable();
	}
		
});

//加载受训单位
function sxdwTable(){
	table.destroy();
	 $('#myTable').empty();
	table = $('#myTable').DataTable({
		 "serverSide": true,
        "dom": 'tflip',
        'searching':false,
        "lengthMenu":[10,20,30],
        "ordering": false,
        'retrieve': true,
        "columns": [
                    	{
                    		"data":"o_id",
                    		"visible": false
                    	},
						{"data": "o_name","title":"单位名称"},
						{"data": "o_introduce","title":"单位介绍"},
						{
						"data": "o_call","title":"单位电话"
						},
						{"data": "o_email","title":"单位右键"},
						{"data": "o_num","title":"单位人数"},
						{"data": "o_create_time","title":"创建时间"}
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
	 $('#myTable tbody').on( 'click', 'tr', function () {
	        if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	        }
	        else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	        }
	} );
}

//加载受训对象
function sxdxTable(){
	table.destroy();
	 $('#myTable').empty();
	table = $('#myTable').DataTable({
		 "serverSide": true,
        "dom": 'tflip',
        'searching':false,
        "lengthMenu":[10,20,30],
        "ordering": false,
        'retrieve': true,
        "columns": [
                    	{
                    		"data":"t_id",
                    		"visible": false
                    	},
						{"data": "o_name","title":"受训单位名称"},
						{"data": "t_name","title":"名称"},
						{
						"data": "t_sex","title":"性别"
						},
						{"data": "t_card","title":"身份证"},
						{"data": "t_identity","title":"人员身份"},
						{"data": "t_enter","title":"进入模式","visible": false},
						{"data": "t_compile","title":"编制模式"},
						{"data": "t_status","title":"人员状态"},
						{"data": "t_time","title":"进入时间"}
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
	 $('#myTable tbody').on( 'click', 'tr', function () {
	        if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	        }
	        else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	        }
	} );
}


//加载实训内容
function sxPlanTable(){
	table.destroy();
	 $('#myTable').empty();
	 table = $('#myTable').DataTable({
		 "serverSide": true,
         "dom": 'tflip',
         'searching':false,
         "lengthMenu":[10,20,30],
         "ordering": false,
         'retrieve': true,
         "columns": [
                     	{
                     		"data":"id",
                     		"visible": false
                     	},
						{
                     		"data": "sxProject",
                     		"title":"名称",
                     		render: function(data, type, row, meta) {
                     			return getSxProjectName(data).name;
                     		}
						},
						{"data": "keepTime","title":"持续时间"},
						{
							"data": "place",
							"title":"地点"
						/*	render: function(data, type, row, meta) {
					            if (data='0') {
					                return "不在编"
					            }
					            return "在编";
					        }*/
						},
						{"data": "teacher","title":"教员",
							render: function(data, type, row, meta) {
								if(data==""||data==null)
									return "";
                     			return getSxTeacherName(data).name;
                     		}
						},
						{
							"data": "sxdwId","title":"受训单位",
							render: function(data, type, row, meta) {
                     			return getSxdwName(data).o_name;
                     		}
						
						},
						{
							"data": "sxdxId","title":"受训对象",
							render: function(data, type, row, meta) {
                     			return getSxdxName(data).t_name;
                     		}
						},
						{"data": "time","title":"时间"}
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
	 $('#myTable tbody').on( 'click', 'tr', function () {
	        if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	        }
	        else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	        }
	} );
}

//加载教员
function sxTeacherTable(){
	table.destroy();
	 $('#myTable').empty();
	 table = $('#myTable').DataTable({
		 "serverSide": true,
         "dom": 'tflip',
         'searching':false,
         "lengthMenu":[10,20,30],
         "ordering": false,
         'retrieve': true,
         "columns": [
                     	{
                     		"data":"id",
                     		"visible": false
                     	},
						{
                     		"data": "name","title":"名称"
						}
      				],
         "ajax" :{
             'url':"teacher_getData",
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
	 $('#myTable tbody').on( 'click', 'tr', function () {
	        if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	        }
	        else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	        }
	} );
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



//查询
$(".query-btn").click(function(){
	table.settings()[0].ajax.data =getFormJson($("#conditionFrom"));
	table.ajax.reload();
})


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