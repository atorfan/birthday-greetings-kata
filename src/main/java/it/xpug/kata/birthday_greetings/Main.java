package it.xpug.kata.birthday_greetings;

import it.xpug.kata.birthday_greetings.adapters.CsvFileSystemReader;
import it.xpug.kata.birthday_greetings.adapters.EmployeeReader;
import it.xpug.kata.birthday_greetings.adapters.EmployeeStreamReader;
import it.xpug.kata.birthday_greetings.adapters.GreetingsEmailSender;
import it.xpug.kata.birthday_greetings.domain.XDate;
import it.xpug.kata.birthday_greetings.ports.EmployeeGreetingsSender;
import it.xpug.kata.birthday_greetings.ports.EmployeeRepository;
import it.xpug.kata.birthday_greetings.ports.GreetingsSender;

public class Main {

	public static void main(final String[] args) {
		final EmployeeReader employeeReader = new CsvFileSystemReader("employee_data.txt");
		final EmployeeRepository employeeRepository = new EmployeeStreamReader(employeeReader);
		final EmployeeGreetingsSender greetingsSender = new GreetingsEmailSender("localhost", 25);
		final GreetingsSender service = new GreetingsSender(employeeRepository, greetingsSender);
		service.sendGreetings(XDate.current());
	}

}
