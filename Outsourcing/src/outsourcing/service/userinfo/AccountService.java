package outsourcing.service.userinfo;

import java.util.ArrayList;
import java.util.List;

import outsourcing.model.Account;
import outsourcing.utils.StringUtils;

public class AccountService {
	/**
	 * ��֤���򣺹��Ұ������ַ�ֳɡ���һ����@�ڶ����֡�����
	 * ��һ���֣�����ĸ�����֡��»��ߡ����ߡ�-������š�.����ɣ�
	 * �ڶ����֣�Ϊһ����������������ĸ�����֡����ߡ�-����������׺��ɣ�
	 * ��������׺һ��Ϊ.xxx��.xxx.xx��һ����������׺һ��Ϊ2-4λ����cn,com,net�����������е�Ҳ�����4λ
	 */
	public static String emailregex = "^(\\w-*\\.*)+@(\\w-?)+(\\.\\w{2,})+$";
	
	/**
	 * ��ĸ�����֡��»�����ɣ�4-16λ�������пո�[a-zA-z0-9\\_]
	 */
	public static String usernameregex = "^\\w{4,16}$";
	
	/**
	 * ������4-16֮�䣬ֻ�ܰ����ַ������ֺͱ����ţ������пո�
	 */
	public static String pwdregex = "^[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~\\?\\,\\_\\-\\+\\(\\)]{4,16}$"; 
	
	/**
	 * �绰�����������ʽ
	 */
	public static String phonenumberregex = "^1[358]\\d{9}$";
	
	/**
	 * ������֤�Ƿ���ȷ
	 * @param info
	 * @param account
	 */
	public boolean validateInfo(List<String> info, Account account, String password2){
		/**
		 * ����Ҫ����  �˺����������Ƿ���� �ַ��� ��֤��ͨ��������ʽ��
		 */
		boolean isvalidate = true;
		if(StringUtils.isEmpty(account.getEmail())){
			info.add("email����Ϊ��");
		}
		if(StringUtils.isEmpty(account.getUserName())){
			info.add("�û�������Ϊ��");
		}
		if(StringUtils.isEmpty(account.getLoginPassword())){
			info.add("���벻��Ϊ��");
		}
		if(!StringUtils.isLengthValidate(account.getUserName())){
			info.add("�û������ȱ�����4-16λ֮��");
		}
		if(!StringUtils.isLengthValidate(account.getLoginPassword())){
			info.add("���볤�ȱ�����4-16λ֮��");
		}
		if(!account.getLoginPassword().equals(password2)){
			info.add("�����������벻һ��");
		}
		if(!account.getEmail().matches(emailregex)){
			info.add("����ĸ�ʽ����ȷ������ĸ�����֡��»��ߡ�-��. ���");
		}
		if(!account.getUserName().matches(usernameregex)){
			info.add("�û����ĸ�ʽ����ȷ����ĸ�����֡��»�����ɣ���ĸ��ͷ");
		}
		if(!account.getLoginPassword().matches(pwdregex)){
			info.add("�û����������ʽ����ȷ��ֻ�ܰ����ַ������ֺ��»���");
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
