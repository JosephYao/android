package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;

import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.PullRequestPayload;

public class PullRequestPayloadBuilder implements PayloadBuilder {
    @Override
    public EventPayload build() {
        PullRequestPayload stubPayload = mock(PullRequestPayload.class);
        return stubPayload;
    }

    public PullRequestPayloadBuilder defaultStubPayload() {
        return this;
    }
}
