package it.xpug.kata.birthday_greetings.adapters;

import it.xpug.kata.birthday_greetings.domain.BirthDateSelector;
import it.xpug.kata.birthday_greetings.domain.Employee;
import it.xpug.kata.birthday_greetings.domain.XDate;
import it.xpug.kata.birthday_greetings.ports.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

public final class EmployeeStreamReader implements EmployeeRepository {

	private final EmployeeReader employeeReader;

	public EmployeeStreamReader(final EmployeeReader employeeReader) {
		this.employeeReader = employeeReader;
	}

	@Override
	public List<Employee> findEmployeesWithBirthDate(final XDate birthDate) {
		return this.employeeReader
				.read()
				.filter(new BirthDateSelector(birthDate))
				//				.peek(employee -> System.out.println("Employee from StreamReader: " + employee))
				.collect(Collectors.toList());
	}
}
