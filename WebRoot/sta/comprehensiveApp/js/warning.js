function setReceive(){
	var text = $("#receiveForm").find("[name='shoujian']").val();
	$.ajax(
			{ 
				url: "warning_setReceive", 
				type:"POST",
				data:"text="+text,
				success: function(msg){
					if(msg>0){
						layer.alert('设置成功!', {
							skin: 'layui-layer-lan'
							,offset: ['100px', '450px']
							,closeBtn: 0
							,anim:1 //动画类型
						});
					}
				}
		});
}