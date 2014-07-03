<%@ page language="java" import="java.util.*"
	import="outsourcing.db.redis.*" import="outsourcing.model.*"
	pageEncoding="UTF-8"%>
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

<link rel="stylesheet" type="text/css" href="css/publishtask1.css">
<script type="text/javascript">
	var id = "1";
	function chooseGrocery(type) {
		switch (type) {
		case '排队服务':
			id = document.getElementById("1");
			break;
		case '代办服务':
			id = document.getElementById("2");
			break;
		case '内务服务':
			id = document.getElementById("3");
			break;
		case '陪同服务':
			id = document.getElementById("4");
			break;
		case '学习服务':
			id = document.getElementById("5");
			break;
		case '网络服务':
			id = document.getElementById("6");
			break;
		case '创意服务':
			id = document.getElementById("7");
			break;
		}
		id.setAttribute('class', 'active');
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
					<li><span class="stepno">1</span>
					<p style="text-align: center;">选择类目</p>
					</li>
					<li style="background-image:url(img/taskmode-step-new-gray.png);">
						<span class="stepno">2</span>
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
		<div class="row" style="margin-top: 15px;margin-bottom: 20px;">
			<!-- 左边选择分类----------------------------------------------- -->
			<div class="span3">
				<ul class="nav nav-list">
					<li class="nav-header">&nbsp;请选择任务分类</li>
					<li id="1"><a href="publishTask_1.jsp?type=排队服务">&nbsp;排队服务</a>
					</li>
					<li id="2"><a href="publishTask_1.jsp?type=代办服务">&nbsp;代办服务</a>
					</li>
					<li id="3"><a href="publishTask_1.jsp?type=内务服务">&nbsp;内务服务</a>
					</li>
					<li id="4"><a href="publishTask_1.jsp?type=陪同服务">&nbsp;陪同服务</a>
					</li>
					<li id="5"><a href="publishTask_1.jsp?type=学习服务">&nbsp;学习服务</a>
					</li>
					<li id="6"><a href="publishTask_1.jsp?type=网络服务">&nbsp;网络服务</a>
					</li>
					<li id="7"><a href="publishTask_1.jsp?type=创意服务">&nbsp;创意服务</a>
					</li>
					<li class="divider"></li>
					<li>
						<div class="input-prepend">
							<span class="add-on"><i class="icon-search"></i>
							</span> <input class="span2" style="padding: 14px;width: 160px;"
								placeholder="请输入关键字" id="prependedInput" type="text">
						</div></li>
				</ul>
			</div>
			<!-- 右边显示分类细节-------------------------------- -->
			<div class="span9">
				<div class="grocery-details">
					<%
						//request.setCharacterEncoding("UTF-8");
						String s = request.getParameter("type");
						String type = "排队服务";
						if (s != null)
							type = new String(s.getBytes("iso-8859-1"), "UTF-8");

						request.setAttribute("type", type);
						System.out.println(type);
						OutsourcingDB Obj = new OutsourcingDB();
						Grocery[] Objpool = Obj.getGroceries(type);
						for (int i = 0; i < Objpool.length; i++) {
							String[] aim = Objpool[i].getItems();
					%>
					<div class="grocery-head">
						<%
							out.print(Objpool[i].getGroceryType());
						%>
					</div>
					<ul class="clearfix">
						<%
							for (int j = 0; j < aim.length; j++) {
						%>
						<li><a href="/Outsourcing/PublishTask1?type=<%=type+">"+ Objpool[i].getGroceryType() +">"+ aim[j] %>">
								<%
									out.print(aim[j]);
								%>
						</a>
						</li>
						<%
							}
						%>
					</ul>
					<%
						}
					%>
					<!--div class="span9">
			<div class="grocery-details">
				<div class="grocery-head">排队买票</div>
				<ul class="clearfix">
					<li><a href="#">代买火车票</a></li>
					<li><a href="#">代买飞机票</a></li>
					<li><a href="#">代买汽车票</a></li>
				</ul>
				
				<div class="grocery-head">排队缴费</div>
				<ul class="clearfix">
					<li><a href="#">水费/电费</a></li>
					<li><a href="#">点卡充值</a></li>
					<li><a href="#">Q币充值</a></li>
					<li><a href="#">校园网卡充值</a></li>
				</ul>
				
				<div class="grocery-head">医院挂号</div>
				<ul class="clearfix">
					<li><a href="#">急诊</a></li>
					<li><a href="#">普通</a></li>
				</ul>
			</div>			
		</div-->
				</div>
			</div>
		</div>
	</div>
	<!-- 底部模块---------------------------------------------------------- -->
	<%@include file="/WEB-INF/pages/footer.jsp"%>
	<script>
		chooseGrocery('${ type }');
	</script>
</body>
</html>
