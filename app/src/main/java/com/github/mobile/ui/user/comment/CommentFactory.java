package com.github.mobile.ui.user.comment;

import android.text.TextUtils;

import org.eclipse.egit.github.core.CommitComment;
import org.eclipse.egit.github.core.event.CommitCommentPayload;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.IssueCommentPayload;
import org.eclipse.egit.github.core.event.PullRequestReviewCommentPayload;

public class CommentFactory {

    public static Comment create(Event event) {
        EventPayload payload = event.getPayload();

        if (payload instanceof IssueCommentPayload)
            return createFromComment(((IssueCommentPayload) payload).getComment());

        if (payload instanceof CommitCommentPayload)
            return createFromCommitComment(((CommitCommentPayload) payload).getComment());
        else
            return createFromCommitComment(((PullRequestReviewCommentPayload) payload).getComment());
    }

    private static Comment createFromComment(org.eclipse.egit.github.core.Comment comment) {
        return new NonCommitIdComment(CommentBodyFactory.createBody(comment));
    }

    private static Comment createFromCommitComment(CommitComment comment) {
        if (isCommitIdEmpty(comment))
            return createFromComment(comment);
        else
            return new CommitIdComment(CommentBodyFactory.createBody(comment), commitId(comment));
    }

    private static String commitId(CommitComment comment) {
        String commitId = comment.getCommitId();

        if (isShortId(commitId))
            return commitId;
        else
            return truncatedOf(commitId);
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

}
