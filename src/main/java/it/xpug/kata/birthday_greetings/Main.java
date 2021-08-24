package it.xpug.kata.birthday_greetings;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;

public class Main {

	public static void main(final String[] args) throws IOException, ParseException, MessagingException {
		final BirthdayService service = new BirthdayService();
		service.sendGreetings("employee_data.txt", new XDate(), "localhost", 25);
	}

}
