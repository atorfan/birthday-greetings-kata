package it.xpug.kata.birthday_greetings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class XDate {

	private final Date date;

	public XDate() {
		this.date = new Date();
	}

	public XDate(final String yyyyMMdd) throws ParseException {
		this.date = new SimpleDateFormat("yyyy/MM/dd").parse(yyyyMMdd);
	}

	public int getDay() {
		return this.getPartOfDate(GregorianCalendar.DAY_OF_MONTH);
	}

	public int getMonth() {
		return 1 + this.getPartOfDate(GregorianCalendar.MONTH);
	}

	public boolean isSameDay(final XDate anotherDate) {
		return anotherDate.getDay() == this.getDay() && anotherDate.getMonth() == this.getMonth();
	}

	@Override
	public int hashCode() {
		return this.date.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof XDate)) {
			return false;
		}
		final XDate other = (XDate) obj;
		return other.date.equals(this.date);
	}

	private int getPartOfDate(final int part) {
		final GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(this.date);
		return calendar.get(part);
	}
}
