package de.nist.timing.requests;

/*
 * A generic response of a command or request.
 */
public class Response<T> {
    private final String etag;
    private final T result;

    public Response(String etag, T result) {
        this.etag = etag;
        this.result = result;
    }

    public String getEtag() {
        return this.etag;
    }

    public T getResult() {
        return this.result;
    }
}