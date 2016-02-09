package com.github.mobile.ui.user.commit;

import static com.github.kevinsawicki.wishlist.ViewUpdater.FORMAT_INT;

import com.github.mobile.ui.StyledText;

import java.util.List;

public class NonEmptyCommits implements Commits {
    private static final int MAX_COMMIT_TO_RENDER = 3;
    private final List<Commit> commits;

    public NonEmptyCommits(List<Commit> commits) {
        this.commits = commits;
    }

    @Override
    public void render(StyledText text) {
        renderNumberOfCommits(text);
        renderDetailOfCommits(text);
    }

    private void renderDetailOfCommits(StyledText text) {
        for (int index = 0; index < countOfCommitToRender(); index++)
            commits.get(index).render(text);
    }

    private int countOfCommitToRender() {
        return Math.min(commits.size(), MAX_COMMIT_TO_RENDER);
    }

    private void renderNumberOfCommits(StyledText text) {
        if (commits.size() > 1)
            renderNumberOfCommitsMoreThanOne(text);
        else
            renderNumberOfOneCommit(text);
    }

    private StyledText renderNumberOfOneCommit(StyledText text) {
        return text.append("1 new commit");
    }

    private StyledText renderNumberOfCommitsMoreThanOne(StyledText text) {
        return text.append(FORMAT_INT.format(commits.size())).append(" new commits");
    }
}
