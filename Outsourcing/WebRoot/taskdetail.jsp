<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>任务详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/taskdetail.css">

  </head>
  
  <body>
    <%@include file="/WEB-INF/pages/Topbar.jsp" %>
    <div class="container">
    	<!-- 顶部logo + 搜索 +发布需求 ------------------------------------------------------>
   		<%@include file="/WEB-INF/pages/search_component.jsp" %>
   		<div class="row">
   		<!-- 左边-------------------------------------------------- -->
		<div class="span9">
			<!-- 任务详情--------------------------------------- -->
			<div class="row">
				<div class="span9 task-detail">
					<div class="show-task-title">${os.taskTitle }</div>
					<div class="clearfix">
						<div class="avatar"></div>
						<div class="detail-frame">
  						<div class="detail-row">发布者：<a href="#" style="margin-right: 15px;">${os.releaseUser }</a>
  						任务地址：<font class="task-position">${os.taskPlace }</font>
						</div>
  							<div class="task-time">发布时间：<font>${showdate }</font></div>
							<div style="float: right;">任务状态：<a class="task-state">此任务已经过期</a></div>
						</div>
					</div>
				</div>
			</div>
			<!-- 任务描述----------------------------------------------------- -->
			<div class="row" style="margin-bottom: 30px;">
				<div class="span9" style="margin-left: 50px;">
					<div class="tabbable" id="tabs-270794" style="border-bottom: 1px solid #ddd;"> 
					<!-- Only required for left/right tabs -->
                  <ul class="nav nav-tabs">
                    <li class="active" style="margin-left: 15px;">
                    <a href="#panel-191498" data-toggle="tab" contenteditable="true">需求详情</a></li>
                    <li class=""><a href="#panel-408664" data-toggle="tab" contenteditable="true">任务地图</a></li>
                  </ul>
                  <div class="tab-content tab-content-adapt" >
                    <div class="tab-pane active" id="panel-191498" contenteditable="true">
                      <p>${os.taskInfo }</p>
                    </div>
                    <div class="tab-pane" id="panel-408664" contenteditable="true">
                      <p>任务地图</p>
                    </div>
                  </div>
                </div>
				</div>
			</div>
			<!-- 竞价 投标------------------------------------------------ -->			
			<div class="row">
				<div class="span9" style="margin-left: 45px;">
				<h3>已经获得<a>0</a>个投标</h3>
				<div class="span8">					
					<form class="form-horizontal">
    				<fieldset>
      				<div id="legend" class="">
        				<legend class="">我要报价</legend>
     			 	</div>
    
    				<div class="control-group">

          				<!-- Prepended text---------------------------->
         	 			<div>
            				<div class="input-prepend">
              					<span class="add-on">￥</span>
              					<input class="span2" style="padding: 14px;width: 260px;" placeholder="请输入您的报价(仅雇主可见)" id="prependedInput" type="text">
            				</div>            				
          				</div>

        			</div>
        			
        			<div class="control-group">

          			<!-- Textarea ------------------------------->
          			<div >
            			<div class="textarea">
                  			<textarea class="textarea-fix"> </textarea>
            			</div>
          			</div>
        			</div>
        			<div class="control-group">

          				<!-- File Upload -->
        				<!-- Button 提交 -->
          				<div style="float: left;">
          				上传附件：
            			<input class="input-file" id="fileInput" type="file" style="font-family: microsoft yahei;color: #888;">
          				</div>
          				<div style="float: right;margin-right: 20px;">
            			<button class="btn btn-success">提 交</button>          				
          				</div>
        			</div>
        			
    				</fieldset>
  					</form>
				</div>
				</div>
			</div>
			<!-- 报价情况------------------------------------------------ -->	
			<div class="row">
				<div class="span9" style="margin-left:45px;border-top: 2px solid #06f;margin-top: 10px;">
				<div class="page-header" contenteditable="true">
                  <h3>报价情况(9)</h3>
                </div>
                
                <div class="row" style="margin-bottom:30px;padding-bottom:10px;border-bottom: 1px dashed #eee;">
                	<div class="span1"><img alt="" src="img/50user.jpg"></div>
                	<div class="span5" style="margin-top: -10px;line-height: 25px;"><a href="#" class="user-name">用户名</a>
                		<div>我的报价：**元</div>
                		<div style="margin: 13px 20px;font-size: small;">内容 200字</div>
                		<div style="color: #888;margin-top: 30px;">                			
                			<div class="footer-of-bid"><i class="icon-time"></i>2014年5月14日</div>
                			<div class="footer-of-bid">参与编号：<a href="#">#1231231</a></div>
                			<div><a href="#">评论</a></div>
                		</div>
                	</div>
                </div>
                <!-- 翻页------------------------------------- -->
                <div class="pagination pagination-centered">
                  <ul contenteditable="true">
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
		<!-- 右边 ------------------------------------------------>
		<div class="span3">
		</div>
		</div>
   	</div>
   	<!-- 底部模块---------------------------------------------------------- -->
	<%@include file="/WEB-INF/pages/footer.jsp" %>
  </body>
</html>
