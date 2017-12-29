package de.nist.timing.domain;

public enum FederalStates {

    // @formatter:off
	BW("Baden-Württemberg"),
	BY("Bayern"),
	BE("Berlin"),
	BB("Brandenburg"),
	HB("Bremen"),
	HH("Hamburg"),
	HE("Hessen"),
	MV("Mecklenburg-Vorpommern"),
	NI("Niedersachsen"),
	NW("Nordrhein-Westfalen"),
	RP("Rheinland-Pfalz"),
	SL("Saarland"),
	SN("Sachsen"),
	ST("Sachsen-Anhalt"),
	SH("Schleswig-Holstein"),
	TH("Thüringen");
	// @formatter:on

    private final String m_name;

    private FederalStates(String name) {
        m_name = name;
    }

    @Override
    public String toString() {
        return m_name;
    }

    public static FederalStates fromString(String enumName) {
        for (FederalStates federalState : FederalStates.values()) {
            if (federalState.toString().equalsIgnoreCase(enumName)) {
                return federalState;
            }
        }
        return null;
    }
}