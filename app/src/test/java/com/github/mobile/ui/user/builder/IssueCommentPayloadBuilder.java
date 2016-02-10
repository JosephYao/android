package com.github.mobile.ui.user.builder;

import org.eclipse.egit.github.core.Comment;
import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.IssueCommentPayload;

public class IssueCommentPayloadBuilder implements PayloadBuilder {
    private IssueBuilder issueBuilder = new IssueBuilder();
    private Integer issueNumber = 100;
    private String comment;
    private CommentBuilder<Comment> commentBuilder = new CommentBuilder<>(Comment.class);
    private PullRequestBuilder pullRequestBuilder = new PullRequestBuilder();

    @Override
    public EventPayload build() {
        IssueCommentPayload issueCommentPayload = new IssueCommentPayload();
        issueCommentPayload.setIssue(issue());
        issueCommentPayload.setComment(comment());
        return issueCommentPayload;
    }

    private Comment comment() {
        return commentBuilder.withComment(comment).build();
    }

    private Issue issue() {
        Issue issue = issueBuilder.withNumber(issueNumber).build();
        issue.setPullRequest(pullRequest());
        return issue;
    }

    private PullRequest pullRequest() {
        return pullRequestBuilder.withHtmlUrl("HtmlUrl").build();
    }

    public IssueCommentPayloadBuilder withPullRequest() {
        pullRequestBuilder = new PullRequestBuilder();
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
