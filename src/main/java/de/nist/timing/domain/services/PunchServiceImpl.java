package de.nist.timing.domain.services;

import java.time.LocalDate;
import java.time.LocalTime;

public class PunchServiceImpl implements PunchService {

	PunchServiceImpl()
	{
		
	}
	
	@Override
	public void punchIn(LocalDate date, LocalTime inTime) {
		throw new RuntimeException("not yet implemented");		
	}

	@Override
	public void punchOut(LocalDate date, LocalTime outTime) {
		throw new RuntimeException("not yet implemented");		
	}

}
