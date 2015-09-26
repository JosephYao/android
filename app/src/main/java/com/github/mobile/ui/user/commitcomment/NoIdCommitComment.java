package com.github.mobile.ui.user.commitcomment;

import com.github.mobile.ui.StyledText;

public class NoIdCommitComment implements CommitComment {
    private final String body;

    public NoIdCommitComment(String body) {
        this.body = body;
    }

    @Override
    public void render(StyledText text) {
        text.append(body.trim());
    }

}
