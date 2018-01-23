package de.nist.timing.domain;

import java.time.LocalDate;

public class AccountFactory {
	public static Account CreateAccount(int hoursPerWeek, LocalDate startDate)
	{
		return new AccountImpl(hoursPerWeek, startDate);
	}

}
