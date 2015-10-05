package com.github.mobile.ui.user;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.action.Action;
import com.github.mobile.ui.user.action.ActionFactory;
import com.github.mobile.ui.user.comment.Comment;
import com.github.mobile.ui.user.comment.CommentFactory;
import com.github.mobile.ui.user.download.Download;
import com.github.mobile.ui.user.download.DownloadFactory;
import com.github.mobile.ui.user.issue.Issue;
import com.github.mobile.ui.user.issue.IssueFactory;
import com.github.mobile.ui.user.ref.PayloadRef;
import com.github.mobile.ui.user.ref.PayloadRefFactory;
import com.github.mobile.ui.user.repo.Repo;
import com.github.mobile.ui.user.repo.RepoFactory;
import com.github.mobile.ui.user.user.User;
import com.github.mobile.ui.user.user.UserFactory;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.CommitCommentPayload;
import org.eclipse.egit.github.core.event.CreatePayload;
import org.eclipse.egit.github.core.event.DeletePayload;
import org.eclipse.egit.github.core.event.DownloadPayload;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.FollowPayload;
import org.eclipse.egit.github.core.event.GistPayload;
import org.eclipse.egit.github.core.event.IssueCommentPayload;
import org.eclipse.egit.github.core.event.IssuesPayload;
import org.eclipse.egit.github.core.event.MemberPayload;

/**
 * Created by twer on 3/22/15.
 */
public enum EventType {

    CommitCommentEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            return generate(main, details);
        }

        private String generate(StyledText main, StyledText details) {
            renderUserActOnRepo(main, " commented on ");
            comment.render(details);
            return TypefaceUtils.ICON_COMMENT;
        }

    },
    CreateEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            return generate(main, details);
        }

        private String generate(StyledText main, StyledText details) {
            user.render(main);
            main.append(" created ");
            payloadRef.render(main);
            return TypefaceUtils.ICON_CREATE;
        }

    },
    DeleteEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            return generate(main);
        }

        private String generate(StyledText main) {
            user.render(main);
            payloadRef.render(main);
            repo.render(main);
            return TypefaceUtils.ICON_DELETE;
        }
    },
    DownloadEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            return generate(main, details);
        }

        private String generate(StyledText main, StyledText details) {
            renderUserActOnRepo(main, " uploaded a file to ");
            download.render(details);
            return TypefaceUtils.ICON_UPLOAD;
        }
    },
    FollowEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            return generate(main);
        }

        private String generate(StyledText main) {
            user.render(main);
            main.append(" started following ");
            target.render(main);
            return TypefaceUtils.ICON_FOLLOW;
        }

    },
    ForkEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            return generate(main);
        }

        private String generate(StyledText main) {
            renderUserActOnRepo(main, " forked repository ");
            return TypefaceUtils.ICON_FORK;
        }

    },
    GistEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            return generate(main);
        }

        private String generate(StyledText main) {
            user.render(main);
            main.append(' ');
            action.render(main);
            return action.getIcon();
        }
    },
    GollumEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            return generate(main);
        }

        private String generate(StyledText main) {
            renderUserActOnRepo(main, " updated the wiki in ");
            return TypefaceUtils.ICON_WIKI;
        }

    },
    IssueCommentEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            return generate(main, details);
        }

        private String generate(StyledText main, StyledText details) {
            user.render(main);
            main.append(" commented on ");
            issue.render(main);
            repo.render(main);
            main.append(" on ");
            comment.render(details);
            return TypefaceUtils.ICON_ISSUE_COMMENT;
        }

    },
    IssuesEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            return generate(main, details);
        }

        private String generate(StyledText main, StyledText details) {
            user.render(main);
            action.render(main);
            repo.render(main);
            issue.render(details);
            return action.getIcon();
        }
    },
    MemberEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            user.render(main);
            main.append(" added ");
            org.eclipse.egit.github.core.User member = ((MemberPayload) event.getPayload()).getMember();
            if (member != null)
                main.bold(member.getLogin());
            main.append(" as a collaborator to ");
            repo.render(main);
            return TypefaceUtils.ICON_ADD_MEMBER;
        }
    },
    PublicEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            iconAndViewTextManager.formatPublic(event, main, details);
            return null;
        }
    },
    PullRequestEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            iconAndViewTextManager.formatPullRequest(event, main, details);
            return TypefaceUtils.ICON_PULL_REQUEST;
        }
    },
    PullRequestReviewCommentEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            iconAndViewTextManager.formatReviewComment(event, main, details);
            return TypefaceUtils.ICON_COMMENT;
        }
    },
    PushEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            iconAndViewTextManager.formatPush(event, main, details);
            return TypefaceUtils.ICON_PUSH;
        }
    },
    TeamAddEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            iconAndViewTextManager.formatTeamAdd(event, main, details);
            return TypefaceUtils.ICON_ADD_MEMBER;
        }
    },
    WatchEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            iconAndViewTextManager.formatWatch(event, main, details);
            return TypefaceUtils.ICON_STAR;
        }
    };

    protected User user;
    protected PayloadRef payloadRef;
    protected Repo repo;
    protected Comment comment;
    protected Download download;
    protected User target;
    protected Action action;
    protected Issue issue;

    public static EventType createInstance(Event event) {
        for(EventType eventType : values())
            if (event.getType().equals(eventType.name())) {
                eventType.user = UserFactory.create(event.getActor());
                eventType.repo = RepoFactory.createRepoFromEventRepository(event.getRepo());
                if (event.getPayload() instanceof CreatePayload)
                    eventType.payloadRef = PayloadRefFactory.createFromCreatePayload((CreatePayload) event.getPayload(),
                            event.getRepo());
                if (event.getPayload() instanceof CommitCommentPayload)
                    eventType.comment = CommentFactory.createFromCommitCommentPayload(
                            (CommitCommentPayload) event.getPayload());
                if (event.getPayload() instanceof DeletePayload)
                    eventType.payloadRef = PayloadRefFactory.createFromDeletePayload((DeletePayload) event.getPayload());
                if (event.getPayload() instanceof DownloadPayload)
                    eventType.download = DownloadFactory.create((DownloadPayload) event.getPayload());
                if (event.getPayload() instanceof FollowPayload)
                    eventType.target = UserFactory.create(((FollowPayload) event.getPayload()).getTarget());
                if (event.getPayload() instanceof GistPayload)
                    eventType.action = ActionFactory.createFromGistPayload((GistPayload) event.getPayload());
                if (event.getPayload() instanceof IssueCommentPayload) {
                    eventType.comment = CommentFactory.createFromIssueCommentPayload((IssueCommentPayload)
                            event.getPayload());
                    eventType.issue = IssueFactory.create((IssueCommentPayload) event.getPayload());
                }
                if (event.getPayload() instanceof IssuesPayload) {
                    eventType.action = ActionFactory.createFromIssuesPayload((IssuesPayload) event.getPayload());
                    eventType.issue = IssueFactory.createFromIssuesPayload((IssuesPayload) event.getPayload());
                }
                return eventType;
            }

        throw new IllegalArgumentException();
    }

    public abstract String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details);

    protected void renderUserActOnRepo(StyledText main, String action) {
        user.render(main);
        main.append(action);
        repo.render(main);
    }

}
