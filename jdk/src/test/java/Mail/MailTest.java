package Mail;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

public class MailTest {

	@Test
	public void test() {
		 String host = "smtp.gmail.com";							//使用GMAIL, 需要將改變帳戶的安全性設定 ,改為低安全性
		  int port = 587;
		  final String username = "XXXXXXXXXXXXx@gmail.com";
		  final String password = "XXXXXXXXXXXXXXX";				//your password

		  Properties props = new Properties();
		  props.put("mail.smtp.host", host);
		  props.put("mail.smtp.auth", "true");
		  props.put("mail.smtp.starttls.enable", "true");
		  props.put("mail.smtp.port", port);
		  Session session = Session.getInstance(props, new Authenticator() {
		   protected PasswordAuthentication getPasswordAuthentication() {
		    return new PasswordAuthentication(username, password);
		   }
		  });

		  try {

		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress("fromn@gmail.com"));
		   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("weichou1229@gmail.com"));
		   message.setSubject("測試寄信.");
		   message.setText("Dear Levin, \n\n 測試 測試 測試 測試 測試 測試 email !");

		   Transport transport = session.getTransport("smtp");
		   transport.connect(host, port, username, password);

		   Transport.send(message);

		   System.out.println("寄送email結束.");

		  } catch (MessagingException e) {
		   throw new RuntimeException(e);
		  }
	}

}
