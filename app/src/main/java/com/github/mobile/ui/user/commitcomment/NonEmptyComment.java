package com.github.mobile.ui.user.commitcomment;

import com.github.mobile.ui.StyledText;

public class NonEmptyComment implements Comment {
    private final String body;

    public NonEmptyComment(String body) {
        this.body = body;
    }

    @Override
    public void render(StyledText text) {
        text.append(body.trim());
    }

}
