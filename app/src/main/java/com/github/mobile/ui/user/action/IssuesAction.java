package com.github.mobile.ui.user.action;

import com.github.mobile.ui.StyledText;
import com.github.mobile.util.TypefaceUtils;

import java.util.HashMap;
import java.util.Map;

public class IssuesAction implements Action {
    private final String action;
    private final int issueNumber;
    private final static Map<String, String> ICONS = new HashMap<String, String>() {{
        put("opened", TypefaceUtils.ICON_ISSUE_OPEN);
        put("reopened", TypefaceUtils.ICON_ISSUE_REOPEN);
        put("closed", TypefaceUtils.ICON_ISSUE_CLOSE);
    }};

    public IssuesAction(String action, int issueNumber) {
        this.action = action;
        this.issueNumber = issueNumber;
    }

    @Override
    public void render(StyledText text) {
        text.append(' ');
        text.append(action);
        text.append(' ');
        text.bold("issue " + issueNumber);
        text.append(" on ");
    }

    @Override
    public String getIcon() {
        return ICONS.get(action);
    }

}
