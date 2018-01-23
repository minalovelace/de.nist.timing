package de.nist.timing.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

class EntryImpl extends Entity implements Entry {

	EntryImpl(final LocalDate date, final LocalTime time, final EntryKind kind) {
		super();
		this.date = date;
		this.time = time;
		this.kind = kind;
	}

	EntryImpl(final UUID id, final LocalDate date, final LocalTime time, final EntryKind kind) {
		super(id);
		this.date = date;
		this.time = time;
		this.kind = kind;
	}
	
	private final LocalDate date;
	private final LocalTime time;
	private final EntryKind kind;
	
	/* (non-Javadoc)
	 * @see de.nist.timing.domain.Entry#date()
	 */
	@Override
	public LocalDate date() {
		return date;
	}

	/* (non-Javadoc)
	 * @see de.nist.timing.domain.Entry#time()
	 */
	@Override
	public LocalTime time() {
		return time;
	}

	/* (non-Javadoc)
	 * @see de.nist.timing.domain.Entry#kind()
	 */
	@Override
	public EntryKind kind() {
		return kind;
	}
}
