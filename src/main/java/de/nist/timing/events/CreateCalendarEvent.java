package de.nist.timing.events;

import java.util.GregorianCalendar;
import java.util.TreeMap;

import de.nist.timing.domain.Calendar;
import de.nist.timing.domain.Entry;
import de.nist.timing.domain.FederalStates;
import de.nist.timing.domain.HolidayEntry;
import de.nist.timing.domain.WeekendEntry;

public class CreateCalendarEvent extends Event {
    private final Integer year;
    private final Integer delta;
    private final FederalStates federalState;

    public CreateCalendarEvent(Metadata metadata, Integer year, Integer delta, FederalStates federalState) {
        super(metadata);
        this.year = year;
        this.delta = delta;
        this.federalState = federalState;
    }

    @Override
    public Calendar apply(Calendar calendar) {
        /* The calendar should be null, because we are creating a new one now. */
        TreeMap<Integer, Entry> entries = createHolidaysAndWeekends();
        return new Calendar(entries, this.year, this.delta);
    }

    private TreeMap<Integer, Entry> createHolidaysAndWeekends() {
        TreeMap<Integer, Entry> entries = new TreeMap<Integer, Entry>();

        /* Create and add all fixed holidays: */
        /* Neujahr */
        HolidayEntry newYear = new HolidayEntry(this.year, 1, 1, "Neujahr");
        entries.put(newYear.getDayOfYear(), newYear);
        /* Heilige Drei Könige */
        HolidayEntry hdk = new HolidayEntry(this.year, 1, 6, "Heilige Drei Könige");
        entries.put(hdk.getDayOfYear(), hdk);
        /* Tag der Arbeit */
        HolidayEntry tda = new HolidayEntry(this.year, 5, 1, "Tag der Arbeit");
        entries.put(tda.getDayOfYear(), tda);
        /* Tag der deutschen Einheit */
        HolidayEntry tdde = new HolidayEntry(this.year, 10, 3, "Tag der deutschen Einheit");
        entries.put(tdde.getDayOfYear(), tdde);
        /* Allerheiligen */
        HolidayEntry allerheiligen = new HolidayEntry(this.year, 11, 1, "Allerheiligen");
        entries.put(allerheiligen.getDayOfYear(), allerheiligen);
        /* 1. Weihnachtstag */
        HolidayEntry firstCD = new HolidayEntry(this.year, 12, 25, "1. Weihnachtstag");
        entries.put(firstCD.getDayOfYear(), firstCD);
        /* 2. Weihnachtstag */
        HolidayEntry secondCD = new HolidayEntry(this.year, 12, 26, "2. Weihnachtstag");
        entries.put(secondCD.getDayOfYear(), secondCD);

        /* Create and add all flexible holidays: */
        /* Ostersonntag */
        GregorianCalendar pivotDay = easterSunday(this.year);
        createAndPutEntry(entries, pivotDay, "Ostersonntag");
        /* Ostermontag */
        pivotDay.add(GregorianCalendar.DAY_OF_YEAR, 1);
        createAndPutEntry(entries, pivotDay, "Ostermontag");
        /* Karfreitag */
        pivotDay.add(GregorianCalendar.DAY_OF_YEAR, -3);
        createAndPutEntry(entries, pivotDay, "Karfreitag");
        /* Christi Himmelfahrt */
        pivotDay.add(GregorianCalendar.DAY_OF_YEAR, 41);
        createAndPutEntry(entries, pivotDay, "Christi Himmelfahrt");
        /* Pfingstsonntag */
        pivotDay.add(GregorianCalendar.DAY_OF_YEAR, 10);
        createAndPutEntry(entries, pivotDay, "Pfingstsonntag");
        /* Pfingstmontag */
        pivotDay.add(GregorianCalendar.DAY_OF_YEAR, 1);
        createAndPutEntry(entries, pivotDay, "Pfingstmontag");
        /* Fronleichnam */
        pivotDay.add(GregorianCalendar.DAY_OF_YEAR, 10);
        createAndPutEntry(entries, pivotDay, "Fronleichnam");

        /* Find all weekends */
        GregorianCalendar cal = new GregorianCalendar(this.year, 0, 1);
        /*
         * The while loop ensures that we are only checking dates in the specified year.
         */
        while (cal.get(GregorianCalendar.YEAR) == this.year) {
            /*
             * The switch checks the day of the week for Saturdays and Sundays
             */
            switch (cal.get(GregorianCalendar.DAY_OF_WEEK)) {
            case GregorianCalendar.SATURDAY:
            case GregorianCalendar.SUNDAY:
                WeekendEntry weekend = new WeekendEntry(this.year, cal.get(GregorianCalendar.MONTH) + 1,
                        cal.get(GregorianCalendar.DAY_OF_MONTH));
                entries.putIfAbsent(cal.get(GregorianCalendar.DAY_OF_YEAR), weekend);
                break;
            }
            cal.add(GregorianCalendar.DAY_OF_YEAR, 1);
        }

        /* Calculate special holidays of the specified year. */
        specialHolidays(entries);

        return entries;
    }

    private void createAndPutEntry(TreeMap<Integer, Entry> entries, GregorianCalendar cal, String comment) {
        HolidayEntry entry = new HolidayEntry(this.year, cal.get(GregorianCalendar.MONTH) + 1,
                cal.get(GregorianCalendar.DAY_OF_MONTH), comment);
        entries.put(cal.get(GregorianCalendar.DAY_OF_YEAR), entry);
    }

    private void specialHolidays(TreeMap<Integer, Entry> entries) {
        if (this.year == 2017) {
            HolidayEntry luther = new HolidayEntry(this.year, 9, 31, "500. Reformationstag");
            entries.put(luther.getDayOfYear(), luther);
        }
    }

    /**
     * Returns the date of Easter Sunday for a given year. This algorithm has been
     * invented by C.F. Gauß.
     *
     * @param year
     *            > 1583
     * @return The date of Easter Sunday for a given year.
     */
    private GregorianCalendar easterSunday(int year) {
        int i = year % 19;
        int j = year / 100;
        int k = year % 100;

        int l = (19 * i + j - (j / 4) - ((j - ((j + 8) / 25) + 1) / 3) + 15) % 30;
        int m = (32 + 2 * (j % 4) + 2 * (k / 4) - l - (k % 4)) % 7;
        int n = l + m - 7 * ((i + 11 * l + 22 * m) / 451) + 114;

        int month = n / 31;
        int day = (n % 31) + 1;

        return new GregorianCalendar(year, month - 1, day);
    }
}