package de.nist.timing.domain.services;

import java.time.LocalDate;
import java.time.LocalTime;

import com.google.inject.Inject;

import de.nist.timing.domain.EventRepository;

public class PunchServiceImpl implements PunchService {

	private final EventRepository repo;

	@Inject
	PunchServiceImpl(EventRepository repo)
	{
		this.repo = repo;
	}
	
	@Override
	public void punchIn(LocalDate date, LocalTime inTime) {
		/* TODO get the event factory */
		throw new RuntimeException("not yet implemented");		
	}

	@Override
	public void punchOut(LocalDate date, LocalTime outTime) {
		throw new RuntimeException("not yet implemented");		
	}

}
