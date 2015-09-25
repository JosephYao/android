package com.github.mobile.ui.user;

import com.github.mobile.ui.StyledText;

import org.eclipse.egit.github.core.event.DeletePayload;

public class DeleteRef implements PayloadRef {
    private final String refType;
    private final String ref;

    public DeleteRef(DeletePayload payload) {
        this.refType = payload.getRefType();
        this.ref = payload.getRef();
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
