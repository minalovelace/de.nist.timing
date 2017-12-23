package de.nist.timing.events;

import com.google.common.base.Strings;

import de.nist.timing.domain.BusinesstripEntry;
import de.nist.timing.domain.Calendar;
import de.nist.timing.domain.IEntry;

public class BusinessTripEvent extends Event {
	private final Integer dayOfYear;
	private final String comment;

	/*
	 * Use this constructor to create a new BusinessDayEvent with a new comment.
	 */
	public BusinessTripEvent(Integer dayOfYear, String comment) {
		super(new Metadata());
		this.dayOfYear = dayOfYear;
		this.comment = comment;
	}

	/*
	 * Use this constructor to create an instance for a given BusinessDayEvent.
	 */
	public BusinessTripEvent(Metadata metadata, Integer dayOfYear, String comment) {
		super(metadata);
		this.dayOfYear = dayOfYear;
		this.comment = comment;
	}

	@Override
	public Boolean apply(Calendar calendar) {
		if (!Strings.isNullOrEmpty(this.comment)) {
			BusinesstripEntry entry = new BusinesstripEntry(this.dayOfYear, this.comment);
			calendar.putEntry(this.dayOfYear, entry);
			return true;
		}

		IEntry previousEntry = calendar.popEntry(this.dayOfYear);
		if (previousEntry != null && previousEntry.isCommentSet()) {
			String comment = previousEntry.getComment();
			BusinesstripEntry entry = new BusinesstripEntry(this.dayOfYear, comment);
			calendar.putEntry(this.dayOfYear, entry);
			return true;
		}

		BusinesstripEntry entry = new BusinesstripEntry(this.dayOfYear);
		calendar.putEntry(this.dayOfYear, entry);
		return true;
	}
}