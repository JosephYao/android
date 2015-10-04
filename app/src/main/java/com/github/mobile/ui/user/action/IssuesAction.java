package com.github.mobile.ui.user.action;

import com.github.mobile.ui.StyledText;

public class IssuesAction implements Action {
    private final String action;
    private final int issueNumber;

    public IssuesAction(String action, int issueNumber) {
        this.action = action;
        this.issueNumber = issueNumber;
    }

    @Override
    public void render(StyledText main) {
        main.append(' ');
        main.append(action);
        main.append(' ');
        main.bold("issue " + issueNumber);
        main.append(" on ");
    }
}
