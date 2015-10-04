package com.github.mobile.ui.user;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.github.mobile.ui.user.builder.PayloadBuilder;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.IssuesPayload;

public class IssuesPayloadBuilder implements PayloadBuilder {
    private String action;
    private Integer issueNumber;

    @Override
    public EventPayload build() {
        IssuesPayload stubPayload = mock(IssuesPayload.class);
        when(stubPayload.getAction()).thenReturn(action);
        Issue stubIssue = stubIssue();
        when(stubPayload.getIssue()).thenReturn(stubIssue);
        return stubPayload;
    }

    private Issue stubIssue() {
        Issue stubIssue = mock(Issue.class);
        when(stubIssue.getNumber()).thenReturn(issueNumber);
        return stubIssue;
    }

    public IssuesPayloadBuilder defaultStubPayload() {
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
}
