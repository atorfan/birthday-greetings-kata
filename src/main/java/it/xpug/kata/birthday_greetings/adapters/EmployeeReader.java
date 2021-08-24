package it.xpug.kata.birthday_greetings.adapters;

import it.xpug.kata.birthday_greetings.domain.Employee;

import java.util.stream.Stream;

public interface EmployeeReader {

	Stream<Employee> read();
}
