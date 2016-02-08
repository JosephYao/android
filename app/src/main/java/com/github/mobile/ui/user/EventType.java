package com.github.mobile.ui.user;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.team.TeamFactory;
import com.github.mobile.ui.user.action.Action;
import com.github.mobile.ui.user.comment.Comment;
import com.github.mobile.ui.user.commit.CommitFactory;
import com.github.mobile.ui.user.commit.Commits;
import com.github.mobile.ui.user.download.Download;
import com.github.mobile.ui.user.issue.Issue;
import com.github.mobile.ui.user.ref.PayloadRef;
import com.github.mobile.ui.user.ref.PayloadRefFactory;
import com.github.mobile.ui.user.repo.Repo;
import com.github.mobile.ui.user.repo.RepoFactory;
import com.github.mobile.ui.user.target.Target;
import com.github.mobile.ui.user.target.TargetFactory;
import com.github.mobile.ui.user.user.User;
import com.github.mobile.ui.user.user.UserFactory;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.PushPayload;
import org.eclipse.egit.github.core.event.TeamAddPayload;

/**
 * Created by twer on 3/22/15.
 */
public enum EventType {

    PushEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            return generate(main, details);
        }

        private String generate(StyledText main, StyledText details) {
            actor.render(main);
            main.append(" pushed to ");
            payloadRef.render(main);
            main.append(" at ");
            repo.render(main);
            commits.render(details);
            return TypefaceUtils.ICON_PUSH;
        }

    },
    TeamAddEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            actor.render(main);
            main.append(" added ");
            target.render(main);
            main.append(" to team");
            team.render(main);
            return TypefaceUtils.ICON_ADD_MEMBER;
        }

    },
    WatchEvent {
        @Override
        public String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details) {
            actor.render(main);
            main.append(" starred ");
            repo.render(main);
            return TypefaceUtils.ICON_STAR;
        }
    };

    protected User actor;
    protected PayloadRef payloadRef;
    protected Repo repo;
    protected Comment comment;
    protected Download download;
    protected User followTarget;
    protected Action action;
    protected Issue issue;
    protected User member;
    protected com.github.mobile.ui.user.pullrequest.PullRequest pullrequest;
    protected Commits commits;
    protected Target target;
    protected com.github.mobile.ui.team.Team team;

    public static EventType createInstance(Event event) {
        for(EventType eventType : values())
            if (event.getType().equals(eventType.name())) {
                eventType.actor = UserFactory.create(event.getActor());
                eventType.repo = RepoFactory.createRepoFromEventRepository(event.getRepo());
                if (event.getPayload() instanceof PushPayload) {
                    eventType.payloadRef = PayloadRefFactory.createFromPushPayload((PushPayload) event.getPayload());
                    eventType.commits = CommitFactory.createCommits((PushPayload) event.getPayload());
                }
                if (event.getPayload() instanceof TeamAddPayload) {
                    eventType.target = TargetFactory.create((TeamAddPayload) event.getPayload(), event);
                    eventType.team = TeamFactory.create((TeamAddPayload) event.getPayload());
                }
                return eventType;
            }

        throw new IllegalArgumentException();
    }

    public abstract String generateIconAndFormatStyledText(IconAndViewTextManager iconAndViewTextManager, Event event, StyledText main, StyledText details);

}
