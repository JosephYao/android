package com.github.mobile.ui.user.event;

import com.github.mobile.ui.user.display.TextDisplay;
import com.github.mobile.ui.user.download.Download;
import com.github.mobile.ui.user.repo.Repo;
import com.github.mobile.ui.user.user.User;
import com.github.mobile.util.TypefaceUtils;

public class DownloadUserEvent extends DisplaysUserEvent {
    public DownloadUserEvent(User actor, Repo repo, Download download) {
        super(TypefaceUtils.ICON_UPLOAD);
        addDisplayToMain(actor);
        addDisplayToMain(new TextDisplay(" uploaded a file to "));
        addDisplayToMain(repo);
        addDisplayToDetails(download);
    }
}
