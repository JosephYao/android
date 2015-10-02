package com.github.mobile.ui.user.commitcomment;

import static com.github.mobile.ui.user.FactoryUtils.isTrimmedTextNotEmpty;
import android.text.TextUtils;

import org.eclipse.egit.github.core.event.CommitCommentPayload;
import org.eclipse.egit.github.core.event.IssueCommentPayload;

public class CommentFactory {

    public static Comment createFromCommitCommentPayload(CommitCommentPayload payload) {
        org.eclipse.egit.github.core.CommitComment comment = payload.getComment();

        if (comment == null || isTrimmedTextNotEmpty(comment.getBody()))
            return new EmptyComment();

        return createBaseOnCommitId(comment.getBody(), comment.getCommitId());
    }

    private static Comment createBaseOnCommitId(String body, String commitId) {
        if (TextUtils.isEmpty(commitId))
            return new NonEmptyComment(body);

        if (isShortId(commitId)) {
            return new CommitIdComment(body, commitId);
        }

        return new CommitIdComment(body, truncatedOf(commitId));
    }

    private static String truncatedOf(String commitId) {
        return commitId.substring(0, 10);
    }

    private static boolean isShortId(String commitId) {
        return commitId.length() <= 10;
    }

    public static Comment createFromIssueCommentPayload(IssueCommentPayload payload) {
        org.eclipse.egit.github.core.Comment comment = payload.getComment();

        if (comment == null || isTrimmedTextNotEmpty(comment.getBody()))
            return new EmptyComment();

        return new NonEmptyComment(comment.getBody());
    }
}
