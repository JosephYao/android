package com.github.mobile.ui.user;

import org.eclipse.egit.github.core.event.DownloadPayload;

public class DownloadFactory {

    public static Download create(DownloadPayload payload) {
        if (payload.getDownload() == null || isTrimmedNameNotEmpty(payload.getDownload().getName()))
            return new EmptyDownload();

        return new NoEmptyDownload(payload);
    }

    private static boolean isTrimmedNameNotEmpty(String name) {
        return name == null || name.trim().length() == 0;
    }

}
