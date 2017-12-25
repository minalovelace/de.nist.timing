package de.nist.timing.commands;

import de.nist.timing.domain.FederalStates;
import de.nist.timing.events.CreateCalendarEvent;
import de.nist.timing.events.Event;
import de.nist.timing.events.Metadata;

public class CreateCalendarCommand implements ICommand {

    private final Metadata metadata;
    private final Integer year;
    private final Integer delta;
    private final FederalStates federalState;

    public CreateCalendarCommand(Metadata metadata, Integer year, Integer delta, FederalStates federalState) {
        this.metadata = metadata;
        this.year = year;
        this.delta = delta;
        this.federalState = federalState;
    }

    @Override
    public Metadata getMetadata() {
        return this.metadata;
    }

    @Override
    public Commands getCommandName() {
        return Commands.CREATE_CALENDAR;
    }

    @Override
    public Event createEvent() {
        return new CreateCalendarEvent(this.metadata, this.year, this.delta, this.federalState);
    }

    @Override
    public Boolean isSnapshotPossible() {
        return true;
    }
}