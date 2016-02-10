package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.CommitComment;
import org.eclipse.egit.github.core.event.CommitCommentPayload;
import org.eclipse.egit.github.core.event.EventPayload;

public class CommitCommentPayloadBuilder implements PayloadBuilder {

    private CommitCommentBuilder commitCommentBuilder;

    public CommitCommentPayloadBuilder() {
        commitCommentBuilder = new CommitCommentBuilder().defaultStubComment().withCommitId("commitId");
    }

    public EventPayload build() {
        CommitCommentPayload stubCommitCommentPayload = mock(CommitCommentPayload.class);
        CommitComment stubComment = commitCommentBuilder.build();
        when(stubCommitCommentPayload.getComment()).thenReturn(stubComment);
        return stubCommitCommentPayload;
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
