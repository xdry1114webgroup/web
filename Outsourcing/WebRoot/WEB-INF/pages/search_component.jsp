<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>Search component</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function changeSearchType(type){
			var obj = document.getElementById('search-type');
			obj.innerHTML = type + '<strong class="caret"></strong>';
		}
		function onClick(){
		window.location.href = "publishTask_1.jsp";
		}
	</script>
  </head>
  
  <body>
    <div class="row" style="margin-top: 50px;">
		<div class="span3">
			<img alt="140x140" src="img/logo.png">
		</div>
		<div class="span1" style="margin-top: 30px;">
			<div class="dropdown pull-left" style="margin-top: 12px;margin-left: 8px;">
					<a id="search-type" href="#" data-toggle="dropdown" class="dropdown-toggle">找服务<strong class="caret"></strong></a>
					<ul class="dropdown-menu" style="min-width: 80px;">
						<li>
							<a href="javascript:changeSearchType('找服务')">找服务</a>
						</li>
						<li>
							<a href="javascript:changeSearchType('找需求')">找需求</a>
						</li>
					</ul>
			</div>
		</div>
		<div class="span5"  style="margin-top: 30px;">			
			<form class="form-search" style="margin-bottom: 0px;">
				<input class="input-xlarge search-query" type="text">
				<button class="btn btn-primary" type="submit" style="margin-left: 10px;padding: 10px 20px;">搜 索</button>
				
			</form>
			<div style="font-size: smaller;color: gray;">热点搜索：
			<a href="#" style="color:gray;">刷交互</a><span style="padding: 0 5px;color: #ccc;">|</span>
			<a href="#" style="color:gray;">代取快递</a><span style="padding: 0 5px;color: #ccc;">|</span>
			<a href="#" style="color:gray;">代买火车票</a>
			</div>
		</div>
		<div class="span3"  style="margin-top: 30px;">
			<button class="btn btn-primary" type="submit" style="padding: 10px 20px; " onClick = "onClick()">发布需求</button>
		</div>
	</div>
  </body>
</html>
