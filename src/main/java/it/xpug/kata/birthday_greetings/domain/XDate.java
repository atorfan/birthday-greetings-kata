package it.xpug.kata.birthday_greetings.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class XDate {

	private final LocalDate localDate;

	public XDate(final LocalDate localDate) {
		this.localDate = localDate;
	}

	public static XDate current() {
		return new XDate(LocalDate.now());
	}

	public static XDate fromPrimitive(final String datePattern, final String datePrimitive) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
		final LocalDate localDate = LocalDate.parse(datePrimitive, formatter);
		return new XDate(localDate);
	}

	public int getDay() {
		return this.localDate.get(ChronoField.DAY_OF_MONTH);
	}

	public int getMonth() {
		return this.localDate.get(ChronoField.MONTH_OF_YEAR);
	}

	public boolean isSameDay(final XDate anotherDate) {
		return anotherDate.getDay() == this.getDay() && anotherDate.getMonth() == this.getMonth();
	}

	@Override
	public int hashCode() {
		return this.localDate.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof XDate)) {
			return false;
		}
		final XDate other = (XDate) obj;
		return other.localDate.equals(this.localDate);
	}
}
