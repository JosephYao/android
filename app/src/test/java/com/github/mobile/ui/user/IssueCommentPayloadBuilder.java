package com.github.mobile.ui.user;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.github.mobile.ui.user.builder.PayloadBuilder;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.IssueCommentPayload;

public class IssueCommentPayloadBuilder implements PayloadBuilder {
    private Integer issueNumber;

    @Override
    public EventPayload build() {
        IssueCommentPayload stubPayload = mock(IssueCommentPayload.class);
        Issue stubIssue = mock(Issue.class);
        when(stubIssue.getNumber()).thenReturn(issueNumber);
        when(stubPayload.getIssue()).thenReturn(stubIssue);
        return stubPayload;
    }

    public IssueCommentPayloadBuilder defaultStubPayload() {
        this.issueNumber = 1;
        return this;
    }
}
