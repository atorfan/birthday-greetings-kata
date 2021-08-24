package it.xpug.kata.birthday_greetings.adapters;

import it.xpug.kata.birthday_greetings.domain.Employee;
import it.xpug.kata.birthday_greetings.domain.XDate;
import it.xpug.kata.birthday_greetings.ports.EmployeeRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public final class EmployeeCsvFile implements EmployeeRepository {

	private static final String DATE_PATTERN = "yyyy/MM/dd";

	private final String fileName;

	public EmployeeCsvFile(final String fileName) {
		this.fileName = fileName;
	}

	@Override
	public List<Employee> findEmployeesWithBirthDate(final XDate birthDate) {
		final BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(this.fileName));
		} catch (final FileNotFoundException e) {
			throw new RuntimeException("Cannot read file " + this.fileName, e);
		}
		final List<Employee> employeeList = new ArrayList<>();
		String str = null;
		try {
			in.readLine();
			while ((str = in.readLine()) != null) {
				final String[] employeeData = str.split(", ");
				final Employee employee = this.parseEmployee(employeeData);
				if (employee.isBirthday(birthDate)) {
					employeeList.add(employee);
				}
			}
		} catch (final IOException | ParseException e) {
			throw new RuntimeException("Cannot read and parse the line\n\"" + str + "\"\nfrom the file " + this.fileName, e);
		}
		return employeeList;
	}

	private Employee parseEmployee(final String[] employeeData) throws ParseException {
		final String firstName = employeeData[1];
		final String lastName = employeeData[0];
		final XDate birthDate = XDate.fromPrimitive(EmployeeCsvFile.DATE_PATTERN, employeeData[2]);
		final String email = employeeData[3];
		return new Employee(firstName, lastName, birthDate, email);
	}
}
