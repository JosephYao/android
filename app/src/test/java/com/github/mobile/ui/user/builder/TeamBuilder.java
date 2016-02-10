package com.github.mobile.ui.user.builder;

import org.eclipse.egit.github.core.Team;

public class TeamBuilder {
    private String name;

    public Team build() {
        Team team = new Team();
        team.setName(name);
        return team;
    }

    public TeamBuilder withName(String name) {
        this.name = name;
        return this;
    }
}
