package com.github.mobile.ui.user;

import org.eclipse.egit.github.core.event.GistPayload;

public class ActionFactory {

    public static Action create(GistPayload payload) {
        return new NormalAction(payload.getAction(), payload.getGist().getId());
    }
}
