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
							"data":"sxxmId",
                     		render: function(data, type, row, meta) {
                     			if(data!=""&&data!=null)
                     				return getSxProjectName(data).name;
                     			return "";
                     		}
						},
						{
							"data": "url",
							"visible": false
						},
						{"data": "type"},
						{
							"data": "status",
							render: function(data, type, row, meta) {
								return getDictionaryName("shzt",data);
                     		}
						}
						
      				],
         "ajax" :{
             'url':"sxResource_getData",
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



$('.main-nav').on('click', 'li', function(){
	$(this).siblings().removeClass('current').end().addClass('current');
	$('#iframe').attr('src', $(this).attr('data-src'));
});


$(window).resize(function(e) {
    $("#bd").height($(window).height() - $("#hd").height() - $("#ft").height()-6);
	$(".wrap").height($("#bd").height()-6);
	$("#iframe").height($(window).height() - $("#hd").height() - $("#ft").height() - 6);
}).resize();

//点击上传按钮
$('.add').click(function(){
	$('#uploadForm')[0].reset();
	$('#addModal').modal('show')
});

//点击下载按钮
$('.download').click(function(){
	download();
});



//上传
function upload(){
			var formData = new FormData($("#uploadForm")[0]);  
			 $.ajax({  
			      url: 'sxResource_upload' ,  
			      type: 'POST',  
			      data: formData,  
			      async: false,  
			      cache: false,  
			      contentType: false,  
			      processData: false,  
			      success: function (returndata) { 
			    	  $('#addModal').modal('hide');
			    	  table.ajax.reload();
			      },  
			 });
}

//下载
function download(){
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
			$("#fileName").val(objs[0].name);
			var formData = new FormData($("#downloadForm")[0]); 
			$("#downloadForm").submit();
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

//点击审核按钮
$('.check').click(function(){
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
	if(objs[0].status==1){
		layer.alert('该项已审核!', {
			skin: 'layui-layer-lan'
			,offset: ['100px', '450px']
			,closeBtn: 0
			,time:1500
			,anim:6 //动画类型
		});
		return;
	}
	$("#checkForm").find("[name='id']").val(objs[0].id);
	$('#checkModal').modal('show');
});

function check(){
	$.ajax(
			{ 
				url: "sxResource_check", 
				type:"POST",
				data:$("#checkForm").serializeArray(),
				success: function(msg){
					if(msg>0){
						$('#checkModal').modal('hide')
						layer.alert('审核成功!', {
							skin: 'layui-layer-lan'
							,offset: ['100px', '450px']
							,closeBtn: 0
							,anim:1 //动画类型
						});
						 table.ajax.reload();
					}
				}
		});
}
