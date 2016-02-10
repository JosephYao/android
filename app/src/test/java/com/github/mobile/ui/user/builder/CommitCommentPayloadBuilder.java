package com.github.mobile.ui.user.builder;

import org.eclipse.egit.github.core.CommitComment;
import org.eclipse.egit.github.core.event.CommitCommentPayload;
import org.eclipse.egit.github.core.event.EventPayload;

public class CommitCommentPayloadBuilder implements PayloadBuilder {

    private CommitCommentBuilder commitCommentBuilder;

    public CommitCommentPayloadBuilder() {
        commitCommentBuilder = new CommitCommentBuilder().defaultStubComment().withCommitId("commitId");
    }

    public EventPayload build() {
        CommitCommentPayload commitCommentPayload = new CommitCommentPayload();
        CommitComment stubComment = commitCommentBuilder.build();
        commitCommentPayload.setComment(stubComment);
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
