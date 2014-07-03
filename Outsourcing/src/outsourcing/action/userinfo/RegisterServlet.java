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
		 * 存在的问题：密码使用MD5加密    41行还没有进行路径跳转     用户account 还是  用户名userName   设置在session变量中？
		 */
		//返回的路径
		String errorpath = "register.jsp";
		String successpath = "index.jsp";	//注册成功后跳转回到之前的页面

		List<String> info = new ArrayList<String>();	//保存返回的所有信息
		//取得页面提交的信息
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		
		System.out.println("email："+email+"| username:"+username+"| password:"+password);
		
		Account account = new Account(email, username, password);	//创建一个账户
		AccountService accountService = new AccountService();
		if(accountService.validateInfo(info, account, password2)){	//验证成功
			if(accountDB.Exist(account.getUserName())){
				info.add("注册失败,用户名已经存在");	//用户名已经存在   + email验证
			}else{
				try {
					accountDB.Register(account);	//注册
				} catch (TransactionCollisionException e) {
					e.printStackTrace();
					info.add("用户注册失败");
				} catch (InvalidUserNameException e) {
					e.printStackTrace();
					info.add("用户注册失败");
				}	
			}
		}
		
		System.out.println(info.toString());
		
		if(info.size()>0){	//进行服务器端跳转（注册失败，显示失败信息）
			request.setAttribute("info", info);
			request.getRequestDispatcher(errorpath).forward(request, response);
		}else{		//注册成功，进行客户端跳转，跳转到原地址
			info.add("恭喜您注册成功");
			request.getSession().setAttribute("user", account.getUserName());		//将用户名设置在session中
			request.getSession().setAttribute("info", info);
			response.sendRedirect(successpath);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}






















