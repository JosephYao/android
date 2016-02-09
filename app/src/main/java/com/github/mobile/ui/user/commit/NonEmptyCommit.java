package com.github.mobile.ui.user.commit;

import android.text.TextUtils;

import com.github.mobile.ui.StyledText;

public class NonEmptyCommit implements Commit {
    private static final char LINE_DELIMITER = '\n';
    private static final int TRUNCATED_POS = 7;
    private final String sha;
    private final String message;

    public NonEmptyCommit(String sha, String message) {
        this.sha = sha;
        this.message = message;
    }

    @Override
    public void render(StyledText text) {
        text.append(LINE_DELIMITER);
        appendSha(text);
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

    private void appendSha(StyledText text) {
        text.monospace(truncatedSha());
    }

    private String truncatedSha() {
        return sha.substring(0, Math.min(TRUNCATED_POS, sha.length()));
    }
}
