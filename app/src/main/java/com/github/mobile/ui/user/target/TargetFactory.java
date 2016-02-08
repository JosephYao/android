package com.github.mobile.ui.user.target;

import com.github.mobile.ui.user.repo.RepoFactory;
import com.github.mobile.ui.user.user.UserFactory;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.TeamAddPayload;

public class TargetFactory {
    public static Target create(Event event) {
        if (event.getPayload() instanceof TeamAddPayload && user(event) != null)
            return UserFactory.create(user(event));

        return RepoFactory.createRepositoryRepo(event);
    }

    private static User user(Event event) {
        return ((TeamAddPayload)event.getPayload()).getUser();
    }
}
