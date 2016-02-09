package com.github.mobile.ui.user.commit;

import android.text.TextUtils;

public class CommitFactory {
    public static Commit create(org.eclipse.egit.github.core.Commit commit) {
        if (commit == null || TextUtils.isEmpty(commit.getSha()))
            return new EmptyCommit();
        else
            return new NonEmptyCommit(new CommitSha(commit.getSha()), commitMessage(commit));
    }

    private static CommitMessage commitMessage(org.eclipse.egit.github.core.Commit commit) {
        if (TextUtils.isEmpty(commit.getMessage()))
            return new EmptyCommitMessage();

        return new NonEmptyCommitMessage(commit.getMessage());
    }
}