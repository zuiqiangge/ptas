$(document).ready(function(){
	$('#traning_object_table').DataTable({
		 //"serverSide": true,
         "dom": 'tflip',
         'searching':false,
         "lengthMenu":[10,20,30],
         "ordering": false,
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
	
	$('#myTable').DataTable({
		 //"serverSide": true,
        "dom": 'tflip',
        'searching':false,
        "lengthMenu":[10,20,30],
        "ordering": false,
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


$('.add').click(function(){
	$('#addModal').modal('show')
});


$('.edit').click(function(){
	$('#editModal').modal('show')
});

$('.del').click(function(){
	$('#delModal').modal('show')
});





$("tbody tr").click(function(){
	$(this).removeClass();
	$(this).addClass("selected");
	/*if($(this).attr("isCheck"))
		$(this).attr("isCheck",false);
	else
		$(this).attr("isCheck",true);
	alert($(this).attr("isCheck"));*/
	$(this).css("background-color","gray");
})