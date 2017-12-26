package de.nist.timing.events;

import java.util.HashMap;

public class EventVisitor {
    private HashMap<String, String> fields = new HashMap<>();
    private EventType eventType;
    private Metadata metadata;

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public EventType getEventType() {
        return this.eventType;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Metadata getMetadata() {
        return this.metadata;
    }

    public void setField(String name, String value) {
        this.fields.put(name, value);
    }

    public HashMap<String, String> getFields() {
        return new HashMap<>(this.fields);
    }
}