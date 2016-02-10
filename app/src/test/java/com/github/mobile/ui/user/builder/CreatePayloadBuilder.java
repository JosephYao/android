package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.event.CreatePayload;
import org.eclipse.egit.github.core.event.EventPayload;

public class CreatePayloadBuilder implements PayloadBuilder {

    private String refType;
    private String ref;

    public CreatePayloadBuilder() {
        this.refType = "refType";
    }

    public EventPayload build() {
        CreatePayload stubPayload = mock(CreatePayload.class);
        when(stubPayload.getRefType()).thenReturn(refType);
        when(stubPayload.getRef()).thenReturn(ref);
        return stubPayload;
    }

    public CreatePayloadBuilder withRefType(String refType) {
        this.refType = refType;
        return this;
    }

    public CreatePayloadBuilder withRef(String ref) {
        this.ref = ref;
        return this;
    }
}
