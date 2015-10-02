package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.CommitComment;
import org.eclipse.egit.github.core.event.CommitCommentPayload;
import org.eclipse.egit.github.core.event.EventPayload;

public class CommitCommentPayloadBuilder implements PayloadBuilder {

    private final CommentBuilder<CommitComment> commentBuilder = new CommentBuilder<>(CommitComment.class).
            defaultStubComment();
    private String commitId;
    private String comment;

    public CommitCommentPayloadBuilder defaultStubPayload() {
        this.commitId = "commitId";
        this.comment = "comment";
        return this;
    }

    public EventPayload build() {
        CommitCommentPayload stubCommitCommentPayload = mock(CommitCommentPayload.class);
        CommitComment stubComment = commentBuilder.withComment(comment).build();
        when(stubComment.getCommitId()).thenReturn(commitId);
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
