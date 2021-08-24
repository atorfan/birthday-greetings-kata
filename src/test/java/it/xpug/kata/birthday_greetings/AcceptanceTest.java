package it.xpug.kata.birthday_greetings;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import it.xpug.kata.birthday_greetings.adapters.EmployeeCsvFile;
import it.xpug.kata.birthday_greetings.adapters.GreetingsEmailSender;
import it.xpug.kata.birthday_greetings.domain.XDate;
import it.xpug.kata.birthday_greetings.ports.EmployeeGreetingsSender;
import it.xpug.kata.birthday_greetings.ports.EmployeeRepository;
import it.xpug.kata.birthday_greetings.ports.GreetingsSender;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AcceptanceTest {

	private static final String DATE_PATTERN = "yyyy/MM/dd";
	private static final int NONSTANDARD_PORT = 9999;

	private GreetingsSender birthdayService;
	private SimpleSmtpServer mailServer;

	@Before
	public void setUp() throws Exception {
		this.mailServer = SimpleSmtpServer.start(AcceptanceTest.NONSTANDARD_PORT);
		final EmployeeRepository employeeRepository = new EmployeeCsvFile("employee_data.txt");
		final EmployeeGreetingsSender greetingsSender = new GreetingsEmailSender("localhost", AcceptanceTest.NONSTANDARD_PORT);
		this.birthdayService = new GreetingsSender(employeeRepository, greetingsSender);
	}

	@After
	public void tearDown() throws Exception {
		this.mailServer.stop();
		Thread.sleep(200);
	}

	@Test
	public void willSendGreetings_whenItsSomebodysBirthday() throws Exception {

		this.birthdayService.sendGreetings(XDate.fromPrimitive(AcceptanceTest.DATE_PATTERN, "2008/10/08"));

		assertEquals("message not sent?", 1, this.mailServer.getReceivedEmailSize());
		final SmtpMessage message = (SmtpMessage) this.mailServer.getReceivedEmail().next();
		assertEquals("Happy Birthday, dear John!", message.getBody());
		assertEquals("Happy Birthday!", message.getHeaderValue("Subject"));
		final String[] recipients = message.getHeaderValues("To");
		assertEquals(1, recipients.length);
		assertEquals("john.doe@foobar.com", recipients[0]);
	}

	@Test
	public void willNotSendEmailsWhenNobodysBirthday() throws Exception {
		this.birthdayService.sendGreetings(XDate.fromPrimitive(AcceptanceTest.DATE_PATTERN, "2008/01/01"));

		assertEquals("what? messages?", 0, this.mailServer.getReceivedEmailSize());
	}
}
