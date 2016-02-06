package com.github.mobile.ui.user.event;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.comment.Comment;
import com.github.mobile.ui.user.comment.CommentFactory;
import com.github.mobile.ui.user.repo.Repo;
import com.github.mobile.ui.user.repo.RepoFactory;
import com.github.mobile.ui.user.user.User;
import com.github.mobile.ui.user.user.UserFactory;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.CommitCommentPayload;
import org.eclipse.egit.github.core.event.Event;

public class CommitCommentEvent {
    private Comment comment;
    private User actor;
    private Repo repo;

    public CommitCommentEvent(Event event) {
        comment = CommentFactory.createFromCommitComment(((CommitCommentPayload) event.getPayload()).getComment());
        actor = UserFactory.create(event.getActor());
        repo = RepoFactory.createRepoFromEventRepository(event.getRepo());
    }

    public String generate(StyledText main, StyledText details) {
        renderUserActOnRepo(main, " commented on ");
        comment.render(details);
        return TypefaceUtils.ICON_COMMENT;
    }

    private void renderUserActOnRepo(StyledText main, String action) {
        actor.render(main);
        main.append(action);
        repo.render(main);
    }
}
