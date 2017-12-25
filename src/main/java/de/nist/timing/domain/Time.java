package de.nist.timing.domain;

/*
 * Our domain specific time does only consist of hours and minutes.
 */
public class Time {

    private Integer hours;
    private Integer minutes;

    public Time() {
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }
}