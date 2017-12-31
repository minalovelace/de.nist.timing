package de.nist.timing.repositories;

import static de.nist.timing.settings.RepositorySettings.FILE_ENDING;
import static de.nist.timing.settings.RepositorySettings.INFORMATION_SEPARATOR;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.base.Strings;

import de.nist.timing.domain.Calendar;
import de.nist.timing.domain.Entries;
import de.nist.timing.domain.Entry;
import de.nist.timing.domain.EntryType;
import de.nist.timing.events.Event;
import de.nist.timing.events.Metadata;
import de.nist.timing.settings.AppSettings;

/*
 * This repository creates, reads, updates, and deletes snapshots. 
 */
public class SnapshotRepository {
    private final Path SNAPSHOT_DIR = Paths.get(AppSettings.WH_CAL_DATA_FOLDER, AppSettings.SNAPSHOT_REPOSITORY)
            .toAbsolutePath();

    private HashMap<String, String> faultedReason = new HashMap<>();

    public Calendar read(String etag) {
        try {
            File snapshotDirFile = this.SNAPSHOT_DIR.toFile();

            /* Try to create a snapshot if it does not exist. */
            if (!snapshotDirFile.exists())
                return tryCreateSnapshot(etag);

            File[] listFiles = snapshotDirFile.listFiles((dir, name) -> {
                if (Strings.isNullOrEmpty(name))
                    return false;

                return name.contains(etag) && name.endsWith(FILE_ENDING);
            });

            if (listFiles == null || listFiles.length != 1)
                return tryCreateSnapshot(etag);

            /*
             * Read snapshot from disk and create a calendar. Return the created calendar as
             * a result of this function.
             */
            List<String> allLines = Files.readAllLines(listFiles[0].toPath(), StandardCharsets.UTF_8);
            if (allLines == null || allLines.size() < 4)
                return tryCreateSnapshot(etag);

            HashMap<String, String[]> serializedSnapshot = readSnapshotFileContent(allLines);
            return createCalendarFromSnapshot(serializedSnapshot);

        } catch (IOException e) {
            this.faultedReason.put(etag, "An IOException occurred in the Snapshot Repository:" + e.getMessage());
            return null;
        }
    }

    public Boolean isFaulted(String etag) {
        return !Strings.isNullOrEmpty(getFaultedReason(etag));
    }

    public Boolean write(Calendar calendar, String etag) {
        // TODO nina implement this method
        return false;
    }

    private Calendar createCalendarFromSnapshot(HashMap<String, String[]> serializedSnapshot) {
        Integer year = null;
        Integer delta = null;
        Set<Entry> entries = new HashSet<>();

        for (String key : serializedSnapshot.keySet()) {
            if ("delta".equals(key)) {
                delta = Integer.parseInt(serializedSnapshot.get("delta")[0]);
                continue;
            }
            if ("year".equals(key)) {
                year = Integer.parseInt(serializedSnapshot.get("year")[0]);
                continue;
            }
            entries.add(readEntryFromString(serializedSnapshot.get(key)));
        }

        return new Calendar(year, delta, entries);
    }

    private Entry readEntryFromString(String[] strings) {
        if (strings == null || strings.length < 4)
            return null;

        EntryType entryType = EntryType.fromString(strings[0]);
        Integer year = Integer.decode(strings[1]);
        Integer month = Integer.decode(strings[2]);
        Integer day = Integer.decode(strings[3]);

        return Entries.create(entryType, year, month, day, Arrays.copyOfRange(strings, 4, strings.length));
    }

    private HashMap<String, String[]> readSnapshotFileContent(List<String> allLines) {
        HashMap<String, String[]> serializedSnapshot = new HashMap<>();
        for (String line : allLines) {
            if (Strings.isNullOrEmpty(line))
                continue;

            String[] split = line.split(INFORMATION_SEPARATOR);
            if (split.length > 1) {
                String key = split[0];
                String[] value = Arrays.copyOfRange(split, 1, split.length);
                serializedSnapshot.put(key, value);
            }
        }
        return serializedSnapshot;
    }

    private Calendar tryCreateSnapshot(String etag) {
        /*
         * Improvement: Get the snapshot before the requested one and apply the needed
         * events.
         */
        EventRepository eventRepository = new EventRepository();
        List<Metadata> metadataList = eventRepository.peek();
        List<String> etagList = new ArrayList<>(metadataList.size());
        metadataList.forEach(m -> etagList.add(m.getEtag()));
        if (!etagList.contains(etag))
            return null;

        Calendar result = null;

        for (String etagInList : etagList) {
            Event event = eventRepository.read(etag);
            result = event.apply(result);

            if (etag.equals(etagInList))
                break;
        }

        // Improvement: Run the write operation on a thread in the background.
        if (result != null)
            write(result, etag);

        return result;
    }

    public String getFaultedReason(String etag) {
        return this.faultedReason.get(etag);
    }
}
