package de.nist.timing.events;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.google.common.base.Strings;

public class Metadata {
    private final DateFormat formatter = new SimpleDateFormat("YYYY_MM_dd-HH_mm_ss");
    private Date date;
    private UUID uuid;
    private String etag;

    public Metadata() {
        this.date = new Date();
        this.uuid = UUID.randomUUID();
        this.etag = newEtag();
    }

    public Metadata(String etag) throws ParseException {
        if (Strings.isNullOrEmpty(etag))
            throw new IllegalArgumentException("The given etag must not be null or empty.");

        this.etag = etag;
        int indexOf = etag.indexOf("W");
        if (indexOf == -1)
            throw new IllegalArgumentException("The given etag must contain the letter 'W'.");

        this.date = formatter.parse(etag.substring(1, indexOf));
        this.uuid = UUID.fromString(etag.substring(indexOf + 1));
    }

    public Metadata(Date date, UUID uuid) {
        this.date = date;
        this.uuid = uuid;
        this.etag = newEtag();
    }

    public Date getDate() {
        return this.date;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public String getEtag() {
        return this.etag;
    }

    private String newEtag() {
        return "E" + this.formatter.format(this.date) + "W" + getUuid().toString();
    }
}