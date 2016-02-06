package com.github.mobile.ui.user.display;

import com.github.mobile.ui.StyledText;

public class CharDisplay implements Display {
    private final char c;

    public CharDisplay(char c) {
        this.c = c;
    }

    @Override
    public void render(StyledText text) {
        text.append(c);
    }
}
