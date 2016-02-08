package com.github.mobile.ui.user.event;

import com.github.mobile.ui.user.display.TextDisplay;
import com.github.mobile.ui.user.repo.Repo;
import com.github.mobile.ui.user.user.User;
import com.github.mobile.util.TypefaceUtils;

public class WatchUserEvent extends DisplaysUserEvent {
    public WatchUserEvent(User actor, Repo repo) {
        super(TypefaceUtils.ICON_STAR);
        addDisplayToMain(actor);
        addDisplayToMain(new TextDisplay(" starred "));
        addDisplayToMain(repo);
    }
}
