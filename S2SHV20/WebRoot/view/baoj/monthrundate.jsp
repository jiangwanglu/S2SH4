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
	#main{
		position:absolute;
		top:9px
	}
	#main1{
		position:absolute;
		left:900px;
		top:9px
	}
	#main2{
		position:absolute;
		top:370px
	}
table.gridtable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>
  </head>
  <body >
  	<div id="main" border="50" style="background-color:#ffffff;width:2%;height:41%;"></div>
	<div id="main2" border="50" style="background-color: #ffffff;width:29%;height:54%;"></div>
    <script type="text/javascript">
    	var str = location.href;
		var date = str.split("?")[1].split("=")[1];
    	$(function(){
    		ma();
    	});
    	function ma(){
					 var taa = 0;
					 if(date == 0){
					 	taa = 25;
					 }else if(date == 1){
					 	taa = 34;
					 }else if(date == 2){
					 	taa = 38;
					 }
					var myChart = echarts.init(document.getElementById('main'));
		    		var option = {
		    			title:{
		    				show:true,
		    				left:'center',
		    				text:'报警数量',
		    			},
					    tooltip: {
					        trigger: 'item',
					        formatter: "{a} <br/>{b}: {c} ({d}%)"
					    },
					     graphic:{
					            type:'text',
					            left:'center',
					            top:'center',
					            style:{
					                text:taa+"个报警数量",
					                textAlign:'center',
					                fontSize: '20',
					                fill:'#000',
					                width:30,
					                height:30
					            }
					        },
					
					    series: [
					        {
					            name:'报警数量',
					            type:'pie',
					            radius: ['60%', '80%'],
					            avoidLabelOverlap: false,
					            color: ['#B2DFEE'],
					            label: {
					                normal: {
					                    show: false,
					                    position: 'center'
					                },
					                emphasis: {
					                    show: true,
					                    textStyle: {
					                        fontSize: '30',
					                        fontWeight: 'bold'
					                    }
					                }
					            },
					            labelLine: {
					                normal: {
					                    show: false
					                }
					            },
					            data:[
					                {value:taa, name:'报警数'},
					            ]
					        }
					    ]
					};
					myChart.setOption(option);
					ma2();
    	}
    	function ma2(){
    		var values = [];
    		var da = [];
    		if(date == 0){
    			da = [4,6,3,5,4,5,1,3];
    			values = ['二0六_6','发电机2#段事故馈线柜','发电机4#段事故馈线柜','三号变压器B电流','四0五_1','一0七_3','一0七_4','一0四_1'];
    		}else if(date == 1){
    			da = [7,6,5,5,8,4,5,5,4,6,7];
    			values = ['二0六_6','二0四_8','发电机2#段事故馈线柜','发电机4#段事故馈线柜','发电机事故电源进线柜','三号变压器B电流','四0五_1','屋顶消防池液位','一0七_3','一0七_4','一0四_1'];
    		}else if(date == 2){
    			 da = [8,6,5,8,9,4,5,5,4,6,7,8,7,8]; 
    			 values = ['二0六_6','二0四_8','发电机2#段事故馈线柜','发电机4#段事故馈线柜','发电机事故电源进线柜','三号变压器B电流','四0五_1','屋顶消防池液位','一0七_3','一0七_4','一0四_1','一0四_3','一0四_6','一号变压器B电流'];
    		}
    			var myChart = echarts.init(document.getElementById('main2'));
				var option = {
				    title: {
				        text: '设备报警个数',
				    },
				    tooltip: {
				        trigger: 'axis',
				    },
				    xAxis:  {
					    axisLabel:{
			                interval : 0,
			                rotate:20 
			            }, 
			            
				        type: 'category',
				        boundaryGap: false,
				        data:values,
				    },
				    yAxis: {
				        type: 'value',
				        axisLabel: {
				            formatter: '{value} 条'
				        }
				    },
				    series: [
				        {
				            name:'报警个数',
				            type:'line',
				            data:da,
				        },
				    ]
				};
				myChart.setOption(option); 
    	}
    </script>
  </body>
</html>