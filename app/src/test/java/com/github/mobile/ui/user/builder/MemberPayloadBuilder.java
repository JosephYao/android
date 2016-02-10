package com.github.mobile.ui.user.builder;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.MemberPayload;

public class MemberPayloadBuilder implements PayloadBuilder {
    private UserBuilder userBuilder = new UserBuilder();

    @Override
    public EventPayload build() {
        MemberPayload memberPayload = new MemberPayload();
        memberPayload.setMember(member());
        return memberPayload;
    }

    private User member() {
        return userBuilder.build();
    }

    public MemberPayloadBuilder withMember(String loginUserName) {
        userBuilder.withLoginUserName(loginUserName);
        return this;
    }
}
