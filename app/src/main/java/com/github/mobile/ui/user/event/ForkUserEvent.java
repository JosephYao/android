package com.github.mobile.ui.user.event;

import com.github.mobile.ui.user.display.TextDisplay;
import com.github.mobile.ui.user.repo.Repo;
import com.github.mobile.ui.user.user.User;
import com.github.mobile.util.TypefaceUtils;

public class ForkUserEvent extends DisplaysUserEvent {
    public ForkUserEvent(User actor, Repo repo) {
        super(TypefaceUtils.ICON_FORK);
        addDisplayToMain(actor);
        addDisplayToMain(new TextDisplay(" forked repository "));
        addDisplayToMain(repo);
    }
}
