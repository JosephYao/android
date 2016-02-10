package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.MemberPayload;

public class MemberPayloadBuilder implements PayloadBuilder {
    private UserBuilder userBuilder;

    @Override
    public EventPayload build() {
        MemberPayload stubPayload = mock(MemberPayload.class);
        User stubUser = userBuilder.build();
        when(stubPayload.getMember()).thenReturn(stubUser);
        return stubPayload;
    }

    public MemberPayloadBuilder defaultStubPayload() {
        userBuilder = new UserBuilder();
        return this;
    }

    public MemberPayloadBuilder withMember(String loginUserName) {
        userBuilder.withLoginUserName(loginUserName);
        return this;
    }
}
