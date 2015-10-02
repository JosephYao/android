package com.github.mobile.ui.user;

import com.github.mobile.ui.StyledText;

public class NonPullRequestIssue implements Issue {
    private final int number;

    public NonPullRequestIssue(int number) {
        this.number = number;
    }

    @Override
    public void render(StyledText text) {
        text.bold("issue " + this.number);
    }
}
