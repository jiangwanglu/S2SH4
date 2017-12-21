<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'arch.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=path%>/js/echarts.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<style type="text/css">
	#main1{
		position:absolute;
		left:380px;
		top:0px
	}
	#main2{
		position:absolute;
		left:1100px;
		top:0px
	}
</style>
  </head>
  <body>
  	<div id="main" border="50" style="background-color: #000000;width:20%;height:80%;"></div>
  	<div id="main1" border="50" style="background-color: #000000;width:40%;height:80%;"></div>
  	<div id="main2" border="50" style="background-color: #000000;width:35%;height:80%;"></div>
    <script type="text/javascript">
    	var url = location.href;
    	var unitid = url.split("?")[1].split("=")[1]; 
   		$(function () {
   			var myChart = echarts.init(document.getElementById('main'));
	    	var option = {
				color: ['#3398DB'],
				title : {
					subtext: '运行天数',
				},
				legend: {
			        data:['运行天数','到达报警']
			    },
				tooltip : {
					trigger: 'axis',
					axisPointer : {            // 坐标轴指示器，坐标轴触发有效
						type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
					}
				},
				grid: {
					left: '0%',
				    right: '3%',
			        bottom: '3%',
			        containLabel: true
			    },
				xAxis : [{
					axisLabel:{
		                interval : 0,
		                rotate:20 
		            }, 
		            alignWithLabel:true,
					type : 'category',
					data : [],
				}],
						 yAxis: {
				        type: 'value',
				        axisLabel: {
				            formatter: '{value} 天'
				        }
				    },
				series : [{
					name:'直接访问',
					type:'bar',
					barWidth: '60%',  
					data:[]}
				]};
				myChart.setOption(option);
	   		 	var name = [];
	    		var value = []; 
	    		var values = [];
	    		var a = 180;
	    		    		 $.ajax({
				type:"POST",
				url:"runDateed.do",
				data:{id:1},
				dataType:"Json",
				success:function(data){
					var msg = eval("("+data+")");
					var msg1 = eval("("+msg+")");
					for(var j=0;j<msg1.length;j++){
						name.push(msg1[j].local);
						value.push(msg1[j].timen);
						if(msg1[j].timen > a){
							var c = parseInt(msg1[j].timen / a);
							c = (c + 1) * a;
							values.push(c);
						}else{
							values.push(a);
						}	
					}
					myChart.hideLoading();
					myChart.setOption({
						xAxis: {
                            data: name
                        },
                        series: [{
                            // 根据名字对应到相应的系列
                            name: '运行天数',
                            data: value,
                            type: 'bar',
                            stack:'sum',  
                        },{
                        	symbol: 'circle',
				            name:'到达报警',
				            type:'line',
				            itemStyle : {  
                                normal : {
                                	color: "#FF0000",
                                    lineStyle:{  
                                        color:'#FF0000', 
                                    }  
                                }  
                            },
				            data:values,
				        }]
					});
					main1();
				} 
			}); 
			
			
		});
		
		function main1(){
			var myChart = echarts.init(document.getElementById('main1'));
	    	var option = {
				color: ['#3398DB'],
				title : {
					subtext: '运行小时',
				},
				legend: {
			        data:['运行小时','到达报警']
			    },
				tooltip : {
					trigger: 'axis',
					axisPointer : {            // 坐标轴指示器，坐标轴触发有效
						type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
					}
				},
				grid: {
					left: '0%',
				    right: '3%',
			        bottom: '3%',
			        containLabel: true
			    },
				xAxis : [{
					axisLabel:{
		                interval : 0,
		                rotate:20 
		            }, 
		            alignWithLabel:true,
					type : 'category',
					data : [],
				}],
/* 				yAxis : [{
					allowDecimals: false,
					alignWithLabel:true,
					splitNumber:40,
	                min: 0,
	                max: 5000,
	                title: {
	                    text: '小时'
	                }
				}], */
								 yAxis: {
				        type: 'value',
				        axisLabel: {
				            formatter: '{value} 小时'
				        }
				    },
				series : [{
					name:'直接访问',
					type:'bar',
					barWidth: '60%',
					data:[]}
				]};
				myChart.setOption(option);
	   		 	var name = [];
	    		var value = []; 
	    		
	    		var values = [];
	    		
	    		var red = [];
	    		var lan = [];
    		 $.ajax({
				type:"POST",
				url:"runDateed.do",
				data:{id:2},
				dataType:"Json",
				success:function(data){
					var msg = eval("("+data+")");
					var msg1 = eval("("+msg+")");
					for(var j=0;j<msg1.length;j++){
						name.push(msg1[j].local);
					
  						if(msg1[j].localid == "DF-KT-40-001"||msg1[j].localid == "DF-KT-40-002" &&msg1[j].timen >= 3200){
							var c = parseInt(msg1[j].timen / 3200) + 1;
							var c1 = c * 3200;
							if(c > 1){
								value.push((c - 1) * 3200);
								red.push(50);
								lan.push(msg1[j].timen - ((c - 1) * 3200) - 50);
							}else{
								value.push(msg1[j].timen);
								red.push(0);
								lan.push(0);
							}
							values.push(c1);
						}else if(msg1[j].localid == "DF-KT-40-001"||msg1[j].localid == "DF-KT-40-002" &&msg1[j].timen < 3200){
							values.push(3200);
							value.push(msg1[j].timen);
							red.push(0);
							lan.push(0);
						}
						
						if(msg1[j].localid == "DF-KT-40-003" &&msg1[j].timen >= 3600){
							var c = parseInt(msg1[j].timen / 3600) + 1;
							var c1 = c * 3600;
							if(c > 1){
								value.push((c - 1) * 3600);
								red.push(50);
								lan.push(msg1[j].timen - ((c - 1) * 3600) - 50);
							}else{
								value.push(msg1[j].timen);
								red.push(0);
								lan.push(0);
							}
							values.push(c1);
						}else if(msg1[j].localid == "DF-KT-40-003" &&msg1[j].timen < 3600){
							values.push(3600);
							value.push(msg1[j].timen);
							red.push(0);
							lan.push(0);
						} 
						
						if(msg1[j].localid == "DF-KT-43-055"||msg1[j].localid == "DF-KT-43-056"||msg1[j].localid == "DF-KT-43-057"||msg1[j].localid == "DF-KT-42-004"||msg1[j].localid == "DF-KT-42-005" ||msg1[j].localid == "DF-KT-42-006"||msg1[j].localid == "DF-KT-41-007"||msg1[j].localid == "DF-KT-41-008"||msg1[j].localid == "DF-KT-41-009"||msg1[j].localid == "DF-KT-41-010"||msg1[j].localid == "DF-KT-41-011"&&msg1[j].timen >= 800){
							var c = parseInt(msg1[j].timen / 800) + 1;
							var c1 = c * 800;
							if(c > 1){
								value.push((c - 1) * 800);
								red.push(50);
								lan.push(msg1[j].timen - (((c - 1) * 800) - 50));
							}else{
								value.push(msg1[j].timen);
								red.push(0);
								lan.push(0);
							}
							values.push(c1);
						}else if(msg1[j].localid == "DF-KT-43-055"||msg1[j].localid == "DF-KT-43-056"||msg1[j].localid == "DF-KT-43-057"||msg1[j].localid == "DF-KT-42-004"||msg1[j].localid == "DF-KT-42-005" ||msg1[j].localid == "DF-KT-42-006"||msg1[j].localid == "DF-KT-41-007"||msg1[j].localid == "DF-KT-41-008"||msg1[j].localid == "DF-KT-41-009"||msg1[j].localid == "DF-KT-41-010"||msg1[j].localid == "DF-KT-41-011"&&msg1[j].timen < 800){
							values.push(800);	
							value.push(msg1[j].timen);
							red.push(0);
							lan.push(0);					
						}  
						
						if(msg1[j].localid == "DF-QD-02-001"&&msg1[j].timen >= 250){
							var c = parseInt(msg1[j].timen / 250);
							c = (c + 1) * 250;
							values.push(c);
						}else if(msg1[j].localid == "DF-QD-02-001"&&msg1[j].timen < 250){
							values.push(250);
							value.push(msg1[j].timen);
							red.push(0);
							lan.push(0);
						}
					}
					myChart.hideLoading();
					myChart.setOption({
						xAxis: {
                            data: name
                        },
                        series: [{
                            // 根据名字对应到相应的系列
                            name: '运行小时',
                            data: value,
                            stack:'sum', 
                        },{
				            type: 'bar',
				            stack:'sum', 
				           	itemStyle : {  
                                normal : {
                                	color: "#FF0000",
                                    lineStyle:{  
                                        color:'#FF0000', 
                                    }  
                                }  
                            },
            				data: red,
        				},{
				            type: 'bar',
				            stack:'sum', 
				           	itemStyle : {  
                                normal : {
                                	color: "#5CACEE",
                                    lineStyle:{  
                                        color:'#FF0000', 
                                    }  
                                }  
                            },
            				data: lan,
        				},{
                        	symbol: 'circle',
				            name:'到达报警',
				            type:'line',
				            itemStyle : {  
                                normal : {
                                	color: "#FF0000",
                                    lineStyle:{  
                                        color:'#FF0000', 
                                    }  
                                }  
                            },
				            data:values,
				        },]
					});
					main2();
				} 
			}); 
		}
		function main2(){
			var myChart = echarts.init(document.getElementById('main2'));
	    	var option = {
				color: ['#3398DB'],
				title : {
					subtext: '运行小时',
				},
				tooltip : {
					trigger: 'axis',
					axisPointer : {            // 坐标轴指示器，坐标轴触发有效
						type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
					}
				},
				grid: {
					left: '0%',
				    right: '3%',
			        bottom: '3%',
			        containLabel: true
			    },
				xAxis : [{
					axisLabel:{
		                interval : 0,
		                rotate:20 
		            }, 
		            alignWithLabel:true,
					type : 'category',
					data : [],
				}],
				 yAxis: {
				        type: 'value',
				        axisLabel: {
				            formatter: '{value} 小时'
				        }
				    },
				series : [{
					name:'直接访问',
					type:'bar',
					barWidth: '60%',
					data:[]}
				]};
				myChart.setOption(option);
	   		 	var name = [];
	    		var value = []; 
	    		
	    		var values = [0,0,0];
    		 $.ajax({
				type:"POST",
				url:"runDateed.do",
				data:{id:0},
				dataType:"Json",
				success:function(data){
					var msg = eval("("+data+")");
					var msg1 = eval("("+msg+")");
					for(var j=0;j<msg1.length;j++){
						name.push(msg1[j].local);
						value.push(msg1[j].timen);
						if(msg1[j].localid == "DF-QD-02-001" && msg1[j].timen >= 250){
							var c = parseInt(msg1[j].timen / 250);
							c = (c + 1) * 250;
							values.push(c);
						}else if(msg1[j].localid == "DF-QD-02-001" && msg1[j].timen < 250){
							values.push(250);
						}
						
						if(msg1[j].localid == "DF-XF-51-F3"||msg1[j].localid == "DF-XF-51-F2"||msg1[j].localid == "DF-XF-51-02"||msg1[j].localid == "DF-XF-51-03"||msg1[j].localid == "DF-XF-51-06"||msg1[j].localid == "DF-XF-51-10"||msg1[j].localid == "DF-XF-51-15"||msg1[j].localid == "DF-XF-51-25"&&msg1[j].timen >= 800){
							var c = parseInt(msg1[j].timen / 800);
							c = (c + 1) * 800;
							values.push(c);
						}else if(msg1[j].localid == "DF-XF-51-F3"||msg1[j].localid == "DF-XF-51-F2"||msg1[j].localid == "DF-XF-51-02"||msg1[j].localid == "DF-XF-51-03"||msg1[j].localid == "DF-XF-51-06"||msg1[j].localid == "DF-XF-51-10"||msg1[j].localid == "DF-XF-51-15"||msg1[j].localid == "DF-XF-51-25"&&msg1[j].timen < 800){
							values.push(800);
						}
						
						if(msg1[j].localid == "DF-GP-30-001"||msg1[j].localid == "DF-GP-30-002"&&msg1[j].timen >= 600){
							var c = parseInt(msg1[j].timen / 600);
							c = (c + 1) * 600;
							values.push(c);	
						}else if(msg1[j].localid == "DF-GP-30-001"||msg1[j].localid == "DF-GP-30-002"&&msg1[j].timen < 600){
							values.push(600);
						}
					}
					myChart.hideLoading();
					myChart.setOption({
						xAxis: {
                            data: name
                        },
                        series: [{
                            // 根据名字对应到相应的系列
                            name: '小时',
                            data: value
                        }]
					});
				} 
			}); 
		}
    </script>
  </body>
</html>