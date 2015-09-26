package com.github.mobile.ui.user.download;

import com.github.mobile.ui.StyledText;

public class NoEmptyDownload implements Download {
    private final String name;

    public NoEmptyDownload(String name) {
        this.name = name;
    }

    @Override
    public void render(StyledText text) {
        text.append(name.trim());
    }

}
