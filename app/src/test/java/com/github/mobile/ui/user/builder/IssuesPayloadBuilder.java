package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.IssuesPayload;

public class IssuesPayloadBuilder implements PayloadBuilder {
    private IssueBuilder issueBuilder;
    private String action;

    @Override
    public EventPayload build() {
        IssuesPayload stubPayload = mock(IssuesPayload.class);
        when(stubPayload.getAction()).thenReturn(action);
        when(stubPayload.getIssue()).thenReturn(issue());
        return stubPayload;
    }

    private Issue issue() {
        return issueBuilder.build();
    }

    public IssuesPayloadBuilder defaultStubPayload() {
        issueBuilder = new IssueBuilder().withNumber(100);
        return this;
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
