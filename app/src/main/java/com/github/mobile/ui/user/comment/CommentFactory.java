package com.github.mobile.ui.user.comment;

import static com.github.mobile.ui.user.FactoryUtils.isTrimmedTextEmpty;
import android.text.TextUtils;

import org.eclipse.egit.github.core.CommitComment;
import org.eclipse.egit.github.core.event.CommitCommentPayload;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.IssueCommentPayload;

public class CommentFactory {

    public static Comment create(Event event) {
        if (event.getPayload() instanceof IssueCommentPayload)
            return createFromIssueCommentPayload((IssueCommentPayload) event.getPayload());

        return createFromCommitComment(((CommitCommentPayload)event.getPayload()).getComment());
    }

    public static Comment createFromIssueCommentPayload(IssueCommentPayload payload) {
        return new NonCommitIdComment(createBody(payload.getComment()));
    }

    public static Comment createFromCommitComment(CommitComment comment) {
        if (isCommitIdEmpty(comment))
            return new NonCommitIdComment(createBody(comment));

        return createCommitIdComment(comment.getCommitId(), createBody(comment));
    }

    private static boolean isCommentBodyEmpty(org.eclipse.egit.github.core.Comment comment) {
        return comment == null || isTrimmedTextEmpty(comment.getBody());
    }

    private static String truncatedOf(String commitId) {
        return commitId.substring(0, 10);
    }

    private static boolean isShortId(String commitId) {
        return commitId.length() <= 10;
    }

    private static boolean isCommitIdEmpty(CommitComment comment) {
        return comment == null || TextUtils.isEmpty(comment.getCommitId());
    }

    private static CommentBody createBody(org.eclipse.egit.github.core.Comment comment) {
        if (isCommentBodyEmpty(comment))
            return new EmptyCommentBody();

        return new NonEmptyCommentBody(comment.getBody());
    }

    private static Comment createCommitIdComment(String commitId, CommentBody body) {
        if (isShortId(commitId))
            return new CommitIdComment(body, commitId);

        return new CommitIdComment(body, truncatedOf(commitId));
    }
}
