package de.nist.timing.domain;

import java.util.Optional;
import java.util.Set;

/**
 * The evaluated Calendar, listing all entries and giving a current summary of
 * the year.
 */
public final class Calendar {
    // The year of the calendar.
    private Integer year;
    // The time in minutes set by the user to transfer minutes of last year to this
    // year.
    private Integer delta;
    // Maps from day of year to domain specific date.
    private Set<Entry> entries;

    public Calendar(Integer year, Integer delta, Set<Entry> entries) {
        this.putEntries(entries);
        this.setYear(year);
        this.setDelta(delta);
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Set<Entry> getEntries() {
        return this.entries;
    }

    public void putEntries(Set<Entry> entries) {
        this.entries = entries;
    }

    public Entry getEntry(Integer dayOfYear) {
        Optional<Entry> findAnyEntry = this.entries.stream().filter(entry -> entry.getDayOfYear() == dayOfYear)
                .findAny();
        if (!findAnyEntry.isPresent())
            return null;

        return findAnyEntry.get();
    }

    public void putEntry(Integer dayOfYear, Entry entry) {
        this.entries.remove(entry);
        this.entries.add(entry);
    }

    public Entry popEntry(Integer dayOfYear) {
        Entry entry = getEntry(dayOfYear);
        this.entries.remove(entry);
        return entry;
    }

    public int getDelta() {
        return this.delta;
    }

    public void setDelta(Integer delta) {
        this.delta = delta;
    }
}