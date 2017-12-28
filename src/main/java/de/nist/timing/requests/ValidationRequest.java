package de.nist.timing.requests;

import de.nist.timing.domain.Calendar;
import de.nist.timing.events.Metadata;
import de.nist.timing.repositories.SnapshotRepository;

public class ValidationRequest extends Request {

    public ValidationRequest(Metadata metadata, RequestType requestType) {
        super(metadata, requestType);
    }

    @Override
    public Response<?> execute() {
        String etag = this.getMetadata().getEtag();
        Calendar calendar = new SnapshotRepository().read(etag);
        Boolean valid = new Validator().isValid(calendar);
        Response<Boolean> response = new Response<Boolean>(etag, valid);
        return response;
    }
}