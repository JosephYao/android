package com.github.mobile.ui.user.builder;

import org.eclipse.egit.github.core.event.CommitCommentPayload;
import org.eclipse.egit.github.core.event.EventPayload;

public class CommitCommentPayloadBuilder implements PayloadBuilder {

    private CommitCommentBuilder commitCommentBuilder = new CommitCommentBuilder().withCommitId("commitId");

    public EventPayload build() {
        CommitCommentPayload commitCommentPayload = new CommitCommentPayload();
        commitCommentPayload.setComment(commitCommentBuilder.build());
        return commitCommentPayload;
    }

    public CommitCommentPayloadBuilder withCommitId(String commitId) {
        commitCommentBuilder.withCommitId(commitId);
        return this;
    }

    public CommitCommentPayloadBuilder withComment(String comment) {
        commitCommentBuilder.withComment(comment);
        return this;
    }
}
