package com.github.mobile.ui.user.event;

import com.github.mobile.ui.user.comment.Comment;
import com.github.mobile.ui.user.display.TextDisplay;
import com.github.mobile.ui.user.issue.Issue;
import com.github.mobile.ui.user.repo.Repo;
import com.github.mobile.ui.user.user.User;
import com.github.mobile.util.TypefaceUtils;

public class IssueCommentUserEvent extends DisplaysUserEvent {
    public IssueCommentUserEvent(User actor, Issue issue, Repo repo, Comment comment) {
        addDisplayToMain(actor);
        addDisplayToMain(new TextDisplay(" commented on "));
        addDisplayToMain(issue);
        addDisplayToMain(repo);
        addDisplayToMain(new TextDisplay(" on "));
        addDisplayToDetails(comment);
        setIcon(TypefaceUtils.ICON_ISSUE_COMMENT);
    }
}
