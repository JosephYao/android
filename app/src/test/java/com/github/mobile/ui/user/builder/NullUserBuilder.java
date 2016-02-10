package com.github.mobile.ui.user.builder;

import org.eclipse.egit.github.core.User;

public class NullUserBuilder extends UserBuilder {
    @Override
    public User build() {
        return null;
    }
}
