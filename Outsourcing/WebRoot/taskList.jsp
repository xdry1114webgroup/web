<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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

<title>需求列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/tasklist.css">


</head>

<body>
	<%@include file="/WEB-INF/pages/Topbar.jsp"%>
<form action="/Outsourcing/TaskList" method="post" style="display:inline;">
	<input type="hidden" name="type" value="全部">
	<input type="hidden" name="taskPlace" value="全部">
	<input type="hidden" name="taskTime" value="全部">
	<input type="hidden" name="taskPay" value="全部">
</form>
	<div class="container">
		<!-- 顶部logo + 搜索 +发布需求 ------------------------------------------------------>
		<%@include file="/WEB-INF/pages/search_component.jsp"%>
		<div class="row">
			<div class="span8">
				<div class="row">
					<div class="span8" style="margin-bottom: 20px;">
						<div style="height:50px;">
							<div class="grocery-title">所有需求</div>
							<!-- 任务类型------------------------------ -->
							<ul class="task-row">
								<li class="task-cell">任务类型：</li>
								<li class="grocery-li"><a
									class="grocery-btn grocery-btn-active"
									href="/Outsourcing/TaskList?type=全部">全部</a> <a class="grocery-btn"
									href="/Outsourcing/TaskList?type=排队">排队</a> <a class="grocery-btn"
									href="/Outsourcing/TaskList?type=代办">代办</a> <a class="grocery-btn"
									href="/Outsourcing/TaskList?type=内务">内务</a> <a class="grocery-btn"
									href="/Outsourcing/TaskList?type=陪同">陪同</a> <a class="grocery-btn"
									href="/Outsourcing/TaskList?type=学习">学习</a> <a class="grocery-btn"
									href="/Outsourcing/TaskList?type=网络">网络</a> <a class="grocery-btn"
									href="/Outsourcing/TaskList?type=创意">创意</a></li>
							</ul>
							<!-- 任务位置 ---------------------------------->
							<ul class="task-row">
								<li class="task-cell">任务位置：</li>
								<li class="grocery-li"><a
									class="grocery-btn grocery-btn-active"
									href="/Outsourcing/TaskList?taskPlace=全部">全部</a> <a class="grocery-btn"
									href="/Outsourcing/TaskList?taskPlace=线上">线上</a> <a class="grocery-btn"
									href="/Outsourcing/TaskList?taskPlace=校内">校内</a> <a class="grocery-btn"
									href="/Outsourcing/TaskList?taskPlace=校外">校外</a></li>
							</ul>
							<!-- 任务时间------------------------ -->
							<ul class="task-row">
								<li class="task-cell">任务时间：</li>
								<li class="grocery-li"><a
									class="grocery-btn grocery-btn-active"
									href="/Outsourcing/TaskList?taskTime=全部">全部</a> <a class="grocery-btn"
									href="/Outsourcing/TaskList?taskTime=今日发布">今日发布</a> <a class="grocery-btn"
									href="/Outsourcing/TaskList?taskTime=昨日发布">昨日发布</a> <a class="grocery-btn"
									href="/Outsourcing/TaskList?taskTime=三天内发布">三天内发布</a></li>
							</ul>
							<!-- 任务赏金------------------------ -->
							<ul class="task-row">
								<li class="task-cell">任务赏金：</li>
								<li class="grocery-li"><a
									class="grocery-btn grocery-btn-active"
									href="/Outsourcing/TaskList?taskPay=全部">全部</a> <a class="grocery-btn"
									href="/Outsourcing/TaskList?taskPay=100元以下">100元以下</a> <a class="grocery-btn"
									href="/Outsourcing/TaskList?taskPay=100-500元">100-500元</a> <a
									class="grocery-btn" href="/Outsourcing/TaskList?taskPay=500-1000元">500-1000元</a>
									<a class="grocery-btn" href="/Outsourcing/TaskList?taskPay=1000元以上">1000元以上</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<!-- 任务一览表---------------------------------------- -->
				<div class="row">
					<div class="span8" style="margin-bottom: 40px;">
						<table class="table-hover adapted-table">
							<thead class="adapted-table-thread">
								<tr>
									<th>赏金</th>
									<th>任务</th>
									<th>发布时间</th>
									<th>状态</th>
								</tr>
							</thead>
							<tbody>
							
							<!---------------------------LOOP START------------------------------>
							<c:forEach items="${oslist}" var="entry">
								<tr class="adapted-table-tr adapted-table-td">
									<td class="task-pay adapted-table-td">${entry.taskPay}</td>
									<td class="task-title adapted-table-td"><a href="/Outsourcing/TaskInfo?taskID=${entry.taskID}">${entry.taskTitle}</a>
									</td>
									<td class="task-time adapted-table-td">test${entry.releaseTime}</td>
									<td class="task-state adapted-table-td">进行中
									</td>
								</tr>
							</c:forEach>
							<!----------------------LOOP END------------------------------->	
							<!-- 
								<tr class="adapted-table-tr">
									<td class="task-pay adapted-table-td">￥10</td>
									<td class="task-title adapted-table-td"><a href="#">求一当地临时导游</a>
									</td>
									<td class="task-time adapted-table-td">01/04/2012</td>
									<td class="task-state adapted-table-td">进行中</td>
								</tr>
								<tr class="adapted-table-tr">
									<td class="task-pay adapted-table-td">￥25</td>
									<td class="task-title adapted-table-td"><a href="#">下午综合楼取快递</a>
									</td>
									<td class="task-time adapted-table-td">02/04/2012</td>
									<td class="task-state adapted-table-td">进行中</td>
								</tr>
								<tr class="adapted-table-tr">
									<td class="task-pay adapted-table-td">￥13</td>
									<td class="task-title adapted-table-td"><a href="#">帮忙刷交互</a>
									</td>
									<td class="task-time adapted-table-td">03/04/2012</td>
									<td class="task-state adapted-table-td">已完成</td>
								</tr>
								<tr class="adapted-table-tr">
									<td class="task-pay adapted-table-td">￥40</td>
									<td class="task-title adapted-table-td"><a href="#">做一个购物网站</a>
									</td>
									<td class="task-time adapted-table-td">04/04/2012</td>
									<td class="task-state adapted-table-td">已完成</td>
								</tr>
								 -->
							</tbody>
						</table>
						<!-- 分页浏览------------------------------------------ -->
						<div class="pagination pagination-right">
							<ul>
								<li><a href="#">上一页</a></li>
								<li><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#">下一页</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="span4">
				<img alt="140x140" src="img/map.png" />
			</div>
		</div>
	</div>
	<!-- 底部模块---------------------------------------------------------- -->
	<%@include file="/WEB-INF/pages/footer.jsp"%>
</body>
</html>
