package de.nist.timing.runtime;

import java.time.LocalDate;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.nist.timing.domain.DomainModule;
import de.nist.timing.domain.services.ServicesModule;

public class TimingInjectorBuilder {
	
	public static Injector CreateInjector(LocalDate startDate, int hoursPerWeek)
	{
		return Guice.createInjector(new DomainModule(startDate,hoursPerWeek),new ServicesModule());
	}
}
