package com.github.mobile.ui.user.ref;

import com.github.mobile.ui.user.repo.RepoFactory;

import org.eclipse.egit.github.core.event.CreatePayload;
import org.eclipse.egit.github.core.event.DeletePayload;
import org.eclipse.egit.github.core.event.EventRepository;

public class PayloadRefFactory {

    public static PayloadRef createFromCreate(CreatePayload payload, EventRepository repo) {
        if (payload.getRefType().equals("repository"))
            return new RepositoryRef(payload.getRefType(),
                    RepoFactory.createRepoFromEventRepositoryAndRefType(repo, payload.getRefType()));
        else
            return new NonRepositoryRef(payload.getRefType(), payload.getRef(),
                    RepoFactory.createRepoFromEventRepositoryAndRefType(repo, payload.getRefType()));
    }

    public static PayloadRef createFromDelete(DeletePayload payload) {
        return new DeleteRef(payload.getRefType(), payload.getRef());
    }
}
