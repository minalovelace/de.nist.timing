package de.nist.timing.repositories;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import de.nist.timing.events.Event;
import de.nist.timing.events.EventVisitor;
import de.nist.timing.settings.AppSettings;

/*
 * This repository is responsible for saving and reading the events. 
 */
public class EventRepository {
    private final Path eventDir = Paths.get(AppSettings.WH_CAL_DATA_FOLDER, AppSettings.EVENT_REPOSITORY)
            .toAbsolutePath();
    private final String FILE_ENDING = ".txt";

    public EventRepository() {
    }

    public List<Event> read() {
        return Collections.emptyList();
    }

    public Boolean write(Event event) {
        try {
            File eventDirFile = this.eventDir.toFile();
            if (!eventDirFile.exists())
                eventDirFile.mkdirs();

            EventVisitor eventVisitor = new EventVisitor();
            event.prepareSerialization(eventVisitor);

            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append(eventVisitor.getEventType().toString());
            strBuilder.append("\n");
            eventVisitor.getFields().forEach((k, v) -> strBuilder.append(k).append("=").append(v).append("\n"));

            File eventFile = this.eventDir.resolve(eventVisitor.getMetadata().getEtag() + FILE_ENDING).toFile();
            eventFile.createNewFile();
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(eventFile), StandardCharsets.UTF_8));

            bw.write(strBuilder.toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}