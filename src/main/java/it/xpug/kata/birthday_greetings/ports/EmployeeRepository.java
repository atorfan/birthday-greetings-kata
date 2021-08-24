package it.xpug.kata.birthday_greetings.ports;

import it.xpug.kata.birthday_greetings.domain.Employee;
import it.xpug.kata.birthday_greetings.domain.XDate;

import java.util.List;

public interface EmployeeRepository {

	List<Employee> findEmployeesWithBirthDate(XDate xdate);
}
