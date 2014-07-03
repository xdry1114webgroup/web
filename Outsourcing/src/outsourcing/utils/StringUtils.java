package outsourcing.utils;

public class StringUtils {
	
	public static boolean isEmpty(String str){
		return str==null || "".equals(str);
	}
	
	public static boolean isLengthValidate(String str){
		boolean isvalidate = true;
		if(str.length()<4 || str.length()>16){
			isvalidate = false;
		}
		return isvalidate;
	}
	
	public static boolean test(String uid,String pwd){
		return uid!=null && !"".equals(uid) &&  pwd!=null && !"".equals(pwd);
	}
}
