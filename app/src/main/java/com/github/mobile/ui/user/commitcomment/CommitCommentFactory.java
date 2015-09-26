package com.github.mobile.ui.user.commitcomment;

import static com.github.mobile.ui.user.FactoryUtils.isTrimmedTextNotEmpty;
import android.text.TextUtils;

import org.eclipse.egit.github.core.event.CommitCommentPayload;
import org.eclipse.egit.github.core.event.EventPayload;

public class CommitCommentFactory {

    public static CommitComment create(EventPayload payload) {
        CommitCommentPayload commitCommentPayload = (CommitCommentPayload) payload;
        org.eclipse.egit.github.core.CommitComment comment = commitCommentPayload.getComment();

        if (comment == null || isTrimmedTextNotEmpty(comment.getBody()))
            return new EmptyCommitComment();

        if (TextUtils.isEmpty(comment.getCommitId()))
            return new NoIdCommitComment(comment.getBody());

        if (comment.getCommitId().length() <= 10) {
            return new IdCommitComment(bodyOf(commitCommentPayload), shortCommitIdOf(commitCommentPayload));
        }

        return new IdCommitComment(bodyOf(commitCommentPayload), longCommitIdOf(commitCommentPayload));
    }

    private static String longCommitIdOf(CommitCommentPayload payload) {
        return payload.getComment().getCommitId().substring(0, 10);
    }

    private static String shortCommitIdOf(CommitCommentPayload payload) {
        return payload.getComment().getCommitId();
    }

    private static String bodyOf(CommitCommentPayload payload) {
        return payload.getComment().getBody();
    }

}
