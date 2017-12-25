package de.nist.timing.repositories;

import java.util.Collections;
import java.util.List;

import de.nist.timing.events.Event;

/*
 * This class is responsible for saving and reading the events. 
 */
public class EventRepository {

    public EventRepository() {
    }

    public List<Event> read() {
        return Collections.emptyList();
    }

    public boolean write(Event event) {
        return false;
    }
}