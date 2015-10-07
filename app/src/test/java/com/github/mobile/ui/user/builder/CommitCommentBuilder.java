package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.CommitComment;

public class CommitCommentBuilder {
    private String comment;
    private String commitId;

    public CommitCommentBuilder defaultStubComment() {
        return this;
    }

    public CommitCommentBuilder withComment(String comment) {
        this.comment = comment;
        return this;
    }

    public CommitCommentBuilder withCommitId(String commitId) {
        this.commitId = commitId;
        return this;
    }

    public CommitComment build() {
        CommitComment stubComment = new CommentBuilder<>(CommitComment.class).defaultStubComment().
                withComment(comment).
                build();
        when(stubComment.getCommitId()).thenReturn(commitId);
        return stubComment;
    }
}
