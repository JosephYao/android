package com.github.mobile.ui.user.event;

import com.github.mobile.ui.user.ref.PayloadRef;
import com.github.mobile.ui.user.repo.Repo;
import com.github.mobile.ui.user.user.User;
import com.github.mobile.util.TypefaceUtils;

public class DeleteUserEvent extends DisplaysUserEvent {
    public DeleteUserEvent(User actor, PayloadRef payloadRef, Repo repo) {
        super(TypefaceUtils.ICON_DELETE);
        addDisplayToMain(actor);
        addDisplayToMain(payloadRef);
        addDisplayToMain(repo);
    }
}
