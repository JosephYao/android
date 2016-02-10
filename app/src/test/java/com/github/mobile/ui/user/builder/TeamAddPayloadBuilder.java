package com.github.mobile.ui.user.builder;

import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.TeamAddPayload;

public class TeamAddPayloadBuilder implements PayloadBuilder {
    private UserBuilder userBuilder = new NullUserBuilder();
    private TeamBuilder teamBuilder = new NullTeamBuilder();

    @Override
    public EventPayload build() {
        TeamAddPayload teamAddPayload = new TeamAddPayload();
        teamAddPayload.setUser(userBuilder.build());
        teamAddPayload.setTeam(teamBuilder.build());
        return teamAddPayload;
    }

    public TeamAddPayloadBuilder withUser(String name) {
        userBuilder = new UserBuilder().withLoginUserName(name);
        return this;
    }

    public TeamAddPayloadBuilder withTeam(String name) {
        teamBuilder = new TeamBuilder().withName(name);
        return this;
    }
}
