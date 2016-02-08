package com.github.mobile.ui.user.commit;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.PushPayload;

public class CommitFactory {
    public static Commits create(Event event) {
        PushPayload payload = (PushPayload)event.getPayload();
        if (payload.getCommits() == null || payload.getCommits().size() == 0)
            return new EmptyCommits();

        List<Commit> commits = new ArrayList<>();
        for (org.eclipse.egit.github.core.Commit commit : payload.getCommits())
            commits.add(createCommit(commit));

        return new NonEmptyCommits(commits);
    }

    private static Commit createCommit(org.eclipse.egit.github.core.Commit commit) {
        if (commit == null || TextUtils.isEmpty(commit.getSha()))
            return new EmptyCommit();

        return new NonEmptyCommit(commit);
    }
}
