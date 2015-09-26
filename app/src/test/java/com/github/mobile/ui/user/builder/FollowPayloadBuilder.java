package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;

import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.FollowPayload;

public class FollowPayloadBuilder implements PayloadBuilder {
    @Override
    public EventPayload build() {
        FollowPayload stubPayload = mock(FollowPayload.class);
        return stubPayload;
    }

    public FollowPayloadBuilder defaultStubPayload() {
        return this;
    }
}
