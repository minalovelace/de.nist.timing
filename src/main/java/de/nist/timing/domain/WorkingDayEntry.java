package de.nist.timing.domain;

import com.google.common.base.Strings;

import de.nist.timing.settings.UserSettings;

public class WorkingDayEntry extends Entry {
    private final Time begin;
    private final Time end;
    private final Integer pause;
    private final String comment;
    private final Integer plannedWorkingTime;

    public WorkingDayEntry(Integer year, Integer month, Integer day, Time begin, Time end, Integer pause,
            String comment, Integer plannedWorkingTime) {
        super(year, month, day);
        this.begin = begin;
        this.end = end;
        this.pause = pause;
        this.comment = comment;
        this.plannedWorkingTime = plannedWorkingTime;
    }

    public WorkingDayEntry(Integer year, Integer month, Integer day, Time begin, Time end, Integer pause,
            String comment) {
        this(year, month, day, begin, end, pause, comment, UserSettings.PLANNED_WORKING_TIME);
    }

    public WorkingDayEntry(Integer year, Integer month, Integer day, Time begin, Time end, Integer pause) {
        this(year, month, day, begin, end, pause, "", UserSettings.PLANNED_WORKING_TIME);
    }

    @Override
    public EntryType getEntryType() {
        return EntryType.WORKINGDAY;
    }

    @Override
    public Time getBegin() {
        return this.begin;
    }

    @Override
    public Time getEnd() {
        return this.end;
    }

    @Override
    public Boolean isCommentSet() {
        return !Strings.isNullOrEmpty(this.comment);
    }

    @Override
    public String getComment() {
        return this.comment;
    }

    @Override
    public Integer getPause() {
        return this.pause;
    }

    @Override
    public Integer getPlannedWorkingTime() {
        return this.plannedWorkingTime;
    }
}
