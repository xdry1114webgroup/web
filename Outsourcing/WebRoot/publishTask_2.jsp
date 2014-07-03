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

<link rel="stylesheet" type="text/css" href="css/publishtask2.css">
<script>
	function Next() {
		window.location.href = "/Outsourcing/PublishTask2";
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
					<li><span class="stepno">2</span>
						<p style="text-align: center;">描述您的需求</p>
					</li>
					<li style="background-image:url(img/taskmode-step-new-gray.png);">
						<span class="stepno">3</span>
						<p style="text-align: center;">设置赏金</p>
					</li>
					<li style="background-image:url(img/taskmode-step-new-gray.png);">
						<span class="stepno">4</span>
						<p style="text-align: center;">确认需求</p>
					</li>
				</ul>

			</div>
		</div>
		<div class="row">
			<div class="span10" style="margin-left: 60px;margin-top: 50px;">
				<div style="line-height:40px">
					您选择了：${sessionScope.type }<a href="publishTask_1.jsp">(修改)</a>
				</div>
				<p></p>
				<div>
					上传附件：
					<form method="post" action="/Outsourcing/UploadOutsourcingFile"
						ENCTYPE="multipart/form-data">
						<input class="input-file" id="fileInput" type="file"
							name="uploadfile"
							style="font-family: microsoft yahei;color: #888;"> <input
							type="submit" value="上传" name="submit"><br>
					文件名：${sessionScope.filename }
					</form>
				</div>
				<form action="/Outsourcing/PublishTask2" method="post">
					<div style="line-height:40px">请用一句话描述您的需求：</div>
					<p></p>
					<div class="input-prepend">
						<span class="add-on"><i class="icon-th-list"></i> </span> <input
							class="span2" name="taskTitle"
							style="padding: 14px;width: 570px;" placeholder="请输入标题"
							id="prependedInput" type="text">
					</div>
					<div style="line-height:40px">请描述您的具体需求：</div>
					<div>
						<div class="textarea">
							<textarea class="textarea-fix" name="taskInfo"> </textarea>
						</div>
					</div>
					<p></p>
					<div style="line-height:40px">为了确保您及时收到交易提醒，请填写您的联系方式：</div>
					<div class="input-prepend">
						<span class="add-on"><img src="img/addon_qq.png"> </span> <input
							class="span2" style="padding: 14px;width: 370px;"
							placeholder="QQ号码" id="prependedInput" name="qqNumber" type="text">
					</div>
					<div class="input-prepend" style="margin-bottom:40px;">
						<span class="add-on"><img src="img/addon_tel.png"
							style="width: 16;"> </span> <input class="span2"
							style="padding: 14px;width: 370px;" placeholder="手机号码" name="phoneNumber"
							id="prependedInput" type="text">
					</div>
					<div style="margin-bottom:40px">
						<button class="btn btn-success" style="padding: 5px 40px;"
							onClick="Next()">下一步></button>
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
