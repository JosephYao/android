package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.Comment;
import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.IssueCommentPayload;

public class IssueCommentPayloadBuilder implements PayloadBuilder {
    private IssueBuilder issueBuilder = new IssueBuilder().defaultStubIssue();
    private Integer issueNumber = 100;
    private String comment;
    private CommentBuilder<Comment> commentBuilder = new CommentBuilder<>(Comment.class);
    private PullRequestBuilder pullRequestBuilder = new PullRequestBuilder().defaultStubPullRequest();

    @Override
    public EventPayload build() {
        IssueCommentPayload issueCommentPayload = new IssueCommentPayload();
        issueCommentPayload.setIssue(stubIssue());
        issueCommentPayload.setComment(comment());
        return issueCommentPayload;
    }

    private Comment comment() {
        return commentBuilder.withComment(comment).build();
    }

    private Issue stubIssue() {
        Issue stubIssue = issueBuilder.withNumber(issueNumber).build();
        PullRequest stubPullRequest = pullRequestBuilder.withHtmlUrl("HtmlUrl").build();
        when(stubIssue.getPullRequest()).thenReturn(stubPullRequest);
        return stubIssue;
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
