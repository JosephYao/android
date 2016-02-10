package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.Team;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.TeamAddPayload;

public class TeamAddPayloadBuilder implements PayloadBuilder {
    private String userName;
    private String teamName;

    @Override
    public EventPayload build() {
        TeamAddPayload stubPayload = new TeamAddPayload();
        stubPayload.setUser(user());
        stubPayload.setTeam(team());
        return stubPayload;
    }

    private Team team() {
        if (teamName == null)
            return null;

        Team team = mock(Team.class);
        when(team.getName()).thenReturn(teamName);
        return team;
    }

    private User user() {
        if (userName == null)
            return null;

        User stubUser = new UserBuilder().withLoginUserName(userName).build();
        return stubUser;
    }

    public TeamAddPayloadBuilder withUser(String name) {
        userName = name;
        return this;
    }

    public TeamAddPayloadBuilder withTeam(String name) {
        teamName = name;
        return this;
    }
}
