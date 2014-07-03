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
		 * ���ڵ����⣺��ȷ������֮�����תҳ��
		 * get��ʽ�ύ���벻��ȫ
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
			msg = "��������벻��Ϊ��";
		}else if(!password.equals(newpassword)){
			msg = "������������벻һ��";
		}else if(!password.matches(AccountService.pwdregex)){
			msg = "��������ĸ�ʽ����ȷ,ֻ��Ϊ4-16λ���顢�ַ���������";
		}
		if(!"".equals(msg)){
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(path).forward(request, response);
		}else{
			AccountDB accountDB = new AccountDB(); 
			String uid = accountDB.getUidByTempLink(timestamp);		//ͨ��ʱ���ȡ��UID
			Account account = new Account(uid);
			account.setLoginPassword(password);
			try {
				accountDB.Update(account);		//����UID����Ӧ���û�������
				request.setAttribute("msg", "�����޸ĳɹ�");
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
