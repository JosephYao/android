package com.github.mobile.ui.user.event;

import com.github.mobile.ui.user.commit.Commits;
import com.github.mobile.ui.user.display.TextDisplay;
import com.github.mobile.ui.user.ref.PayloadRef;
import com.github.mobile.ui.user.repo.Repo;
import com.github.mobile.ui.user.user.User;
import com.github.mobile.util.TypefaceUtils;

public class PushUserEvent extends DisplaysUserEvent {
    public PushUserEvent(User actor, PayloadRef payloadRef, Repo repo, Commits commits) {
        super(TypefaceUtils.ICON_PUSH);
        addDisplayToMain(actor);
        addDisplayToMain(new TextDisplay(" pushed to "));
        addDisplayToMain(payloadRef);
        addDisplayToMain(new TextDisplay(" at "));
        addDisplayToMain(repo);
        addDisplayToDetails(commits);
    }
}
