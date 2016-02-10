package com.github.mobile.ui.user.builder;

import org.eclipse.egit.github.core.event.CreatePayload;
import org.eclipse.egit.github.core.event.EventPayload;

public class CreatePayloadBuilder implements PayloadBuilder {

    private String refType = "refType";
    private String ref;

    public EventPayload build() {
        CreatePayload createPayload = new CreatePayload();
        createPayload.setRefType(refType);
        createPayload.setRef(ref);
        return createPayload;
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
