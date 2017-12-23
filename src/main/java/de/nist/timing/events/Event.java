package de.nist.timing.events;

import de.nist.timing.domain.Calendar;

/*
 * This is a base class for every event.
 */
public abstract class Event {
	// The Metadata may contain stuff like creation of the event, username or ip, and
	// other eventually useful stuff.
	public Metadata getMetadata() {
		// TODO nina implement
		return null;
	}

	// Applies the event to the given calendar.
	public abstract Boolean apply(Calendar calendar);
}