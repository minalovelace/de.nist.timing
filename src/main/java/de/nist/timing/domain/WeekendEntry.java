package de.nist.timing.domain;

import com.google.common.base.Strings;

public class WeekendEntry extends Entry {

    private final String comment;

    public WeekendEntry(Integer year, Integer month, Integer day) {
        super(year, month, day);
        this.comment = "";
    }

    public WeekendEntry(Integer year, Integer month, Integer day, String comment) {
        super(year, month, day);
        this.comment = comment;
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
        return Strings.isNullOrEmpty(this.comment);
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public Integer getPlannedWorkingTime() {
        return null;
    }
}
