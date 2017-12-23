package de.nist.timing.domain;

public enum EntryType {
    // @formatter:off
    BUSINESSTRIP("businessTrip"),
    HOLIDAY("holiday"),
    HOURREDUCTION("hourReduction"),
    ILLNESS("illness"),
    STAFFTRAINING("staffTraining"),
    VACATION("vacation"),
    PARTIALVACATION("partialvacation"),
    WEEKEND("weekend"),
    WORKINGDAY("workingDay");
    // @formatter:on

    private final String m_enumName;

    private EntryType(String enumName)
    {
        m_enumName = enumName;
    }

    @Override
    public String toString()
    {
        return m_enumName;
    }
}