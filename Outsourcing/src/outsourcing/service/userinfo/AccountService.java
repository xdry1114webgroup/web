package outsourcing.service.userinfo;

import java.util.ArrayList;
import java.util.List;

import outsourcing.model.Account;
import outsourcing.utils.StringUtils;

public class AccountService {
	/**
	 * 验证规则：姑且把邮箱地址分成“第一部分@第二部分”这样
	 * 第一部分：由字母、数字、下划线、短线“-”、点号“.”组成，
	 * 第二部分：为一个域名，域名由字母、数字、短线“-”、域名后缀组成，
	 * 而域名后缀一般为.xxx或.xxx.xx，一区的域名后缀一般为2-4位，如cn,com,net，现在域名有的也会大于4位
	 */
	public static String emailregex = "^(\\w-*\\.*)+@(\\w-?)+(\\.\\w{2,})+$";
	
	/**
	 * 字母、数字、下划线组成，4-16位，不能有空格。[a-zA-z0-9\\_]
	 */
	public static String usernameregex = "^\\w{4,16}$";
	
	/**
	 * 长度在4-16之间，只能包含字符、数字和标点符号，不能有空格
	 */
	public static String pwdregex = "^[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~\\?\\,\\_\\-\\+\\(\\)]{4,16}$"; 
	
	/**
	 * 电话号码的正则表达式
	 */
	public static String phonenumberregex = "^1[358]\\d{9}$";
	
	/**
	 * 进行验证是否正确
	 * @param info
	 * @param account
	 */
	public boolean validateInfo(List<String> info, Account account, String password2){
		/**
		 * 还需要进行  账号密码邮箱是否符合 字符的 验证（通过正则表达式）
		 */
		boolean isvalidate = true;
		if(StringUtils.isEmpty(account.getEmail())){
			info.add("email不能为空");
		}
		if(StringUtils.isEmpty(account.getUserName())){
			info.add("用户名不能为空");
		}
		if(StringUtils.isEmpty(account.getLoginPassword())){
			info.add("密码不能为空");
		}
		if(!StringUtils.isLengthValidate(account.getUserName())){
			info.add("用户名长度必须在4-16位之间");
		}
		if(!StringUtils.isLengthValidate(account.getLoginPassword())){
			info.add("密码长度必须在4-16位之间");
		}
		if(!account.getLoginPassword().equals(password2)){
			info.add("两次输入密码不一致");
		}
		if(!account.getEmail().matches(emailregex)){
			info.add("邮箱的格式不正确：由字母、数字、下划线、-、. 组成");
		}
		if(!account.getUserName().matches(usernameregex)){
			info.add("用户名的格式不正确：字母、数字、下划线组成，字母开头");
		}
		if(!account.getLoginPassword().matches(pwdregex)){
			info.add("用户名的密码格式不正确：只能包含字符、数字和下划线");
		}
		if(info.size() > 0){
			isvalidate = false;
		}
		return isvalidate;
	}
	
	public static void main(String args[]){
//		AccountService accountService = new  AccountService(); 
//		List<String> info = new ArrayList<String>();
//		Account account = new Account("zhangqi102@163.com","Ok34","l2.-*&,.!@#$%^76");
//		accountService.validateInfo(info, account, account.getLoginPassword());
//		for(int i=0; i<info.size();i++){
//			System.out.println(info.get(i).toString());
//		}
//		System.out.println("13691767282".matches(phonenumberregex));
	}
	
}
