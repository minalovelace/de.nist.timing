package de.nist.timing.domain;

import java.time.LocalDate;

public class AccountImpl implements Account {
	AccountImpl(int hoursPerWeek, LocalDate startDate) {
		this.hoursPerWeek = hoursPerWeek;
		this.startDate = startDate;
	}

	private final int hoursPerWeek;
	private final LocalDate startDate;
}
