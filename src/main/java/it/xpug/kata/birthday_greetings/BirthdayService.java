package it.xpug.kata.birthday_greetings;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

public class BirthdayService {

	public void sendGreetings(final String fileName, final XDate xDate, final String smtpHost, final int smtpPort) throws IOException, ParseException, MessagingException {
		final BufferedReader in = new BufferedReader(new FileReader(fileName));
		String str = "";
		str = in.readLine(); // skip header
		while ((str = in.readLine()) != null) {
			final String[] employeeData = str.split(", ");
			final Employee employee = new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);
			if (employee.isBirthday(xDate)) {
				final String recipient = employee.getEmail();
				final String body = "Happy Birthday, dear %NAME%".replace("%NAME%", employee.getFirstName());
				final String subject = "Happy Birthday!";
				this.sendMessage(smtpHost, smtpPort, "sender@here.com", subject, body, recipient);
			}
		}
	}

	private void sendMessage(final String smtpHost, final int smtpPort, final String sender, final String subject, final String body, final String recipient) throws MessagingException {
		// Create a mail session
		final java.util.Properties props = new java.util.Properties();
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.port", "" + smtpPort);
		final Session session = Session.getInstance(props, null);

		// Construct the message
		final Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(sender));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		msg.setSubject(subject);
		msg.setText(body);

		// Send the message
		Transport.send(msg);
	}
}
