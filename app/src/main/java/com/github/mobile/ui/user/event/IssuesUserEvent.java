package com.github.mobile.ui.user.event;

import com.github.mobile.ui.user.action.Action;
import com.github.mobile.ui.user.issue.Issue;
import com.github.mobile.ui.user.repo.Repo;
import com.github.mobile.ui.user.user.User;

public class IssuesUserEvent extends DisplaysUserEvent {
    public IssuesUserEvent(User actor, Action action, Repo repo, Issue issue) {
        super(action.getIcon());
        addDisplayToMain(actor);
        addDisplayToMain(action);
        addDisplayToMain(repo);
        addDisplayToDetails(issue);
    }
}
