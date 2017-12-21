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
	<title>Bootstrap进度条动画特效</title>
	<script type="text/javascript" src="<%=path%>/js/echarts.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
	<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	
	<link rel="stylesheet" type="text/css" href="css/demo.css">
	
	<style type="text/css">
		.progress-title{
		    font-size: 16px;
		    font-weight: 700;
		    color: #333;
		    margin: 0 0 20px;
		}
		.progress{
		    height: 7px;
		    background: #333;
		    border-radius: 0;
		    box-shadow: none;
		    margin-bottom: 30px;
		    overflow: visible;
		}
		.progress .progress-bar{
		    position: relative;
		    -webkit-animation: animate-positive 2s;
		    animation: animate-positive 2s;
		}
		.progress .progress-bar:after{
		    content: "";
		    display: inline-block;
		    width: 9px;
		    background: #fff;
		    position: absolute;
		    top: -10px;
		    bottom: -10px;
		    right: -1px;
		    z-index: 1;
		    transform: rotate(35deg);
		}
		.progress .progress-value{
		    display: block;
		    font-size: 16px;
		    font-weight: 600;
		    color: #333;
		    position: absolute;
		    top: -30px;
		    right: -25px;
		}
		@-webkit-keyframes animate-positive{
		    0%{ width: 0; }
		}
		@keyframes animate-positive {
		    0%{ width: 0; }
		}
		body{  
            margin:0;  
            padding:0;  
            background-color:#F0F0F0;  
 
            /*body的背景色是不受body本身的宽高的影响的。  
              body的背景色就是铺满整个页面的。  
            */  
              
        }  
	</style>
	<script>
	$(function(){
		$.ajax({
			type:"POST",
			url:"planed.do",
			success:function(data){
				var msg = eval("("+data+")");
				var msg1 = eval("("+msg+")");
				for(var j=0;j<msg1.length;j++){
					if(j == 0){
						document.getElementById("q"+j+"").innerHTML=msg1[j].local+"号(今天),完成率";
					}else if(j == 1){
						document.getElementById("q"+j+"").innerHTML=msg1[j].local+"号(昨天),完成率";
					}else if(j == 2){
						document.getElementById("q"+j+"").innerHTML=msg1[j].local+"号(前天),完成率";
					}else{
						document.getElementById("q"+j+"").innerHTML=msg1[j].local+"号,完成率";
					}
					document.getElementById("w"+j+"").style.width=msg1[j].id+"%";
					document.getElementById("e"+j+"").innerHTML=msg1[j].id+"%";
				}
			},
		});
	});
	function s(){
	
	}
</script>
</head>
<body>

<br /><br /><br />

<div class="demo" style="width:52%">
	<div class="container">
		<div class="row">
			<div class="col-md-offset-3 col-md-6" >
				<h3 class="progress-title" id="q0">HTML5</h3>
				<div class="progress">
					<div id="w0" class="progress-bar" style="width:99%;height:7px; background:#00BFFF;">
						<div id="e0" class="progress-value">99%</div>
					</div>
				</div>

				<h3 class="progress-title" id="q1">CSS3</h3>
				<div class="progress">
					<div id="w1" class="progress-bar" style="width:99%;height:7px; background:#00BFFF;">
						<div id="e1" class="progress-value">99%</div>
					</div>
				</div>

				<h3 class="progress-title" id="q2">J-Query</h3>
				<div class="progress">
					<div id="w2" class="progress-bar" style="width:99%;height:7px; background:#00BFFF;">
						<div id="e2" class="progress-value">99%</div>
					</div>
				</div>

				<h3 class="progress-title" id="q3">Bootstrap</h3>
				<div class="progress">
					<div id="w3" class="progress-bar" style="width:99%;height:7px; background:	#FFA500;">
						<div id="e3" class="progress-value">99%</div>
					</div>
				</div>
				<h3 class="progress-title" id="q4">41615</h3>
				<div class="progress">
					<div id="w4" class="progress-bar" style="width:99%;height:7px; background:	#FFA500;">
						<div id="e4" class="progress-value">99%</div>
					</div>
				</div>
				<h3 class="progress-title" id="q5">416dsada15</h3>
				<div class="progress">
					<div id="w5" class="progress-bar" style="width:99%;height:7px; background:	#FFA500;">
						<div id="e5" class="progress-value">99%</div>
					</div>
				</div>
				<h3 class="progress-title" id="q6">416dsada15</h3>
				<div class="progress">
					<div id="w6" class="progress-bar" style="width:99%;height:7px; background:	#FFA500;">
						<div id="e6" class="progress-value">99%</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>