package de.nist.timing.events;

import de.nist.timing.domain.Calendar;

/*
 * This is a base class for every event.
 */
public abstract class Event {
    private final Metadata metadata;

    public Event(Metadata metadata) {
        this.metadata = metadata;
    }

    /*
     * The Metadata may contain things like creation of the event, username or ip,
     * and other eventually useful stuff.
     */
    public Metadata getMetadata() {
        return this.metadata;
    }

    /* Applies the event to the given calendar. */
    public abstract Calendar apply(Calendar calendar);
    
    /* Gives the visitor every needed information to serialize the event. */
    public abstract void prepareSerialization(EventVisitor visitor);
}