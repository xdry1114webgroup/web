<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户注册</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		
	</script>
  </head>
<link href="css/registry.css" rel="stylesheet" type="text/css" media="all" />
</head>
<body>
	<table id="login" height="400px" align="center">
		<tr>
			<td><img alt="login_back" src="img/login_back.png"></td>
			<td style="background-color: white;">
					<form method="post" id="loginForm" action="/Outsourcing/register">						
							<h2 style="text-align:center;margin-bottom: 45px;">用户注册</h2>
							<p>
								<label for="email">电子邮箱:</label> <input type="text" id="email" name="email" class="width_value"/>
							</p>
							<p>
								<label for="username">用户账号:</label> <input type="text" id="username" name="username" class="width_value"/>
							</p>
							<p>
								<label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码:</label> <input type="password" id="password" name="password" class="width_value"/>
							</p>
							<p>
								<label for="password2">确认密码:</label> <input type="password" id="password" name="password2" class="width_value"/>
							</p>
							<p>
								<input type="submit" id="submit" value="注册"  class="btn_reg"/>
								 <input type="reset" id="reset" value="重置" class="btn_res"/>
							</p>																		
					</form>
			</td>
		</tr>
	</table>
</body>
</html>