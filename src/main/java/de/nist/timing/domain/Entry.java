package de.nist.timing.domain;

import java.util.GregorianCalendar;

/*
 * An entry in the calendar
 */
public abstract class Entry {
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
}