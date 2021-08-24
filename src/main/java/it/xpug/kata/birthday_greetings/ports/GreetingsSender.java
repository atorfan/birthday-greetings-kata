package it.xpug.kata.birthday_greetings.ports;

import it.xpug.kata.birthday_greetings.domain.Employee;
import it.xpug.kata.birthday_greetings.domain.XDate;

import java.util.List;

public final class GreetingsSender {

	private final EmployeeRepository employeeRepository;
	private final EmployeeGreetingsSender employeeGreetingsSender;

	public GreetingsSender(final EmployeeRepository employeeRepository, final EmployeeGreetingsSender employeeGreetingsSender) {
		this.employeeRepository = employeeRepository;
		this.employeeGreetingsSender = employeeGreetingsSender;
	}

	public void sendGreetings(final XDate xDate) {
		final List<Employee> employeeList = this.employeeRepository.findEmployeesWithBirthDate(xDate);
		for (final Employee employee : employeeList) {
			this.employeeGreetingsSender.sendGreetings(employee);
		}
	}
}
