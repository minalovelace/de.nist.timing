package de.nist.timing.test.services;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Inject;

import de.nist.timing.domain.services.PunchService;
import de.nist.timing.domain.services.ReportService;
import de.nist.timing.runtime.TimingInjectorBuilder;

public class EntriesAndEvaluation {

	@Inject
	private ReportService reportService;

	@Inject
	private PunchService punchService;
	
	@Before
	public void init()
	{
		TimingInjectorBuilder.CreateInjector(LocalDate.of(2017, 06, 05), 40).injectMembers(this);
	}

	@Test
	public void bookInAndOutAndEvaluate() {
		/* Add new booking entry to arrive and to leave */
		punchService.punchIn(LocalDate.of(2018, 06, 05), LocalTime.of(8, 00));
		punchService.punchOut(LocalDate.of(2018, 06, 05), LocalTime.of(16, 45));

		/* Read the current time balance */
		assertEquals(Duration.ofMinutes(45), reportService.balance());
	}

}
