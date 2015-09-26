package com.github.mobile.ui.user.download;

import static com.github.mobile.ui.user.FactoryUtils.isTrimmedTextNotEmpty;

import org.eclipse.egit.github.core.event.DownloadPayload;

public class DownloadFactory {

    public static Download create(DownloadPayload payload) {
        if (payload.getDownload() == null || isTrimmedTextNotEmpty(payload.getDownload().getName()))
            return new EmptyDownload();

        return new NoEmptyDownload(payload.getDownload().getName());
    }

}
