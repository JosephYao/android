package com.github.mobile.ui.user.builder;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.IssuesPayload;

public class IssuesPayloadBuilder implements PayloadBuilder {
    private IssueBuilder issueBuilder = new IssueBuilder().withNumber(100);
    private String action;

    @Override
    public EventPayload build() {
        IssuesPayload issuesPayload = new IssuesPayload();
        issuesPayload.setAction(action);
        issuesPayload.setIssue(issue());
        return issuesPayload;
    }

    private Issue issue() {
        return issueBuilder.build();
    }

    public IssuesPayloadBuilder withAction(String action) {
        this.action = action;
        return this;
    }

    public IssuesPayloadBuilder withIssueNumber(int number) {
        issueBuilder.withNumber(number);
        return this;
    }

    public IssuesPayloadBuilder withIssueTitle(String title) {
        issueBuilder.withTitle(title);
        return this;
    }
}
