package de.nist.timing.requests;

import de.nist.timing.events.Metadata;

public abstract class Request {
    private final Metadata metadata;
    private final RequestType requestType;

    public Request(Metadata metadata, RequestType requestType) {
        this.metadata = metadata;
        this.requestType = requestType;
    }

    public Metadata getMetadata() {
        return this.metadata;
    }

    public RequestType getRequestType() {
        return this.requestType;
    }

    public abstract Response<?> execute();
}
