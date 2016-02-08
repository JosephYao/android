package com.github.mobile.ui.user.user;

import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.FollowPayload;
import org.eclipse.egit.github.core.event.MemberPayload;

public class UserFactory {

    public static User createMember(Event event) {
        return create(((MemberPayload) event.getPayload()).getMember());
    }

    public static User createFollowee(Event event) {
        return create(((FollowPayload) event.getPayload()).getTarget());
    }

    public static User createActor(Event event) {
        return create(event.getActor());
    }

    public static User create(org.eclipse.egit.github.core.User user) {
        if (user == null)
            return new EmptyUser();

        return new NonEmptyUser(user.getLogin());
    }
}
