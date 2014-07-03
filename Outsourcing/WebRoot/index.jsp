<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/mainpage.css">
	
  </head>
  
  <body>
    <%@include file="/WEB-INF/pages/Topbar.jsp" %>
   <div class="container">
   	<!-- 顶部logo + 搜索 +发布需求 ------------------------------------------------------>
   	<%@include file="/WEB-INF/pages/search_component.jsp" %>   
	<!-- 所有服务分类 +  轮播图片   + 论坛展示--------------------------------------------------->
	<div class="row" style="margin-top: 15px;">
		<!-- 左边功能条----------------------------------------------- -->		
		<div class="span3">
			<ul class="nav nav-list">
				<li class="nav-header">
					&nbsp;所有服务分类
				</li>
				<li class="active">
					<a href="#">&nbsp;首页</a>
				</li>
				<li>
					<a href="taskList.jsp?type=排队服务">&nbsp;排队服务</a>
				</li>
				<li>
					<a href="taskList.jsp?type=代办服务">&nbsp;代办服务</a>
				</li>
				<li>
					<a href="taskList.jsp?type=内务服务">&nbsp;内务服务</a>
				</li>
				<li>
					<a href="taskList.jsp?type=陪同服务">&nbsp;陪同服务</a>
				</li>
				<li>
					<a href="taskList.jsp?type=学习服务">&nbsp;学习服务</a>
				</li>
				<li>
					<a href="taskList.jsp?type=网络服务">&nbsp;网络服务</a>
				</li>
				<li>
					<a href="taskList.jsp?type=创意服务">&nbsp;创意服务</a>
				</li>				
				<li class="divider">
				</li>
				<li>
					<a href="#">&nbsp;帮助</a>
				</li>
			</ul>
		</div>
		<!-- 右边---------------------------------------------- -->
		<div class="span9">
		<div class="row">
			<!-- 轮播------------------------------------------------------- -->
			<div class="span4">
				<div class="carousel slide" id="carousel-581045">
					<ol class="carousel-indicators">
						<li class="active" data-slide-to="0" data-target="#carousel-581045">
						</li>
						<li data-slide-to="1" data-target="#carousel-581045">
						</li>
						<li data-slide-to="2" data-target="#carousel-581045">
						<br></li>
					</ol>
					<div class="carousel-inner">
						<div class="item active">
							<img alt="" src="img/xidian_2.png" />
							<div class="carousel-caption">
								<h4>
									棒球
								</h4>
								<p>
									棒球运动是一种以棒打球。
								</p>
							</div>
						</div>
						<div class="item">
							<img alt="" src="img/xidian_3.png" />
							<div class="carousel-caption">
								<h4>
									冲浪
								</h4>
								<p>
									冲浪是以海浪为动力，动。
								</p>
							</div>
						</div>
						<div class="item">
							<img alt="" src="img/xidian_4.png" />
							<div class="carousel-caption">
								<h4>
									自行车
								</h4>
								<p>
									以自行车为工赛项目。环法赛为锦标赛。
								</p>
							</div>
						</div>
					</div> <a data-slide="prev" href="#carousel-581045" class="left carousel-control">‹</a> <a data-slide="next" href="#carousel-581045" class="right carousel-control">›</a>
				</div>
			</div>
			<!-- 论坛模块------------------------------------------------------------- -->
			<div class="span5">
				<%@include file="/WEB-INF/pages/post_component.jsp"%>
			</div>
		</div>
		<!-- 交易流程------------------------------------- -->
		<div class="row">
			<div class="span9">
				<p>
					<img alt="" src="img/transaction_workflow.png">
				</p>
			</div>
		</div>
		</div>
	</div>
	<!-- 热门服务---------------------------------------------- -->
	<div class="row">
		<div class="span12 gray-frame" style="width: auto;">
			<div class="hot-service-left-head">
			<h4>热门服务<span style="margin-left:10px;"><font color="red">HOT</font></span></h4>
			</div>
			<div style="float: left;width: 700px;">
				<ul class="clearfix">
					<li class="hot-service"><a href="#"><i class="icon-map-marker"></i>取快递</a></li>
					<li class="hot-service"><a href="#"><i class="icon-map-marker"></i>发传单</a></li>
					<li class="hot-service"><a href="#"><i class="icon-map-marker"></i>刷交互</a></li>
					<li class="hot-service"><a href="#"><i class="icon-map-marker"></i>补作业</a></li>
					<li class="hot-service"><a href="#"><i class="icon-map-marker"></i>考研辅导</a></li>
					<li class="hot-service"><a href="#"><i class="icon-map-marker"></i>看外场</a></li>
					<li class="hot-service"><a href="#"><i class="icon-map-marker"></i>寄包裹</a></li>
					<li class="hot-service"><a href="#"><i class="icon-map-marker"></i>送外卖</a></li>
					<li class="hot-service"><a href="#"><i class="icon-map-marker"></i>英语翻译</a></li>
					<li class="hot-service"><a href="#"><i class="icon-map-marker"></i>睿思邀请码</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- 需求类别展示 -1------------------------------------------- -->
	<div class="row">
		<div class="span12">
			<ul class="thumbnails">
				<li class="span4">
					<div class="thumbnail">
						<img alt="300x200" src="img/people.jpg" />
						<div class="caption">
							<h3>
								排队服务
							</h3>
							<p>
								也称普林斯顿结构，是一种将程序指令存储器和数据存储器合并在一起的存储器结构。程序指令存储地址和数据存储地址指向同一个存储器的不同物理位置。
							</p>
							<p>
								<a class="btn btn-primary" href="#">浏览</a> <a class="btn" href="#">分享</a>
							</p>
						</div>
					</div>
				</li>
				<li class="span4">
					<div class="thumbnail">
						<img alt="300x200" src="img/city.jpg" />
						<div class="caption">
							<h3>
								代理服务
							</h3>
							<p>
								哈佛结构是一种将程序指令存储和数据存储分开的存储器结构，它的主要特点是将程序和数据存储在不同的存储空间中，进行独立编址。
							</p>
							<p>
								<a class="btn btn-primary" href="#">浏览</a> <a class="btn" href="#">分享</a>
							</p>
						</div>
					</div>
				</li>
				<li class="span4">
					<div class="thumbnail">
						<img alt="300x200" src="img/sports.jpg" />
						<div class="caption">
							<h3>
								学习服务
							</h3>
							<p>
								改进型的哈佛结构具有一条独立的地址总线和一条独立的数据总线，两条总线由程序存储器和数据存储器分时复用，使结构更紧凑。
							</p>
							<p>
								<a class="btn btn-primary" href="#">浏览</a> <a class="btn" href="#">分享</a>
							</p>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
	<!-- 需求展示 2 ----------------------------------------- -->
	<div class="row">
		<div class="span12">
			<ul class="thumbnails">
				<li class="span4">
					<div class="thumbnail">
						<img alt="300x200" src="img/city.jpg" />
						<div class="caption">
							<h3>
								陪同服务
							</h3>
							<p>
								也称普林斯顿结构，是一种将程序指令存储器和数据存储器合并在一起的存储器结构。程序指令存储地址和数据存储地址指向同一个存储器的不同物理位置。
							</p>
							<p>
								<a class="btn btn-primary" href="#">浏览</a> <a class="btn" href="#">分享</a>
							</p>
						</div>
					</div>
				</li>
				<li class="span4">
					<div class="thumbnail">
						<img alt="300x200" src="img/sports.jpg" />
						<div class="caption">
							<h3>
								创意服务
							</h3>
							<p>
								哈佛结构是一种将程序指令存储和数据存储分开的存储器结构，它的主要特点是将程序和数据存储在不同的存储空间中，进行独立编址。
							</p>
							<p>
								<a class="btn btn-primary" href="#">浏览</a> <a class="btn" href="#">分享</a>
							</p>
						</div>
					</div>
				</li>
				<li class="span4">
					<div class="thumbnail">
						<img alt="300x200" src="img/people.jpg" />
						<div class="caption">
							<h3>
								内务服务
							</h3>
							<p>
								改进型的哈佛结构具有一条独立的地址总线和一条独立的数据总线，两条总线由程序存储器和数据存储器分时复用，使结构更紧凑。
							</p>
							<p>
								<a class="btn btn-primary" href="#">浏览</a> <a class="btn" href="#">分享</a>
							</p>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>	
	</div>
	<!-- 底部模块---------------------------------------------------------- -->
	<%@include file="/WEB-INF/pages/footer.jsp" %>
  </body>
</html>
