package com.github.mobile.ui.user.event;

import com.github.mobile.ui.user.action.Action;
import com.github.mobile.ui.user.pullrequest.PullRequest;
import com.github.mobile.ui.user.repo.Repo;
import com.github.mobile.ui.user.user.User;
import com.github.mobile.util.TypefaceUtils;

public class PullRequestUserEvent extends DisplaysUserEvent {
    public PullRequestUserEvent(User actor, Action action, Repo repo, PullRequest pullRequest) {
        super(TypefaceUtils.ICON_PULL_REQUEST);
        addDisplayToMain(actor);
        addDisplayToMain(action);
        addDisplayToMain(repo);
        addDisplayToDetails(pullRequest);
    }
}
