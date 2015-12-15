package com.github.mobile.ui.target;

import com.github.mobile.ui.user.user.UserFactory;

import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.TeamAddPayload;

public class TargetFactory {
    public static Target create(TeamAddPayload payload, Event event) {
        if (payload.getUser() != null)
            return new UserTarget(UserFactory.create(payload.getUser()));

        return new RepoTarget(event);
    }
}
