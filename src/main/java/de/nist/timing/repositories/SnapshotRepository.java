package de.nist.timing.repositories;

import static org.junit.jupiter.api.DynamicTest.stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import com.google.common.base.Strings;

import de.nist.timing.domain.Calendar;
import de.nist.timing.events.EventVisitor;
import de.nist.timing.settings.AppSettings;

/*
 * This repository creates, reads, updates, and deletes snapshots. 
 */
public class SnapshotRepository {
    private final Path SNAPSHOT_DIR = Paths.get(AppSettings.WH_CAL_DATA_FOLDER, AppSettings.SNAPSHOT_REPOSITORY)
            .toAbsolutePath();
    private final String FILE_ENDING = ".txt";

    private HashMap<String, String> faultedReason = new HashMap<>();

    public Calendar read(String etag) {
        try {
            File snapshotDirFile = this.SNAPSHOT_DIR.toFile();

            /* Try to create a snapshot if it does not exist. */
            if (!snapshotDirFile.exists())
                return tryCreateSnapshot(etag);

            File[] listFiles = snapshotDirFile.listFiles((dir, name) -> {
                String lowercaseName = name.toLowerCase();
                if (Strings.isNullOrEmpty(lowercaseName))
                    return false;

                return lowercaseName.contains(etag) && lowercaseName.endsWith(FILE_ENDING);
            });

            if (listFiles == null || listFiles.length != 1)
                return tryCreateSnapshot(etag);

            /*
             * Read snapshot from disk and create a calendar. Return the created calendar as
             * a result of the execution.
             */
            // TODO nina read file and create calendar.
            File snapshotFile = listFiles[0];
            List<String> allLines = Files.readAllLines(snapshotFile.toPath(), StandardCharsets.UTF_8);
            if (allLines == null || allLines.size() < 4)
                return tryCreateSnapshot(etag);

            HashMap<String, String> serializedSnapshot = new HashMap<>();
            for (String line : allLines) {
                if (line.contains("=") && line.split("[=]").length > 1) {
                    String key = line.split("[=]")[0];
                    String value = line.split("[=]")[1];
                    serializedSnapshot.put(key, value);
                }
            }

        } catch (IOException e) {
            this.faultedReason.put(etag, "An IOException occurred in the Snapshot Repository.");
        }
        return null;
    }

    private Calendar tryCreateSnapshot(String etag) {
        // TODO Auto-generated method stub
        return null;
    }

    public Boolean isFaulted(String etag) {
        return !Strings.isNullOrEmpty(getFaultedReason(etag));
    }

    public String getFaultedReason(String etag) {
        return this.faultedReason.get(etag);
    }

    public Boolean write(Calendar calendar, String etag) {
        // TODO nina implement this method
        return false;
    }
}