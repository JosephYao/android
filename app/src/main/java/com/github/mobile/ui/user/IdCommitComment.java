package com.github.mobile.ui.user;

import com.github.mobile.ui.StyledText;

public class IdCommitComment implements CommitComment{

    private final String commitId;
    private final String body;

    public IdCommitComment(String body, String commitId) {
        this.commitId = commitId;
        this.body = body;
    }

    @Override
    public void render(StyledText text) {
        appendCommitId(text);
        new NoIdCommitComment(body).render(text);
    }

    private void appendCommitId(StyledText text) {
        text.append("Comment in");
        text.append(' ');
        text.monospace(commitId);
        text.append(':').append('\n');
    }
}
