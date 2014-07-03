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

<link rel="stylesheet" type="text/css" href="css/publishtask4.css">
<script>
	function Next() {
		window.location.href = "/Outsourcing/PublishTask4";
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
						<p style="text-align: center;">选择类目</p>
					</li>
					<li><span class="stepno"> <i class="icon-white icon-ok"
							style="margin-right:2px;"></i> </span>
					<p style="text-align: center;">描述您的需求</p>
					</li>
					<li><span class="stepno"> <i class="icon-white icon-ok"
							style="margin-right:2px;"></i> </span>
					<p style="text-align: center;">设置赏金</p>
					</li>
					<li><span class="stepno">4</span>
					<p style="text-align: center;">确认需求</p>
					</li>
				</ul>

			</div>
		</div>
		<!-- 确认需求 -->
		<div class="row">
			<div class="span12" style="margin-left:60px;">
				<h3>确认您的需求订单：</h3>
				<div
					style="margin: 10px 60px;padding:20px;background-color:#eee;width:660px;border: 1px solid #ccc;">
					<h3 align="center">需求订单</h3>
					<div class="item">
						需求订单编号: <font style="color:red;font-weight:bold;">#${sessionScope.taskID }</font>
					</div>
					<div class="item">需求类别：${sessionScope.type }</div>
					<div class="item">简要描述：${sessionScope.taskTitle }</div>
					<div class="item">具体描述：${sessionScope.taskInfo }</div>
					<div style="line-height:40px;"></div>
					<div class="item">悬赏金额：${sessionScope.taskPay }元</div>
					<div class="item">任务位置：${sessionScope.taskPlace }</div>
					<div class="item">联系方式：QQ:${sessionScope.qqNumber }&nbsp;&nbsp;电话:${sessionScope.phoneNumber }</div>
					<div class="item">
						附件：${sessionScope.filename }[<a href="${sessionScope.attachURL }">下载</a>]
					</div>
					<div style="color: #888;margin-top: 50px;margin-left: 60px;">
						<div class="footer-of-bid">
							<i class="icon-time"></i>${sessionScope.releaseTime }
						</div>
						<div class="footer-of-bid">
							参与编号：<a href="#">${sessionScope.taskID }</a>
						</div>
						<div>
							附件数量<a href="#">${sessionScope.fileNumber }</a>
						</div>
					</div>
				</div>
				<div style="margin-bottom:40px;margin-top:30px;margin-right:50px;"
					align="center">
					<button class="btn btn-success" onclick="Next()" style="padding: 5px 40px;">确
						认></button>
					<a href="publishTask_3.jsp" style="margin: auto 20px;">上一步</a>
				</div>
			</div>
		</div>
	</div>
	<!-- 底部模块---------------------------------------------------------- -->
	<%@include file="/WEB-INF/pages/footer.jsp"%>
</body>
</html>
