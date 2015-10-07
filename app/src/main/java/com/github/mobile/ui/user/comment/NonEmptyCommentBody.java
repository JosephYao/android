package com.github.mobile.ui.user.comment;

import com.github.mobile.ui.StyledText;

public class NonEmptyCommentBody implements CommentBody {
    private final String body;

    public NonEmptyCommentBody(String body) {
        this.body = body;
    }

    @Override
    public void render(StyledText text) {
        text.append(body.trim());
    }
}
