package outsourcing.utils.mail;

public class TestSendMail {

	public static void main(String[] args) {
		// �������Ҫ�������ʼ�
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.163.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("zhangqi102@163.com");
		mailInfo.setPassword("199210026516");// ������������
		mailInfo.setFromAddress("zhangqi102@163.com");
		mailInfo.setToAddress("zhangqi102@163.com");
		mailInfo.setSubject("�����������");
		mailInfo.setContent("������������");
		// �������Ҫ�������ʼ�
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendTextMail(mailInfo);// ���������ʽ
		sms.sendHtmlMail(mailInfo);// ����html��ʽ
	

	}

}
