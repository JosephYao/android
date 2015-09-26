package com.github.mobile.ui.user;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.commitcomment.CommitComment;
import com.github.mobile.ui.user.commitcomment.CommitCommentFactory;
import com.github.mobile.ui.user.download.Download;
import com.github.mobile.ui.user.download.DownloadFactory;
import com.github.mobile.ui.user.ref.PayloadRef;
import com.github.mobile.ui.user.ref.PayloadRefFactory;
import com.github.mobile.ui.user.repo.Repo;
import com.github.mobile.ui.user.repo.RepoFactory;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.CommitCommentPayload;
import org.eclipse.egit.github.core.event.CreatePayload;
import org.eclipse.egit.github.core.event.DeletePayload;
import org.eclipse.egit.github.core.event.DownloadPayload;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.FollowPayload;
import org.eclipse.egit.github.core.event.IssuesPayload;

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
            commitComment.render(details);
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
            iconAndViewTextManager.formatFork(event, main, details);
            return TypefaceUtils.ICON_FORK;
        }
    },
    GistEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            iconAndViewTextManager.formatGist(event, main, details);
            return TypefaceUtils.ICON_GIST;
        }
    },
    GollumEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            iconAndViewTextManager.formatWiki(event, main, details);
            return TypefaceUtils.ICON_WIKI;
        }
    },
    IssueCommentEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            iconAndViewTextManager.formatIssueComment(event, main, details);
            return TypefaceUtils.ICON_ISSUE_COMMENT;
        }
    },
    IssuesEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            iconAndViewTextManager.formatIssues(event, main, details);
            String action = ((IssuesPayload) event.getPayload()).getAction();
            String icon = null;
            if (IconAndViewTextManager.ISSUES_PAYLOAD_ACTION_OPENED.equals(action))
                icon = TypefaceUtils.ICON_ISSUE_OPEN;
            else if (IconAndViewTextManager.ISSUES_PAYLOAD_ACTION_REOPENED.equals(action))
                icon = TypefaceUtils.ICON_ISSUE_REOPEN;
            else if (IconAndViewTextManager.ISSUES_PAYLOAD_ACTION_CLOSED.equals(action))
                icon = TypefaceUtils.ICON_ISSUE_CLOSE;
            return icon;
        }
    },
    MemberEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            iconAndViewTextManager.formatAddMember(event, main, details);
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

    protected com.github.mobile.ui.user.User user;
    protected PayloadRef payloadRef;
    protected Repo repo;
    protected CommitComment commitComment;
    protected Download download;
    protected com.github.mobile.ui.user.User target;

    public static EventType createInstance(Event event) {
        for(EventType eventType : values())
            if (event.getType().equals(eventType.name())) {
                eventType.user = UserFactory.create(event.getActor());
                eventType.repo = RepoFactory.createRepoFromEventRepository(event.getRepo());
                if (event.getPayload() instanceof CreatePayload)
                    eventType.payloadRef = PayloadRefFactory.createFromCreate((CreatePayload) event.getPayload(),
                            event.getRepo());
                if (event.getPayload() instanceof CommitCommentPayload)
                    eventType.commitComment = CommitCommentFactory.create(event.getPayload());
                if (event.getPayload() instanceof DeletePayload)
                    eventType.payloadRef = PayloadRefFactory.createFromDelete((DeletePayload) event.getPayload());
                if (event.getPayload() instanceof DownloadPayload)
                    eventType.download = DownloadFactory.create((DownloadPayload) event.getPayload());
                if (event.getPayload() instanceof FollowPayload)
                    eventType.target = UserFactory.create(((FollowPayload) event.getPayload()).getTarget());
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
