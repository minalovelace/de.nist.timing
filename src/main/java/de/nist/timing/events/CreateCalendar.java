package de.nist.timing.events;

import java.util.TreeMap;

import de.nist.timing.domain.Calendar;
import de.nist.timing.domain.Entry;
import de.nist.timing.domain.FederalStates;

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
        TreeMap<Integer, Entry> entries = createHolidaysAndWeekends();
        return new Calendar(entries, this.year, this.delta);
    }

    private TreeMap<Integer, Entry> createHolidaysAndWeekends() {
        // TODO Auto-generated method stub
        return null;
    }
}