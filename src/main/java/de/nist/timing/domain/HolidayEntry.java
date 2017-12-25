package de.nist.timing.domain;

public class HolidayEntry implements IEntry {

    private final Integer dayOfYear;
    private final String comment;

    public HolidayEntry(Integer dayOfYear, String comment) {
        this.dayOfYear = dayOfYear;
        this.comment = comment;
    }

    @Override
    public EntryType getEntryType() {
        return EntryType.HOLIDAY;
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
        return true;
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