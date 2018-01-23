package de.nist.timing.domain.services;

import com.google.inject.AbstractModule;

public class ServicesModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(PunchService.class).to(PunchServiceImpl.class);
		bind(ReportService.class).to(ReportServiceImpl.class);
	}
}
