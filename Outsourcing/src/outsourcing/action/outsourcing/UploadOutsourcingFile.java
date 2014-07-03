package outsourcing.action.outsourcing;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import outsourcing.utils.UploadFile;

public class UploadOutsourcingFile extends HttpServlet {

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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String attachURL = UploadFile.uploadFile(request, "files/outsourcing");	//  files/outsourcing/xxx.doc
		int fileNumber = 0;
		if(attachURL!=null && attachURL.length()>0){
			fileNumber = 1;
		}
		String filename = attachURL.substring(attachURL.lastIndexOf("/")+1); //files/outsourcing/xxx.doc
		request.getSession().setAttribute("filename", filename);
		request.getSession().setAttribute("attachURL", attachURL);
		request.getSession().setAttribute("fileNumber", fileNumber);
		request.getRequestDispatcher("/publishTask_2.jsp").forward(request, response);
	}


}

























