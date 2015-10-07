package com.github.mobile.ui.user.comment;

import com.github.mobile.ui.StyledText;

public class CommitIdComment implements Comment {

    private final String commitId;
    private final CommentBody body;

    public CommitIdComment(CommentBody body, String commitId) {
        this.commitId = commitId;
        this.body = body;
    }

    @Override
    public void render(StyledText text) {
        appendCommitId(text);
        body.render(text);
    }

    private void appendCommitId(StyledText text) {
        text.append("Comment in");
        text.append(' ');
        text.monospace(commitId);
        text.append(':').append('\n');
    }
}
