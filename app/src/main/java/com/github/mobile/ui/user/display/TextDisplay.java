package com.github.mobile.ui.user.display;

import com.github.mobile.ui.StyledText;

public class TextDisplay implements Display {
    private final String text;

    public TextDisplay(String text) {
        this.text = text;
    }

    @Override
    public void render(StyledText styledText) {
        styledText.append(text);
    }
}
