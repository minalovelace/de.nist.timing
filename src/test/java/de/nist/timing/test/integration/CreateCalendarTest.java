package de.nist.timing.test.integration;

import static de.nist.timing.domain.FederalStates.BW;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import de.nist.timing.commands.CommandHandler;
import de.nist.timing.commands.CreateCalendarCommand;
import de.nist.timing.domain.Calendar;
import de.nist.timing.domain.Entry;
import de.nist.timing.domain.HolidayEntry;
import de.nist.timing.events.Metadata;
import de.nist.timing.repositories.SnapshotRepository;

public class CreateCalendarTest {
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
        assertTrue(actualNeujahr instanceof HolidayEntry);
        Assertions.assertEquals("Neujahr", actualNeujahr.getComment());
    }
}
