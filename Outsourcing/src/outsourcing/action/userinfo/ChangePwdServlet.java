package outsourcing.action.userinfo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.mail.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import outsourcing.db.redis.AccountDB;
import outsourcing.exceptions.InvalidUserNameException;
import outsourcing.service.userinfo.AccountService;
import outsourcing.utils.mail.MailSenderInfo;
import outsourcing.utils.mail.SimpleMailSender;

public class ChangePwdServlet extends HttpServlet {

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
		 * ���ڵ����⣺�û�email����Ĵ�����ʾ
		 */
		String path = "getPassword.html";
		String email = request.getParameter("email");
		String identifier_code = request.getParameter("identifier_code");
		String errormsg = "";
		AccountDB accountDB = new AccountDB();
		// ��֤��֤���Ƿ���ȷ
		if (!email.matches(AccountService.emailregex)) {
			errormsg = "email��ʽ���Ϸ�";
		} else if (!accountDB.Exist(email)) {
			errormsg = "emailδע��Ϊ�û�";
		}
		if (!"".equals(errormsg)) {
			request.setAttribute("errormsg", errormsg);
			request.getRequestDispatcher(path).forward(request, response);
		} else {
			try {
				String timestamp = accountDB.GenerateTempLink2Uid(email); // ����ʱ���������
				String sendurl = request.getScheme() + "://"
						+ request.getServerName() + ":"
						+ request.getServerPort() + request.getContextPath()
						+ "/getPassword.html?timestamp=" + timestamp;
				System.out.println(email + " | ���ӣ�" + sendurl);

				// �������Ҫ�������ʼ�
				MailSenderInfo mailInfo = new MailSenderInfo();
				mailInfo.setMailServerHost("smtp.163.com");
				mailInfo.setMailServerPort("25");
				mailInfo.setValidate(true);
				mailInfo.setUserName("zhangqi102@163.com");
				mailInfo.setPassword("199210026516");// ������������
				mailInfo.setFromAddress("zhangqi102@163.com");
				mailInfo.setToAddress(email);
				mailInfo.setSubject("��������-��������ҳ��");
				String format = "<a href=\"%s\">%s</a>";
				format = String.format(format, sendurl,sendurl);
				mailInfo.setContent("�������õ�ַ��"+format);
				// �������Ҫ�������ʼ�
				SimpleMailSender sms = new SimpleMailSender();
				sms.sendHtmlMail(mailInfo);// ����html��ʽ

				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("true");
				out.flush();
				out.close();
			} catch (InvalidUserNameException e) {
				e.printStackTrace();
			}
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
