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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import de.nist.timing.commands.CommandHandler;
import de.nist.timing.commands.CommentCommand;
import de.nist.timing.commands.CreateCalendarCommand;
import de.nist.timing.domain.Calendar;
import de.nist.timing.domain.Entry;
import de.nist.timing.domain.HolidayEntry;
import de.nist.timing.domain.WeekendEntry;
import de.nist.timing.events.Metadata;
import de.nist.timing.repositories.SnapshotRepository;
import de.nist.timing.settings.AppSettings;

public class CommentTest {
    @BeforeAll
    public static void initAll() throws IOException {
        CommentTest.cleanup();
    }

    @AfterEach
    public void tearDown() throws IOException {
        CommentTest.cleanup();
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
    @Disabled("'WorkingdayEntry' not yet implemented")
    public void set_comment_calendar_for_bw_2020() {
        Metadata metadataCreateCalendar = new Metadata();
        Integer expectedYear = 2020;
        Integer expectedMonth = 7;
        Integer expectedDay = 15;
        String expectedComment = "Expected comment...";

        CreateCalendarCommand createCalendarCommand = new CreateCalendarCommand(metadataCreateCalendar, expectedYear, 0,
                BW);
        CommandHandler createCalendarCommandHandler = new CommandHandler(createCalendarCommand);
        assertTrue(createCalendarCommandHandler.execute());
        Metadata metadataComment = new Metadata();
        CommentCommand commentCommand = new CommentCommand(metadataComment, expectedYear, expectedMonth, expectedDay,
                expectedComment);
        CommandHandler commentCommandHandler = new CommandHandler(commentCommand);
        assertTrue(commentCommandHandler.execute());

        SnapshotRepository snapshotRepository = new SnapshotRepository();
        Calendar calendar = snapshotRepository.read(metadataComment.getEtag());
        assertNotNull(calendar);
        assertEquals(expectedYear, calendar.getYear());
        assertEquals(0, calendar.getDelta().intValue());

        Entry actualComment = calendar.getEntry(LocalDate.of(expectedYear, expectedMonth, expectedDay).getDayOfYear());
        assertNotNull(actualComment, "'Businesstrip' was <null>");
        // TODO nina uncomment as soon as WorkingdayEntry is implemented
        // assertTrue(actualComment instanceof WorkingdayEntry,
        // "'Workingday' was of type: '" + actualComment.getClass().getName() + "'.");
        Assertions.assertEquals(expectedComment, actualComment.getComment());
    }

    @Test
    public void change_comment_on_weekend_calendar_for_bw_2021() {
        Metadata metadataCreateCalendar = new Metadata();
        Integer expectedYear = 2021;
        Integer expectedMonth = 8;
        Integer expectedDay = 22;
        String oldComment = "Unexpected comment...";
        String expectedComment = "Expected comment on a sunday!";

        CreateCalendarCommand createCalendarCommand = new CreateCalendarCommand(metadataCreateCalendar, expectedYear, 0,
                BW);
        CommandHandler createCalendarCommandHandler = new CommandHandler(createCalendarCommand);
        assertTrue(createCalendarCommandHandler.execute());
        Metadata oldMetadataComment = new Metadata();
        CommentCommand oldCommentCommand = new CommentCommand(oldMetadataComment, expectedYear, expectedMonth,
                expectedDay, oldComment);
        assertTrue(new CommandHandler(oldCommentCommand).execute());
        Metadata metadataComment = new Metadata();
        CommentCommand commentCommand = new CommentCommand(metadataComment, expectedYear, expectedMonth, expectedDay,
                expectedComment);
        assertTrue(new CommandHandler(commentCommand).execute());

        SnapshotRepository snapshotRepository = new SnapshotRepository();
        Calendar calendar = snapshotRepository.read(metadataComment.getEtag());
        assertNotNull(calendar);
        assertEquals(expectedYear, calendar.getYear());
        assertEquals(0, calendar.getDelta().intValue());

        Entry actualComment = calendar.getEntry(LocalDate.of(expectedYear, expectedMonth, expectedDay).getDayOfYear());
        assertNotNull(actualComment, "'Weekend' was <null>");
        assertTrue(actualComment instanceof WeekendEntry,
                "'Weekend' was of type: '" + actualComment.getClass().getName() + "'.");
        Assertions.assertEquals(expectedComment, actualComment.getComment());
    }

    @Test
    public void change_holiday_comment_calendar_for_bw_2022() {
        Metadata metadataCreateCalendar = new Metadata();
        Integer expectedYear = 2022;
        Integer expectedMonth = 10;
        Integer expectedDay = 3;
        String expectedComment = "New comment for a holiday!";

        CreateCalendarCommand createCalendarCommand = new CreateCalendarCommand(metadataCreateCalendar, expectedYear, 0,
                BW);
        CommandHandler createCalendarCommandHandler = new CommandHandler(createCalendarCommand);
        assertTrue(createCalendarCommandHandler.execute());
        Metadata metadataComment = new Metadata();
        CommentCommand commentCommand = new CommentCommand(metadataComment, expectedYear, expectedMonth, expectedDay,
                expectedComment);
        CommandHandler commentCommandHandler = new CommandHandler(commentCommand);
        assertTrue(commentCommandHandler.execute());

        SnapshotRepository snapshotRepository = new SnapshotRepository();
        Calendar calendar = snapshotRepository.read(metadataComment.getEtag());
        assertNotNull(calendar);
        assertEquals(expectedYear, calendar.getYear());
        assertEquals(0, calendar.getDelta().intValue());

        Entry actualComment = calendar.getEntry(LocalDate.of(expectedYear, expectedMonth, expectedDay).getDayOfYear());
        assertNotNull(actualComment, "'Holiday' was <null>");
        assertTrue(actualComment instanceof HolidayEntry,
                "'Holiday' was of type: '" + actualComment.getClass().getName() + "'.");
        Assertions.assertEquals(expectedComment, actualComment.getComment());
    }
}
