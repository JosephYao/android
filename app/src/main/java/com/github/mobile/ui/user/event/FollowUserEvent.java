package com.github.mobile.ui.user.event;

import com.github.mobile.ui.user.display.TextDisplay;
import com.github.mobile.ui.user.user.User;
import com.github.mobile.util.TypefaceUtils;

public class FollowUserEvent extends DisplaysUserEvent {
    public FollowUserEvent(User actor, User followTarget) {
        addDisplayToMain(actor);
        addDisplayToMain(new TextDisplay(" started following "));
        addDisplayToMain(followTarget);
        setIcon(TypefaceUtils.ICON_FOLLOW);
    }
}
