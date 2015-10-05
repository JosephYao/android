package com.github.mobile.ui.user.action;

import com.github.mobile.ui.StyledText;

public class PullRequestAction implements Action {
    private final String action;
    private final int payloadNumber;

    public PullRequestAction(String action, int payloadNumber) {
        this.action = action;
        this.payloadNumber = payloadNumber;
    }

    @Override
    public void render(StyledText text) {
        text.append(' ');
        text.append(action);
        text.append(' ');
        text.bold("pull request " + payloadNumber);
        text.append(" on ");
    }

    @Override
    public String getIcon() {
        return null;
    }
}
