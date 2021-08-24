package it.xpug.kata.birthday_greetings;

import it.xpug.kata.birthday_greetings.domain.Employee;
import it.xpug.kata.birthday_greetings.domain.XDate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class EmployeeTest {

	private static final String DATE_PATTERN = "yyyy/MM/dd";

	@Test
	public void testBirthday() throws Exception {
		final Employee employee = new Employee("foo", "bar", XDate.fromPrimitive(EmployeeTest.DATE_PATTERN, "1990/01/31"), "a@b.c");
		assertFalse("not his birthday", employee.isBirthday(XDate.fromPrimitive(EmployeeTest.DATE_PATTERN, "2008/01/30")));
		assertTrue("his birthday", employee.isBirthday(XDate.fromPrimitive(EmployeeTest.DATE_PATTERN, "2008/01/31")));
	}

	@Test
	public void equality() throws Exception {
		final Employee base = new Employee("First", "Last", XDate.fromPrimitive(EmployeeTest.DATE_PATTERN, "1999/09/01"), "first@last.com");
		final Employee same = new Employee("First", "Last", XDate.fromPrimitive(EmployeeTest.DATE_PATTERN, "1999/09/01"), "first@last.com");
		final Employee different = new Employee("First", "Last", XDate.fromPrimitive(EmployeeTest.DATE_PATTERN, "1999/09/01"), "boom@boom.com");

		assertNotEquals(null, base);
		assertEquals(base, same);
		assertNotEquals(base, different);
	}
}
