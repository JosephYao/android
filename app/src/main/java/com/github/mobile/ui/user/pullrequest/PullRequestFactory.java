package com.github.mobile.ui.user.pullrequest;

import android.text.TextUtils;

import org.eclipse.egit.github.core.event.PullRequestPayload;

public class PullRequestFactory {
    public static PullRequest create(PullRequestPayload payload) {
        org.eclipse.egit.github.core.PullRequest request = payload.getPullRequest();
        String action = payload.getAction();

        if (("opened".equals(action) || "closed".equals(action)) &&
                isPullRequestNotEmpty(request)) {
            return new TitlePullRequest(request.getTitle());
        }

        return new NonTitlePullRequest();
    }

    private static boolean isPullRequestNotEmpty(org.eclipse.egit.github.core.PullRequest request) {
        return request != null && !TextUtils.isEmpty(request.getTitle());
    }

}
