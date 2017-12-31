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
        LocalDate neujahr = LocalDate.of(2017, 1, 1);
        Entry actualNeujahr = calendar.getEntry(neujahr.getDayOfYear());
        assertTrue(actualNeujahr instanceof HolidayEntry,
                "'actualNeujahr' was of type: '" + actualNeujahr.getClass().getName() + "'.");
        Assertions.assertEquals("Neujahr", actualNeujahr.getComment());
    }
}
