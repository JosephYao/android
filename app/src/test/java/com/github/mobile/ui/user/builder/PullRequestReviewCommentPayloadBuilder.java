package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.CommitComment;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.PullRequestReviewCommentPayload;

public class PullRequestReviewCommentPayloadBuilder implements PayloadBuilder {
    private CommitCommentBuilder commitCommentBuilder;

    @Override
    public EventPayload build() {
        PullRequestReviewCommentPayload stubPayload = mock(PullRequestReviewCommentPayload.class);
        CommitComment stubComment = commitCommentBuilder.build();
        when(stubPayload.getComment()).thenReturn(stubComment);
        return stubPayload;
    }

    public PullRequestReviewCommentPayloadBuilder defaultStubPayload() {
        commitCommentBuilder = new CommitCommentBuilder().defaultStubComment().withCommitId("commitId");
        return this;
    }

    public PullRequestReviewCommentPayloadBuilder withCommitId(String commitId) {
        commitCommentBuilder.withCommitId(commitId);
        return this;
    }

    public PullRequestReviewCommentPayloadBuilder withComment(String comment) {
        commitCommentBuilder.withComment(comment);
        return this;
    }
}
