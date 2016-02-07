package com.github.mobile.ui.user.event;

import com.github.mobile.ui.user.comment.Comment;
import com.github.mobile.ui.user.display.TextDisplay;
import com.github.mobile.ui.user.repo.Repo;
import com.github.mobile.ui.user.user.User;
import com.github.mobile.util.TypefaceUtils;

public class CommitCommentUserEvent extends DisplaysUserEvent {

    public CommitCommentUserEvent(Comment comment, User actor, Repo repo) {
        super(TypefaceUtils.ICON_COMMENT);
        addDisplayToMain(actor);
        addDisplayToMain(new TextDisplay(" commented on "));
        addDisplayToMain(repo);
        addDisplayToDetails(comment);
    }

}
