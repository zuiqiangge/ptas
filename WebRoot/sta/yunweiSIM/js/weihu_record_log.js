var table;
$(document).ready(function(){
	var type=$("#sType").val();
	if(type ==''){
		type = '2';
	}
	if(type =='1'){
		//显示维护报表
		whObject();
	}
	if(type == '2'){
		//显示操作日志
		table = $('#myTable').DataTable({
			 "serverSide": true,
			  "dom": 'tflip',
			  'searching':false,
			  "lengthMenu":[10,20,30],
			  "ordering": false,
			  "columns": [
			              	{
			              		"data":"ml_id",
			              		"visible": false
			              	},
							{"data": "ml_name"},
							{"data": "ml_card"},
							{
							"data": "ml_caozuo",
							},
							{"data": "ml_ip"},
							{"data": "ml_url"},
							{"data": "ml_time"}
						],
			  "ajax" :{
			      'url':"yunWei_getMaintenanceLogListData",
			      "type": "POST"
			  },
			  'language': {  
			      'emptyTable': '没有数据',  
			      'loadingRecords': '加载中...',  
			      'processing': '查询中...',  
			      'lengthMenu': "每页  _MENU_行",  
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
	
});
//显示维护报表
function whObject(){
	
}

//显示工作人员信息
function sxPersonal(){
	
}



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


//查询
$(".query-btn").click(function(){
	var sType = $("#selecttype").val();
	location.href = "yunWei_preJiLu?sType="+sType;
})
