package com.github.mobile.ui.user.commit;

import static com.github.kevinsawicki.wishlist.ViewUpdater.FORMAT_INT;
import android.text.TextUtils;

import com.github.mobile.ui.StyledText;

import java.util.List;

import org.eclipse.egit.github.core.Commit;

public class NonEmptyCommits implements Commits {
    private final List<Commit> commits;

    public NonEmptyCommits(List<Commit> commits) {
        this.commits = commits;
    }

    @Override
    public void render(StyledText text) {
        renderNumberOfCommits(text, commits.size());
        renderDetailOfCommits(text, commits);
    }

    private void renderDetailOfCommits(StyledText details, List<Commit> commits) {
        int max = 3;
        int appended = 0;
        for (Commit commit : commits) {
            renderDetailOfCommit(details, commit);

            appended++;
            if (appended == max)
                return;
        }
    }

    private void renderDetailOfCommit(StyledText details, Commit commit) {
        if (commit == null)
            return;

        String sha = commit.getSha();
        if (TextUtils.isEmpty(sha))
            return;

        details.append('\n');
        if (sha.length() > 7)
            details.monospace(sha.substring(0, 7));
        else
            details.monospace(sha);

        String message = commit.getMessage();
        if (!TextUtils.isEmpty(message)) {
            details.append(' ');
            int newline = message.indexOf('\n');
            if (newline > 0)
                details.append(message.subSequence(0, newline));
            else
                details.append(message);
        }
    }

    private void renderNumberOfCommits(StyledText details, int size) {
        if (size != 1)
            details.append(FORMAT_INT.format(size)).append(" new commits");
        else
            details.append("1 new commit");
    }
}
