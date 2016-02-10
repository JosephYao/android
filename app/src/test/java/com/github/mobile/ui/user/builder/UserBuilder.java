package com.github.mobile.ui.user.builder;

import org.eclipse.egit.github.core.User;

public class UserBuilder {

    private String loginUserName;

    public User build() {
        User user = new User();
        user.setLogin(loginUserName);
        return user;
    }

    public UserBuilder withLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
        return this;
    }
}
