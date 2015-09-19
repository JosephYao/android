package com.github.mobile.ui.user;

import android.text.TextUtils;

import com.github.mobile.ui.StyledText;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.Comment;
import org.eclipse.egit.github.core.CommitComment;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.event.CommitCommentPayload;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.EventRepository;
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
            renderUserCommentOnRepo(main);
            renderCommitComment(details);
            return TypefaceUtils.ICON_COMMENT;
        }

        private CommitComment commitComment() {
            return ((CommitCommentPayload)payload).getComment();
        }

        private void renderUserCommentOnRepo(StyledText main) {
            renderUserLogin(main);
            main.append(" commented on ");
            renderRepoName(main);
        }

        private void renderRepoName(StyledText main) {
            if (repo != null)
                main.bold(repo.getName());
        }

        private void renderUserLogin(StyledText main) {
            if (user != null)
                main.bold(user.getLogin());
        }

        private void renderCommitComment(final StyledText details) {
            if (commitComment() == null)
                return;

            String id = commitComment().getCommitId();
            if (!TextUtils.isEmpty(id)) {
                if (id.length() > 10)
                    id = id.substring(0, 10);
                appendText(details, "Comment in");
                details.append(' ');
                details.monospace(id);
                details.append(':').append('\n');
            }
            appendComment(details, commitComment());
        }

        private void appendText(final StyledText details, String text) {
            if (text == null)
                return;
            text = text.trim();
            if (text.length() == 0)
                return;

            details.append(text);
        }

        private void appendComment(final StyledText details,
                final Comment comment) {
            if (comment != null)
                appendText(details, comment.getBody());
        }
    },
    CreateEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            iconAndViewTextManager.formatCreate(event, main, details);
            return TypefaceUtils.ICON_CREATE;
        }
    },
    DeleteEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            iconAndViewTextManager.formatDelete(event, main, details);
            return TypefaceUtils.ICON_DELETE;
        }
    },
    DownloadEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            iconAndViewTextManager.formatDownload(event, main, details);
            return TypefaceUtils.ICON_UPLOAD;
        }
    },
    FollowEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            iconAndViewTextManager.formatFollow(event, main, details);
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

    protected User user;
    protected EventRepository repo;
    protected EventPayload payload;

    public static EventType createInstance(Event event) {
        for(EventType eventType : values())
            if (event.getType().equals(eventType.name())) {
                eventType.user = event.getActor();
                eventType.repo = event.getRepo();
                eventType.payload = event.getPayload();
                return eventType;
            }

        throw new IllegalArgumentException();
    }

    public abstract String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details);
}
