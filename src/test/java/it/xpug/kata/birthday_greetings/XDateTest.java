package it.xpug.kata.birthday_greetings;

import it.xpug.kata.birthday_greetings.domain.XDate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class XDateTest {

	private static final String DATE_PATTERN = "yyyy/MM/dd";

	@Test
	public void getters() throws Exception {
		final XDate date = XDate.fromPrimitive(XDateTest.DATE_PATTERN, "1789/01/24");
		assertEquals(1, date.getMonth());
		assertEquals(24, date.getDay());
	}

	@Test
	public void isSameDate() throws Exception {
		final XDate date = XDate.fromPrimitive(XDateTest.DATE_PATTERN, "1789/01/24");
		final XDate sameDay = XDate.fromPrimitive(XDateTest.DATE_PATTERN, "2001/01/24");
		final XDate notSameDay = XDate.fromPrimitive(XDateTest.DATE_PATTERN, "1789/01/25");
		final XDate notSameMonth = XDate.fromPrimitive(XDateTest.DATE_PATTERN, "1789/02/25");

		assertTrue("same", date.isSameDay(sameDay));
		assertFalse("not same day", date.isSameDay(notSameDay));
		assertFalse("not same month", date.isSameDay(notSameMonth));
	}

	@Test
	public void equality() throws Exception {
		final XDate base = XDate.fromPrimitive(XDateTest.DATE_PATTERN, "2000/01/02");
		final XDate same = XDate.fromPrimitive(XDateTest.DATE_PATTERN, "2000/01/02");
		final XDate different = XDate.fromPrimitive(XDateTest.DATE_PATTERN, "2000/01/04");

		assertNotEquals(null, base);
		assertEquals(base, base);
		assertEquals(base, same);
		assertNotEquals(base, different);
	}
}
