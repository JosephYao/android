package com.github.mobile.ui.user.event;

import com.github.mobile.ui.user.action.Action;
import com.github.mobile.ui.user.display.CharDisplay;
import com.github.mobile.ui.user.user.User;

public class GistUserEvent extends DisplaysUserEvent {
    public GistUserEvent(User actor, Action action) {
        super(action.getIcon());
        addDisplayToMain(actor);
        addDisplayToMain(new CharDisplay(' '));
        addDisplayToMain(action);
    }
}
