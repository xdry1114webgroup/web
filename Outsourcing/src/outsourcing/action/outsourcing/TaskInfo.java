package outsourcing.action.outsourcing;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import outsourcing.db.redis.OutsourcingDB;
import outsourcing.model.Outsourcing;

public class TaskInfo extends HttpServlet {

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
		 *    /Outsourcing/TaskInfo
		 */
		String taskID = request.getParameter("taskID");
		OutsourcingDB osDB = new OutsourcingDB();
		Outsourcing os = osDB.getOutsourcingById(taskID);
		Date date = os.getReleaseTime();
		int year = date.getYear();
		int month = date.getMonth();
		int day = date.getDay();
		String showdate = year+"Äê"+month+"ÔÂ"+day+"ÈÕ";
		request.setAttribute("showdate", showdate);
		request.setAttribute("os", os);
		request.getRequestDispatcher("/taskdetail.jsp").forward(request, response);
		
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
