package com.github.mobile.ui.user.target;

import com.github.mobile.ui.user.repo.RepoFactory;
import com.github.mobile.ui.user.user.UserFactory;

import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.TeamAddPayload;

public class TargetFactory {
    public static Target create(TeamAddPayload payload, Event event) {
        if (payload.getUser() != null)
            return UserFactory.create(payload.getUser());

        return RepoFactory.createRepoFromEventRepositoryAndRefType(event.getRepo(), "repository");
    }
}
