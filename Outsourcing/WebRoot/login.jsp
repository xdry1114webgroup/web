<%@ page language="java" import="java.util.*,outsourcing.utils.*,outsourcing.model.*,outsourcing.db.redis.*"
 pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户登录界面</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<link href="css/login.css" rel="stylesheet" type="text/css" media="all" />
	<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
	<script type="text/javascript">
		function showInfo(info){
			if(info!=null && ""!=info){		
				document.getElementById("information").innerText = info;
				$("#myModal").modal();
			}
		}
				
		function login_btn_click(){
			var username = document.getElementById("username");
			var password = document.getElementById("password");
			var form = document.getElementById("loginForm");
			if(username.value==null || username.value==""){
				showInfo("账号不能为空！");
				return;
			}
			if(password.value==null || password.value==""){
				showInfo("密码不能为空！");
				return;
			}
			form.submit();
		}
	</script>
  </head>
	  
  <body style="	margin-top:60px;" onload="showInfo('${ info }')">
  	<%
  		String url = request.getParameter("url");  	   
  	  	if(url!=null && !"".equals(url))
  			request.getSession().setAttribute("url", url);
  		if(session.getAttribute("info")==null){//不是因为登陆错误而跳回本页面的 
	  		//读取cookie
			Map<String, Cookie> cookies = CookieUtils.ReadCookieMap(request);
			Cookie cookie_uid = cookies.get("uid");
			Cookie cookie_pwd = cookies.get("password");
			if(cookie_uid!=null && cookie_pwd!=null){
				String uid = cookie_uid.getValue();
				String pwd = cookie_pwd.getValue();
				if(uid!=null && !"".equals(uid) &&
				   pwd!=null && !"".equals(pwd)){
					AccountDB db = new AccountDB();
					try{
						Account acc = db.getAccountByUid(uid);
						String md5 = MD5Util.MD5Encode(acc.getLoginPassword());
						if(md5.equals(pwd)){//通过检验
							session.setAttribute("user", acc.getUserName());
							response.sendRedirect("index.jsp");
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
				}//end of if  判断cookie是否有效
			}//end of if 判断是否存在cookie
		}//end of if 错误跳转判断 
  	 %>
	<table id="login" style="height:400px; " align="center">
		<tr>
			<td><img alt="login_back" src="img/login_back.png"></td>
			<td style="background-color:white;">
					<form method="post" id="loginForm" action="/Outsourcing/login">						
							<h2 id="login-title">用户登录</h2>
							<p>
								<label for="email">账号：</label> <input type="text" placeholder="用户名/电子邮箱/手机号" name="username" tabindex="1" id="username"/>
							</p>
							<p>
								<label for="password">密码：</label> <input type="password"  placeholder="请输入密码"
									name="password" tabindex="2" id="password" class="input-text" />
							</p>
							<div id="main">
								<div id="left">
									<font size="2"><input type="checkbox" name="rememberMe" style="float: left;"
										id="rememberMe" checked="true" /><label for="rememberMe" style="font-size: small;">下次自动登录</label>
									</font>
								</div>
								<div id="right">
									<font size="2"><a tabindex="5" href='getPassword.html'>忘记密码?</a> </font>
								</div>
							</div>
							
							<p align="center">
								<input type="button" id="login" tabindex="4" class="btn btn-info"
									value="登录" style="padding: 5px 40px;" onClick="login_btn_click()"/>
							</p>						
							<p align="center">
								<font size=2 color="gray">没有开通账户？<a
									href="register.jsp">立即注册</a>
								</font>
							</p>												
					</form>
			</td>
		</tr>
	</table>	
	<!-- 提示框 -->
	<div id="myModal" class="modal hide fade">
		<div class="modal-header">
			<h3>提示</h3>
		</div>
		<div class="modal-body">
			<div id="information">确定要关闭？</div>		
		</div>
		<div class="modal-footer">
			<button class="btn btn-info" data-dismiss="modal">关闭</button>
		</div>
	</div>
  </body>  	
</html>  
