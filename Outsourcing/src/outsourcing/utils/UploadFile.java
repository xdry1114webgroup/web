package outsourcing.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 上传文件和验证文件的操作类
 * @author Administrator
 *
 */
public class UploadFile {
	
	/**
	 * 上传文件,返回文件路径
	 * @param request 
	 * @param pathdir 上传的文件路径  例如："files/outsourcing"
	 * @return
	 */
	public static String uploadFile(HttpServletRequest request, String pathdir){
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);	//判断是否是文件上传表单
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();	//穿件磁盘工厂
			ServletFileUpload upload = new ServletFileUpload(factory);	//创建处理工具
			upload.setFileSizeMax(31457280);		//设置最大上传大小  30*1024*1024 = 30M
			try {
				Iterator items = null;
				items = upload.parseRequest(request).iterator();	//接受全部内容，将全部内容转化为iterator实例
				while (items.hasNext()) {
					FileItem item = (FileItem) items.next();	//取出每一个上传的文件
					if (!item.isFormField() && validateFileType(item)) {	//不是普通的文本数据，是上传文件 && 文件后缀和类型都合法
						// /files/outsourcing/UUID.gif
						//文件保存的真实路径
						String realdir = request.getSession().getServletContext().getRealPath(pathdir);	//真实路径
						File imagesavedir = new File(realdir);
						if(!imagesavedir.exists())   imagesavedir.mkdirs();
						//文件名
						String imagename = item.getName();
//						String exten = item.getName().substring(item.getName().lastIndexOf("."));
//						String imagename = UUID.randomUUID().toString() + exten;
						FileOutputStream fileoutstream = new FileOutputStream(new File(realdir, imagename));
						fileoutstream.write(item.get());
						fileoutstream.close();
						String userimage = pathdir +"/"+ imagename;
						return userimage;
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 验证上传文件是否属于图片/flash动画/word文件/exe文件/pdf文件/TxT文件/xls文件/ppt文件
	 * @param formfile
	 * @return
	 */
	public static boolean validateFileType(FileItem file){
		if(file!=null && file.getSize()>0){
			List<String> allowTypes = Arrays.asList("image/bmp","image/png","image/gif","image/jpg","image/jpeg","image/pjpeg",
					"application/x-shockwave-flash","application/msword","text/plain","application/vnd.ms-excel","application/vnd.ms-powerpoint","application/pdf","application/octet-stream");	//允许上传的文件类型
			List<String> allowExtension = Arrays.asList("gif","jpg","bmp","png","jpeg","pjpeg","swf","doc","txt","xls","ppt","pdf","exe");	//允许上传的文件后缀
			String ext = file.getName().substring(file.getName().lastIndexOf(".")+1).toLowerCase();
			String contenttype = file.getContentType().toLowerCase();
			return allowTypes.contains(contenttype) && allowExtension.contains(ext);
		}
		return  true;
	}
	
	
	/**
	 * 上传图片文件,返回文件路径
	 * @param request 
	 * @param pathdir 上传的图片文件路径  例如："images/userimage"
	 * @return
	 */
	public static String uploadImageFile(HttpServletRequest request, String pathdir){
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);	//判断是否是文件上传表单
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();	//穿件磁盘工厂
			ServletFileUpload upload = new ServletFileUpload(factory);	//创建处理工具
			upload.setFileSizeMax(3145728);		//设置最大上传大小  3*1024*1024 = 3M
			try {
				Iterator items = null;
				items = upload.parseRequest(request).iterator();	//接受全部内容，将全部内容转化为iterator实例
				while (items.hasNext()) {
					FileItem item = (FileItem) items.next();	//取出每一个上传的文件
					if (!item.isFormField() && validateImageFileType(item)) {	//不是普通的文本数据，是上传文件 && 文件后缀和类型都合法
						// /iamges/userimage/UUID.gif
						//文件保存的真实路径
						String realdir = request.getSession().getServletContext().getRealPath(pathdir);	//真实路径
						File imagesavedir = new File(realdir);
						if(!imagesavedir.exists())   imagesavedir.mkdirs();
						//文件名
						String exten = item.getName().substring(item.getName().lastIndexOf("."));
						String imagename = UUID.randomUUID().toString() + exten;
						FileOutputStream fileoutstream = new FileOutputStream(new File(realdir, imagename));
						fileoutstream.write(item.get());
						fileoutstream.close();
						String userimage = pathdir +"/"+ imagename;
						return userimage;
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 验证上传文件类型是否属于图片格式
	 * @param formfile
	 * @return
	 */
	public static boolean validateImageFileType(FileItem file){
		if(file!=null && file.getSize()>0){
			List<String> allowTypes = Arrays.asList("image/bmp","image/png","image/gif","image/jpg","image/jpeg","image/pjpeg");	//允许上传的图片类型
			List<String> allowExtension = Arrays.asList("gif","jpg","bmp","png","jpeg","pjpeg");	//允许上传的图片后缀
			String ext = file.getName().substring(file.getName().lastIndexOf(".")+1).toLowerCase();
			String contenttype = file.getContentType().toLowerCase();
			return allowTypes.contains(contenttype) && allowExtension.contains(ext);
		}
		return  true;
	}
	
}
