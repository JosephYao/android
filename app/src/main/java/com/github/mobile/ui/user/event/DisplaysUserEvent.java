package com.github.mobile.ui.user.event;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.display.Display;

import java.util.ArrayList;
import java.util.List;

public class DisplaysUserEvent implements UserEvent {
    private final List<Display> displaysToMain = new ArrayList<>();
    private final List<Display> displaysToDetails = new ArrayList<>();
    private String icon;

    @Override
    public String generate(StyledText main, StyledText details) {
        renderDisplaysToMain(main);
        renderDisplaysToDetails(details);
        return icon;
    }

    public void addDisplayToMain(Display display) {
        displaysToMain.add(display);
    }

    public void addDisplayToDetails(Display display) {
        displaysToDetails.add(display);
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    private void renderDisplaysToDetails(StyledText details) {
        for (Display display : displaysToDetails)
            display.render(details);
    }

    private void renderDisplaysToMain(StyledText main) {
        for (Display display : displaysToMain)
            display.render(main);
    }
}
