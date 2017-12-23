package de.nist.timing.domain;

import com.google.common.base.Strings;

public class BusinesstripEntry implements IEntry {
	private final Integer dayOfYear;
	private final String comment;

	public BusinesstripEntry(Integer dayOfYear) {
		this.dayOfYear = dayOfYear;
		this.comment = "";
	}

	public BusinesstripEntry(Integer dayOfYear, String comment) {
		this.dayOfYear = dayOfYear;
		this.comment = comment;
	}

	@Override
	public EntryType getEntryType() {
		return EntryType.BUSINESSTRIP;
	}

	@Override
	public Time getBegin() {
		return null;
	}

	@Override
	public Time getEnd() {
		return null;
	}

	@Override
	public Boolean isCommentSet() {
		return !Strings.isNullOrEmpty(this.comment);
	}

	@Override
	public String getComment() {
		return this.comment;
	}

	@Override
	public Integer getDayOfYear() {
		return this.dayOfYear;
	}

	@Override
	public Integer getPause() {
		return null;
	}

	@Override
	public Integer getPlannedWorkingTime() {
		return null;
	}
}