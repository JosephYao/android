package com.github.mobile.ui.user.commit;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.PushPayload;

public class CommitsFactory {
    public static Commits create(Event event) {
        if (hasNoCommit(payloadCommits(event)))
            return new EmptyCommits();
        else
            return new NonEmptyCommits(commitsFrom(payloadCommits(event)));
    }

    private static List<org.eclipse.egit.github.core.Commit> payloadCommits(Event event) {
        return ((PushPayload)event.getPayload()).getCommits();
    }

    private static List<Commit> commitsFrom(List<org.eclipse.egit.github.core.Commit> payloadCommits) {
        List<Commit> commits = new ArrayList<>();
        for (org.eclipse.egit.github.core.Commit payloadCommit : payloadCommits)
            commits.add(CommitFactory.create(payloadCommit));
        return commits;
    }

    private static boolean hasNoCommit(List<org.eclipse.egit.github.core.Commit> payloadCommits) {
        return payloadCommits == null || payloadCommits.size() == 0;
    }

}
