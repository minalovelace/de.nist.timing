package de.nist.timing.domain;

public interface IEntryRepository {

	public void createEntry(WorkingDayEntry entry);
	public void loadEntry(String id);
	public void deleteEntry(WorkingDayEntry entry);
}
