package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.User;

public class UserBuilder {

    private String loginUserName;

    public UserBuilder defaultStubUser() {
        return this;
    }

    public User build() {
        User stubUser = mock(User.class);
        when(stubUser.getLogin()).thenReturn(loginUserName);
        return stubUser;
    }

    public UserBuilder withLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
        return this;
    }
}
