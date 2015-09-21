package com.github.mobile.ui.user;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.CommitComment;
import org.eclipse.egit.github.core.event.CommitCommentPayload;
import org.eclipse.egit.github.core.event.EventPayload;

public class PayloadBuilder {

    private String commentId;
    private String comment;

    public PayloadBuilder defaultStubCommitCommentPayload() {
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
}
