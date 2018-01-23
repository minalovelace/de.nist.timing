package de.nist.timing.test.services;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import de.nist.timing.domain.Account;
import de.nist.timing.domain.AccountFactory;
import de.nist.timing.domain.services.PunchService;
import de.nist.timing.domain.services.ReportService;

public class EntriesAndEvaluation {

	@Test
	public void bookInAndOutAndEvaluate()
	{
		/* Prepare a context with 8 work hours per day, a start date */
		Account account = AccountFactory.CreateAccount(40,LocalDate.of(2017, 06, 05));
		PunchService punchService = PunchService.Create();
		
		/* Add new booking entry to arrive and to leave */
		punchService.punchIn(LocalDate.of(2018, 06, 05), LocalTime.of(8, 00));		
		punchService.punchOut(LocalDate.of(2018, 06, 05), LocalTime.of(16, 45));
		
		/* Read the current time balance */
		ReportService reportService = ReportService.Create();
		assertEquals(Duration.ofMinutes(45), reportService.balance());
	}
	
}
