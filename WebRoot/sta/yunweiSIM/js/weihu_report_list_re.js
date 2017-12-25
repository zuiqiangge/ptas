$(document).ready(function(){
	//本极度维护分布
	jidufenbuDate('1');
	//本月度维护分布
	jidufenbuDate('2');
	//本季度维护统计
	jidutongjiDateGroup('1');
	//本月维护统计
	jidutongjiDateGroup('2');
	//维护记录统计
	jidutongjiDateGroup('3');
	//jilutongji();
});



function jidufenbuDate(type){
	$.ajax({
        url: 'yunWei_selectDealBytime',
        type: 'POST',
        data:{
           timeType:type
        },
        dataType: 'json',
        success: function(resp){
        	if(type == '1'){
        		//季度分布
        		jidufenbu(resp.listStr,resp.listValue);
        	}
        	if(type == '2'){
        		//本月分布
        		yuefenbu(resp.listStr,resp.listValue);
        	}
        }
	});
}


function jidutongjiDateGroup(type){
	$.ajax({
        url: 'yunWei_selectDealBytimeGroup',
        type: 'POST',
        data:{
           timeType:type
        },
        dataType: 'json',
        success: function(resp){
        	if(type == '1'){
        		//季度统计
        		jidutongji(resp.listStr,resp.listInt,resp.max);
        	}
        	if(type == '2'){
        		//本月统计/人员分组
        		yuetongji(resp.listStr,resp.listInt,resp.max);
        	}
        	if(type == '3'){
        		//本月统计/日期分组
        		jilutongji(resp.listStr,resp.listInt,resp.max);
        	}
        }
	});
}


function jilutongji(data1,data2,max){
	var myCharts = echarts.init(document.getElementById('ec5'));
	var dataAxis = data1;//['点', '击', '柱', '子', '或', '者', '两', '指', '在', '触', '屏', '上', '滑', '动', '能', '够', '自', '动', '缩', '放'];
	var data = data2;//[220, 182, 191, 234, 290, 330, 310, 123, 442, 321, 90, 149, 210, 122, 133, 334, 198, 123, 125, 220];
	var yMax = max;
	var dataShadow = [];

	for (var i = 0; i < data.length; i++) {
	    dataShadow.push(yMax);
	}

	option = {
	    title: {
	        text: '维护统计',
	        subtext: '时间段内每天的次数/默认本月',
	        left: 'center'
	    },
	    xAxis: {
	        data: dataAxis,
	        axisLabel: {
	            inside: true,
	            textStyle: {
	                color: 'red'
	            }
	        },
	        axisTick: {
	            show: false
	        },
	        axisLine: {
	            show: false
	        },
	        z: 10
	    },
	    yAxis: {
	        axisLine: {
	            show: false
	        },
	        axisTick: {
	            show: false
	        },
	        axisLabel: {
	            textStyle: {
	                color: '#999'
	            }
	        }
	    },
	    dataZoom: [
	        {
	            type: 'inside'
	        }
	    ],
	    series: [
	        { // For shadow
	            type: 'bar',
	            itemStyle: {
	                normal: {color: 'rgba(0,0,0,0.05)'}
	            },
	            barGap:'-100%',
	            barCategoryGap:'40%',
	            data: dataShadow,
	            animation: false
	        },
	        {
	            type: 'bar',
	            itemStyle: {
	                normal: {
	                    color: new echarts.graphic.LinearGradient(
	                        0, 0, 0, 1,
	                        [
	                            {offset: 0, color: '#83bff6'},
	                            {offset: 0.5, color: '#188df0'},
	                            {offset: 1, color: '#188df0'}
	                        ]
	                    )
	                },
	                emphasis: {
	                    color: new echarts.graphic.LinearGradient(
	                        0, 0, 0, 1,
	                        [
	                            {offset: 0, color: '#2378f7'},
	                            {offset: 0.7, color: '#2378f7'},
	                            {offset: 1, color: '#83bff6'}
	                        ]
	                    )
	                }
	            },
	            data: data
	        }
	    ]
	};

	// Enable data zoom when user click bar.
	var zoomSize = 6;
	myCharts.on('click', function (params) {
	    console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
	    myCharts.dispatchAction({
	        type: 'dataZoom',
	        startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
	        endValue: dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
	    });
	});
	myCharts.setOption(option);
}


