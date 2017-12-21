<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Bootstrapè¿åº¦æ¡å¨ç»ç¹æ</title>
	<script type="text/javascript" src="<%=path%>/js/echarts.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
	<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/demo.css">
	<style type="text/css">
	</style>
	<script>
	$(function(){
 		var str = location.href;
		var date = str.split("?")[1].split("=")[1];
		$.ajax({
			type:"POST",
			url:"planweeked.do",
			data:{date:date},
			dataType:"Json",
			success:function(data){
				var msg = eval("("+data+")");
					var msg1 = eval("("+msg+")");
					var myChart = echarts.init(document.getElementById('main1'));
					var option = {
					    title : {
					        text: date,
					        subtext: '巡检率',
					        x:'center'
					    },
					    tooltip : {
					        trigger: 'item',
					        formatter: "{a} <br/>{b} : {c} ({d}%)"
					    },
					    color:[	'#FFA54F', '#00BFFF'],
					    legend: {
					        orient: 'vertical',
					        left: 'left',
					        data: [	msg1[0].localid,msg1[0].local]
					        
					    },
					    series : [
					        {
					            name: '巡检',
					            type: 'pie',
					            radius : '80%',
					            center: ['50%', '60%'],
					            data:[
					                {value:msg1[0].timen, name:msg1[0].localid},
					                {value:msg1[0].id, name:msg1[0].local.split("?")[0]}
					            ],
					            itemStyle: {
					            	normal:{
					            		label:{
					            			show:true,
					            			formatter:'{b}\n({d}%)',
					            		},
					            		labelLine:{
					            			show:true,
					            		}
					            	},
					                emphasis: {
					                    shadowBlur: 10,
					                    shadowOffsetX: 0,
					                    shadowColor: 'rgba(0, 0, 0, 0.5)'
					                }
					            }
					        }
					    ]
					};
					myChart.setOption(option);
					year();
			}
		}); 
	});
	function year(){
			$.ajax({
				type:"POST",
				url:"planyeared.do",
				success:function(data){
					var msg = eval("("+data+")");
					var msg1 = eval("("+msg+")");
					var myChart = echarts.init(document.getElementById('main'));
					var option = {
					    title : {
					        text: '上一年巡检率',
					        subtext: '巡检率',
					        x:'center'
					    },
					    tooltip : {
					        trigger: 'item',
					        formatter: "{a} <br/>{b} : {c} ({d}%)"
					    },
					    color:[	'#B0E0E6', '#00BFFF'],
					    legend: {
					        orient: 'vertical',
					        left: 'left',
					        data: [	msg1[0].localid,msg1[0].local]
					        
					    },
					    series : [
					        {
					            name: '巡检',
					            type: 'pie',
					            radius : '80%',
					            center: ['50%', '60%'],
					            data:[
					                {value:msg1[0].timen, name:msg1[0].localid},
					                {value:msg1[0].id, name:msg1[0].local}
					            ],
					            itemStyle: {
					            	normal:{
					            		label:{
					            			show:true,
					            			formatter:'{b}\n({d}%)',
					            		},
					            		labelLine:{
					            			show:true,
					            		}
					            	},
					                emphasis: {
					                    shadowBlur: 10,
					                    shadowOffsetX: 0,
					                    shadowColor: 'rgba(0, 0, 0, 0.5)'
					                }
					            }
					        }
					    ]
					};
					myChart.setOption(option);
				} 
			}); 
	}
</script>
</head>
<body>
	<div id="main" style="width:100%;height:350px;"></div>
	-----------------------------------------------------------------------------------------------------------------
	<div id="main1" style="width: 100%;height:400px;"></div>
</body>
</html>