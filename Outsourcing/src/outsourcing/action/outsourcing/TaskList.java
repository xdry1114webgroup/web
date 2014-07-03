package outsourcing.action.outsourcing;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import outsourcing.db.redis.OutsourcingDB;
import outsourcing.model.Outsourcing;
import outsourcing.model.Paging;

/**
 * 任务列表
 * @author Administrator
 *
 */
public class TaskList extends HttpServlet {

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
		 *		 /Outsourcing/TaskList
		 */
		String path = "/taskList.jsp";
//		String type = request.getParameter("type");		//任务类型
//		String taskPlace = request.getParameter("taskPlace");	//任务地点
//		String taskTime = request.getParameter("taskTime");		//任务发布的时间:  一天内
//		String taskPay = request.getParameter("taskPay");		//任务赏金
//		
//		System.out.println("type:"+type);
//		System.out.println("taskPlace:"+taskPlace);
//		System.out.println("taskTime:"+taskTime);
//		System.out.println("taskPay:"+taskPay);
//		
//		int page = Integer.parseInt(request.getParameter("page"));	//请求显示的第几页
//		int startIndex = (page-1) * 10;		//第一个开始索引
//		request.setAttribute("type", "哈哈哈");
		
		OutsourcingDB osDB = new OutsourcingDB();
		List<Outsourcing> oslist = new ArrayList<Outsourcing>();
		Paging page = new Paging(10, 0);
		HashMap<String, String> condition = new LinkedHashMap<String, String>();
 		oslist = osDB.viewOutsourcing(condition, page);
		
		request.setAttribute("oslist", oslist);
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
