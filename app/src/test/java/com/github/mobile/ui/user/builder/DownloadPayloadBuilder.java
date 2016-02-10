package com.github.mobile.ui.user.builder;

import org.eclipse.egit.github.core.Download;
import org.eclipse.egit.github.core.event.DownloadPayload;
import org.eclipse.egit.github.core.event.EventPayload;

public class DownloadPayloadBuilder implements PayloadBuilder {
    private String download;

    @Override
    public EventPayload build() {
        DownloadPayload downloadPayload = new DownloadPayload();
        downloadPayload.setDownload(aDownload());
        return downloadPayload;
    }

    private Download aDownload() {
        Download download = new Download();
        download.setName(this.download);
        return download;
    }

    public PayloadBuilder withDownload(String download) {
        this.download = download;
        return this;
    }
}
