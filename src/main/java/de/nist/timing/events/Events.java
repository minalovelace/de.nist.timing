package de.nist.timing.events;

import java.util.HashMap;

import de.nist.timing.domain.FederalStates;

public class Events {
    public static Event create(EventType eventType, Metadata metadata, HashMap<String, String> fields) {
        switch (eventType) {
        case BUSINESSTRIP: {
            if (fields == null || fields.size() < 4)
                throw new IllegalArgumentException(
                        "Cannot create a new BusinesstripEvent if the necessary information is not provided.");

            Integer year = Integer.decode(fields.get("year"));
            Integer month = Integer.decode(fields.get("month"));
            Integer day = Integer.decode(fields.get("day"));
            String comment = fields.get("comment");
            return new BusinessTripEvent(metadata, year, month, day, comment);
        }
        case CHANGE_SETTINGS:
            throw new UnsupportedOperationException("Not yet implemented.");
        case CLOCK_IN:
            throw new UnsupportedOperationException("Not yet implemented.");
        case CLOCK_OUT:
            throw new UnsupportedOperationException("Not yet implemented.");
        case COMMENT: {
            if (fields == null || fields.size() < 4)
                throw new IllegalArgumentException(
                        "Cannot create a new CommentEvent if the necessary information is not provided.");

            Integer year = Integer.decode(fields.get("year"));
            Integer month = Integer.decode(fields.get("month"));
            Integer day = Integer.decode(fields.get("day"));
            String comment = fields.get("comment");
            return new CommentEvent(metadata, year, month, day, comment);
        }
        case CREATE_CALENDAR: {
            if (fields == null || fields.size() < 3)
                throw new IllegalArgumentException(
                        "Cannot create a new CreateCalendarEvent if the necessary information is not provided.");

            Integer year = Integer.decode(fields.get("year"));
            Integer delta = Integer.decode(fields.get("delta"));
            FederalStates federalState = FederalStates.fromString(fields.get("federalState"));
            return new CreateCalendarEvent(metadata, year, delta, federalState);
        }
        case DELETE_DAY:
            throw new UnsupportedOperationException("Not yet implemented.");
        case HOURREDUCTION:
            throw new UnsupportedOperationException("Not yet implemented.");
        case ILLNESS:
            throw new UnsupportedOperationException("Not yet implemented.");
        case PARTIALVACATION:
            throw new UnsupportedOperationException("Not yet implemented.");
        case SIGMA_DELTA_LAST_YEAR:
            throw new UnsupportedOperationException("Not yet implemented.");
        case STAFFTRAINING:
            throw new UnsupportedOperationException("Not yet implemented.");
        case VACATION:
            throw new UnsupportedOperationException("Not yet implemented.");
        case WORKINGDAY:
            throw new UnsupportedOperationException("Not yet implemented.");
        default:
            throw new UnsupportedOperationException("Unknown entry type.");
        }
    }
}
