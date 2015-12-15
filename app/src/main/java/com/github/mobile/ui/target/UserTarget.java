package com.github.mobile.ui.target;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.user.User;

public class UserTarget implements Target {
    private final User user;

    public UserTarget(User user) {
        this.user = user;
    }

    @Override
    public void render(StyledText text) {
        user.render(text);
    }
}
