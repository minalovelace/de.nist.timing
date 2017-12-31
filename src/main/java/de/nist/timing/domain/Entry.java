package de.nist.timing.domain;

import java.time.LocalDate;
import java.util.GregorianCalendar;

/*
 * An entry in the calendar
 */
public abstract class Entry implements Comparable<Entry> {
    private Integer year;
    private Integer month;
    private Integer day;
    private final GregorianCalendar gregorianCalendar;

    public Entry(Integer year, Integer month, Integer day) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.gregorianCalendar = new GregorianCalendar(this.year, this.month - 1, this.day);
    }

    public abstract EntryType getEntryType();

    public abstract Time getBegin();

    public abstract Time getEnd();

    public abstract Integer getPause();

    public abstract Boolean isCommentSet();

    public abstract String getComment();

    public abstract Integer getPlannedWorkingTime();

    public Integer getYear() {
        return this.year;
    }

    public Integer getMonth() {
        return this.month;
    }

    public Integer getDay() {
        return this.day;
    }

    public Integer getDayOfYear() {
        return this.gregorianCalendar.get(GregorianCalendar.DAY_OF_YEAR);
    }

    public Integer getWeekOfYear() {
        return this.gregorianCalendar.get(GregorianCalendar.WEEK_OF_YEAR);
    }

    public int compareTo(Entry o) {
        LocalDate thisLD = LocalDate.of(getYear(), getMonth(), getDay());
        LocalDate oLD = LocalDate.of(o.getYear(), o.getMonth(), o.getDay());
        return thisLD.compareTo(oLD);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        Integer dayOfYear = this.getDayOfYear();
        result = prime * result + ((dayOfYear == null) ? 0 : dayOfYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Entry other = (Entry) obj;
        Integer otherDayOfYear = other.getDayOfYear();
        Integer thisDayOfYear = this.getDayOfYear();
        if (thisDayOfYear == null) {
            if (otherDayOfYear != null)
                return false;
        } else if (!thisDayOfYear.equals(otherDayOfYear))
            return false;
        return true;
    }
}
