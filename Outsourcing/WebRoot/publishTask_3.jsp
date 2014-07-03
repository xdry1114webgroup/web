<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>发布任务</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/publishtask3.css">
<script>
	function Next() {
		window.location.href = "/Outsourcing/PublishTask3";
	}
</script>

</head>

<body>
	<%@include file="/WEB-INF/pages/Topbar.jsp"%>
	<div class="container">
		<!-- 顶部logo + 搜索 +发布需求 ------------------------------------------------------>
		<%@include file="/WEB-INF/pages/search_component.jsp"%>
		<div class="row">
			<div class="trade-line">
				<div class="prgbar"></div>
				<div class="prgbar-inner"></div>
				<ul class="clearfix">
					<li><span class="stepno"> <i class="icon-white icon-ok"
							style="margin-right:2px;"></i> </span>
						<p style="text-align: center;">选择类目</p></li>
					<li><span class="stepno"> <i class="icon-white icon-ok"
							style="margin-right:2px;"></i> </span>
						<p style="text-align: center;">描述您的需求</p></li>
					<li><span class="stepno">3</span>
						<p style="text-align: center;">设置赏金</p></li>
					<li style="background-image:url(img/taskmode-step-new-gray.png);">
						<span class="stepno">4</span>
						<p style="text-align: center;">确认需求</p></li>
				</ul>

			</div>
		</div>
		<div class="row" style="margin-left: 60px;">
			<div class="span10" style="margin-left: 60px;margin-top: 50px;">
				<div style="line-height:40px">
					您的需求：${sessionScope.type }<a href="#">(修改)</a>
				</div>
				<p></p>
				<div style="line-height:40px">具体的需求描述：${sessionScope.taskTitle }</div>
				<div style="line-height:40px;font-size:small;">${sessionScope.taskInfo }</div>
				<div style="color: #888;margin-top: 60px;margin-left: 50px;">
					<div class="footer-of-bid">
						<i class="icon-time"></i>${sessionScope.showdate }
					</div>
					<div class="footer-of-bid">
						参与编号：<a href="#">#${sessionScope.taskID }</a>
					</div>
					<div>
						附件数量<a href="#">${sessionScope.fileNumber }</a>
					</div>
				</div>
			</div>
		</div>

		<div class="row" style="margin-left: 60px;">
			<div style="margin-left:50px;margin-top:20px;">
			<form action="/Outsourcing/PublishTask3" method="post">
				<h3>请设置赏金：</h3>
				<div class="input-prepend">
					<span class="add-on" style="color:gray;">悬赏</span> <span
						class="add-on" style="color: gray;font-weight: bold;">￥</span> <input
						class="span2" style="padding: 14px;width: 350px;"
						placeholder="请输入赏金" id="prependedInput" name="taskPay" type="text">
				</div>
				<h3>请选择任务位置：</h3>
				<div class="input-prepend">
					<span class="add-on" style="color: gray;font-weight: bold;">
						<i class="icon-map-marker"></i> </span> <input class="span2"
						style="padding: 14px;width: 390px;" placeholder="请输入任务位置" name="taskPlace"
						id="prependedInput" type="text">
				</div>
				<div style="margin-bottom:40px;margin-top:30px;">
					<button class="btn btn-success" style="padding: 5px 40px;"
						onclick="Next()">下一步></button>
					<a href="publishTask_1.jsp" style="margin: auto 20px;">上一步</a>
				</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 底部模块---------------------------------------------------------- -->
	<%@include file="/WEB-INF/pages/footer.jsp"%>
</body>
</html>
