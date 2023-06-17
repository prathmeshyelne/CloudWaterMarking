/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

 
public class Mail {
    public boolean sendMail(String msg,String emailid,String sub)
    {
        final String username = "test@codegyan.in";
        final String password = "AYxErmCjO_k]";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "mail.codegyan.in");
		props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try 
                {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(emailid));
			message.setSubject(sub);
			message.setText(msg);
 
			Transport.send(message);
 
			System.out.println("Done");
                        return true;
 
		} catch (MessagingException e) {
                    
			throw new RuntimeException(e);
                        
		}
 
    }
    
}
