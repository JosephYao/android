package com.github.mobile.ui.user.action;

import com.github.mobile.ui.StyledText;
import com.github.mobile.util.TypefaceUtils;

import java.util.HashMap;
import java.util.Map;

public class GistAction implements Action {

    private final String action;
    private final String gistId;
    private final Map<String, String> textToBeAppended;

    public GistAction(String action, String gistId) {
        this.action = action;
        this.gistId = gistId;
        textToBeAppended = new HashMap<String, String>() {{
            put("create", "created");
            put("update", "updated");
        }};
        if (!textToBeAppended.containsKey(action))
            textToBeAppended.put(action, action);
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
        main.append(textToBeAppended.get(action));
    }
}
