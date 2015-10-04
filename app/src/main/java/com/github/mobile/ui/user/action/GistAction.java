package com.github.mobile.ui.user.action;

import com.github.mobile.ui.StyledText;
import com.github.mobile.util.TypefaceUtils;

public class GistAction implements Action {

    private final String action;
    private final String gistId;

    public GistAction(String action, String gistId) {
        this.action = action;
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

    private void renderGistId(StyledText main) {
        main.append(" Gist ");
        main.append(gistId);
    }

    private void renderAction(StyledText main) {
        if ("create".equals(action))
            main.append("created");
        else if ("update".equals(action))
            main.append("updated");
        else
            main.append(action);
    }
}
