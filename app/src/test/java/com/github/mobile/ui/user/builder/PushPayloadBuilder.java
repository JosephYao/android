package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.PushPayload;

public class PushPayloadBuilder implements PayloadBuilder {
    private String ref;

    public PushPayloadBuilder defaultStubPayload() {
        this.ref = "ref";
        return this;
    }

    @Override
    public EventPayload build() {
        PushPayload stubPayload = mock(PushPayload.class);
        when(stubPayload.getRef()).thenReturn(ref);
        return stubPayload;
    }
}
