package it.xpug.kata.birthday_greetings.adapters;

import it.xpug.kata.birthday_greetings.domain.Employee;
import it.xpug.kata.birthday_greetings.ports.EmployeeGreetingsSender;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public final class GreetingsEmailSender implements EmployeeGreetingsSender {

	private final String smtpHost;
	private final int smtpPort;

	public GreetingsEmailSender(final String smtpHost, final int smtpPort) {
		this.smtpHost = smtpHost;
		this.smtpPort = smtpPort;
	}

	@Override
	public void sendGreetings(final Employee employee) {
		final String recipient = employee.getEmail();
		final String body = "Happy Birthday, dear %NAME%!".replace("%NAME%", employee.getFirstName());
		final String subject = "Happy Birthday!";
		try {
			this.sendMessage(this.smtpHost, this.smtpPort, "sender@here.com", subject, body, recipient);
		} catch (final MessagingException e) {
			throw new RuntimeException("Cannot send a greetings email to the employee", e);
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
