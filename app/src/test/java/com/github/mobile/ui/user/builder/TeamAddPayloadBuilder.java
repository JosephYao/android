package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.TeamAddPayload;

public class TeamAddPayloadBuilder implements PayloadBuilder {
    private String userName;

    @Override
    public EventPayload build() {
        TeamAddPayload stubPayload = mock(TeamAddPayload.class);
        User stubUser = mock(User.class);
        when(stubUser.getLogin()).thenReturn(this.userName);
        when(stubPayload.getUser()).thenReturn(stubUser);
        return stubPayload;
    }

    public TeamAddPayloadBuilder defaultStubPayload() {
        return this;
    }

    public TeamAddPayloadBuilder withUser(String userName) {
        this.userName = userName;
        return this;
    }
}
