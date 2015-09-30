package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.Comment;
import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.IssueCommentPayload;

public class IssueCommentPayloadBuilder implements PayloadBuilder {
    private Integer issueNumber;
    private boolean withPullRequest;
    private String comment;

    @Override
    public EventPayload build() {
        IssueCommentPayload stubPayload = mock(IssueCommentPayload.class);
        Issue stubIssue = stubIssue();
        when(stubPayload.getIssue()).thenReturn(stubIssue);
        Comment stubComment = mock(Comment.class);
        when(stubComment.getBody()).thenReturn(comment);
        when(stubPayload.getComment()).thenReturn(stubComment);
        return stubPayload;
    }

    private Issue stubIssue() {
        Issue stubIssue = mock(Issue.class);
        when(stubIssue.getNumber()).thenReturn(issueNumber);
        stubPullRequest(stubIssue);
        return stubIssue;
    }

    private void stubPullRequest(Issue stubIssue) {
        if (withPullRequest) {
            PullRequest stubPullRequest = mock(PullRequest.class);
            when(stubPullRequest.getHtmlUrl()).thenReturn("HtmlUrl");
            when(stubIssue.getPullRequest()).thenReturn(stubPullRequest);
        }
    }

    public IssueCommentPayloadBuilder defaultStubPayload() {
        this.withPullRequest = false;
        this.issueNumber = 1;
        return this;
    }

    public IssueCommentPayloadBuilder withPullRequest() {
        this.withPullRequest = true;
        return this;
    }

    public IssueCommentPayloadBuilder withIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
        return this;
    }

    public IssueCommentPayloadBuilder withOutPullRequest() {
        this.withPullRequest = false;
        return this;
    }

    public IssueCommentPayloadBuilder withComment(String comment) {
        this.comment = comment;
        return this;
    }
}
