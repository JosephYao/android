package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.Comment;

public class CommentBuilder<T extends Comment> {

    private final Class<T> classOfComment;
    private String comment;

    public CommentBuilder(Class<T> classOfComment) {
        this.classOfComment = classOfComment;
    }

    public CommentBuilder<T> defaultStubComment() {
        return this;
    }

    public T build() {
        T stubComment = mock(classOfComment);
        when(stubComment.getBody()).thenReturn(comment);
        return stubComment;
    }

    public CommentBuilder<T> withComment(String comment) {
        this.comment = comment;
        return this;
    }

}
