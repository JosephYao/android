package com.github.mobile.ui.team;

import com.github.mobile.ui.StyledText;

public class NonEmptyNameTeam implements Team {
    private String name;

    public NonEmptyNameTeam(String name) {
        this.name = name;
    }

    @Override
    public void render(StyledText text) {
        text.append(' ').bold(name);
    }

}
