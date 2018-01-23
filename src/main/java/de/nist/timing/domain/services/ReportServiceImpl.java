package de.nist.timing.domain.services;

import java.time.Duration;

import com.google.inject.Inject;

import de.nist.timing.domain.EventRepository;

public class ReportServiceImpl implements ReportService {

	private final EventRepository repo;
	
	@Inject	
	public ReportServiceImpl(final EventRepository repo) {
		this.repo = repo;
	}



	@Override
	public Duration balance() {
		throw new RuntimeException("not yet implemented");
	}
}
