package de.nist.timing.events;

import java.util.Date;
import java.util.UUID;

public class Metadata {

    private Date date;
    private UUID uuid;
    private String etag;

    public Metadata() {
        this.date = new Date();
        this.uuid = UUID.randomUUID();
        this.etag = newEtag();
    }

    public Metadata(Date date, UUID uuid, String etag) {
        this.date = date;
        this.uuid = uuid;
        this.etag = etag;
    }

    public Date getDate() {
        return date;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getEtag() {
        return etag;
    }

    private String newEtag() {
        return "W" + getDate().toString() + getUuid().toString().hashCode();
    }
}