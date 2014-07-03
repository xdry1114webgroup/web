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
		 * 存在的问题：登录成功或者失败之后跳转的页面？？ 
		 * 失败后是登录页面，
		 * 成功后是原来跳转到这里的请求页面 main.jsp -> login.jsp(urladdress = main.jsp) ->(/login)-> main.jsp
		 */
		String errorpath = "login.jsp";
		String successpath = (String)req.getSession().getAttribute("url");	//注册成功后跳转回到之前的页面
		if(successpath==null){
			successpath = "index.jsp";
		}
		
		String login_acc = req.getParameter("username");
		String password = req.getParameter("password");
		String rememberMe = req.getParameter("rememberMe");
		if(StringUtils.isEmpty(login_acc) || StringUtils.isEmpty(password)){
			req.setAttribute("info", "您的输入有误！");			
			req.getRequestDispatcher(errorpath).forward(req, resp);
			return;
		}
		
		AccountDB control = new AccountDB();
		if (control.Exist(login_acc)){		//用户存在
			boolean isLoginSuccess = false;
			try {
				isLoginSuccess = control.Login(login_acc, password);
			} catch (InvalidUserNameException e) {
				e.printStackTrace();
			}
			if(isLoginSuccess){		//登录成功
				String username = login_acc;	//默认为 账户名		
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
				
				if(remember){//保存cookie
					int maxAge = 60*60*24*100;
					CookieUtils.addCookie(resp, "uid", uid, maxAge);
					CookieUtils.addCookie(resp, "username", username, maxAge);
					CookieUtils.addCookie(resp, "password", MD5Util.MD5Encode(password), maxAge);
					
				}else{//删除cookie
					CookieUtils.addCookie(resp, "uid", null, 0);
					CookieUtils.addCookie(resp, "user", null, 0);
					CookieUtils.addCookie(resp, "password", null, 0);					
				}
				resp.sendRedirect(successpath);
			}else{	//登录失败
				req.setAttribute("info", "账号密码不匹配");
				req.getRequestDispatcher(errorpath).forward(req, resp);
			}
		} else {	//用户名不存在
			req.setAttribute("info", "用户名不存在");			
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
