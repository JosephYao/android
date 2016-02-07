package com.github.mobile.ui.user.issue;

import com.github.mobile.core.issue.IssueUtils;
import com.github.mobile.ui.user.FactoryUtils;

import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.IssueCommentPayload;
import org.eclipse.egit.github.core.event.IssuesPayload;

public class IssueFactory {

    public static Issue create(Event event) {
        if (event.getPayload() instanceof IssueCommentPayload)
            return create((IssueCommentPayload) event.getPayload());

        return createFromIssuesPayload((IssuesPayload) event.getPayload());
    }

    public static Issue create(IssueCommentPayload payload) {
        org.eclipse.egit.github.core.Issue issue = payload.getIssue();

        if (IssueUtils.isPullRequest(issue))
            return new PullRequestIssue(issue.getNumber());
        else
            return new NonPullRequestIssue(issue.getNumber());
    }

    public static Issue createFromIssuesPayload(IssuesPayload payload) {
        String title = payload.getIssue().getTitle();

        if (FactoryUtils.isTrimmedTextEmpty(title))
            return new NoTitleIssue();
        else
            return new TitleIssue(title);
    }
}
