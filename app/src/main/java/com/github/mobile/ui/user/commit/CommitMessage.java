package com.github.mobile.ui.user.commit;

import android.text.TextUtils;

import com.github.mobile.ui.StyledText;

public class CommitMessage {
    private static final char LINE_DELIMITER = '\n';
    private final String message;

    public CommitMessage(String message) {
        this.message = message;
    }

    public void render(StyledText text) {
        if (TextUtils.isEmpty(message))
            return;

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
        return message.indexOf(LINE_DELIMITER);
    }
}