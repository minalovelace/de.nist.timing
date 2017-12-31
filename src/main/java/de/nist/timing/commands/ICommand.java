package de.nist.timing.commands;

import de.nist.timing.events.Event;
import de.nist.timing.events.EventType;
import de.nist.timing.events.Metadata;

/*
 * Base interface for every command.
 */
public interface ICommand {

    public EventType getEventType();

    public Event createEvent();

    public Metadata getMetadata();
}
