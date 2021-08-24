package it.xpug.kata.birthday_greetings;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmployeeTest {

	@Test
	public void testBirthday() throws Exception {
		final Employee employee = new Employee("foo", "bar", "1990/01/31", "a@b.c");
		assertFalse("not his birthday", employee.isBirthday(new XDate("2008/01/30")));
		assertTrue("his birthday", employee.isBirthday(new XDate("2008/01/31")));
	}

	@Test
	public void equality() throws Exception {
		final Employee base = new Employee("First", "Last", "1999/09/01", "first@last.com");
		final Employee same = new Employee("First", "Last", "1999/09/01", "first@last.com");
		final Employee different = new Employee("First", "Last", "1999/09/01", "boom@boom.com");

		assertFalse(base.equals(null));
		assertFalse(base.equals(""));
		assertTrue(base.equals(same));
		assertFalse(base.equals(different));
	}
}
