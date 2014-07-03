package outsourcing.action.outsourcing;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import outsourcing.db.redis.OutsourcingDB;

public class PublishTask2 extends HttpServlet {

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
		 * 		处理publishTask_2.jsp的请求 -->publishTask_3.jsp
		 * 		/Outsourcing/PublishTask2
		 */
		String path = "/publishTask_3.jsp";
		String taskTitle = request.getParameter("taskTitle");	//标题
		String taskInfo = request.getParameter("taskInfo");		//内容
		String qqNumber = request.getParameter("qqNumber");		//QQ号
		String phoneNumber = request.getParameter("phoneNumber");	//电话号码
		Date releaseTime = new Date();
		OutsourcingDB outsourcingDB = new OutsourcingDB();
		String taskID = outsourcingDB.generateID();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		
		request.getSession().setAttribute("showdate", format.format(releaseTime));
		
		request.getSession().setAttribute("taskTitle", taskTitle);
		request.getSession().setAttribute("taskInfo", taskInfo);
		request.getSession().setAttribute("qqNumber", qqNumber);
		request.getSession().setAttribute("phoneNumber", phoneNumber);
		request.getSession().setAttribute("releaseTime",releaseTime);
		request.getSession().setAttribute("taskID", taskID);
		request.getRequestDispatcher(path).forward(request, response);
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
