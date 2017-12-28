package de.nist.timing.domain;

public enum EntryType {
    // @formatter:off
    BUSINESSTRIP("businessTrip"),
    HOLIDAY("holiday"),
    HOURREDUCTION("hourReduction"),
    ILLNESS("illness"),
    STAFFTRAINING("staffTraining"),
    VACATION("vacation"),
    PARTIALVACATION("partialVacation"),
    WEEKEND("weekend"),
    WORKINGDAY("workingDay");
    // @formatter:on

    private final String enumName;

    private EntryType(String enumName) {
        this.enumName = enumName;
    }

    @Override
    public String toString() {
        return this.enumName;
    }

    public static EntryType fromString(String enumName) {
        for (EntryType entryType : EntryType.values()) {
            if (entryType.toString().equalsIgnoreCase(enumName)) {
                return entryType;
            }
        }
        return null;
    }
}