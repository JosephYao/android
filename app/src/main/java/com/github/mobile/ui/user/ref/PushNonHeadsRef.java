package com.github.mobile.ui.user.ref;

import com.github.mobile.ui.StyledText;

public class PushNonHeadsRef implements PayloadRef {
    private final String ref;

    public PushNonHeadsRef(String ref) {
        this.ref = ref;
    }

    @Override
    public void render(StyledText text) {
        text.bold(ref);
    }
}
