package com.github.mobile.ui.user;

import android.text.TextUtils;

import org.eclipse.egit.github.core.event.CommitCommentPayload;
import org.eclipse.egit.github.core.event.EventPayload;

public class CommitCommentFactory {

    public static CommitComment create(EventPayload payload) {
        CommitCommentPayload commitCommentPayload = (CommitCommentPayload) payload;
        org.eclipse.egit.github.core.CommitComment comment = commitCommentPayload.getComment();

        if (comment == null)
            return new EmptyCommitComment();

        if (TextUtils.isEmpty(comment.getCommitId()))
            return new NoIdCommitComment(comment.getBody());

        if (comment.getCommitId().length() <= 10)
            return new ShortIdCommitComment(commitCommentPayload);

        return new LongIdCommitComment(commitCommentPayload);
    }
}
