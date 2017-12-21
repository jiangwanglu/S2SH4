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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <body>
    <div id="main" style="width: 600px;height:350px;"></div>
    <script type="text/javascript">	
    	var index = "";
		$(function () {
    		GetseriesValue();  //获取数据源信息
		});
		function GetseriesValue(){
			$.ajax({
				type:"POST",
				url:"fuzaied.do",
				success:function(data){
					var msg = eval("("+data+")");
					var msg1 = eval("("+msg+")");
					for(var o in msg1){
						index = msg1[o];
					}
					start();
				}
			});
		}
		
    	function start(){
	    	//获取到dom对象,初始化echarts
	    	var myChart = echarts.init(document.getElementById('main'));
	    	//指定仪表盘的配置
	    	var option = {
		    	title : {
			        text: '当日能耗：(数据)实际负载率',
			        x:'center'
			    },
			    tooltip : {
			        formatter: "{a} <br/>{b} : {c}%"
			    },
			    series: [
			        {
			            name: '负载率',
			            type: 'gauge',
			            detail: {formatter:'{value}%'},
			            data: [{value: 50, name: '负载率'}]
			        }
			    ]
			};
			option.series[0].data[0].value = index;
			myChart.setOption(option);
		}
    </script>
  </body>
</html>