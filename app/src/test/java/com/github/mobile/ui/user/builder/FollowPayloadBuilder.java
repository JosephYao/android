package com.github.mobile.ui.user.builder;

import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.FollowPayload;

public class FollowPayloadBuilder implements PayloadBuilder {
    private UserBuilder userBuilder = new UserBuilder();

    @Override
    public EventPayload build() {
        FollowPayload followPayload = new FollowPayload();
        followPayload.setTarget(userBuilder.build());
        return followPayload;
    }

    public FollowPayloadBuilder withTarget(String target) {
        userBuilder.withLoginUserName(target);
        return this;
    }
}
