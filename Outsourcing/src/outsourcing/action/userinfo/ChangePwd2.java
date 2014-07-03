package outsourcing.action.userinfo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import outsourcing.db.redis.AccountDB;
import outsourcing.exceptions.InvalidUserNameException;
import outsourcing.model.Account;
import outsourcing.service.userinfo.AccountService;

public class ChangePwd2 extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 存在的问题：正确、错误之后的跳转页面
		 * get方式提交密码不安全
		 * http://localhost/Outsourcing/changepwd2?email=abc123&identifier_code=abc123&timestamp=1404112711979
		 */
		String path = "myjsp.jsp";
		String password = (String) request.getParameter("email");
		String newpassword = (String) request.getParameter("identifier_code");
		String timestamp = (String) request.getParameter("timestamp");
		String msg = "";
		
		System.out.println(password);
		System.out.println(newpassword);
		
		if(password==null || "".equals(password) || newpassword==null || "".equals(newpassword)){
			msg = "输入的密码不能为空";
		}else if(!password.equals(newpassword)){
			msg = "两次输入的密码不一致";
		}else if(!password.matches(AccountService.pwdregex)){
			msg = "密码输入的格式不正确,只能为4-16位数组、字符、标点组合";
		}
		if(!"".equals(msg)){
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(path).forward(request, response);
		}else{
			AccountDB accountDB = new AccountDB(); 
			String uid = accountDB.getUidByTempLink(timestamp);		//通过时间戳取得UID
			Account account = new Account(uid);
			account.setLoginPassword(password);
			try {
				accountDB.Update(account);		//更新UID所对应的用户的密码
				request.setAttribute("msg", "密码修改成功");
				request.getRequestDispatcher(path).forward(request, response);
			} catch (InvalidUserNameException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
