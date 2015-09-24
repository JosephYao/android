package com.github.mobile.ui.user;

import org.eclipse.egit.github.core.event.CommitCommentPayload;

public class LongIdCommitComment extends IdCommitComment {
    public LongIdCommitComment(CommitCommentPayload payload) {
        super(payload, payload.getComment().getCommitId().substring(0, 10));
    }
}
