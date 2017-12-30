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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.hours).append(":");

        if (this.minutes < 10)
            sb.append("0");

        sb.append(this.minutes).append(" Uhr");

        return sb.toString();
    }
}