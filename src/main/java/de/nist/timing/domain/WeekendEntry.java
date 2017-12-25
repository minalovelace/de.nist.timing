package de.nist.timing.domain;

public class WeekendEntry extends Entry {

    public WeekendEntry(Integer year, Integer month, Integer day, String comment) {
        super(year, month, day);
    }

    @Override
    public EntryType getEntryType() {
        return EntryType.WEEKEND;
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
        return false;
    }

    @Override
    public String getComment() {
        return null;
    }

    @Override
    public Integer getPlannedWorkingTime() {
        return null;
    }
}