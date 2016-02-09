package com.github.mobile.ui.user.commit;

import com.github.mobile.ui.StyledText;

public class NonEmptyCommitMessage implements CommitMessage {
    private final String message;

    public NonEmptyCommitMessage(String message) {
        this.message = message;
    }

    public void render(StyledText text) {
        text.append(' ');
        text.append(message());
    }

    private CharSequence message() {
        if (hasMoreThanOneLine())
            return firstLineOfMessage();
        else
            return message;
    }

    private CharSequence firstLineOfMessage() {
        return message.subSequence(0, indexOfLineDelimiter());
    }

    private boolean hasMoreThanOneLine() {
        return indexOfLineDelimiter() > 0;
    }

    private int indexOfLineDelimiter() {
        return message.indexOf('\n');
    }
}