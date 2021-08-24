package it.xpug.kata.birthday_greetings;

import java.text.ParseException;

public class Employee {

	private final XDate birthDate;
	private final String lastName;
	private final String firstName;
	private final String email;

	public Employee(final String firstName, final String lastName, final String birthDate, final String email) throws ParseException {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = new XDate(birthDate);
		this.email = email;
	}

	public boolean isBirthday(final XDate today) {
		return today.isSameDay(this.birthDate);
	}

	public String getEmail() {
		return this.email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	@Override
	public String toString() {
		return "Employee " + this.firstName + " " + this.lastName + " <" + this.email + "> born " + this.birthDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.birthDate == null) ? 0 : this.birthDate.hashCode());
		result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
		result = prime * result
				+ ((this.firstName == null) ? 0 : this.firstName.hashCode());
		result = prime * result
				+ ((this.lastName == null) ? 0 : this.lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Employee)) {
			return false;
		}
		final Employee other = (Employee) obj;
		if (this.birthDate == null) {
			if (other.birthDate != null) {
				return false;
			}
		} else if (!this.birthDate.equals(other.birthDate)) {
			return false;
		}
		if (this.email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!this.email.equals(other.email)) {
			return false;
		}
		if (this.firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!this.firstName.equals(other.firstName)) {
			return false;
		}
		if (this.lastName == null) {
			return other.lastName == null;
		} else {
			return this.lastName.equals(other.lastName);
		}
	}

}
