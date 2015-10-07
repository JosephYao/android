package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.CommitComment;
import org.eclipse.egit.github.core.event.CommitCommentPayload;
import org.eclipse.egit.github.core.event.EventPayload;

public class CommitCommentPayloadBuilder implements PayloadBuilder {

    private final CommitCommentBuilder commitCommentBuilder = new CommitCommentBuilder().defaultStubComment();
    private String commitId;
    private String comment;

    public CommitCommentPayloadBuilder defaultStubPayload() {
        this.commitId = "commitId";
        return this;
    }

    public EventPayload build() {
        CommitCommentPayload stubCommitCommentPayload = mock(CommitCommentPayload.class);
        CommitComment stubComment = commitCommentBuilder.
                withComment(comment).
                withCommitId(commitId).
                build();
        when(stubCommitCommentPayload.getComment()).thenReturn(stubComment);
        return stubCommitCommentPayload;
    }

    public CommitCommentPayloadBuilder withCommitId(String commitId) {
        this.commitId = commitId;
        return this;
    }

    public CommitCommentPayloadBuilder withComment(String comment) {
        this.comment = comment;
        return this;
    }
}
