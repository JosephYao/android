package com.github.mobile.ui.team;

import org.eclipse.egit.github.core.event.TeamAddPayload;

public class TeamFactory {
    public static Team create(TeamAddPayload payload) {
        return new OnlyOneTeam(payload);
    }
}
