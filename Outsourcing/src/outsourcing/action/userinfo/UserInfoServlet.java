package outsourcing.action.userinfo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import outsourcing.db.redis.AccountDB;
import outsourcing.exceptions.InvalidUserNameException;
import outsourcing.model.Account;

public class UserInfoServlet extends HttpServlet {

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
		 * 存在的问题是：
		 *  phonenumber更新失败
		 *  显示页面跳转到编辑页面传递值
		 *  由birthday自动计算年龄age
		 */
		String userInfoPath = "userinfo.jsp";
		String choose = request.getParameter("choose").trim();
		if("list".equals(choose)){
			userInfoPath = "userinfo.jsp";
		}
		if("update".equals(choose)){
			userInfoPath = "updateuserinfo.jsp";
		}
		
		String userName = (String) request.getSession().getAttribute("user");
		AccountDB accountDB = new AccountDB();
		Account account = new Account();
		try {
			account = accountDB.getAccount(userName);
		} catch (InvalidUserNameException e) {
			e.printStackTrace();
		}
		request.setAttribute("email", account.getEmail());
		request.setAttribute("userimage", account.getUserImage());
		request.setAttribute("realname", account.getRealName());
		String year = "";
		String month = "";
		String day = "";
		if(account.getBirthday() != null){
			year = String.valueOf(account.getBirthday().getYear());
			month = String.valueOf(account.getBirthday().getMonth());
			day = String.valueOf(account.getBirthday().getDay());
		}
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("day", day);
		request.setAttribute("age", account.getAge());
		request.setAttribute("gender", account.getGender());
		request.setAttribute("academy", account.getAcademy());
		request.setAttribute("grade", account.getGrade());
		request.setAttribute("phonenumber", account.getPhoneNumber());
		request.setAttribute("address", account.getAddress());
//		request.setAttribute("account", account);
		request.getRequestDispatcher(userInfoPath).forward(request, response);
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
