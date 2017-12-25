package de.nist.timing.events;

import java.util.TreeMap;

import de.nist.timing.domain.Calendar;
import de.nist.timing.domain.FederalStates;
import de.nist.timing.domain.IEntry;

public class CreateCalendar extends Event {
    private final Integer year;
    private final Integer delta;
    private final FederalStates federalState;

    public CreateCalendar(Metadata metadata, Integer year, Integer delta, FederalStates federalState) {
        super(metadata);
        this.year = year;
        this.delta = delta;
        this.federalState = federalState;
    }

    @Override
    public Calendar apply(Calendar calendar) {
        /* The calendar should be null, because we are creating a new one now. */
        TreeMap<Integer, IEntry> entries = getHolidaysAndWeekends();
        return new Calendar(entries, this.year, this.delta);
    }

    private TreeMap<Integer, IEntry> getHolidaysAndWeekends() {
        // TODO Auto-generated method stub
        return null;
    }
}