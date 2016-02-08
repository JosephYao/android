package com.github.mobile.ui.user.commit;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.PushPayload;

public class CommitsFactory {
    public static Commits create(Event event) {
        if (hasNoCommit(payloadCommits(event)))
            return new EmptyCommits();

        return new NonEmptyCommits(commits(payloadCommits(event)));
    }

    private static List<org.eclipse.egit.github.core.Commit> payloadCommits(Event event) {
        return ((PushPayload)event.getPayload()).getCommits();
    }

    private static List<Commit> commits(List<org.eclipse.egit.github.core.Commit> payloadCommits) {
        List<Commit> commits = new ArrayList<>();
        for (org.eclipse.egit.github.core.Commit commit : payloadCommits)
            commits.add(createCommit(commit));
        return commits;
    }

    private static boolean hasNoCommit(List<org.eclipse.egit.github.core.Commit> payloadCommits) {
        return payloadCommits == null || payloadCommits.size() == 0;
    }

    private static Commit createCommit(org.eclipse.egit.github.core.Commit commit) {
        if (commit == null || TextUtils.isEmpty(commit.getSha()))
            return new EmptyCommit();

        return new NonEmptyCommit(commit);
    }
}
