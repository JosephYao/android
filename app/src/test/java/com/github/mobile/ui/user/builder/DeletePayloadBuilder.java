package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.event.DeletePayload;
import org.eclipse.egit.github.core.event.EventPayload;

public class DeletePayloadBuilder implements PayloadBuilder {
    private String refType;
    private String ref;

    @Override
    public EventPayload build() {
        DeletePayload stubPayload = mock(DeletePayload.class);
        when(stubPayload.getRefType()).thenReturn(this.refType);
        when(stubPayload.getRef()).thenReturn(this.ref);
        return stubPayload;
    }

    public DeletePayloadBuilder defaultStubPayload() {
        return this;
    }

    public DeletePayloadBuilder withRefType(String refType) {
        this.refType = refType;
        return this;
    }

    public DeletePayloadBuilder withRef(String ref) {
        this.ref = ref;
        return this;
    }
}
