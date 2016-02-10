package com.github.mobile.ui.user.builder;

import org.eclipse.egit.github.core.CommitComment;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.PullRequestReviewCommentPayload;

public class PullRequestReviewCommentPayloadBuilder implements PayloadBuilder {
    private CommitCommentBuilder commitCommentBuilder = new CommitCommentBuilder().withCommitId("commitId");

    @Override
    public EventPayload build() {
        PullRequestReviewCommentPayload pullRequestReviewCommentPayload = new PullRequestReviewCommentPayload();
        pullRequestReviewCommentPayload.setComment(comment());
        return pullRequestReviewCommentPayload;
    }

    private CommitComment comment() {
        return commitCommentBuilder.build();
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
