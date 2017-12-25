

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


//打开预约窗口
function newOrder(){
	$("#newOrderModal").modal('show');
}
//提交预约
function submitOrder(){
	$.ajax(
			{ 
				url: "sxOrder_insert", 
				type:"POST",
				data:$("#newOrderForm").serializeArray(),
				success: function(msg){
					if(msg>0){
						$('#newOrderModal').modal('hide');
						layer.alert('提交成功!', {
							skin: 'layui-layer-lan'
							,offset: ['100px', '450px']
							,closeBtn: 0
							,anim:1 //动画类型
						});
					}
				}
		});
}