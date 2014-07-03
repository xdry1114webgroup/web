package outsourcing.action.userinfo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import outsourcing.utils.CookieUtils;

/**
 * �û��Ƴ���¼
 */
public class LogoutServlet extends HttpServlet {

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
		 * ���ڵ����⣺�˳���¼֮��ҳ����ת��  �˳���¼��ʱ���ڵ�ҳ��
		 */
		request.getSession().removeAttribute("user");	//��session��Χ�е�user����ȥ��
		//ɾ��cookie
		CookieUtils.addCookie(response, "uid", null, 0);
		CookieUtils.addCookie(response, "username", null, 0);
		CookieUtils.addCookie(response, "password", null, 0);
		
		PrintWriter out = response.getWriter();
		out.println("OK");
		out.flush();
		out.close();
		System.out.println("�˳�");
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
