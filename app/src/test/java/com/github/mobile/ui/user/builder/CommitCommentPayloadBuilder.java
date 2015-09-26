package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.CommitComment;
import org.eclipse.egit.github.core.event.CommitCommentPayload;
import org.eclipse.egit.github.core.event.EventPayload;

public class CommitCommentPayloadBuilder implements PayloadBuilder {

    private String commentId;
    private String comment;

    public CommitCommentPayloadBuilder defaultStubPayload() {
        this.commentId = "commentId";
        this.comment = "comment";
        return this;
    }

    public EventPayload build() {
        CommitCommentPayload stubCommitCommentPayload = mock(CommitCommentPayload.class);
        CommitComment stubComment = stubCommitComment();
        when(stubCommitCommentPayload.getComment()).thenReturn(stubComment);
        return stubCommitCommentPayload;
    }

    private CommitComment stubCommitComment() {
        CommitComment stubComment = mock(CommitComment.class);
        when(stubComment.getCommitId()).thenReturn(commentId);
        when(stubComment.getBody()).thenReturn(comment);
        return stubComment;
    }

    public PayloadBuilder withCommentId(String commentId) {
        this.commentId = commentId;
        return this;
    }

    public PayloadBuilder withComment(String comment) {
        this.comment = comment;
        return this;
    }
}