function jidutongji(data1,data2,max){
	var myCharts = echarts.init(document.getElementById('ec3'));
	
	var dataAxis = data1;
	var data = data2;//[220, 182, 191, 234, 290, 330, 310, 123, 442, 321, 90, 149, 210, 122, 133, 334, 198, 123, 125, 220];
	var yMax = max;
	var dataShadow = [];

	for (var i = 0; i < data.length; i++) {
	    dataShadow.push(yMax);
	}

	option = {
	    title: {
	        text: '本季度维护统计',
	        subtext: '人员/次数'
	    },
	    xAxis: {
	        data: dataAxis,
	        axisLabel: {
	            inside: true,
	            textStyle: {
	                color: 'red'
	            }
	        },
	        axisTick: {
	            show: false
	        },
	        axisLine: {
	            show: false
	        },
	        z: 10
	    },
	    yAxis: {
	        axisLine: {
	            show: false
	        },
	        axisTick: {
	            show: false
	        },
	        axisLabel: {
	            textStyle: {
	                color: '#999'
	            }
	        }
	    },
	    dataZoom: [
	        {
	            type: 'inside'
	        }
	    ],
	    series: [
	        { // For shadow
	            type: 'bar',
	            itemStyle: {
	                normal: {color: 'rgba(0,0,0,0.05)'}
	            },
	            barGap:'-100%',
	            barCategoryGap:'40%',
	            data: dataShadow,
	            animation: false
	        },
	        {
	            type: 'bar',
	            itemStyle: {
	                normal: {
	                    color: new echarts.graphic.LinearGradient(
	                        0, 0, 0, 1,
	                        [
	                            {offset: 0, color: '#83bff6'},
	                            {offset: 0.5, color: '#188df0'},
	                            {offset: 1, color: '#188df0'}
	                        ]
	                    )
	                },
	                emphasis: {
	                    color: new echarts.graphic.LinearGradient(
	                        0, 0, 0, 1,
	                        [
	                            {offset: 0, color: '#2378f7'},
	                            {offset: 0.7, color: '#2378f7'},
	                            {offset: 1, color: '#83bff6'}
	                        ]
	                    )
	                }
	            },
	            data: data
	        }
	    ]
	};

	// Enable data zoom when user click bar.
	var zoomSize = 6;
	myCharts.on('click', function (params) {
	    console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
	    myCharts.dispatchAction({
	        type: 'dataZoom',
	        startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
	        endValue: dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
	    });
	});
	myCharts.setOption(option);
}

function yuetongji(data1,data2,max){
	var myCharts = echarts.init(document.getElementById('ec4'));
	var dataAxis = data1;//['点', '击', '柱', '子', '或', '者', '两', '指', '在', '触', '屏', '上', '滑', '动', '能', '够', '自', '动', '缩', '放'];
	var data = data2;//[220, 182, 191, 234, 290, 330, 310, 123, 442, 321, 90, 149, 210, 122, 133, 334, 198, 123, 125, 220];
	var yMax = max;
	var dataShadow = [];

	for (var i = 0; i < data.length; i++) {
	    dataShadow.push(yMax);
	}

	option = {
	    title: {
	        text: '本月维护统计',
	        subtext: '人员/次数'
	    },
	    xAxis: {
	        data: dataAxis,
	        axisLabel: {
	            inside: true,
	            textStyle: {
	                color: 'red'
	            }
	        },
	        axisTick: {
	            show: false
	        },
	        axisLine: {
	            show: false
	        },
	        z: 10
	    },
	    yAxis: {
	        axisLine: {
	            show: false
	        },
	        axisTick: {
	            show: false
	        },
	        axisLabel: {
	            textStyle: {
	                color: '#999'
	            }
	        }
	    },
	    dataZoom: [
	        {
	            type: 'inside'
	        }
	    ],
	    series: [
	        { // For shadow
	            type: 'bar',
	            itemStyle: {
	                normal: {color: 'rgba(0,0,0,0.05)'}
	            },
	            barGap:'-100%',
	            barCategoryGap:'40%',
	            data: dataShadow,
	            animation: false
	        },
	        {
	            type: 'bar',
	            itemStyle: {
	                normal: {
	                    color: new echarts.graphic.LinearGradient(
	                        0, 0, 0, 1,
	                        [
	                            {offset: 0, color: '#83bff6'},
	                            {offset: 0.5, color: '#188df0'},
	                            {offset: 1, color: '#188df0'}
	                        ]
	                    )
	                },
	                emphasis: {
	                    color: new echarts.graphic.LinearGradient(
	                        0, 0, 0, 1,
	                        [
	                            {offset: 0, color: '#2378f7'},
	                            {offset: 0.7, color: '#2378f7'},
	                            {offset: 1, color: '#83bff6'}
	                        ]
	                    )
	                }
	            },
	            data: data
	        }
	    ]
	};

	// Enable data zoom when user click bar.
	var zoomSize = 6;
	myCharts.on('click', function (params) {
	    console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
	    myCharts.dispatchAction({
	        type: 'dataZoom',
	        startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
	        endValue: dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
	    });
	});
	myCharts.setOption(option);
}

function yuefenbu(date1,data2){
	var myCharts = echarts.init(document.getElementById('ec2'));
	option = {
		    title: {
		        text: '本月维护分布',
		        subtext: '维护类型'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        // orient: 'vertical',
		        // top: 'middle',
		        bottom: 10,
		        left: 'center',
		        data: date1//['西凉', '益州','兖州','荆州','幽州']
		    },
		    series : [
		        {
		            type: 'pie',
		            radius : '65%',
		            center: ['50%', '50%'],
		            selectedMode: 'single',
		            data:data2,
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
	    myCharts.setOption(option);
}


function jidufenbu(data1,data2){
	var myCharts = echarts.init(document.getElementById('ec1'));
	option = {
		    title: {
		        text: '本季度维护分布',
		        subtext: '维护类型'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        // orient: 'vertical',
		        // top: 'middle',
		        bottom: 10,
		        left: 'center',
		        data: data1//['西凉', '益州','兖州','荆州','幽州']
		    },
		    series : [
		        {
		            type: 'pie',
		            radius : '65%',
		            center: ['50%', '50%'],
		            selectedMode: 'single',
		            data:data2//[
		                //{value:1548,name: '幽州'},
		                //{value:535, name: '荆州'},
		                //{value:510, name: '兖州'},
		                //{value:634, name: '益州'},
		                //{value:735, name: '西凉'}
		            //]
		            	,
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
	    myCharts.setOption(option);
}



