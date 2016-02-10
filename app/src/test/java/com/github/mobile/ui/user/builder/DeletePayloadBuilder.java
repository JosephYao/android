package com.github.mobile.ui.user.builder;

import org.eclipse.egit.github.core.event.DeletePayload;
import org.eclipse.egit.github.core.event.EventPayload;

public class DeletePayloadBuilder implements PayloadBuilder {
    private String refType;
    private String ref;

    @Override
    public EventPayload build() {
        DeletePayload deletePayload = new DeletePayload();
        deletePayload.setRefType(refType);
        deletePayload.setRef(ref);
        return deletePayload;
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
