package com.github.mobile.ui.user;

import com.github.mobile.ui.StyledText;

public class NoIdCommitComment implements CommitComment {
    private final String body;

    public NoIdCommitComment(String body) {
        this.body = body;
    }

    @Override
    public void render(StyledText text) {
        if (!isTrimmedBodyEmpty())
            text.append(body.trim());
    }

    private boolean isTrimmedBodyEmpty() {
        return body == null && body.trim().length() == 0;
    }

}
