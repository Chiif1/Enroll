package com.aol.nugrs;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailUtil {
	
	public void sendMail(String recepient) {
		System.out.print("preparing to send  email");
		Properties properties = new Properties();
		
	properties.put("mail.smtp.auth", "true");
	properties.put("mail.smtp.starttls.enable", "true");
	properties.put("mail.smtp.host", "smtp.gmail.com");
	properties.put("mail.smtp.port", "587");
	
	String myAccountEmail = "nugrsnugrs@gmail.com";
	String password = "Survival1";
	
	 Session session = Session.getInstance(properties, new Authenticator() {
		@Override
		 protected PasswordAuthentication getPasswordAuthentication( ) {
			
			 return new PasswordAuthentication(myAccountEmail, password);
		 }
		 
	 });
	 
	 Message message = prepareMessage(session, myAccountEmail, recepient);
	 try {
		Transport.send(message);
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 System.out.print("Message sent successfully");
	}
	
	private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Recruitment Confirmation");
			message.setText("Your Submisson Received");
			return message;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
