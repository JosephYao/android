package com.github.mobile.ui.user.pullrequest;

import com.github.mobile.ui.StyledText;

public class TitlePullRequest implements PullRequest {

    private final String title;

    public TitlePullRequest(String title) {
        this.title = title;
    }

    @Override
    public void render(StyledText text) {
        text.append(title);
    }
}
