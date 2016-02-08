package com.github.mobile.ui.user.event;

import com.github.mobile.ui.team.TeamFactory;
import com.github.mobile.ui.user.IconAndViewTextManager;
import com.github.mobile.ui.user.action.Action;
import com.github.mobile.ui.user.action.ActionFactory;
import com.github.mobile.ui.user.comment.Comment;
import com.github.mobile.ui.user.comment.CommentFactory;
import com.github.mobile.ui.user.commit.CommitFactory;
import com.github.mobile.ui.user.download.Download;
import com.github.mobile.ui.user.download.DownloadFactory;
import com.github.mobile.ui.user.issue.Issue;
import com.github.mobile.ui.user.issue.IssueFactory;
import com.github.mobile.ui.user.pullrequest.PullRequest;
import com.github.mobile.ui.user.pullrequest.PullRequestFactory;
import com.github.mobile.ui.user.ref.PayloadRef;
import com.github.mobile.ui.user.ref.PayloadRefFactory;
import com.github.mobile.ui.user.repo.Repo;
import com.github.mobile.ui.user.repo.RepoFactory;
import com.github.mobile.ui.user.target.TargetFactory;
import com.github.mobile.ui.user.user.User;
import com.github.mobile.ui.user.user.UserFactory;

import org.eclipse.egit.github.core.event.DownloadPayload;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.FollowPayload;
import org.eclipse.egit.github.core.event.MemberPayload;
import org.eclipse.egit.github.core.event.PullRequestPayload;
import org.eclipse.egit.github.core.event.PushPayload;
import org.eclipse.egit.github.core.event.TeamAddPayload;

public class UserEventFactory {
    public static UserEvent create(final Event event, final IconAndViewTextManager iconAndViewTextManager) {
        switch (event.getType()) {
        case Event.TYPE_COMMIT_COMMENT:
            return new CommitCommentUserEvent(comment(event), actor(event), repo(event));
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
        case Event.TYPE_GOLLUM:
            return new GollumUserEvent(actor(event), repo(event));
        case Event.TYPE_ISSUE_COMMENT:
            return new IssueCommentUserEvent(actor(event), issue(event), repo(event), comment(event));
        case Event.TYPE_ISSUES:
            return new IssuesUserEvent(actor(event), action(event), repo(event), issue(event));
        case Event.TYPE_MEMBER:
            return new MemberUserEvent(actor(event), member(event), repo(event));
        case Event.TYPE_PUBLIC:
            return new PublicUserEvent(actor(event), repo(event));
        case Event.TYPE_PULL_REQUEST:
            return new PullRequestUserEvent(actor(event), action(event), repo(event), pullRequest(event));
        case Event.TYPE_PULL_REQUEST_REVIEW_COMMENT:
            return new PullRequestReviewCommentUserEvent(actor(event), repo(event), comment(event));
        case Event.TYPE_PUSH:
            return new PushUserEvent(actor(event), payloadRef(event), repo(event), CommitFactory.createCommits((PushPayload) event.getPayload()));
        case Event.TYPE_TEAM_ADD:
            return new TeamAddUserEvent(actor(event), TargetFactory.create((TeamAddPayload) event.getPayload(), event), TeamFactory.create((TeamAddPayload) event.getPayload()));
        case Event.TYPE_WATCH:
            return new WatchUserEvent(actor(event), repo(event));
        default:
            throw new IllegalStateException(event.getType() + "is not implemented.");
        }
    }

    private static PullRequest pullRequest(Event event) {
        return PullRequestFactory.create((PullRequestPayload) event.getPayload());
    }

    private static User member(Event event) {
        return UserFactory.create(((MemberPayload) event.getPayload()).getMember());
    }

    private static Comment comment(Event event) {
        return CommentFactory.create(event);
    }

    private static Issue issue(Event event) {
        return IssueFactory.create(event);
    }

    private static Action action(Event event) {
        return ActionFactory.create(event);
    }

    private static User target(Event event) {
        return UserFactory.create(((FollowPayload) event.getPayload()).getTarget());
    }

    private static Download download(Event event) {
        return DownloadFactory.create((DownloadPayload) event
                .getPayload());
    }

    private static PayloadRef payloadRef(Event event) {
        return PayloadRefFactory.create(event);
    }

    private static Repo repo(Event event) {
        return RepoFactory.createRepoFromEventRepository(event.getRepo());
    }

    private static User actor(Event event) {
        return UserFactory.create(event.getActor());
    }
}
