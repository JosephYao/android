package com.github.mobile.ui.team;

import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.TeamAddPayload;

public class TeamFactory {
    public static Team create(Event event) {
        org.eclipse.egit.github.core.Team team = ((TeamAddPayload)event.getPayload()).getTeam();
        if (team == null || team.getName() == null)
            return new EmptyTeam();

        return new NonEmptyNameTeam(team.getName());
    }

}
