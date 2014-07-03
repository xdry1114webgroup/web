package outsourcing.action.userinfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import outsourcing.db.redis.AccountDB;
import outsourcing.exceptions.InvalidUserNameException;
import outsourcing.exceptions.TransactionCollisionException;
import outsourcing.model.Account;
import outsourcing.service.userinfo.AccountService;
import outsourcing.utils.StringUtils;

public class RegisterServlet extends HttpServlet {
	
	private AccountDB accountDB = new AccountDB();
	
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
		 * ���ڵ����⣺����ʹ��MD5����    41�л�û�н���·����ת     �û�account ����  �û���userName   ������session�����У�
		 */
		//���ص�·��
		String errorpath = "register.jsp";
		String successpath = "index.jsp";	//ע��ɹ�����ת�ص�֮ǰ��ҳ��

		List<String> info = new ArrayList<String>();	//���淵�ص�������Ϣ
		//ȡ��ҳ���ύ����Ϣ
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		
		System.out.println("email��"+email+"| username:"+username+"| password:"+password);
		
		Account account = new Account(email, username, password);	//����һ���˻�
		AccountService accountService = new AccountService();
		if(accountService.validateInfo(info, account, password2)){	//��֤�ɹ�
			if(accountDB.Exist(account.getUserName())){
				info.add("ע��ʧ��,�û����Ѿ�����");	//�û����Ѿ�����   + email��֤
			}else{
				try {
					accountDB.Register(account);	//ע��
				} catch (TransactionCollisionException e) {
					e.printStackTrace();
					info.add("�û�ע��ʧ��");
				} catch (InvalidUserNameException e) {
					e.printStackTrace();
					info.add("�û�ע��ʧ��");
				}	
			}
		}
		
		System.out.println(info.toString());
		
		if(info.size()>0){	//���з���������ת��ע��ʧ�ܣ���ʾʧ����Ϣ��
			request.setAttribute("info", info);
			request.getRequestDispatcher(errorpath).forward(request, response);
		}else{		//ע��ɹ������пͻ�����ת����ת��ԭ��ַ
			info.add("��ϲ��ע��ɹ�");
			request.getSession().setAttribute("user", account.getUserName());		//���û���������session��
			request.getSession().setAttribute("info", info);
			response.sendRedirect(successpath);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}






















