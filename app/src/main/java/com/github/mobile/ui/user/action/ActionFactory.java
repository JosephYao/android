package com.github.mobile.ui.user.action;

import org.eclipse.egit.github.core.event.GistPayload;
import org.eclipse.egit.github.core.event.IssuesPayload;

public class ActionFactory {

    public static Action createFromGistPayload(GistPayload payload) {
        return new GistAction(payload.getAction(), payload.getGist().getId());
    }

    public static Action createFromIssuesPayload(IssuesPayload payload) {
        return new IssuesAction(payload.getAction(), payload.getIssue().getNumber());
    }
}
