package com.github.mobile.ui.user.download;

import static com.github.mobile.ui.user.FactoryUtils.isTrimmedTextEmpty;

import org.eclipse.egit.github.core.event.DownloadPayload;
import org.eclipse.egit.github.core.event.Event;

public class DownloadFactory {

    public static Download create(Event event) {
        org.eclipse.egit.github.core.Download download = ((DownloadPayload)event.getPayload()).getDownload();

        if (download == null || isTrimmedTextEmpty(download.getName()))
            return new EmptyDownload();
        else
            return new NonEmptyDownload(download.getName());
    }

}
