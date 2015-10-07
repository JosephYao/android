package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.CommitComment;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.PullRequestReviewCommentPayload;

public class PullRequestReviewCommentPayloadBuilder implements PayloadBuilder {
    private String commitId;
    private String comment;

    @Override
    public EventPayload build() {
        PullRequestReviewCommentPayload stubPayload = mock(PullRequestReviewCommentPayload.class);
        CommitComment stubComment = mock(CommitComment.class);
        when(stubComment.getCommitId()).thenReturn(commitId);
        when(stubComment.getBody()).thenReturn(comment);
        when(stubPayload.getComment()).thenReturn(stubComment);
        return stubPayload;
    }

    public PullRequestReviewCommentPayloadBuilder defaultStubPayload() {
        this.commitId = "commitId";
        return this;
    }

    public PullRequestReviewCommentPayloadBuilder withCommitId(String commitId) {
        this.commitId = commitId;
        return this;
    }

    public PullRequestReviewCommentPayloadBuilder withComment(String comment) {
        this.comment = comment;
        return this;
    }
}
