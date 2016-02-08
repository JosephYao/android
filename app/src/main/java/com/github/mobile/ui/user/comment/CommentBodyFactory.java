package com.github.mobile.ui.user.comment;

import com.github.mobile.ui.user.FactoryUtils;

import org.eclipse.egit.github.core.Comment;

public class CommentBodyFactory {
    private static boolean isCommentBodyEmpty(Comment comment) {
        return comment == null || FactoryUtils.isTrimmedTextEmpty(comment.getBody());
    }

    public static CommentBody createBody(Comment comment) {
        if (isCommentBodyEmpty(comment))
            return new EmptyCommentBody();

        return new NonEmptyCommentBody(comment.getBody());
    }
}