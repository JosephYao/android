package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.FollowPayload;

public class FollowPayloadBuilder implements PayloadBuilder {
    private UserBuilder userBuilder;
    private String target;

    @Override
    public EventPayload build() {
        FollowPayload stubPayload = mock(FollowPayload.class);
        User stubUser = userBuilder.withLoginUserName(target).build();
        when(stubPayload.getTarget()).thenReturn(stubUser);
        return stubPayload;
    }

    public FollowPayloadBuilder defaultStubPayload() {
        userBuilder = new UserBuilder().defaultStubUser();
        return this;
    }

    public FollowPayloadBuilder withTarget(String target) {
        this.target = target;
        return this;
    }
}
