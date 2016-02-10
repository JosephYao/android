package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.CommitComment;

public class CommitCommentBuilder {
    private String commitId;
    private CommentBuilder<CommitComment> commentBuilder;

    public CommitCommentBuilder() {
        commentBuilder = new CommentBuilder<>(CommitComment.class).defaultStubComment();
    }

    public CommitCommentBuilder withComment(String comment) {
        commentBuilder.withComment(comment);
        return this;
    }

    public CommitCommentBuilder withCommitId(String commitId) {
        this.commitId = commitId;
        return this;
    }

    public CommitComment build() {
        CommitComment stubComment = commentBuilder.build();
        when(stubComment.getCommitId()).thenReturn(commitId);
        return stubComment;
    }
}
