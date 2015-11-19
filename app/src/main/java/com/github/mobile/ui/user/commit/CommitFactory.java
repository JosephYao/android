package com.github.mobile.ui.user.commit;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.egit.github.core.event.PushPayload;

public class CommitFactory {
    public static Commits createCommits(PushPayload payload) {
        if (payload.getCommits() == null || payload.getCommits().size() == 0)
            return new EmptyCommits();

        List<Commit> commits = new ArrayList<>();
        for (org.eclipse.egit.github.core.Commit commit : payload.getCommits())
            commits.add(new NonEmptyCommit(commit));

        return new NonEmptyCommits(commits);
    }
}
