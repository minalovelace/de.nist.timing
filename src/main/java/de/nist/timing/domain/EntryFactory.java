package de.nist.timing.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public final class EntryFactory {
	
	public Entry CreateEntry(LocalDate date, LocalTime time, EntryKind kind)
	{
		return new EntryImpl(date,time,kind);
	}
}
