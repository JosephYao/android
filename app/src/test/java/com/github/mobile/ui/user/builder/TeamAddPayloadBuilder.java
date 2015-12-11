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
        TeamAddPayload stubPayload = mock(TeamAddPayload.class);
        if (this.userName != null) {
            User stubUser = mock(User.class);
            when(stubUser.getLogin()).thenReturn(this.userName);
            when(stubPayload.getUser()).thenReturn(stubUser);
        }
        if (this.teamName != null) {
            Team team = mock(Team.class);
            when(team.getName()).thenReturn(teamName);
            when(stubPayload.getTeam()).thenReturn(team);
        }
        return stubPayload;
    }

    public TeamAddPayloadBuilder defaultStubPayload() {
        return this;
    }

    public TeamAddPayloadBuilder withUser(String userName) {
        this.userName = userName;
        return this;
    }

    public TeamAddPayloadBuilder withTeam(String name) {
        this.teamName = name;
        return this;
    }
}
