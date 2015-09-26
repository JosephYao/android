package com.github.mobile.ui.user;

import com.github.mobile.ui.StyledText;

public class NonEmptyUser implements User {
    private final String login;

    public NonEmptyUser(String login) {
        this.login = login;
    }

    @Override
    public void render(StyledText text) {
        text.bold(login);
    }
}
