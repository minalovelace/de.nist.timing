package de.nist.timing.domain;

import java.util.TreeMap;

/**
 * The evaluated Calendar, listing all entries and giving a current summary of
 * the year.
 */
public final class Calendar {
	// Maps from day of year to domain specific date.
	private TreeMap<Integer, IEntry> entries;
	// The year of the calendar.
	private Integer year;
	// The time in minutes set by the user to transfer minutes of last year to this
	// year.
	private Integer delta;

	public Calendar(TreeMap<Integer, IEntry> entries, Integer year, Integer delta) {
		this.putEntries(entries);
		this.setYear(year);
		this.setDelta(delta);
	}

	public int getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public TreeMap<Integer, IEntry> getEntries() {
		return entries;
	}

	public void putEntries(TreeMap<Integer, IEntry> entries) {
		this.entries = entries;
	}

	public TreeMap<Integer, IEntry> getEntry() {
		return entries;
	}

	public void putEntry(Integer dayOfYear, IEntry entry) {
		this.entries.put(dayOfYear, entry);
	}

	public int getDelta() {
		return delta;
	}

	public void setDelta(Integer delta) {
		this.delta = delta;
	}
}