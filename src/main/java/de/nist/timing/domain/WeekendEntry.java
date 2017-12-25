package de.nist.timing.domain;

public class WeekendEntry implements IEntry {

    private final Integer dayOfYear;

    public WeekendEntry(Integer dayOfYear) {
        this.dayOfYear = dayOfYear;
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
    public Boolean isCommentSet() {
        return false;
    }

    @Override
    public String getComment() {
        return null;
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