package it.xpug.kata.birthday_greetings.domain;

import java.util.function.Predicate;

public final class BirthDateSelector implements Predicate<Employee> {

	private final XDate birthDate;

	public BirthDateSelector(final XDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public boolean test(final Employee employee) {
		return employee.isBirthday(this.birthDate);
	}
}
