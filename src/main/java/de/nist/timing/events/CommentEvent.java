package de.nist.timing.events;

import de.nist.timing.domain.Calendar;

public class CommentEvent extends Event {

    public CommentEvent(Metadata metadata, Integer year, Integer month, Integer day, String comment) {
        super(metadata);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Calendar apply(Calendar calendar) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void prepareSerialization(EventVisitor visitor) {
        // TODO Auto-generated method stub

    }

}
