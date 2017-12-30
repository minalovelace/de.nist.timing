package de.nist.timing.events;

import static de.nist.timing.events.EventType.BUSINESSTRIP;

import de.nist.timing.domain.BusinesstripEntry;
import de.nist.timing.domain.Calendar;
import de.nist.timing.domain.Entry;

public class BusinessTripEvent extends Event {
    private final String comment;
    private final Integer year;
    private final Integer month;
    private final Integer day;

    /*
     * Use this constructor to create a new BusinessDayEvent with a new comment.
     */
    public BusinessTripEvent(Integer year, Integer month, Integer day, String comment) {
        super(new Metadata());
        this.year = year;
        this.month = month;
        this.day = day;
        this.comment = comment;
    }

    /*
     * Use this constructor to create an instance for a given BusinessDayEvent.
     */
    public BusinessTripEvent(Metadata metadata, Integer year, Integer month, Integer day, String comment) {
        super(metadata);
        this.year = year;
        this.month = month;
        this.day = day;
        this.comment = comment;
    }

    @Override
    public Calendar apply(Calendar calendar) {
        BusinesstripEntry entry = new BusinesstripEntry(this.year, this.month, this.day, this.comment);
        Integer dayOfYear = entry.getDayOfYear();

        Entry previousEntry = calendar.popEntry(dayOfYear);
        if (previousEntry != null && previousEntry.isCommentSet()) {
            String comment = previousEntry.getComment();
            entry = new BusinesstripEntry(this.year, this.month, this.day, comment);
            calendar.putEntry(dayOfYear, entry);
            return calendar;
        }

        calendar.putEntry(dayOfYear, entry);
        return calendar;
    }

    @Override
    public void prepareSerialization(EventVisitor visitor) {
        visitor.setEventType(BUSINESSTRIP);
        visitor.setMetadata(getMetadata());
        visitor.setField("year", this.year.toString());
        visitor.setField("month", this.month.toString());
        visitor.setField("day", this.day.toString());
        visitor.setField("comment", this.comment.toString());
    }
}