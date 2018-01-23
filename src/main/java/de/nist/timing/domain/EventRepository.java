package de.nist.timing.domain;

import java.util.List;

public interface EventRepository {
	void Store(Entry entry);
	List<EntryImpl> Load();
}
