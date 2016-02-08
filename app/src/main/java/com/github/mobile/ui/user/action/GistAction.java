package com.github.mobile.ui.user.action;

import com.github.mobile.ui.StyledText;
import com.github.mobile.util.TypefaceUtils;

public class GistAction implements Action {

    private final String gistId;
    private final String actionText;

    public GistAction(String action, String gistId) {
        this.actionText = actionText(action);
        this.gistId = gistId;
    }

    @Override
    public void render(StyledText text) {
        renderAction(text);
        renderGistId(text);
    }

    @Override
    public String getIcon() {
        return TypefaceUtils.ICON_GIST;
    }

    private void renderGistId(StyledText text) {
        text.append(" Gist ");
        text.append(gistId);
    }

    private void renderAction(StyledText text) {
        text.append(actionText);
    }

    private String actionText(String action) {
        if ("create".equals(action))
            return "created";

        if ("update".equals(action))
            return "updated";

        return action;
    }
}
