package com.github.mobile.ui.team;

import com.github.mobile.ui.StyledText;

import org.eclipse.egit.github.core.event.TeamAddPayload;

public class OnlyOneTeam implements Team {
    private TeamAddPayload payload;

    public OnlyOneTeam(TeamAddPayload payload) {
        this.payload = payload;
    }

    @Override
    public void render(StyledText text) {
        renderTeamName(text);
    }

    private void renderTeamName(StyledText main) {
        org.eclipse.egit.github.core.Team team = payload.getTeam();
        String teamName = team != null ? team.getName() : null;
        if (teamName != null)
            main.append(' ').bold(teamName);
    }

}
