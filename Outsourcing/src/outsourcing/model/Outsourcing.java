package outsourcing.model;

import java.util.Date;

public class Outsourcing {
	String taskID;//����ID  ������1,2,3,...,max
	String releaseUser;//userNameӳ��
	String taskTitle;//�������
	String taskInfo;//��������
	double taskPay;//��������ֵ
	boolean isComplete;//�Ƿ��ѱ����
	String taskPlace;//������ɵĵص�
	Date releaseTime;//����ʱ��
	Date deadline;//�������
	int collectValue;//���ղصĴ���
	String type;//������𡪡�>����
	String grocery;//������𡪡�>��
	String item;//������𡪡�>С��
	String attachURL;//����URL
	String qq;//��ϵ��ʽQQ
	
	public Outsourcing(String taskID, String releaseUser, String taskTitle,
			String taskInfo, double taskPay, String taskPlace,
			Date releaseTime, String type, String grocery, String item,
			String attachURL) {
		this.taskID = taskID;
		this.releaseUser = releaseUser;
		this.taskTitle = taskTitle;
		this.taskInfo = taskInfo;
		this.taskPay = taskPay;
		this.taskPlace = taskPlace;
		this.releaseTime = releaseTime;
		this.type = type;
		this.grocery = grocery;
		this.item = item;
		this.attachURL = attachURL;
		this.isComplete = false;
		this.collectValue = 0;
	}

	public Outsourcing() {}
	
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	String tel;//��ϵ��ʽ�ֻ���
	
	public String getAttachURL() {
		return attachURL;
	}
	public void setAttachURL(String attachURL) {
		this.attachURL = attachURL;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGrocery() {
		return grocery;
	}
	public void setGrocery(String grocery) {
		this.grocery = grocery;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	
	public String getTaskID() {
		return taskID;
	}
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}
	public String getReleaseUser() {
		return releaseUser;
	}
	public void setReleaseUser(String releaseUser) {
		this.releaseUser = releaseUser;
	}
	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public String getTaskInfo() {
		return taskInfo;
	}
	public void setTaskInfo(String taskInfo) {
		this.taskInfo = taskInfo;
	}
	public double getTaskPay() {
		return taskPay;
	}
	public void setTaskPay(double taskPay) {
		this.taskPay = taskPay;
	}
	public boolean isComplete() {
		return isComplete;
	}
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	public String getTaskPlace() {
		return taskPlace;
	}
	public void setTaskPlace(String taskPlace) {
		this.taskPlace = taskPlace;
	}
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public int getCollectValue() {
		return collectValue;
	}
	public void setCollectValue(int collectValue) {
		this.collectValue = collectValue;
	}
	
	
}
