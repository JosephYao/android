package com.github.mobile.ui.user.commit;

import org.eclipse.egit.github.core.event.PushPayload;

public class CommitFactory {
    public static Commits createCommits(PushPayload payload) {
        if (payload.getCommits() == null || payload.getCommits().size() == 0)
            return new EmptyCommits();

        return new NonEmptyCommits(payload.getCommits());
    }
}
