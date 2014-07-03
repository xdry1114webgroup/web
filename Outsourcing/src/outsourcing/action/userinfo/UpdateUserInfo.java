package outsourcing.action.userinfo;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import outsourcing.db.redis.AccountDB;
import outsourcing.exceptions.InvalidUserNameException;
import outsourcing.model.Account;

public class UpdateUserInfo extends HttpServlet {
	
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
	
		String path = "/userinfo?choose=list";
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		Date birthday = new Date(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));		//生日
		String userName = (String) request.getSession().getAttribute("user");
		String realName = request.getParameter("realname");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		String phoneNumber = request.getParameter("phonenumber");
		String academy = request.getParameter("academy");
		String grade = request.getParameter("grade");
		String address = request.getParameter("address");
		
		AccountDB accountDB = new AccountDB();
		String userID = accountDB.getUidByUserName(userName);
		Account account = new Account(userID, 
				birthday, 
				realName, 
				age, 
				gender, 
				address, 
				phoneNumber, 
				academy, 
				grade);
		try {
			accountDB.Update(account);
		} catch (InvalidUserNameException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(path).forward(request, response);	//返回用户信息界面
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
