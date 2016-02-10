package com.github.mobile.ui.user.builder;

import org.eclipse.egit.github.core.Comment;

public class CommentBuilder<T extends Comment> {

    private final Class<T> classOfComment;
    private String comment;

    public CommentBuilder(Class<T> classOfComment) {
        this.classOfComment = classOfComment;
    }

    public T build() {
        T comment = comment();
        comment.setBody(this.comment);
        return comment;
    }

    private T comment() {
        T comment = null;
        try {
            comment = classOfComment.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return comment;
    }

    public CommentBuilder<T> withComment(String comment) {
        this.comment = comment;
        return this;
    }

}
