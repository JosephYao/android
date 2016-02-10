package com.github.mobile.ui.user.builder;

import org.eclipse.egit.github.core.CommitComment;

public class CommitCommentBuilder {
    private String commitId;
    private CommentBuilder<CommitComment> commentBuilder = new CommentBuilder<>(CommitComment.class);

    public CommitCommentBuilder withComment(String comment) {
        commentBuilder.withComment(comment);
        return this;
    }

    public CommitCommentBuilder withCommitId(String commitId) {
        this.commitId = commitId;
        return this;
    }

    public CommitComment build() {
        CommitComment commitComment = commentBuilder.build();
        commitComment.setCommitId(commitId);
        return commitComment;
    }
}
