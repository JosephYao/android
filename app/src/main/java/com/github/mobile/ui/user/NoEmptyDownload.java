package com.github.mobile.ui.user;

import com.github.mobile.ui.StyledText;

import org.eclipse.egit.github.core.event.DownloadPayload;

public class NoEmptyDownload implements Download {
    private final String name;

    public NoEmptyDownload(DownloadPayload payload) {
        this.name = payload.getDownload().getName();
    }

    @Override
    public void render(StyledText text) {
        text.append(name.trim());
    }

}
