package com.github.mobile.ui.user.download;

import com.github.mobile.ui.StyledText;

public class NonEmptyDownload implements Download {
    private final String name;

    public NonEmptyDownload(String name) {
        this.name = name;
    }

    @Override
    public void render(StyledText text) {
        text.append(name.trim());
    }

}
