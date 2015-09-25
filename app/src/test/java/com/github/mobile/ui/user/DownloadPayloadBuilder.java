package com.github.mobile.ui.user;

import static org.mockito.Mockito.mock;

import org.eclipse.egit.github.core.event.DownloadPayload;
import org.eclipse.egit.github.core.event.EventPayload;

public class DownloadPayloadBuilder implements PayloadBuilder {
    @Override
    public EventPayload build() {
        DownloadPayload stubPayload = mock(DownloadPayload.class);
        return stubPayload;
    }

    public DownloadPayloadBuilder defaultStubPayload() {
        return this;
    }
}
