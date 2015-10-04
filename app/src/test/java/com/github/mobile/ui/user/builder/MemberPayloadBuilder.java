package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;

import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.MemberPayload;

public class MemberPayloadBuilder implements PayloadBuilder {
    @Override
    public EventPayload build() {
        MemberPayload stubPayload = mock(MemberPayload.class);
        return stubPayload;
    }

    public MemberPayloadBuilder defaultStubPayload() {
        return this;
    }
}
