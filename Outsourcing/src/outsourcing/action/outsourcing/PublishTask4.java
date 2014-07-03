package outsourcing.action.outsourcing;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import outsourcing.db.redis.OutsourcingDB;
import outsourcing.exceptions.TransactionCollisionException;
import outsourcing.model.Outsourcing;

public class PublishTask4 extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 处理publishTask_4.jsp的请求 -->main.jsp 跳转到主界面 /PublishTask3
		 */
		String path = "/index.jsp";
		String type = (String) request.getSession().getAttribute("type"); // 排队服务>排队缴费>校园卡充值
		String taskTitle = (String) request.getSession().getAttribute("taskTitle"); // 标题
		String taskInfo = (String) request.getSession().getAttribute("taskInfo"); // 内容
		String attachURL = (String) request.getSession().getAttribute("attachURL"); // files/outsourcing/xxx.doc
		String taskID = (String) request.getSession().getAttribute("taskID");
		double taskPay = (Double) request.getSession().getAttribute("taskPay");
		String taskPlace = (String) request.getSession().getAttribute("taskPlace");
		String releaseUser = (String) request.getSession().getAttribute("user");
		Date releaseTime = (Date) request.getSession().getAttribute("releaseTime");
		String[] types = type.split(">");

		Outsourcing os = new Outsourcing(taskID, releaseUser,
				taskTitle, taskInfo, taskPay, taskPlace, releaseTime, types[0],
				types[1], types[2], attachURL);
		OutsourcingDB outsourcingDB = new OutsourcingDB();
		
		System.out.println(os.toString());
		
		try {			
			outsourcingDB.addOutsourcing(os);
			request.getRequestDispatcher(path).forward(request, response);
		} catch (TransactionCollisionException e) {
			e.printStackTrace();
			request.getRequestDispatcher("/publishTask_4.jsp").forward(request, response);
		}
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}