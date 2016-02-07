package com.github.mobile.ui.user.event;

import com.github.mobile.ui.user.display.TextDisplay;
import com.github.mobile.ui.user.ref.PayloadRef;
import com.github.mobile.ui.user.user.User;
import com.github.mobile.util.TypefaceUtils;

public class CreateUserEvent extends DisplaysUserEvent {

    public CreateUserEvent(User actor, PayloadRef payloadRef) {
        super(TypefaceUtils.ICON_CREATE);
        addDisplayToMain(actor);
        addDisplayToMain(new TextDisplay(" created "));
        addDisplayToMain(payloadRef);
    }

}
