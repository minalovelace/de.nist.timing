package de.nist.timing.test.integration;

import static de.nist.timing.domain.FederalStates.BW;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.nist.timing.commands.CommandHandler;
import de.nist.timing.commands.CreateCalendarCommand;
import de.nist.timing.domain.Calendar;
import de.nist.timing.domain.Entry;
import de.nist.timing.domain.HolidayEntry;
import de.nist.timing.events.Metadata;
import de.nist.timing.repositories.SnapshotRepository;
import de.nist.timing.settings.AppSettings;

public class CreateCalendarTest {
    @BeforeAll
    public static void initAll() throws IOException {
        CreateCalendarTest.cleanup();
    }

    @AfterEach
    public void tearDown() throws IOException {
        CreateCalendarTest.cleanup();
    }

    private static void cleanup() throws IOException {
        Path whCalDataFolder = Paths.get(AppSettings.WH_CAL_DATA_FOLDER);
        if (whCalDataFolder.toFile().exists()) {
            final List<Path> pathsToDelete = Files.walk(whCalDataFolder).sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());
            for (Path path : pathsToDelete) {
                Files.deleteIfExists(path);
            }
        }
    }

    private void checkHoliday(Calendar calendar, LocalDate expectedHoliday, String expectedComment) {
        Entry actualHoliday = calendar.getEntry(expectedHoliday.getDayOfYear());
        assertNotNull(actualHoliday, expectedComment + " was <null>");
        assertTrue(actualHoliday instanceof HolidayEntry,
                "'actualHoliday' was of type: '" + actualHoliday.getClass().getName() + "'.");
        Assertions.assertEquals(expectedComment, actualHoliday.getComment());
    }

    @Test
    public void create_calendar_for_bw_2017() {
        Metadata metadata = new Metadata();
        String etag = metadata.getEtag();
        CreateCalendarCommand command = new CreateCalendarCommand(metadata, 2017, 0, BW);
        CommandHandler commandHandler = new CommandHandler(command);
        Boolean execute = commandHandler.execute();
        assertTrue(execute);
        SnapshotRepository snapshotRepository = new SnapshotRepository();
        Calendar calendar = snapshotRepository.read(etag);
        assertNotNull(calendar);
        assertEquals(2017, calendar.getYear());
        assertEquals(0, calendar.getDelta());

        checkHoliday(calendar, LocalDate.of(2017, 1, 1), "Neujahr");
        checkHoliday(calendar, LocalDate.of(2017, 1, 6), "Heilige Drei Könige");
        checkHoliday(calendar, LocalDate.of(2017, 4, 14), "Karfreitag");
        checkHoliday(calendar, LocalDate.of(2017, 4, 17), "Ostermontag");
        checkHoliday(calendar, LocalDate.of(2017, 5, 1), "Tag der Arbeit");
        checkHoliday(calendar, LocalDate.of(2017, 5, 25), "Christi Himmelfahrt");
        checkHoliday(calendar, LocalDate.of(2017, 6, 5), "Pfingstmontag");
        checkHoliday(calendar, LocalDate.of(2017, 6, 15), "Fronleichnam");
        // "Mariä Himmelfahrt" must not exist
        LocalDate mh = LocalDate.of(2017, 8, 15);
        Entry mhEntry = calendar.getEntry(mh.getDayOfYear());
        assertNull(mhEntry);
        checkHoliday(calendar, LocalDate.of(2017, 10, 3), "Tag der deutschen Einheit");
        checkHoliday(calendar, LocalDate.of(2017, 10, 31), "500. Reformationstag");
        checkHoliday(calendar, LocalDate.of(2017, 11, 1), "Allerheiligen");
        // "Buß- und Bettag" must not exist
        LocalDate bb = LocalDate.of(2017, 11, 22);
        Entry bbEntry = calendar.getEntry(bb.getDayOfYear());
        assertNull(bbEntry);
        checkHoliday(calendar, LocalDate.of(2017, 12, 25), "1. Weihnachtstag");
        checkHoliday(calendar, LocalDate.of(2017, 12, 26), "2. Weihnachtstag");
    }

    @Test
    public void create_calendar_for_bw_2018() {
        Metadata metadata = new Metadata();
        String etag = metadata.getEtag();
        int expectedYear = 2018;
        CreateCalendarCommand command = new CreateCalendarCommand(metadata, expectedYear, 0, BW);
        CommandHandler commandHandler = new CommandHandler(command);
        Boolean execute = commandHandler.execute();
        assertTrue(execute);
        SnapshotRepository snapshotRepository = new SnapshotRepository();
        Calendar calendar = snapshotRepository.read(etag);
        assertNotNull(calendar);
        assertEquals(expectedYear, calendar.getYear());
        assertEquals(0, calendar.getDelta());

        checkHoliday(calendar, LocalDate.of(expectedYear, 1, 1), "Neujahr");
        checkHoliday(calendar, LocalDate.of(expectedYear, 1, 6), "Heilige Drei Könige");
        checkHoliday(calendar, LocalDate.of(expectedYear, 3, 30), "Karfreitag");
        checkHoliday(calendar, LocalDate.of(expectedYear, 4, 2), "Ostermontag");
        checkHoliday(calendar, LocalDate.of(expectedYear, 5, 1), "Tag der Arbeit");
        checkHoliday(calendar, LocalDate.of(expectedYear, 5, 10), "Christi Himmelfahrt");
        checkHoliday(calendar, LocalDate.of(expectedYear, 5, 21), "Pfingstmontag");
        checkHoliday(calendar, LocalDate.of(expectedYear, 5, 31), "Fronleichnam");
        // "Mariä Himmelfahrt" must not exist
        LocalDate mh = LocalDate.of(expectedYear, 8, 15);
        Entry mhEntry = calendar.getEntry(mh.getDayOfYear());
        assertNull(mhEntry);
        checkHoliday(calendar, LocalDate.of(expectedYear, 10, 3), "Tag der deutschen Einheit");
        // "Reformationstag" must not exist
        LocalDate rt = LocalDate.of(expectedYear, 10, 31);
        Entry rtEntry = calendar.getEntry(rt.getDayOfYear());
        assertNull(rtEntry);
        checkHoliday(calendar, LocalDate.of(expectedYear, 11, 1), "Allerheiligen");
        // "Buß- und Bettag" must not exist
        LocalDate bb = LocalDate.of(expectedYear, 11, 22);
        Entry bbEntry = calendar.getEntry(bb.getDayOfYear());
        assertNull(bbEntry);
        checkHoliday(calendar, LocalDate.of(expectedYear, 12, 25), "1. Weihnachtstag");
        checkHoliday(calendar, LocalDate.of(expectedYear, 12, 26), "2. Weihnachtstag");
    }

    @Test
    public void create_calendar_for_bw_2019() {
        Metadata metadata = new Metadata();
        String etag = metadata.getEtag();
        Integer expectedDelta = 180;
        int expectedYear = 2019;
        CreateCalendarCommand command = new CreateCalendarCommand(metadata, expectedYear, expectedDelta, BW);
        CommandHandler commandHandler = new CommandHandler(command);
        Boolean execute = commandHandler.execute();
        assertTrue(execute);
        SnapshotRepository snapshotRepository = new SnapshotRepository();
        Calendar calendar = snapshotRepository.read(etag);
        assertNotNull(calendar);
        assertEquals(expectedYear, calendar.getYear());
        assertEquals(expectedDelta.intValue(), calendar.getDelta());

        checkHoliday(calendar, LocalDate.of(expectedYear, 1, 1), "Neujahr");
        checkHoliday(calendar, LocalDate.of(expectedYear, 1, 6), "Heilige Drei Könige");
        checkHoliday(calendar, LocalDate.of(expectedYear, 4, 19), "Karfreitag");
        checkHoliday(calendar, LocalDate.of(expectedYear, 4, 22), "Ostermontag");
        checkHoliday(calendar, LocalDate.of(expectedYear, 5, 1), "Tag der Arbeit");
        checkHoliday(calendar, LocalDate.of(expectedYear, 5, 30), "Christi Himmelfahrt");
        checkHoliday(calendar, LocalDate.of(expectedYear, 6, 10), "Pfingstmontag");
        checkHoliday(calendar, LocalDate.of(expectedYear, 6, 20), "Fronleichnam");
        // "Mariä Himmelfahrt" must not exist
        LocalDate mh = LocalDate.of(expectedYear, 8, 15);
        Entry mhEntry = calendar.getEntry(mh.getDayOfYear());
        assertNull(mhEntry);
        checkHoliday(calendar, LocalDate.of(expectedYear, 10, 3), "Tag der deutschen Einheit");
        // "Reformationstag" must not exist
        LocalDate rt = LocalDate.of(expectedYear, 10, 31);
        Entry rtEntry = calendar.getEntry(rt.getDayOfYear());
        assertNull(rtEntry);
        checkHoliday(calendar, LocalDate.of(expectedYear, 11, 1), "Allerheiligen");
        // "Buß- und Bettag" must not exist
        LocalDate bb = LocalDate.of(expectedYear, 11, 22);
        Entry bbEntry = calendar.getEntry(bb.getDayOfYear());
        assertNull(bbEntry);
        checkHoliday(calendar, LocalDate.of(expectedYear, 12, 25), "1. Weihnachtstag");
        checkHoliday(calendar, LocalDate.of(expectedYear, 12, 26), "2. Weihnachtstag");
    }
}
