package de.nist.timing.requests;

/*
 * This class handles requests by the user. Requests are:
 *    -- Ask for consistency of the calendar
 *    -- Create pdf file from calendar
 *    -- Download pdf file
 *    -- Get an html representation of the calendar
 */
public class RequestHandler {
    public Response<?> execute(Request request) {
        return request.execute();
    }
}