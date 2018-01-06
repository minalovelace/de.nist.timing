package de.nist.timing.commands;

import static de.nist.timing.events.EventType.BUSINESSTRIP;

import de.nist.timing.events.BusinessTripEvent;
import de.nist.timing.events.Event;
import de.nist.timing.events.EventType;
import de.nist.timing.events.Metadata;

public class BusinesstripCommand extends AbsCommand {

    private final Metadata metadata;
    private final Integer year;
    private final Integer month;
    private final Integer day;
    private final String comment;

    public BusinesstripCommand(Metadata metadata, Integer year, Integer month, Integer day, String comment) {
        this.metadata = metadata;
        this.year = year;
        this.month = month;
        this.day = day;
        this.comment = comment;
    }

    @Override
    public Metadata getMetadata() {
        return this.metadata;
    }

    @Override
    public EventType getEventType() {
        return BUSINESSTRIP;
    }

    @Override
    public Event createEvent() {
        return new BusinessTripEvent(this.metadata, this.year, this.month, this.day, this.comment);
    }
}
