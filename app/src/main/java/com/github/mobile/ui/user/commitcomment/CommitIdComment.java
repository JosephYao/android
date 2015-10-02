package com.github.mobile.ui.user.commitcomment;

import com.github.mobile.ui.StyledText;

public class CommitIdComment implements Comment {

    private final String commitId;
    private final String body;

    public CommitIdComment(String body, String commitId) {
        this.commitId = commitId;
        this.body = body;
    }

    @Override
    public void render(StyledText text) {
        appendCommitId(text);
        new NonEmptyComment(body).render(text);
    }

    private void appendCommitId(StyledText text) {
        text.append("Comment in");
        text.append(' ');
        text.monospace(commitId);
        text.append(':').append('\n');
    }
}
