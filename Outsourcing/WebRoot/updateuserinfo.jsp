<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户信息完善</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/modi_info.css" rel="stylesheet" type="text/css"	media="all" />
	<link type="text/css" rel="stylesheet" href="css/common-validator.css">
	<style type="text/css">
	body{	
		background-image:url(img/repeat_back.png);
		background-repeat:repeat-x;
		background-attachment:fixed;
		background-position:left top;
	} 
	</style>
	</head>
<body>
	<div id="login_wrapper">
		<div id="login_main">
			<div class="login_form">
				<table>
				<tr>
					<div class="login_form_header">
							<h2 style="text-align: center;">用户信息完善</h2>
					</div>
				</tr>
				<tr>
					<td>
						<table class="form_style" >
							<tr>
								<td><img alt="login_back" src="img/123.jpg" class ="image_posi" style="margin-top: 34px;"></td>
							</tr>
							<tr>
								<td><input type="button" class="select_button" value="请提交头像，亲" class="submit_button underline"></td>
							</tr>
						</table>
					</td>
					<td>
						<form name="register_items" id="reg_form" method="post" class="right_infor">
							<table class="info_table_style" style="margin-left: 55px;">
								<tr>
									<td style="width:80px"><label class="input_val"><font color="red">*</font>真实姓名:</label></td>
									<td><input type="text" id="name" style="width: 150px;vertical-align: middle;"/>
								</tr>
								<tr>
									<td><label class="input_val"><font color="red">*</font>年龄:</label></td>
									<td><input class="input" id="age" style="width: 150px;vertical-align: middle;"/></td>
								</tr>
								<tr>
									<td><label class="input_val"><font color="red">*</font>性别:</label></td>
									<td><input type="radio" id="sex_radio_boy" name="sex" value="1" checked style="margin-left: 16px">男 <input type="radio" id="sex_radio_girl" name="sex" value="2" style="margin-left: 47px">女</td>
								</tr>
								<tr>
									<td><label class="input_val"><font color="red">*</font>生日:</label></td>
									<td><select id="province" name="province" class="size_select""></select>年<select id="province" name="province" class="size_select""></select>月<select id="province" name="province" class="size_select"></select>日</td>
								</tr>
								<tr>
									<td><label class="input_val"><font color="red">*</font>学院:</label></td>
									<td><select id="province" name="province" onchange="getCity()" style="width: 150px;"></select></td>
								</tr>
								<tr>
									<td><label class="input_val"><font color="red">*</font>年级:</label></td>
									<td><input class="input" name="profile" style="width: 150px;vertical-align: middle;"/></td>
								</tr>
								<tr>
									<td><label class="input_val"><font color="red">*</font>电话号码:</label></td>
									<td><input class="input" name="profile" style="width: 150px;vertical-align: middle;"/></td>
								</tr>
								<tr>
									<td><label class="input_val"><font color="red">*</font>住址:</label></td>
									<td><input class="input" name="product_name" style="width: 150px;vertical-align: middle;"/></td>
								</tr>
								<tr>
									<td><label class="input_val"><font color="red">*</font>验证码:</label></td>
									<td height="30px"><input class="input" name="check_code" id="check_code" style="width:61px;vertical-align:middle;"/> <img alt="验证码" id="check_code_img" border=0 src="imageServlet" style="vertical-align:middle;cursor:pointer;"></td>
								</tr>
							</table>
						</form>
					</td>
				</tr>
				</table>
				<div class="remeber" style="margin-bottom:15px;">
						
				</div>
				<div>
					<input type="button" value="确认修改" class="submit_button" style="text-align: center;margin-left: 267px;">
				</div>
			</div>
		</div>
	</div>
</body>
</html>
