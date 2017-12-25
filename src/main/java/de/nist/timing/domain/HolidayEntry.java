package de.nist.timing.domain;

public class HolidayEntry extends Entry {

    private final String comment;

    public HolidayEntry(Integer year, Integer month, Integer day, String comment) {
        super(year,month,day);
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
    public Integer getPause() {
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
    public Integer getPlannedWorkingTime() {
        return null;
    }
}