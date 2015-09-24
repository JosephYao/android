package com.github.mobile.ui.user;

import static org.mockito.Mockito.mock;

import org.eclipse.egit.github.core.event.DeletePayload;
import org.eclipse.egit.github.core.event.EventPayload;

public class DeletePayloadBuilder implements PayloadBuilder {
    @Override
    public EventPayload build() {
        DeletePayload stubPayload = mock(DeletePayload.class);
        return stubPayload;
    }

    public DeletePayloadBuilder defaultStubPayload() {
        return this;
    }
}
