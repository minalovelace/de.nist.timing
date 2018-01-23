package de.nist.timing.domain.services;

import java.time.LocalDate;
import java.time.LocalTime;

public interface PunchService {
	
	public void punchIn(LocalDate date, LocalTime inTime);
	public void punchOut(LocalDate date, LocalTime outTime);
}
