package com.github.mobile.ui.user.commit;

import com.github.mobile.ui.StyledText;

public class NonEmptyCommit implements Commit {
    private final CommitSha commitSha;
    private final CommitMessage commitMessage;

    public NonEmptyCommit(String sha, String message) {
        this.commitSha = new CommitSha(sha);
        this.commitMessage = new CommitMessage(message);
    }

    @Override
    public void render(StyledText text) {
        text.append('\n');
        commitSha.render(text);
        commitMessage.render(text);
    }

}
