package com.github.mobile.ui.user.issue;

import com.github.mobile.core.issue.IssueUtils;

import org.eclipse.egit.github.core.event.IssueCommentPayload;

public class IssueFactory {

    public static Issue create(IssueCommentPayload payload) {
        org.eclipse.egit.github.core.Issue issue = payload.getIssue();

        if (IssueUtils.isPullRequest(issue))
            return new PullRequestIssue(issue.getNumber());
        else
            return new NonPullRequestIssue(issue.getNumber());
    }
}
