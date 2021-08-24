package it.xpug.kata.birthday_greetings;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class XDateTest {

	@Test
	public void getters() throws Exception {
		final XDate date = new XDate("1789/01/24");
		assertEquals(1, date.getMonth());
		assertEquals(24, date.getDay());
	}

	@Test
	public void isSameDate() throws Exception {
		final XDate date = new XDate("1789/01/24");
		final XDate sameDay = new XDate("2001/01/24");
		final XDate notSameDay = new XDate("1789/01/25");
		final XDate notSameMonth = new XDate("1789/02/25");

		assertTrue("same", date.isSameDay(sameDay));
		assertFalse("not same day", date.isSameDay(notSameDay));
		assertFalse("not same month", date.isSameDay(notSameMonth));
	}

	@Test
	public void equality() throws Exception {
		final XDate base = new XDate("2000/01/02");
		final XDate same = new XDate("2000/01/02");
		final XDate different = new XDate("2000/01/04");

		assertFalse(base.equals(null));
		assertFalse(base.equals(""));
		assertTrue(base.equals(base));
		assertTrue(base.equals(same));
		assertFalse(base.equals(different));
	}
}
