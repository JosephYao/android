package com.github.mobile.ui.user.issue;

import com.github.mobile.core.issue.IssueUtils;
import com.github.mobile.ui.user.FactoryUtils;

import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.IssueCommentPayload;
import org.eclipse.egit.github.core.event.IssuesPayload;

public class IssueFactory {

    public static Issue create(Event event) {
        if (event.getPayload() instanceof IssueCommentPayload)
            return createFromIssue(issue(event));

        return createFromTitle(title(event));
    }

    private static String title(Event event) {
        return ((IssuesPayload) event.getPayload()).getIssue().getTitle();
    }

    private static org.eclipse.egit.github.core.Issue issue(Event event) {
        return ((IssueCommentPayload) event.getPayload()).getIssue();
    }

    private static Issue createFromIssue(org.eclipse.egit.github.core.Issue issue) {
        if (IssueUtils.isPullRequest(issue))
            return new PullRequestIssue(issue.getNumber());
        else
            return new NonPullRequestIssue(issue.getNumber());
    }

    private static Issue createFromTitle(String title) {
        if (FactoryUtils.isTrimmedTextEmpty(title))
            return new NoTitleIssue();
        else
            return new TitleIssue(title);
    }
}
