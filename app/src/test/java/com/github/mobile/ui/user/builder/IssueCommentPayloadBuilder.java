package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.Comment;
import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.IssueCommentPayload;

public class IssueCommentPayloadBuilder implements PayloadBuilder {
    private IssueBuilder issueBuilder;
    private Integer issueNumber;
    private String comment;
    private CommentBuilder<Comment> commentBuilder;
    private PullRequestBuilder pullRequestBuilder;

    @Override
    public EventPayload build() {
        IssueCommentPayload stubPayload = mock(IssueCommentPayload.class);
        stubIssueAndPullRequest(stubPayload);
        stubComment(stubPayload);
        return stubPayload;
    }

    private void stubComment(IssueCommentPayload stubPayload) {
        Comment stubComment = commentBuilder.withComment(comment).build();
        when(stubPayload.getComment()).thenReturn(stubComment);
    }

    private void stubIssueAndPullRequest(IssueCommentPayload stubPayload) {
        Issue stubIssue = issueBuilder.withNumber(issueNumber).build();
        PullRequest stubPullRequest = pullRequestBuilder.withHtmlUrl("HtmlUrl").build();
        when(stubIssue.getPullRequest()).thenReturn(stubPullRequest);
        when(stubPayload.getIssue()).thenReturn(stubIssue);
    }

    public IssueCommentPayloadBuilder() {
        issueBuilder = new IssueBuilder().defaultStubIssue();
        commentBuilder = new CommentBuilder<>(Comment.class);
        pullRequestBuilder = new PullRequestBuilder().defaultStubPullRequest();
        this.issueNumber = 1;
    }

    public IssueCommentPayloadBuilder withPullRequest() {
        pullRequestBuilder = new PullRequestBuilder().defaultStubPullRequest();
        return this;
    }

    public IssueCommentPayloadBuilder withIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
        return this;
    }

    public IssueCommentPayloadBuilder withoutPullRequest() {
        pullRequestBuilder = new NullPullRequestBuilder();
        return this;
    }

    public IssueCommentPayloadBuilder withComment(String comment) {
        this.comment = comment;
        return this;
    }
}
