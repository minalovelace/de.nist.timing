package de.nist.timing.domain;

import java.util.TreeMap;

/**
 * The evaluated Calendar, listing all entries and giving a current summary of
 * the year.
 */
public final class Calendar {
    // Maps from day of year to domain specific date.
    private TreeMap<Integer, Entry> entries;
    // The year of the calendar.
    private Integer year;
    // The time in minutes set by the user to transfer minutes of last year to this
    // year.
    private Integer delta;

    public Calendar(Integer year, Integer delta, TreeMap<Integer, Entry> entries) {
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

    public TreeMap<Integer, Entry> getEntries() {
        return entries;
    }

    public void putEntries(TreeMap<Integer, Entry> entries) {
        this.entries = entries;
    }

    public TreeMap<Integer, Entry> getEntry() {
        return entries;
    }

    public void putEntry(Integer dayOfYear, Entry entry) {
        this.entries.put(dayOfYear, entry);
    }

    public Entry popEntry(Integer dayOfYear) {
        return this.entries.remove(dayOfYear);
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(Integer delta) {
        this.delta = delta;
    }
}