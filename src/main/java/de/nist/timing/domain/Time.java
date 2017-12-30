package de.nist.timing.domain;

/*
 * Our domain specific time does only consist of hours and minutes.
 */
public class Time {

    private final Integer hours;
    private final Integer minutes;

    public Time(Integer hours, Integer minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public Time(String hours, String minutes) {
        this(Integer.decode(hours), Integer.decode(minutes));
    }

    public Integer getHours() {
        return hours;
    }

    public Integer getMinutes() {
        return minutes;
    }
}