package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;

import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.TeamAddPayload;

public class TeamAddPayloadBuilder implements PayloadBuilder {
    @Override
    public EventPayload build() {
        TeamAddPayload stubPayload = mock(TeamAddPayload.class);
        return stubPayload;
    }

    public TeamAddPayloadBuilder defaultStubPayload() {
        return this;
    }
}
