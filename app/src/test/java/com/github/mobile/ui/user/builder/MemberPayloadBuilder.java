package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.MemberPayload;

public class MemberPayloadBuilder implements PayloadBuilder {
    private String memberLogin;

    @Override
    public EventPayload build() {
        MemberPayload stubPayload = mock(MemberPayload.class);
        User stubUser = stubUser();
        when(stubPayload.getMember()).thenReturn(stubUser);
        return stubPayload;
    }

    private User stubUser() {
        User stubUser = mock(User.class);
        when(stubUser.getLogin()).thenReturn(memberLogin);
        return stubUser;
    }

    public MemberPayloadBuilder defaultStubPayload() {
        return this;
    }

    public MemberPayloadBuilder withMember(String loginUserName) {
        this.memberLogin = loginUserName;
        return this;
    }
}
