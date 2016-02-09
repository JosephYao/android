package com.github.mobile.ui.user.commit;

import android.text.TextUtils;

import com.github.mobile.ui.StyledText;

public class NonEmptyCommit implements Commit {
    private static final char LINE_DELIMITER = '\n';
    private final String message;
    private final CommitSha commitSha;

    public NonEmptyCommit(String sha, String message) {
        this.commitSha = new CommitSha(sha);
        this.message = message;
    }

    @Override
    public void render(StyledText text) {
        text.append(LINE_DELIMITER);
        commitSha.render(text);
        appendMessage(text);
    }

    private void appendMessage(StyledText text) {
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
