package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.MemberPayload;

public class MemberPayloadBuilder implements PayloadBuilder {
    private UserBuilder userBuilder;
    private String memberLogin;

    @Override
    public EventPayload build() {
        MemberPayload stubPayload = mock(MemberPayload.class);
        User stubUser = userBuilder.withLoginUserName(memberLogin).build();
        when(stubPayload.getMember()).thenReturn(stubUser);
        return stubPayload;
    }

    public MemberPayloadBuilder defaultStubPayload() {
        userBuilder = new UserBuilder().defaultStubUser();
        return this;
    }

    public MemberPayloadBuilder withMember(String loginUserName) {
        this.memberLogin = loginUserName;
        return this;
    }
}
