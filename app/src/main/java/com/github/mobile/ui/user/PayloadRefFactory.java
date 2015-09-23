package com.github.mobile.ui.user;

import android.text.TextUtils;

import org.eclipse.egit.github.core.event.CreatePayload;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.EventRepository;

public class PayloadRefFactory {

    public static PayloadRef create(EventPayload payload, EventRepository repo) {
        CreatePayload createPayload = (CreatePayload) payload;

        if (createPayload.getRefType().equals("repository"))
            return new RepositoryRef(createPayload.getRefType(),
                    createRepo(createPayload, repo));
        else
            return new NonRepositoryRef(createPayload.getRefType(), createPayload.getRef(),
                    createRepo(createPayload, repo));
    }

    private static Repo createRepo(CreatePayload createPayload, EventRepository repo) {
        if (repo == null || TextUtils.isEmpty(repo.getName()))
            return new EmptyRepo();

        if (createPayload.getRefType().equals("repository"))
            return new RepositoryRepo(repo);
        else
            return new NonRepositoryRepo(repo);
    }

}
