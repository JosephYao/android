package com.github.mobile.ui.user.event;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.comment.Comment;
import com.github.mobile.ui.user.repo.Repo;
import com.github.mobile.ui.user.user.User;
import com.github.mobile.util.TypefaceUtils;

public class CommitCommentUserEvent implements UserEvent {
    private final Comment comment;
    private final User actor;
    private final Repo repo;

    public CommitCommentUserEvent(Comment comment, User actor, Repo repo) {
        this.comment = comment;
        this.actor = actor;
        this.repo = repo;
    }

    @Override
    public String generate(StyledText main, StyledText details) {
        actor.render(main);
        main.append(" commented on ");
        repo.render(main);
        comment.render(details);
        return TypefaceUtils.ICON_COMMENT;
    }

}
