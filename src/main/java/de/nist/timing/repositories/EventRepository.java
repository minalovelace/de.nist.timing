package de.nist.timing.repositories;

import static de.nist.timing.settings.RepositorySettings.FILE_ENDING;
import static de.nist.timing.settings.RepositorySettings.INFORMATION_SEPARATOR;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.google.common.base.Strings;

import de.nist.timing.events.Event;
import de.nist.timing.events.EventType;
import de.nist.timing.events.EventVisitor;
import de.nist.timing.events.Events;
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
        List<Metadata> metadataList = peek();
        List<Event> result = new ArrayList<>();

        for (Metadata metadata : metadataList) {
            Event event = read(metadata.getEtag());
            result.add(event);
        }
        return result;
    }

    /*
     * Read event from disk and create the corresponding event. Return the created
     * event as a result of this function. If the creation of the event fails, null
     * will be returned.
     */
    public Event read(String etag) {
        try {
            File eventDirFile = this.EVENT_DIR.toFile();

            if (!eventDirFile.exists())
                return null;

            File[] listFiles = eventDirFile.listFiles((dir, name) -> {
                String lowercaseName = name.toLowerCase();
                if (Strings.isNullOrEmpty(lowercaseName))
                    return false;

                return lowercaseName.contains(etag) && lowercaseName.endsWith(FILE_ENDING);
            });

            if (listFiles == null || listFiles.length != 1)
                return null;

            List<String> allLines = Files.readAllLines(listFiles[0].toPath(), StandardCharsets.UTF_8);
            if (allLines == null || allLines.size() < 2)
                return null;

            Metadata metadata = new Metadata(etag);
            EventType eventType = EventType.fromString(allLines.get(0));
            HashMap<String, String> fields = new HashMap<>(allLines.size() - 1);

            for (int i = 1; i < allLines.size(); i++) {
                String[] split = allLines.get(i).split(INFORMATION_SEPARATOR);
                if (split.length != 2)
                    continue;

                String key = split[0];
                String value = split[1];
                fields.put(key, value);
            }
            return Events.create(eventType, metadata, fields);
        } catch (IOException | ParseException e) {
            // TODO nina log and handle exception
            return null;
        }
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
                continue;

            String etag = name.substring(0, name.length() - FILE_ENDING.length());
            try {
                Metadata metadata = new Metadata(etag);
                result.add(metadata);
            } catch (IllegalArgumentException | ParseException e) {
                // TODO nina log and handle exception(s)
            }
        }

        Collections.sort(result);
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
            Files.write(eventFile.toPath(), strBuilder.toString().getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}