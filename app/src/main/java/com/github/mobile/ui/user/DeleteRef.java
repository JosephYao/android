package com.github.mobile.ui.user;

import com.github.mobile.ui.StyledText;

public class DeleteRef implements PayloadRef {
    private final String refType;
    private final String ref;

    public DeleteRef(String refType, String ref) {
        this.refType = refType;
        this.ref = ref;
    }

    @Override
    public void render(StyledText text) {
        text.append(" deleted ");
        text.append(refType);
        text.append(' ');
        text.append(ref);
        text.append(" at ");
    }
}
