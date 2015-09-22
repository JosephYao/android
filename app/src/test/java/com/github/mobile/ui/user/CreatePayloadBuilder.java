package com.github.mobile.ui.user;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.event.CreatePayload;
import org.eclipse.egit.github.core.event.EventPayload;

public class CreatePayloadBuilder implements PayloadBuilder {

    private String refType;

    public CreatePayloadBuilder defaultStubPayload() {
        return this;
    }

    public EventPayload build() {
        CreatePayload stubPayload = mock(CreatePayload.class);
        when(stubPayload.getRefType()).thenReturn(refType);
        return stubPayload;
    }

    public CreatePayloadBuilder withRefType(String refType) {
        this.refType = refType;
        return this;
    }
}
