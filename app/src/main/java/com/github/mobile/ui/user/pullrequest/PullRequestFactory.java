package com.github.mobile.ui.user.pullrequest;

import android.text.TextUtils;

import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.PullRequestPayload;

public class PullRequestFactory {
    public static PullRequest create(Event event) {
        PullRequestPayload payload = (PullRequestPayload) event.getPayload();

        if (isActionOpenedOrClosed(payload.getAction()) && isPullRequestNotEmpty(payload.getPullRequest()))
            return new TitlePullRequest(payload.getPullRequest().getTitle());
        else
            return new NonTitlePullRequest();
    }

    private static boolean isActionOpenedOrClosed(String action) {
        return "opened".equals(action) || "closed".equals(action);
    }

    private static boolean isPullRequestNotEmpty(org.eclipse.egit.github.core.PullRequest request) {
        return request != null && !TextUtils.isEmpty(request.getTitle());
    }

}
