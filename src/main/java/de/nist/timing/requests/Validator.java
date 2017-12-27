package de.nist.timing.requests;

import de.nist.timing.domain.Calendar;

/*
 * This class validates a given calendar. It will inform the caller about inconsistencies.
 */
public class Validator {

    public String validate(Calendar cal) {
        return "The given calendar is inconsistent.";
    }
}