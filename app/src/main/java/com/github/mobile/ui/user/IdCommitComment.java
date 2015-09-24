package com.github.mobile.ui.user;

import com.github.mobile.ui.StyledText;

import org.eclipse.egit.github.core.event.CommitCommentPayload;

public abstract class IdCommitComment implements CommitComment{

    private final String commitId;
    private final String body;

    public IdCommitComment(CommitCommentPayload payload, String commitId) {
        this.commitId = commitId;
        this.body = payload.getComment().getBody();
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
