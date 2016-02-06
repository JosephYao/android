package com.github.mobile.ui.user.event;

import com.github.mobile.ui.StyledText;

public interface UserEvent {
    String generate(StyledText main, StyledText details);
}
