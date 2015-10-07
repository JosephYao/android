package com.github.mobile.ui.user.comment;

import com.github.mobile.ui.StyledText;

public class EmptyComment implements Comment {
    private final CommentBody body;

    public EmptyComment(CommentBody body) {
        this.body = body;
    }

    @Override
    public void render(StyledText text) {
        body.render(text);
    }
}
