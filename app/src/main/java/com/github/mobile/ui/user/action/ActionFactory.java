package com.github.mobile.ui.user.action;

import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.GistPayload;
import org.eclipse.egit.github.core.event.IssuesPayload;
import org.eclipse.egit.github.core.event.PullRequestPayload;

public class ActionFactory {

    public static Action create(Event event) {
        EventPayload payload = event.getPayload();

        if (payload instanceof GistPayload)
            return createFromGistPayload((GistPayload) payload);

        if (payload instanceof IssuesPayload)
            return createFromIssuesPayload((IssuesPayload) payload);

        return createFromPullRequestPayload((PullRequestPayload) payload);
    }

    private static Action createFromGistPayload(GistPayload payload) {
        return new GistAction(payload.getAction(), payload.getGist().getId());
    }

    private static Action createFromIssuesPayload(IssuesPayload payload) {
        return new IssuesAction(payload.getAction(), payload.getIssue().getNumber());
    }

    private static Action createFromPullRequestPayload(PullRequestPayload payload) {
        if ("synchronize".equals(payload.getAction()))
            return new PullRequestAction("updated", payload.getNumber());

        return new PullRequestAction(payload.getAction(), payload.getNumber());
    }
}
