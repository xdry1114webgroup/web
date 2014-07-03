<%@ page language="java" import="java.util.*,outsourcing.utils.*,outsourcing.db.redis.*,outsourcing.utils.*,outsourcing.model.*" 
pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>Topbar</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
	<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.js"></script>  
  	<script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script>
  </head>  
  <body>    
  <%  	
  	String user = (String) session.getAttribute("user");
  	if(user==null){
  		Map<String, Cookie> cookies = CookieUtils.ReadCookieMap(request);
		Cookie cookie_uid = cookies.get("uid");
		Cookie cookie_pwd = cookies.get("password");
		if(cookie_uid!=null && cookie_pwd!=null){
			String uid = cookie_uid.getValue();
			String pwd = cookie_pwd.getValue();
			//获取到cookie
			
			//"null".equals(uid)
			if(uid!=null && !"".equals(uid) && !"null".equals(uid) &&
			   pwd!=null && !"".equals(pwd) && !"null".equals(pwd)){
				AccountDB db = new AccountDB();
				try{
					Account acc = db.getAccountByUid(uid);
					String md5 = MD5Util.MD5Encode(acc.getLoginPassword());
					if(md5.equals(pwd)){//通过检验
						session.setAttribute("user", acc.getUserName());
					}else{
						throw new Exception("检查到无效的cookie");
					}
				}catch(Exception e){//删除错误 cookie
					e.printStackTrace();
					%>
						<script type="text/javascript">
							document.cookie = "";
						</script>
					<%
				}
			}
		}
  	}
  	
  	if(session.getAttribute("user")!=null){
	%>
	
	<div class="row-fluid" style="float: left;position: fixed;z-index: 100;">
			<div class="breadcrumb" style="height: 30px;border-bottom-color: #ccc;
				border-bottom-width: 1px;border-bottom-style: solid;">
			<ul class="breadcrumb" style="float: left;">
				<li>
					<i class="icon-user"></i>
				</li>
				<li class="dropdown">
					<a data-toggle="dropdown" class="dropdown-toggle" href="#">${sessionScope.user }</a> 
					<ul class="dropdown-menu">
						<li>
							<a href="#">个人中心</a>
						</li>
						<li>
							<a href="#">更改密码</a>
						</li>
						<li>
							<a href="updateuserinfo.jsp">完善资料</a>
						</li>
						<li class="divider">
						</li>
						<li>
							<a href="javascript:logout();">退出</a>
						</li>
					</ul>
					<span class="divider">|</span>
				</li>
				<li>
					<a href="#">消息(1)</a> <span class="divider">|</span>
				</li>		
				<li>
					<a href="#">积分:20</a>
				</li>			
			</ul>
			<ul class="breadcrumb" style="float: right;">
				<li>
					<a href="#">收藏夹</a> <span class="divider">|</span>					
				</li>
				<li>
					<a href="#">联系客服</a> <span class="divider">|</span>					
				</li>
				<li>
					<a href="#">网站导航</a>
				</li>
			</ul>
			</div>
		</div>
	<%}else{%>
		
	<div class="row-fluid" style="float: left;position: fixed;z-index: 100;">
			<div class="breadcrumb" style="height: 30px;border-bottom-color: #ccc;
				border-bottom-width: 1px;border-bottom-style: solid;">
			<ul class="breadcrumb" style="float: left;">
				<li>
					<a href="javascript:login()">登录</a> <span class="divider">|</span>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/register.jsp">立即注册</a> <span class="divider">|</span>
				</li>				
			</ul>
			<ul class="breadcrumb" style="float: right;">
				<li>
					<a href="#">收藏夹</a> <span class="divider">|</span>					
				</li>
				<li>
					<a href="#">联系客服</a> <span class="divider">|</span>					
				</li>
				<li>
					<a href="#">网站导航</a>			
				</li>
			</ul>
			</div>
		</div>	
	
	<%}%>
	<div id="myModal" class="modal hide fade" style="">
		<div class="modal-header">
			<h3>提示</h3>
		</div>
		<div class="modal-body">
			<p>确定要关闭？</p>			
		</div>
		<div class="modal-footer">
			<button class="btn btn-info" data-dismiss="modal">关闭</button>
		</div>
	</div>
  </body>
</html>
