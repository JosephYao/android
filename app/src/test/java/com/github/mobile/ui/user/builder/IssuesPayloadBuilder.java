package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.IssuesPayload;

public class IssuesPayloadBuilder implements PayloadBuilder {
    private IssueBuilder issueBuilder;
    private String action;
    private Integer issueNumber;
    private String issueTitle;

    @Override
    public EventPayload build() {
        IssuesPayload stubPayload = mock(IssuesPayload.class);
        when(stubPayload.getAction()).thenReturn(action);
        Issue stubIssue = issueBuilder.withNumber(issueNumber).withTitle(issueTitle).build();
        when(stubPayload.getIssue()).thenReturn(stubIssue);
        return stubPayload;
    }

    public IssuesPayloadBuilder defaultStubPayload() {
        issueBuilder = new IssueBuilder().defaultStubIssue();
        this.issueNumber = 100;
        return this;
    }

    public IssuesPayloadBuilder withAction(String action) {
        this.action = action;
        return this;
    }

    public IssuesPayloadBuilder withIssueNumber(int number) {
        this.issueNumber = number;
        return this;
    }

    public IssuesPayloadBuilder withIssueTitle(String title) {
        this.issueTitle = title;
        return this;
    }
}
