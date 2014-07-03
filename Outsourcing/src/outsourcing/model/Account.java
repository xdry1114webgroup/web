package outsourcing.model;

import java.util.Date;

public class Account {
	String UserID;//�û�ID  ������1,2,3,...,max
	String userName;//�ǳ� (Ψһ�����ظ�)
	String loginPassword;//����
	Date   birthday;//����
	String realName;//��ʵ����
	int    age;//����
	String gender;//�Ա� �� Ů
	String userImage;//�û�ͷ���URL��ַ
	String address;//סַ
	String email;//��������
	String phoneNumber;//�绰����
	String academy;//ѧԺ
	String grade;//�꼶
	
	public Account(){
		
	}

	public Account(String email, String username, String password) {
		this.email = email;
		this.userName = username;
		this.loginPassword = password;
	}

	public Account(String userID, Date birthday, String realName, int age,
			String gender, String address, String phoneNumber,
			String academy, String grade) {
		this.UserID = userID;
		this.birthday = birthday;
		this.realName = realName;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.academy = academy;
		this.grade = grade;
	}

	public Account(String uid) {
		this.UserID = uid;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gendar) {
		this.gender = gendar;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
}
