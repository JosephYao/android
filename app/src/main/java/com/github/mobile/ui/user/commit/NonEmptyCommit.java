package com.github.mobile.ui.user.commit;

import android.text.TextUtils;

import com.github.mobile.ui.StyledText;

public class NonEmptyCommit implements Commit {
    private final org.eclipse.egit.github.core.Commit commit;

    public NonEmptyCommit(org.eclipse.egit.github.core.Commit commit) {
        this.commit = commit;
    }

    @Override
    public void render(StyledText text) {
        if (commit == null)
            return;

        String sha = commit.getSha();
        if (TextUtils.isEmpty(sha))
            return;

        text.append('\n');
        if (sha.length() > 7)
            text.monospace(sha.substring(0, 7));
        else
            text.monospace(sha);

        String message = commit.getMessage();
        if (!TextUtils.isEmpty(message)) {
            text.append(' ');
            int newline = message.indexOf('\n');
            if (newline > 0)
                text.append(message.subSequence(0, newline));
            else
                text.append(message);
        }
    }
}
