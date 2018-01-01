package de.nist.timing.commands;

import de.nist.timing.events.Event;
import de.nist.timing.events.EventType;
import de.nist.timing.events.Metadata;

public class CommentCommand implements ICommand {

    public CommentCommand(Metadata metadataComment, Integer expectedYear, Integer expectedMonth, Integer expectedDay,
            String expectedComment) {
        // TODO Auto-generated constructor stub
    }

    @Override
    public EventType getEventType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Event createEvent() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Metadata getMetadata() {
        // TODO Auto-generated method stub
        return null;
    }

}
