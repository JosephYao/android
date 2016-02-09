package com.github.mobile.ui.user.ref;

import com.github.mobile.ui.user.repo.Repo;
import com.github.mobile.ui.user.repo.RepoFactory;

import org.eclipse.egit.github.core.event.CreatePayload;
import org.eclipse.egit.github.core.event.DeletePayload;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.PushPayload;

public class PayloadRefFactory {

    public static PayloadRef create(Event event) {
        if (event.getPayload() instanceof CreatePayload)
            return createFromCreatePayload((CreatePayload) event.getPayload(), createRepo(event));

        if (event.getPayload() instanceof DeletePayload)
            return createFromDeletePayload((DeletePayload) event.getPayload());

        return createFromPushPayloadRef(((PushPayload) event.getPayload()).getRef());
    }

    private static PayloadRef createFromCreatePayload(CreatePayload payload, Repo repo) {
        String refType = payload.getRefType();

        if (refType.equals("repository"))
            return new RepositoryRef(refType, repo);
        else
            return new NonRepositoryRef(refType, payload.getRef(), repo);
    }

    private static Repo createRepo(Event event) {
        return RepoFactory.createRepoFromRefType(event.getRepo(), ((CreatePayload) event.getPayload()).getRefType());
    }

    private static PayloadRef createFromDeletePayload(DeletePayload payload) {
        return new DeleteRef(payload.getRefType(), payload.getRef());
    }

    private static PayloadRef createFromPushPayloadRef(String ref) {
        if (ref.startsWith("refs/heads/"))
            return new PushHeadsRef(ref);
        else
            return new PushNonHeadsRef(ref);
    }
}
