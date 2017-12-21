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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <body>
    <div id="main" style="width: 600px;height:350px;"></div>
    <script type="text/javascript">	
    	var tTmp = "";
		var dataTmp = "";
		var dataTmp1 = "";
		var d = new Date();
		var i=0;
		var str = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
		
		
    	//获取到dom对象,初始化echarts
    	var myChart = echarts.init(document.getElementById('main'));
    	//指定仪表盘的配置
    	var option = {
    				title : {
				        text: '昨日能耗:(折线图)',
				        x:'center'
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
				            data : ['1#', '2#', '3#', '4#', '5#', '6#', '7#'],
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
		myChart.setOption(option);
    </script>
  </body>
</html>