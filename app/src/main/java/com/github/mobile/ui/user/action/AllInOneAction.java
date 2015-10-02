package com.github.mobile.ui.user.action;

import com.github.mobile.ui.StyledText;

public class AllInOneAction implements Action {

    private final String action;
    private final String gistId;

    public AllInOneAction(String action, String gistId) {
        this.action = action;
        this.gistId = gistId;
    }

    @Override
    public void render(StyledText main) {
        renderAction(main);
        renderGistId(main);
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