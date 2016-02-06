package com.github.mobile.ui.user.event;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.EventType;
import com.github.mobile.ui.user.IconAndViewTextManager;
import com.github.mobile.ui.user.action.Action;
import com.github.mobile.ui.user.action.ActionFactory;
import com.github.mobile.ui.user.comment.Comment;
import com.github.mobile.ui.user.comment.CommentFactory;
import com.github.mobile.ui.user.download.Download;
import com.github.mobile.ui.user.download.DownloadFactory;
import com.github.mobile.ui.user.ref.PayloadRef;
import com.github.mobile.ui.user.ref.PayloadRefFactory;
import com.github.mobile.ui.user.repo.Repo;
import com.github.mobile.ui.user.repo.RepoFactory;
import com.github.mobile.ui.user.user.User;
import com.github.mobile.ui.user.user.UserFactory;

import org.eclipse.egit.github.core.event.CommitCommentPayload;
import org.eclipse.egit.github.core.event.DownloadPayload;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.FollowPayload;
import org.eclipse.egit.github.core.event.GistPayload;

public class UserEventFactory {
    public static UserEvent create(final Event event, final IconAndViewTextManager iconAndViewTextManager) {
        switch (event.getType()) {
        case Event.TYPE_COMMIT_COMMENT:
            return new CommitCommentUserEvent(commitComment(event), actor(event), repo(event));
        case Event.TYPE_CREATE:
            return new CreateUserEvent(actor(event), payloadRef(event));
        case Event.TYPE_DELETE:
            return new DeleteUserEvent(actor(event), payloadRef(event), repo(event));
        case Event.TYPE_DOWNLOAD:
            return new DownloadUserEvent(actor(event), repo(event), download(event));
        case Event.TYPE_FOLLOW:
            return new FollowUserEvent(actor(event), target(event));
        case Event.TYPE_FORK:
            return new ForkUserEvent(actor(event), repo(event));
        case Event.TYPE_GIST:
            return new GistUserEvent(actor(event), action(event));
        default:
            return new UserEvent() {
                @Override
                public String generate(StyledText main, StyledText details) {
                    return EventType.createInstance(event).generateIconAndFormatStyledText(iconAndViewTextManager, event, main, details);
                }
            };
        }
    }

    private static Action action(Event event) {
        return ActionFactory.createFromGistPayload((GistPayload) event.getPayload());
    }

    private static User target(Event event) {
        return UserFactory.create(((FollowPayload) event.getPayload()).getTarget());
    }

    private static Download download(Event event) {
        return DownloadFactory.create((DownloadPayload) event
                .getPayload());
    }

    private static PayloadRef payloadRef(Event event) {
        return PayloadRefFactory.createFromEvent(event);
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
