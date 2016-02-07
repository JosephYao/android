package com.github.mobile.ui.user.event;

import com.github.mobile.ui.user.display.TextDisplay;
import com.github.mobile.ui.user.repo.Repo;
import com.github.mobile.ui.user.user.User;
import com.github.mobile.util.TypefaceUtils;

public class GollumUserEvent extends DisplaysUserEvent {
    public GollumUserEvent(User actor, Repo repo) {
        super(TypefaceUtils.ICON_WIKI);
        addDisplayToMain(actor);
        addDisplayToMain(new TextDisplay(" updated the wiki in "));
        addDisplayToMain(repo);
    }
}
