package com.github.mobile.ui.team;

import org.eclipse.egit.github.core.event.TeamAddPayload;

public class TeamFactory {
    public static Team create(TeamAddPayload payload) {
        org.eclipse.egit.github.core.Team team = payload.getTeam();
        if (team == null || team.getName() == null)
            return new EmptyTeam();

        return new NonEmptyNameTeam(team.getName());
    }

}
