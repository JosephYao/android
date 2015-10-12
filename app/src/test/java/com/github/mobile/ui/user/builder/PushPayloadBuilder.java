package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.eclipse.egit.github.core.Commit;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.PushPayload;

public class PushPayloadBuilder implements PayloadBuilder {
    private String ref;
    private int commitCount;
    private String commitSha;
    private String commitMessage;

    public PushPayloadBuilder defaultStubPayload() {
        this.ref = "ref";
        commitSha = "Sha";
        commitMessage = "Message";
        commitCount = 0;
        return this;
    }

    @Override
    public EventPayload build() {
        PushPayload stubPayload = mock(PushPayload.class);
        when(stubPayload.getRef()).thenReturn(ref);
        List<Commit> commits = Collections.nCopies(commitCount, stubCommit());
        when(stubPayload.getCommits()).thenReturn(commits);
        return stubPayload;
    }

    private Commit stubCommit() {
        Commit stubCommit = mock(Commit.class);
        when(stubCommit.getSha()).thenReturn(commitSha);
        when(stubCommit.getMessage()).thenReturn(commitMessage);
        return stubCommit;
    }

    public PushPayloadBuilder withRef(String ref) {
        this.ref = ref;
        return this;
    }

    public PushPayloadBuilder withNumberOfCommits(int number) {
        commitCount = number;
        return this;
    }

    public PushPayloadBuilder withCommitSha(String sha) {
        commitSha = sha;
        return this;
    }

    public PushPayloadBuilder withCommitMessage(String message) {
        commitMessage = message;
        return this;
    }
}
