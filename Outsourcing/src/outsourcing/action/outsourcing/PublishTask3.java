package outsourcing.action.outsourcing;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import outsourcing.db.redis.OutsourcingDB;
import outsourcing.utils.UploadFile;

public class PublishTask3 extends HttpServlet {

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
		 * 		处理publishTask_3.jsp的请求 -->publishTask_4.jsp
		 * 		/Outsourcing/PublishTask3
		 */
		String path = "/publishTask_4.jsp";
		double taskPay = Double.parseDouble(request.getParameter("taskPay"));
		String taskPlace = request.getParameter("taskPlace");
		
		request.getSession().setAttribute("taskPay", taskPay);
		request.getSession().setAttribute("taskPlace", taskPlace);
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