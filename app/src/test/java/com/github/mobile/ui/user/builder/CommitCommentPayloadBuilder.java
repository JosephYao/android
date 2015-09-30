package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.CommitComment;
import org.eclipse.egit.github.core.event.CommitCommentPayload;
import org.eclipse.egit.github.core.event.EventPayload;

public class CommitCommentPayloadBuilder implements PayloadBuilder {

    private final CommentBuilder<CommitComment> commentBuilder = new CommentBuilder<>(CommitComment.class).
            defaultStubComment();
    private String commentId;
    private String comment;

    public CommitCommentPayloadBuilder defaultStubPayload() {
        this.commentId = "commentId";
        this.comment = "comment";
        return this;
    }

    public EventPayload build() {
        CommitCommentPayload stubCommitCommentPayload = mock(CommitCommentPayload.class);
        CommitComment stubComment = commentBuilder.withComment(comment).build();
        when(stubComment.getCommitId()).thenReturn(commentId);
        when(stubCommitCommentPayload.getComment()).thenReturn(stubComment);
        return stubCommitCommentPayload;
    }

    public CommitCommentPayloadBuilder withCommentId(String commentId) {
        this.commentId = commentId;
        return this;
    }

    public CommitCommentPayloadBuilder withComment(String comment) {
        this.comment = comment;
        return this;
    }
}
