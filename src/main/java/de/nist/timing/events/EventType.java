package de.nist.timing.events;

public enum EventType {
    // @formatter:off
    BUSINESSTRIP,
    CHANGE_SETTINGS,
    CLOCK_IN,
    CLOCK_OUT,
    COMMENT,
    CREATE_CALENDAR,
    DELETE_DAY,
    HOURREDUCTION,
    ILLNESS,
    PARTIALVACATION,
    SIGMA_DELTA_LAST_YEAR,
    STAFFTRAINING,
    VACATION,
    WORKINGDAY;
    // @formatter:on

    public static EventType fromString(String enumName) {
        for (EventType eventType : EventType.values()) {
            if (eventType.toString().equalsIgnoreCase(enumName)) {
                return eventType;
            }
        }
        return null;
    }
}
