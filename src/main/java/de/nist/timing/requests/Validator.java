package de.nist.timing.requests;

import de.nist.timing.domain.Calendar;

/*
 * This class validates a given calendar. It will inform the caller about inconsistencies.
 */
public class Validator {

    /*
     * At the moment, a calendar is valid if it exists.
     */
    public Boolean isValid(Calendar cal) {
        if (cal == null)
            return false;

        return true;
    }
}