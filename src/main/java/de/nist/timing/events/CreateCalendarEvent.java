package de.nist.timing.events;

import static de.nist.timing.domain.FederalStates.BB;
import static de.nist.timing.domain.FederalStates.BW;
import static de.nist.timing.domain.FederalStates.BY;
import static de.nist.timing.domain.FederalStates.HE;
import static de.nist.timing.domain.FederalStates.MV;
import static de.nist.timing.domain.FederalStates.NW;
import static de.nist.timing.domain.FederalStates.RP;
import static de.nist.timing.domain.FederalStates.SL;
import static de.nist.timing.domain.FederalStates.SN;
import static de.nist.timing.domain.FederalStates.ST;
import static de.nist.timing.domain.FederalStates.TH;
import static de.nist.timing.events.EventType.CREATE_CALENDAR;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

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
        Set<Entry> entries = createHolidays();
        Set<Entry> weekends = createWeekends();
        entries.addAll(weekends);
        return new Calendar(this.year, this.delta, entries);
    }

    @Override
    public void prepareSerialization(EventVisitor visitor) {
        visitor.setEventType(CREATE_CALENDAR);
        visitor.setMetadata(getMetadata());
        visitor.setField("year", this.year.toString());
        visitor.setField("delta", this.delta.toString());
        visitor.setField("federalState", this.federalState.toString());
    }

    private Set<Entry> createWeekends() {
        Set<Entry> entries = new HashSet<Entry>();
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
                entries.add(weekend);
                break;
            }
            cal.add(GregorianCalendar.DAY_OF_YEAR, 1);
        }
        return entries;
    }

    private Set<Entry> createHolidays() {
        Set<Entry> entries = new HashSet<Entry>();
        /* Create and add all fixed holidays: */
        /* Neujahr */
        HolidayEntry newYear = new HolidayEntry(this.year, 1, 1, "Neujahr");
        entries.add(newYear);
        if (this.federalState == BW || this.federalState == BY || this.federalState == ST) {
            /* Heilige Drei Könige */
            HolidayEntry hdk = new HolidayEntry(this.year, 1, 6, "Heilige Drei Könige");
            entries.add(hdk);
        }
        /* Tag der Arbeit */
        HolidayEntry tda = new HolidayEntry(this.year, 5, 1, "Tag der Arbeit");
        entries.add(tda);
        if (this.federalState == SL) {
            /* Mariä Himmelfahrt */
            HolidayEntry mh = new HolidayEntry(this.year, 8, 15, "Mariä Himmelfahrt");
            entries.add(mh);
        }
        /* Tag der deutschen Einheit */
        HolidayEntry tdde = new HolidayEntry(this.year, 10, 3, "Tag der deutschen Einheit");
        entries.add(tdde);
        if (this.federalState == BB || this.federalState == MV || this.federalState == SN || this.federalState == ST
                || this.federalState == TH) {
            /* Reformationstag */
            HolidayEntry rt = new HolidayEntry(this.year, 10, 31, "Reformationstag");
            entries.add(rt);
        }
        if (this.federalState == BW || this.federalState == BY || this.federalState == NW || this.federalState == RP
                || this.federalState == SL) {
            /* Allerheiligen */
            HolidayEntry allerheiligen = new HolidayEntry(this.year, 11, 1, "Allerheiligen");
            entries.add(allerheiligen);
        }
        /* 1. Weihnachtstag */
        HolidayEntry firstCD = new HolidayEntry(this.year, 12, 25, "1. Weihnachtstag");
        entries.add(firstCD);
        /* 2. Weihnachtstag */
        HolidayEntry secondCD = new HolidayEntry(this.year, 12, 26, "2. Weihnachtstag");
        entries.add(secondCD);

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
        if (this.federalState == BW || this.federalState == BY || this.federalState == HE || this.federalState == NW
                || this.federalState == RP || this.federalState == SL) {
            /* Fronleichnam */
            pivotDay.add(GregorianCalendar.DAY_OF_YEAR, 10);
            createAndPutEntry(entries, pivotDay, "Fronleichnam");
        }
        if (this.federalState == SN) {
            /* Buß- u. Bettag */
            LocalDate bbLocalDate = LocalDate.of(this.year, 11, 22);
            bbLocalDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.WEDNESDAY));
            HolidayEntry bb = new HolidayEntry(this.year, bbLocalDate.getMonth().getValue(),
                    bbLocalDate.getDayOfMonth(), "Buß- und Bettag");
            entries.add(bb);
        }

        /* Calculate special holidays of the specified year. */
        specialHolidays(entries);

        return entries;
    }

    private void createAndPutEntry(Set<Entry> entries, GregorianCalendar cal, String comment) {
        HolidayEntry entry = new HolidayEntry(this.year, cal.get(GregorianCalendar.MONTH) + 1,
                cal.get(GregorianCalendar.DAY_OF_MONTH), comment);
        entries.add(entry);
    }

    private void specialHolidays(Set<Entry> entries) {
        if (this.year == 2017) {
            HolidayEntry luther = new HolidayEntry(this.year, 9, 31, "500. Reformationstag");
            entries.add(luther);
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
