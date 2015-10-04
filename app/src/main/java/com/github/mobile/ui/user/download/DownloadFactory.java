package com.github.mobile.ui.user.download;

import static com.github.mobile.ui.user.FactoryUtils.isTrimmedTextEmpty;

import org.eclipse.egit.github.core.event.DownloadPayload;

public class DownloadFactory {

    public static Download create(DownloadPayload payload) {
        org.eclipse.egit.github.core.Download download = payload.getDownload();

        if (download == null || isTrimmedTextEmpty(download.getName()))
            return new EmptyDownload();

        return new NonEmptyDownload(download.getName());
    }

}
