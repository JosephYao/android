package com.github.mobile.ui.user;

import static org.mockito.Mockito.mock;

import org.eclipse.egit.github.core.event.CreatePayload;
import org.eclipse.egit.github.core.event.EventPayload;

public class CreatePayloadBuilder implements PayloadBuilder {

    public CreatePayloadBuilder defaultStubPayload() {
        return this;
    }

    public EventPayload build() {
        return mock(CreatePayload.class);
    }
}
