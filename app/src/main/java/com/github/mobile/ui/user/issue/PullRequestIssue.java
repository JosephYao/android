package com.github.mobile.ui.user.issue;

import com.github.mobile.ui.StyledText;

public class PullRequestIssue implements Issue {
    private final int number;

    public PullRequestIssue(int number) {
        this.number = number;
    }

    @Override
    public void render(StyledText text) {
        text.bold("pull request " + number);
    }
}
