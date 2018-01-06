package de.nist.timing.commands;

import de.nist.timing.events.Event;
import de.nist.timing.events.EventType;
import de.nist.timing.events.Metadata;
import de.nist.timing.repositories.EventRepository;

/*
 * Base class for every command.
 */
public abstract class AbsCommand {

	public abstract EventType getEventType();

	public abstract Event createEvent();

	public abstract Metadata getMetadata();

	/*
	 * Executes the given command. The command creates an event, which will be saved
	 * in the event repository.
	 */
	public Boolean execute() {
		Event event = this.createEvent();
		return new EventRepository().write(event);
	}
}
