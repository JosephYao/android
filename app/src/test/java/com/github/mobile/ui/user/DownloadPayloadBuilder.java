package com.github.mobile.ui.user;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.Download;
import org.eclipse.egit.github.core.event.DownloadPayload;
import org.eclipse.egit.github.core.event.EventPayload;

public class DownloadPayloadBuilder implements PayloadBuilder {
    private String download;

    @Override
    public EventPayload build() {
        DownloadPayload stubPayload = mock(DownloadPayload.class);
        Download stubDownload = stubDownload();
        when(stubPayload.getDownload()).thenReturn(stubDownload);
        return stubPayload;
    }

    private Download stubDownload() {
        Download stubDownload = mock(Download.class);
        when(stubDownload.getName()).thenReturn(this.download);
        return stubDownload;
    }

    public DownloadPayloadBuilder defaultStubPayload() {
        return this;
    }

    public PayloadBuilder withDownload(String download) {
        this.download = download;
        return this;
    }
}
