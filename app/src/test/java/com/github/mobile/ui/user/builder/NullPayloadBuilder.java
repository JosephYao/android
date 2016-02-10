package com.github.mobile.ui.user.builder;

import org.eclipse.egit.github.core.event.EventPayload;

public class NullPayloadBuilder implements PayloadBuilder {
    @Override
    public EventPayload build() {
        return null;
    }
}
