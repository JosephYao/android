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
    private boolean withPullRequest;
    private String comment;
    private CommentBuilder<Comment> commentBuilder;

    @Override
    public EventPayload build() {
        IssueCommentPayload stubPayload = mock(IssueCommentPayload.class);
        Issue stubIssue = issueBuilder.withNumber(issueNumber).build();
        addStubPullRequestAsNeeded(stubIssue);
        when(stubPayload.getIssue()).thenReturn(stubIssue);
        Comment stubComment = commentBuilder.withComment(comment).build();
        when(stubPayload.getComment()).thenReturn(stubComment);
        return stubPayload;
    }

    private void addStubPullRequestAsNeeded(Issue stubIssue) {
        if (withPullRequest) {
            PullRequest stubPullRequest = mock(PullRequest.class);
            when(stubPullRequest.getHtmlUrl()).thenReturn("HtmlUrl");
            when(stubIssue.getPullRequest()).thenReturn(stubPullRequest);
        }
    }

    public IssueCommentPayloadBuilder defaultStubPayload() {
        issueBuilder = new IssueBuilder().defaultStubIssue();
        commentBuilder = new CommentBuilder<>(Comment.class).defaultStubComment();
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
