package com.github.mobile.ui.user;

import org.eclipse.egit.github.core.event.CreatePayload;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.EventRepository;

public class PayloadRefFactory {

    public static PayloadRef create(EventPayload payload, EventRepository repo) {
        CreatePayload createPayload = (CreatePayload) payload;

        if (createPayload.getRefType().equals("repository"))
            return new RepositoryRef(createPayload.getRefType(),
                    RepoFactory.createRepoFromEventRepositoryAndRefType(repo, createPayload.getRefType()));
        else
            return new NonRepositoryRef(createPayload.getRefType(), createPayload.getRef(),
                    RepoFactory.createRepoFromEventRepositoryAndRefType(repo, createPayload.getRefType()));
    }

}
