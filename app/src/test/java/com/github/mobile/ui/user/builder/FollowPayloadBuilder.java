package com.github.mobile.ui.user.builder;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.FollowPayload;

public class FollowPayloadBuilder implements PayloadBuilder {
    private UserBuilder userBuilder;

    @Override
    public EventPayload build() {
        FollowPayload followPayload = new FollowPayload();
        User stubUser = userBuilder.build();
        followPayload.setTarget(stubUser);
        return followPayload;
    }

    public FollowPayloadBuilder() {
        userBuilder = new UserBuilder().defaultStubUser();
    }

    public FollowPayloadBuilder withTarget(String target) {
        userBuilder.withLoginUserName(target);
        return this;
    }
}
