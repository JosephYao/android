package com.github.mobile.ui.user.event;

import com.github.mobile.ui.team.Team;
import com.github.mobile.ui.user.display.TextDisplay;
import com.github.mobile.ui.user.target.Target;
import com.github.mobile.ui.user.user.User;
import com.github.mobile.util.TypefaceUtils;

public class TeamAddUserEvent extends DisplaysUserEvent {
    public TeamAddUserEvent(User actor, Target target, Team team) {
        super(TypefaceUtils.ICON_ADD_MEMBER);
        addDisplayToMain(actor);
        addDisplayToMain(new TextDisplay(" added "));
        addDisplayToMain(target);
        addDisplayToMain(new TextDisplay(" to team"));
        addDisplayToMain(team);
    }
}
