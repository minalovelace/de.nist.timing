package de.nist.timing.domain;

public class Entries {
    public static Entry create(EntryType entryType, Integer year, Integer month, Integer day, String[] args) {
        String comment = "";

        if (args != null && args.length > 0)
            comment = args[0];

        switch (entryType) {
        case BUSINESSTRIP:
            return new BusinesstripEntry(year, month, day, comment);
        case HOLIDAY:
            return new HolidayEntry(year, month, day, comment);
        case HOURREDUCTION:
            throw new UnsupportedOperationException("Not yet implemented.");
        case ILLNESS:
            throw new UnsupportedOperationException("Not yet implemented.");
        case STAFFTRAINING:
            throw new UnsupportedOperationException("Not yet implemented.");
        case VACATION:
            throw new UnsupportedOperationException("Not yet implemented.");
        case PARTIALVACATION:
            throw new UnsupportedOperationException("Not yet implemented.");
        case WEEKEND:
            return new WeekendEntry(year, month, day, comment);
        case WORKINGDAY:
            if (args == null || args.length < 5)
                return null;

            Time begin = new Time(args[0], args[1]);
            Time end = new Time(args[2], args[3]);
            Integer pause = Integer.decode(args[4]);

            if (args.length == 5)
                return new WorkingDayEntry(year, month, day, begin, end, pause);

            if (args.length == 6)
                return new WorkingDayEntry(year, month, day, begin, end, pause, args[5]);

            if (args.length == 7)
                return new WorkingDayEntry(year, month, day, begin, end, pause, args[5], Integer.decode(args[6]));

        default:
            throw new UnsupportedOperationException("Unknown entry type.");
        }
    }
}
