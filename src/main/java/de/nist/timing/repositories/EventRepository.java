package de.nist.timing.repositories;

import static de.nist.timing.settings.RepositorySettings.FILE_ENDING;
import static de.nist.timing.settings.RepositorySettings.INFORMATION_SEPARATOR;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Strings;
import com.google.common.io.Files;

import de.nist.timing.events.Event;
import de.nist.timing.events.EventVisitor;
import de.nist.timing.events.Metadata;
import de.nist.timing.settings.AppSettings;

/*
 * This repository is responsible for saving and reading the events. 
 */
public class EventRepository {
    private final Path EVENT_DIR = Paths.get(AppSettings.WH_CAL_DATA_FOLDER, AppSettings.EVENT_REPOSITORY)
            .toAbsolutePath();

    public EventRepository() {
    }

    /*
     * Return a list, which is sorted by date in an ascending (from old to new)
     * order. The list contains every user event read from the repository.
     */
    public List<Event> read() {
        // TODO nina implement
        return Collections.emptyList();
    }

    /*
     * Returns a user event specified by the given etag read from the repository.
     */
    public Event read(String etag) {
        // TODO nina implement
        return null;
    }

    /*
     * Return a list, which is sorted by date in an ascending (from old to new)
     * order. The list contains the metadata to every user event read from the
     * repository.
     */
    public List<Metadata> peek() {
        File[] eventFiles = this.EVENT_DIR.toFile().listFiles();
        List<Metadata> result = new ArrayList<>();

        for (File file : eventFiles) {
            String name = file.getName();
            if (Strings.isNullOrEmpty(name) || !name.endsWith(FILE_ENDING) || name.length() < FILE_ENDING.length() + 1)
                break;

            String etag = name.substring(0, name.length() - FILE_ENDING.length());
            try {
                Metadata metadata = new Metadata(etag);
                result.add(metadata);
            } catch (IllegalArgumentException | ParseException e) {
                // TODO nina log and handle exception(s)
            }
        }
        return result;
    }

    public Boolean write(Event event) {
        try {
            File eventDirFile = this.EVENT_DIR.toFile();
            if (!eventDirFile.exists())
                eventDirFile.mkdirs();

            EventVisitor eventVisitor = new EventVisitor();
            event.prepareSerialization(eventVisitor);

            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append(eventVisitor.getEventType().toString());
            strBuilder.append("\n");
            eventVisitor.getFields()
                    .forEach((k, v) -> strBuilder.append(k).append(INFORMATION_SEPARATOR).append(v).append("\n"));

            File eventFile = this.EVENT_DIR.resolve(eventVisitor.getMetadata().getEtag() + FILE_ENDING).toFile();
            eventFile.createNewFile();
            Files.write(strBuilder.toString().getBytes(StandardCharsets.UTF_8), eventFile);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}