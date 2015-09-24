package com.github.mobile.ui.user;

import org.eclipse.egit.github.core.event.CommitCommentPayload;

public class ShortIdCommitComment extends IdCommitComment {
    public ShortIdCommitComment(CommitCommentPayload payload) {
        super(payload, payload.getComment().getCommitId());
    }
}
