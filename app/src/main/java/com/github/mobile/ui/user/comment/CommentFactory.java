package com.github.mobile.ui.user.comment;

import static com.github.mobile.ui.user.FactoryUtils.isTrimmedTextEmpty;
import android.text.TextUtils;

import org.eclipse.egit.github.core.CommitComment;
import org.eclipse.egit.github.core.event.CommitCommentPayload;
import org.eclipse.egit.github.core.event.IssueCommentPayload;

public class CommentFactory {

    public static Comment createFromCommitCommentPayload(CommitCommentPayload payload) {
        org.eclipse.egit.github.core.CommitComment comment = payload.getComment();
        String commitId = comment.getCommitId();

        CommentBody body = createBody(comment);

        if (comment == null || TextUtils.isEmpty(commitId))
            return new NonCommitIdComment(body);

        if (isShortId(commitId))
            return new CommitIdComment(body, commitId);

        return new CommitIdComment(body, truncatedOf(commitId));
    }

    private static CommentBody createBody(CommitComment comment) {
        if (comment == null || isTrimmedTextEmpty(comment.getBody()))
            return new EmptyCommentBody();

        return new NonEmptyCommentBody(comment.getBody());
    }

    public static Comment createFromIssueCommentPayload(IssueCommentPayload payload) {
        org.eclipse.egit.github.core.Comment comment = payload.getComment();

        if (isCommentEmpty(comment))
            return new EmptyComment(new EmptyCommentBody());

        return new NonCommitIdComment(new NonEmptyCommentBody(comment.getBody()));
    }

    private static boolean isCommentEmpty(org.eclipse.egit.github.core.Comment comment) {
        return comment == null || isTrimmedTextEmpty(comment.getBody());
    }

    private static String truncatedOf(String commitId) {
        return commitId.substring(0, 10);
    }

    private static boolean isShortId(String commitId) {
        return commitId.length() <= 10;
    }

}
