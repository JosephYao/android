package com.github.mobile.ui.user;

import com.github.mobile.ui.StyledText;

public class NonRepositoryRepo implements Repo {
    private final String name;

    public NonRepositoryRepo(String name) {
        this.name = name;
    }

    @Override
    public void render(StyledText text) {
        text.bold(name);
    }
}
