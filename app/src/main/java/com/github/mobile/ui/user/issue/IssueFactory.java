package com.github.mobile.ui.user.issue;

import com.github.mobile.core.issue.IssueUtils;
import com.github.mobile.ui.user.FactoryUtils;

import org.eclipse.egit.github.core.event.IssueCommentPayload;
import org.eclipse.egit.github.core.event.IssuesPayload;

public class IssueFactory {

    public static Issue create(IssueCommentPayload payload) {
        org.eclipse.egit.github.core.Issue issue = payload.getIssue();

        if (IssueUtils.isPullRequest(issue))
            return new PullRequestIssue(issue.getNumber());
        else
            return new NonPullRequestIssue(issue.getNumber());
    }

    public static Issue createFromIssuesPayload(IssuesPayload payload) {
        if (FactoryUtils.isTrimmedTextNotEmpty(payload.getIssue().getTitle()))
            return new NoTitleIssue();
        else
            return new TitleIssue(payload.getIssue().getTitle());
    }
}
