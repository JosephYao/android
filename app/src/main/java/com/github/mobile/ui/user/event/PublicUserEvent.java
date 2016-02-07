package com.github.mobile.ui.user.event;

import com.github.mobile.ui.user.display.TextDisplay;
import com.github.mobile.ui.user.repo.Repo;
import com.github.mobile.ui.user.user.User;

public class PublicUserEvent extends DisplaysUserEvent {
    public PublicUserEvent(User actor, Repo repo) {
        super(null);
        addDisplayToMain(actor);
        addDisplayToMain(new TextDisplay(" open sourced repository "));
        addDisplayToMain(repo);
    }
}
