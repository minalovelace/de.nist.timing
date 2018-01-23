package de.nist.timing.domain;

import java.time.LocalDate;

import com.google.inject.AbstractModule;

public final class DomainModule extends AbstractModule {

	private final int hoursPerWeek;
	private final LocalDate startDate;

	public DomainModule(final LocalDate startDate, final int hoursPerWeek) {
		this.startDate = startDate;
		this.hoursPerWeek = hoursPerWeek;
	}

	@Override
	protected void configure() {
		bind(Account.class).toInstance(new AccountImpl(hoursPerWeek, startDate));
		bind(EventRepository.class).to(InMemoryRepositoryImpl.class);
	}

}
