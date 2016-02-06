package com.github.mobile.ui.user.event;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.ref.PayloadRef;
import com.github.mobile.ui.user.user.User;
import com.github.mobile.util.TypefaceUtils;

public class CreateUserEvent implements UserEvent {
    private final User actor;
    private final PayloadRef payloadRef;

    public CreateUserEvent(User actor, PayloadRef payloadRef) {
        this.actor = actor;
        this.payloadRef = payloadRef;
    }

    @Override
    public String generate(StyledText main, StyledText details) {
        actor.render(main);
        main.append(" created ");
        payloadRef.render(main);
        return TypefaceUtils.ICON_CREATE;
    }
}
