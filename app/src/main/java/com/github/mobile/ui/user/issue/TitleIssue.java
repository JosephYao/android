package com.github.mobile.ui.user.issue;

import com.github.mobile.ui.StyledText;

public class TitleIssue implements Issue {
    private final String title;

    public TitleIssue(String title) {
        this.title = title;
    }

    @Override
    public void render(StyledText text) {
        text.append(title);
    }
}
