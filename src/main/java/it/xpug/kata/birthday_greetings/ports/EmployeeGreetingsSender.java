package it.xpug.kata.birthday_greetings.ports;

import it.xpug.kata.birthday_greetings.domain.Employee;

public interface EmployeeGreetingsSender {

	void sendGreetings(Employee employee);
}
