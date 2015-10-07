package com.github.mobile.ui.user.comment;

import com.github.mobile.ui.StyledText;

public class NonBobyCommitIdComment implements Comment {

    private final String commitId;

    public NonBobyCommitIdComment(String commitId) {
        this.commitId = commitId;
    }

    @Override
    public void render(StyledText text) {
        appendCommitId(text);
    }

    private void appendCommitId(StyledText text) {
        text.append("Comment in");
        text.append(' ');
        text.monospace(commitId);
        text.append(':').append('\n');
    }
}
