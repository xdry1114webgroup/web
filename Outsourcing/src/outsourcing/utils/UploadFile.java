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
 * �ϴ��ļ�����֤�ļ��Ĳ�����
 * @author Administrator
 *
 */
public class UploadFile {
	
	/**
	 * �ϴ��ļ�,�����ļ�·��
	 * @param request 
	 * @param pathdir �ϴ����ļ�·��  ���磺"files/outsourcing"
	 * @return
	 */
	public static String uploadFile(HttpServletRequest request, String pathdir){
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);	//�ж��Ƿ����ļ��ϴ���
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();	//�������̹���
			ServletFileUpload upload = new ServletFileUpload(factory);	//����������
			upload.setFileSizeMax(31457280);		//��������ϴ���С  30*1024*1024 = 30M
			try {
				Iterator items = null;
				items = upload.parseRequest(request).iterator();	//����ȫ�����ݣ���ȫ������ת��Ϊiteratorʵ��
				while (items.hasNext()) {
					FileItem item = (FileItem) items.next();	//ȡ��ÿһ���ϴ����ļ�
					if (!item.isFormField() && validateFileType(item)) {	//������ͨ���ı����ݣ����ϴ��ļ� && �ļ���׺�����Ͷ��Ϸ�
						// /files/outsourcing/UUID.gif
						//�ļ��������ʵ·��
						String realdir = request.getSession().getServletContext().getRealPath(pathdir);	//��ʵ·��
						File imagesavedir = new File(realdir);
						if(!imagesavedir.exists())   imagesavedir.mkdirs();
						//�ļ���
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
	 * ��֤�ϴ��ļ��Ƿ�����ͼƬ/flash����/word�ļ�/exe�ļ�/pdf�ļ�/TxT�ļ�/xls�ļ�/ppt�ļ�
	 * @param formfile
	 * @return
	 */
	public static boolean validateFileType(FileItem file){
		if(file!=null && file.getSize()>0){
			List<String> allowTypes = Arrays.asList("image/bmp","image/png","image/gif","image/jpg","image/jpeg","image/pjpeg",
					"application/x-shockwave-flash","application/msword","text/plain","application/vnd.ms-excel","application/vnd.ms-powerpoint","application/pdf","application/octet-stream");	//�����ϴ����ļ�����
			List<String> allowExtension = Arrays.asList("gif","jpg","bmp","png","jpeg","pjpeg","swf","doc","txt","xls","ppt","pdf","exe");	//�����ϴ����ļ���׺
			String ext = file.getName().substring(file.getName().lastIndexOf(".")+1).toLowerCase();
			String contenttype = file.getContentType().toLowerCase();
			return allowTypes.contains(contenttype) && allowExtension.contains(ext);
		}
		return  true;
	}
	
	
	/**
	 * �ϴ�ͼƬ�ļ�,�����ļ�·��
	 * @param request 
	 * @param pathdir �ϴ���ͼƬ�ļ�·��  ���磺"images/userimage"
	 * @return
	 */
	public static String uploadImageFile(HttpServletRequest request, String pathdir){
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);	//�ж��Ƿ����ļ��ϴ���
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();	//�������̹���
			ServletFileUpload upload = new ServletFileUpload(factory);	//����������
			upload.setFileSizeMax(3145728);		//��������ϴ���С  3*1024*1024 = 3M
			try {
				Iterator items = null;
				items = upload.parseRequest(request).iterator();	//����ȫ�����ݣ���ȫ������ת��Ϊiteratorʵ��
				while (items.hasNext()) {
					FileItem item = (FileItem) items.next();	//ȡ��ÿһ���ϴ����ļ�
					if (!item.isFormField() && validateImageFileType(item)) {	//������ͨ���ı����ݣ����ϴ��ļ� && �ļ���׺�����Ͷ��Ϸ�
						// /iamges/userimage/UUID.gif
						//�ļ��������ʵ·��
						String realdir = request.getSession().getServletContext().getRealPath(pathdir);	//��ʵ·��
						File imagesavedir = new File(realdir);
						if(!imagesavedir.exists())   imagesavedir.mkdirs();
						//�ļ���
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
	 * ��֤�ϴ��ļ������Ƿ�����ͼƬ��ʽ
	 * @param formfile
	 * @return
	 */
	public static boolean validateImageFileType(FileItem file){
		if(file!=null && file.getSize()>0){
			List<String> allowTypes = Arrays.asList("image/bmp","image/png","image/gif","image/jpg","image/jpeg","image/pjpeg");	//�����ϴ���ͼƬ����
			List<String> allowExtension = Arrays.asList("gif","jpg","bmp","png","jpeg","pjpeg");	//�����ϴ���ͼƬ��׺
			String ext = file.getName().substring(file.getName().lastIndexOf(".")+1).toLowerCase();
			String contenttype = file.getContentType().toLowerCase();
			return allowTypes.contains(contenttype) && allowExtension.contains(ext);
		}
		return  true;
	}
	
}
