package com.github.mobile.ui.user.event;

import com.github.mobile.ui.user.display.TextDisplay;
import com.github.mobile.ui.user.repo.Repo;
import com.github.mobile.ui.user.user.User;
import com.github.mobile.util.TypefaceUtils;

public class MemberUserEvent extends DisplaysUserEvent {
    public MemberUserEvent(User actor, User member, Repo repo) {
        addDisplayToMain(actor);
        addDisplayToMain(new TextDisplay(" added "));
        addDisplayToMain(member);
        addDisplayToMain(new TextDisplay(" as a collaborator to "));
        addDisplayToMain(repo);
        setIcon(TypefaceUtils.ICON_ADD_MEMBER);
    }
}
