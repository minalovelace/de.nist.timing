package de.nist.timing.test.integration;

import static de.nist.timing.domain.FederalStates.BW;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import de.nist.timing.commands.BusinesstripCommand;
import de.nist.timing.commands.CommandHandler;
import de.nist.timing.commands.CreateCalendarCommand;
import de.nist.timing.domain.BusinesstripEntry;
import de.nist.timing.domain.Calendar;
import de.nist.timing.domain.Entry;
import de.nist.timing.events.Metadata;
import de.nist.timing.repositories.SnapshotRepository;
import de.nist.timing.settings.AppSettings;

public class BusinesstripCalendarTest {
    @BeforeAll
    public static void initAll() throws IOException {
        BusinesstripCalendarTest.cleanup();
    }

    @AfterEach
    public void tearDown() throws IOException {
        BusinesstripCalendarTest.cleanup();
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

    @Test
    public void businesstrip_calendar_for_bw_2018() {
        Metadata metadataCreateCalendar = new Metadata();
        Integer expectedYear = 2018;
        Integer expectedMonth = 2;
        Integer expectedDay = 7;
        String expectedComment = "An important reason!";

        CreateCalendarCommand createCalendarCommand = new CreateCalendarCommand(metadataCreateCalendar, expectedYear, 0,
                BW);
        CommandHandler createCalendarCommandHandler = new CommandHandler(createCalendarCommand);
        assertTrue(createCalendarCommandHandler.execute());
        Metadata metadataBusinesstrip = new Metadata();
        String etagBusinesstrip = metadataBusinesstrip.getEtag();
        BusinesstripCommand businesstripCommand = new BusinesstripCommand(metadataBusinesstrip, expectedYear,
                expectedMonth, expectedDay, expectedComment);
        CommandHandler businesstripCommandHandler = new CommandHandler(businesstripCommand);
        assertTrue(businesstripCommandHandler.execute());

        SnapshotRepository snapshotRepository = new SnapshotRepository();
        Calendar calendar = snapshotRepository.read(etagBusinesstrip);
        assertNotNull(calendar);
        assertEquals(expectedYear, calendar.getYear());
        assertEquals(0, calendar.getDelta().intValue());

        Entry actualBusinesstrip = calendar
                .getEntry(LocalDate.of(expectedYear, expectedMonth, expectedDay).getDayOfYear());
        assertNotNull(actualBusinesstrip, "'Businesstrip' was <null>");
        assertTrue(actualBusinesstrip instanceof BusinesstripEntry,
                "'Businesstrip' was of type: '" + actualBusinesstrip.getClass().getName() + "'.");
        Assertions.assertEquals(expectedComment, actualBusinesstrip.getComment());
    }

    @Test
    public void businesstrip_weekend_calendar_for_bw_2018() {
        Metadata metadataCreateCalendar = new Metadata();
        Integer expectedYear = 2018;
        Integer expectedMonth = 3;
        Integer expectedDay = 11;
        String expectedComment = "An important reason at a sunday!";

        CreateCalendarCommand createCalendarCommand = new CreateCalendarCommand(metadataCreateCalendar, expectedYear, 0,
                BW);
        CommandHandler createCalendarCommandHandler = new CommandHandler(createCalendarCommand);
        assertTrue(createCalendarCommandHandler.execute());
        Metadata metadataBusinesstrip = new Metadata();
        String etagBusinesstrip = metadataBusinesstrip.getEtag();
        BusinesstripCommand businesstripCommand = new BusinesstripCommand(metadataBusinesstrip, expectedYear,
                expectedMonth, expectedDay, expectedComment);
        CommandHandler businesstripCommandHandler = new CommandHandler(businesstripCommand);
        assertTrue(businesstripCommandHandler.execute());

        SnapshotRepository snapshotRepository = new SnapshotRepository();
        Calendar calendar = snapshotRepository.read(etagBusinesstrip);
        assertNotNull(calendar);
        assertEquals(expectedYear, calendar.getYear());
        assertEquals(0, calendar.getDelta().intValue());

        Entry actualBusinesstrip = calendar
                .getEntry(LocalDate.of(expectedYear, expectedMonth, expectedDay).getDayOfYear());
        assertNotNull(actualBusinesstrip, "'Businesstrip' was <null>");
        assertTrue(actualBusinesstrip instanceof BusinesstripEntry,
                "'Businesstrip' was of type: '" + actualBusinesstrip.getClass().getName() + "'.");
        Assertions.assertEquals(expectedComment, actualBusinesstrip.getComment());
    }

    @Test
    public void businesstrip_holiday_calendar_for_bw_2018() {
        Metadata metadataCreateCalendar = new Metadata();
        Integer expectedYear = 2018;
        Integer expectedMonth = 10;
        Integer expectedDay = 3;
        String expectedComment = "An important reason at a holiday!";

        CreateCalendarCommand createCalendarCommand = new CreateCalendarCommand(metadataCreateCalendar, expectedYear, 0,
                BW);
        CommandHandler createCalendarCommandHandler = new CommandHandler(createCalendarCommand);
        assertTrue(createCalendarCommandHandler.execute());
        Metadata metadataBusinesstrip = new Metadata();
        BusinesstripCommand businesstripCommand = new BusinesstripCommand(metadataBusinesstrip, expectedYear,
                expectedMonth, expectedDay, expectedComment);
        CommandHandler businesstripCommandHandler = new CommandHandler(businesstripCommand);
        assertTrue(businesstripCommandHandler.execute());

        SnapshotRepository snapshotRepository = new SnapshotRepository();
        Calendar calendar = snapshotRepository.read(metadataBusinesstrip.getEtag());
        assertNotNull(calendar);
        assertEquals(expectedYear, calendar.getYear());
        assertEquals(0, calendar.getDelta().intValue());

        Entry actualBusinesstrip = calendar
                .getEntry(LocalDate.of(expectedYear, expectedMonth, expectedDay).getDayOfYear());
        assertNotNull(actualBusinesstrip, "'Businesstrip' was <null>");
        assertTrue(actualBusinesstrip instanceof BusinesstripEntry,
                "'Businesstrip' was of type: '" + actualBusinesstrip.getClass().getName() + "'.");
        Assertions.assertEquals(expectedComment, actualBusinesstrip.getComment());
    }
}
