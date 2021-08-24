package it.xpug.kata.birthday_greetings.adapters;

import it.xpug.kata.birthday_greetings.domain.Employee;
import it.xpug.kata.birthday_greetings.domain.XDate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public final class CsvFileSystemReader implements EmployeeReader {

	private static final String DATE_PATTERN = "yyyy/MM/dd";

	private final String fileName;

	public CsvFileSystemReader(final String fileName) {
		this.fileName = fileName;
	}

	@Override
	public Stream<Employee> read() {
		try {
			return Files.lines(Paths.get(this.fileName))
					.filter(CsvFileSystemReader::rowWithData)
					.filter(CsvFileSystemReader::employeeRow)
					.map(this::parseEmployee)
					//					.peek(employee -> System.out.println("Employee from CSV: " + employee))
					;
		} catch (final IOException e) {
			throw new RuntimeException("Cannot read file " + this.fileName, e);
		}
	}

	private static boolean rowWithData(final String s) {
		return !s.trim().isEmpty();
	}

	private static boolean employeeRow(final String s) {
		return !s.contains("date_of_birth");
	}

	private Employee parseEmployee(final String employeeDataLine) {
		final String[] employeeData = employeeDataLine.split(",");
		final String firstName = employeeData[1].trim();
		final String lastName = employeeData[0].trim();
		final XDate birthDate = XDate.fromPrimitive(CsvFileSystemReader.DATE_PATTERN, employeeData[2].trim());
		final String email = employeeData[3].trim();
		return new Employee(firstName, lastName, birthDate, email);
	}
}
