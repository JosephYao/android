package com.github.mobile.ui.user.builder;

import org.eclipse.egit.github.core.Team;

public class NullTeamBuilder extends TeamBuilder {
    @Override
    public Team build() {
        return null;
    }
}
