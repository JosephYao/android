package com.github.mobile.ui.user.ref;

import com.github.mobile.ui.user.repo.RepoFactory;

import org.eclipse.egit.github.core.event.CreatePayload;
import org.eclipse.egit.github.core.event.DeletePayload;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.EventRepository;
import org.eclipse.egit.github.core.event.PushPayload;

public class PayloadRefFactory {

    public static PayloadRef create(Event event) {
        if (event.getPayload() instanceof CreatePayload)
            return createFromCreatePayload((CreatePayload) event.getPayload(), event.getRepo());

        if (event.getPayload() instanceof DeletePayload)
            return createFromDeletePayload((DeletePayload) event.getPayload());

        return createFromPushPayload((PushPayload) event.getPayload());
    }

    public static PayloadRef createFromCreatePayload(CreatePayload payload, EventRepository repo) {
        String refType = payload.getRefType();

        if (refType.equals("repository"))
            return new RepositoryRef(refType, RepoFactory.createRepoFromRefType(repo, refType));
        else
            return new NonRepositoryRef(refType, payload.getRef(), RepoFactory.createRepoFromRefType(repo, refType));
    }

    public static PayloadRef createFromDeletePayload(DeletePayload payload) {
        return new DeleteRef(payload.getRefType(), payload.getRef());
    }

    public static PayloadRef createFromPushPayload(PushPayload payload) {
        String ref = payload.getRef();

        if (ref.startsWith("refs/heads/"))
            return new PushHeadsRef(ref);

        return new PushNonHeadsRef(ref);
    }
}
