package com.github.mobile.ui.user.commit;

import com.github.mobile.ui.StyledText;

public class NonEmptyCommit implements Commit {
    private final CommitSha commitSha;
    private final CommitMessage commitMessage;

    public NonEmptyCommit(CommitSha commitSha, CommitMessage commitMessage) {
        this.commitSha = commitSha;
        this.commitMessage = commitMessage;
    }

    @Override
    public void render(StyledText text) {
        text.append('\n');
        commitSha.render(text);
        commitMessage.render(text);
    }

}
