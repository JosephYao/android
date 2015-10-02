package com.github.mobile.ui.user.user;

import org.eclipse.egit.github.core.User;

public class UserFactory {

    public static com.github.mobile.ui.user.user.User create(User user) {
        if (user == null)
            return new EmptyUser();

        return new NonEmptyUser(user.getLogin());
    }
}
