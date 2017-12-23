package de.nist.timing.domain;

/*
 * An entry in the calendar
 */
public interface IEntry {
	public EntryType getEntryType();

	public Time getBegin();

	public Time getEnd();

	public Boolean isCommentSet();

	public String getComment();

	public Integer getDayOfYear();

	public Integer getPause();

	public Integer getPlannedWorkingTime();
}