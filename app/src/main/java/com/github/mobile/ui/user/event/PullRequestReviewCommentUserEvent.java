package com.github.mobile.ui.user.event;

import com.github.mobile.ui.user.comment.Comment;
import com.github.mobile.ui.user.display.TextDisplay;
import com.github.mobile.ui.user.repo.Repo;
import com.github.mobile.ui.user.user.User;
import com.github.mobile.util.TypefaceUtils;

public class PullRequestReviewCommentUserEvent extends DisplaysUserEvent {
    public PullRequestReviewCommentUserEvent(User actor, Repo repo, Comment comment) {
        super(TypefaceUtils.ICON_COMMENT);
        addDisplayToMain(actor);
        addDisplayToMain(new TextDisplay(" commented on "));
        addDisplayToMain(repo);
        addDisplayToDetails(comment);
    }
}
