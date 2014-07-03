package outsourcing.action.userinfo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import outsourcing.db.redis.AccountDB;
import outsourcing.exceptions.InvalidUserNameException;
import outsourcing.service.userinfo.AccountService;
import outsourcing.utils.CookieUtils;
import outsourcing.utils.MD5Util;
import outsourcing.utils.StringUtils;

public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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
		doPost(request, response);
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
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/**
		 * ���ڵ����⣺��¼�ɹ�����ʧ��֮����ת��ҳ�棿�� 
		 * ʧ�ܺ��ǵ�¼ҳ�棬
		 * �ɹ�����ԭ����ת�����������ҳ�� main.jsp -> login.jsp(urladdress = main.jsp) ->(/login)-> main.jsp
		 */
		String errorpath = "login.jsp";
		String successpath = (String)req.getSession().getAttribute("url");	//ע��ɹ�����ת�ص�֮ǰ��ҳ��
		if(successpath==null){
			successpath = "index.jsp";
		}
		
		String login_acc = req.getParameter("username");
		String password = req.getParameter("password");
		String rememberMe = req.getParameter("rememberMe");
		if(StringUtils.isEmpty(login_acc) || StringUtils.isEmpty(password)){
			req.setAttribute("info", "������������");			
			req.getRequestDispatcher(errorpath).forward(req, resp);
			return;
		}
		
		AccountDB control = new AccountDB();
		if (control.Exist(login_acc)){		//�û�����
			boolean isLoginSuccess = false;
			try {
				isLoginSuccess = control.Login(login_acc, password);
			} catch (InvalidUserNameException e) {
				e.printStackTrace();
			}
			if(isLoginSuccess){		//��¼�ɹ�
				String username = login_acc;	//Ĭ��Ϊ �˻���		
				String uid;
				if(login_acc.matches(AccountService.emailregex)){	//email
					uid = control.getUidByEmail(login_acc);
					try {
						username = control.getAccountByUid(uid).getUserName();
					} catch (InvalidUserNameException e) {
						e.printStackTrace();
					}
				}else if(login_acc.matches(AccountService.phonenumberregex)){
					uid = control.getUidByPhoneNumber(login_acc);
					try {
						username = control.getAccountByUid(uid).getUserName();
					} catch (InvalidUserNameException e) {
						e.printStackTrace();
					}
				}else{
					uid = control.getUidByUserName(login_acc);
				}
				req.getSession().setAttribute("user", username);
				boolean remember = "on".equals(rememberMe);
				
				if(remember){//����cookie
					int maxAge = 60*60*24*100;
					CookieUtils.addCookie(resp, "uid", uid, maxAge);
					CookieUtils.addCookie(resp, "username", username, maxAge);
					CookieUtils.addCookie(resp, "password", MD5Util.MD5Encode(password), maxAge);
					
				}else{//ɾ��cookie
					CookieUtils.addCookie(resp, "uid", null, 0);
					CookieUtils.addCookie(resp, "user", null, 0);
					CookieUtils.addCookie(resp, "password", null, 0);					
				}
				resp.sendRedirect(successpath);
			}else{	//��¼ʧ��
				req.setAttribute("info", "�˺����벻ƥ��");
				req.getRequestDispatcher(errorpath).forward(req, resp);
			}
		} else {	//�û���������
			req.setAttribute("info", "�û���������");			
			req.getRequestDispatcher(errorpath).forward(req, resp);
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
