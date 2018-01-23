package de.nist.timing.domain.services;

import java.time.Duration;

public interface ReportService {

	Duration balance();

	// TODO Use DI
	static ReportService Create() {
		return new ReportServiceImpl();
	}

}