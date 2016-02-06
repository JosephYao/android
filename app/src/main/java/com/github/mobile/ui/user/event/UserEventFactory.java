package com.github.mobile.ui.user.event;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.EventType;
import com.github.mobile.ui.user.IconAndViewTextManager;
import com.github.mobile.ui.user.comment.Comment;
import com.github.mobile.ui.user.comment.CommentFactory;
import com.github.mobile.ui.user.repo.Repo;
import com.github.mobile.ui.user.repo.RepoFactory;
import com.github.mobile.ui.user.user.User;
import com.github.mobile.ui.user.user.UserFactory;

import org.eclipse.egit.github.core.event.CommitCommentPayload;
import org.eclipse.egit.github.core.event.Event;

public class UserEventFactory {
    public static UserEvent create(final Event event, final IconAndViewTextManager iconAndViewTextManager) {
        if (event.getType().equals(Event.TYPE_COMMIT_COMMENT)) {
            return new CommitCommentUserEvent(commitComment(event), actor(event), repo(event));
        }

        return new UserEvent() {
            @Override
            public String generate(StyledText main, StyledText details) {
                return EventType.createInstance(event).generateIconAndFormatStyledText(iconAndViewTextManager, event, main, details);
            }
        };
    }

    private static Repo repo(Event event) {
        return RepoFactory.createRepoFromEventRepository(event.getRepo());
    }

    private static User actor(Event event) {
        return UserFactory.create(event.getActor());
    }

    private static Comment commitComment(Event event) {
        return CommentFactory.createFromCommitComment(((CommitCommentPayload) event.getPayload()).getComment());
    }
}
