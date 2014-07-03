package outsourcing.action.userinfo;

import java.io.IOException;

import outsourcing.utils.UploadFile;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import outsourcing.db.redis.AccountDB;
import outsourcing.exceptions.InvalidUserNameException;
import outsourcing.model.Account;

public class ChangeUserImage extends HttpServlet {

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
		String path = "/userinfo?choose=list";
		String userName = (String) request.getSession().getAttribute("user");
		String userImage = UploadFile.uploadImageFile(request, "images/userimage");	//½«Í¼Æ¬ÉÏ´«
		AccountDB accountDB = new AccountDB(); 
		Account account = null;
		try {
			account = accountDB.getAccountByUid(accountDB.getUidByUserName(userName));
			account.setUserImage(userImage);
			accountDB.Update(account);
		} catch (InvalidUserNameException e) {
			e.printStackTrace();
		}
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
