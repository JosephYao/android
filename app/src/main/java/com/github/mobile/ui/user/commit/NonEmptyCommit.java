package com.github.mobile.ui.user.commit;

import android.text.TextUtils;

import com.github.mobile.ui.StyledText;

public class NonEmptyCommit implements Commit {
    private final String sha;
    private final String message;

    public NonEmptyCommit(org.eclipse.egit.github.core.Commit commit) {
        sha = commit.getSha();
        message = commit.getMessage();
    }

    @Override
    public void render(StyledText text) {
        text.append('\n');
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
        return message.indexOf('\n');
    }

    private void appendSha(StyledText text) {
        if (sha.length() > 7)
            text.monospace(sha.substring(0, 7));
        else
            text.monospace(sha);
    }
}
