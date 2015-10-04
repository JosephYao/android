package com.github.mobile.ui.user.action;

import com.github.mobile.ui.StyledText;

public interface Action {

    void render(StyledText text);
    String getIcon();
}
