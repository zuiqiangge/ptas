$(document).ready(function(){
	
	jidufenbu();
	//本月维护分布
	yuefenbu();
	//本季度维护统计
	jidutongji();
	//本月维护统计
	yuetongji();
	//维护记录统计
	jilutongji();
});



function jilutongji(){
	var myCharts = echarts.init(document.getElementById('ec5'));
	option = {
		title: {
	        text: '维护记录统计',
	        subtext: '---维护记录统计---',
	        left: 'center'
	    },
	    color: ['#3398DB'],
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            type : 'category',
	            data : ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
	            axisTick: {
	                alignWithLabel: true
	            }
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'直接访问',
	            type:'bar',
	            barWidth: '60%',
	            data:[10, 52, 200, 334, 390, 330, 220]
	        }
	    ]
	};
	myCharts.setOption(option);
}


function jidutongji(){
	var myCharts = echarts.init(document.getElementById('ec3'));
	
	var dataAxis = ['点', '击', '柱', '子', '或', '者', '两', '指', '在', '触', '屏', '上', '滑', '动', '能', '够', '自', '动', '缩', '放'];
	var data = [220, 182, 191, 234, 290, 330, 310, 123, 442, 321, 90, 149, 210, 122, 133, 334, 198, 123, 125, 220];
	var yMax = 500;
	var dataShadow = [];

	for (var i = 0; i < data.length; i++) {
	    dataShadow.push(yMax);
	}

	option = {
	    title: {
	        text: '本季度维护统计',
	        subtext: '---本季度维护统计---'
	    },
	    xAxis: {
	        data: dataAxis,
	        axisLabel: {
	            inside: true,
	            textStyle: {
	                color: '#fff'
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

function yuetongji(){
	var myCharts = echarts.init(document.getElementById('ec4'));
	var dataAxis = ['点', '击', '柱', '子', '或', '者', '两', '指', '在', '触', '屏', '上', '滑', '动', '能', '够', '自', '动', '缩', '放'];
	var data = [220, 182, 191, 234, 290, 330, 310, 123, 442, 321, 90, 149, 210, 122, 133, 334, 198, 123, 125, 220];
	var yMax = 500;
	var dataShadow = [];

	for (var i = 0; i < data.length; i++) {
	    dataShadow.push(yMax);
	}

	option = {
	    title: {
	        text: '本月维护统计',
	        subtext: '---本月维护统计---'
	    },
	    xAxis: {
	        data: dataAxis,
	        axisLabel: {
	            inside: true,
	            textStyle: {
	                color: '#fff'
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

function yuefenbu(){
	var myCharts = echarts.init(document.getElementById('ec2'));
	option = {
		    title: {
		        text: '本月维护分布',
		        subtext: '虚构数据'
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
		        data: ['西凉', '益州','兖州','荆州','幽州']
		    },
		    series : [
		        {
		            type: 'pie',
		            radius : '65%',
		            center: ['50%', '50%'],
		            selectedMode: 'single',
		            data:[
		                {
		                    value:1548,
		                    name: '幽州',
		                   
		                },
		                {value:535, name: '荆州'},
		                {value:510, name: '兖州'},
		                {value:634, name: '益州'},
		                {value:735, name: '西凉'}
		            ],
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


function jidufenbu(){
	
	var myCharts = echarts.init(document.getElementById('ec1'));
	option = {
		    title: {
		        text: '本季度维护分布',
		        subtext: '虚构数据'
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
		        data: ['西凉', '益州','兖州','荆州','幽州']
		    },
		    series : [
		        {
		            type: 'pie',
		            radius : '65%',
		            center: ['50%', '50%'],
		            selectedMode: 'single',
		            data:[
		                {value:1548,name: '幽州'},
		                {value:535, name: '荆州'},
		                {value:510, name: '兖州'},
		                {value:634, name: '益州'},
		                {value:735, name: '西凉'}
		            ],
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



