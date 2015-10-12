package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.eclipse.egit.github.core.Commit;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.PushPayload;

public class PushPayloadBuilder implements PayloadBuilder {
    private String ref;
    private int commitCount;

    public PushPayloadBuilder defaultStubPayload() {
        this.ref = "ref";
        return this;
    }

    @Override
    public EventPayload build() {
        PushPayload stubPayload = mock(PushPayload.class);
        when(stubPayload.getRef()).thenReturn(ref);
        List<Commit> commits = Arrays.asList(stubCommit());
        when(stubPayload.getCommits()).thenReturn(commits);
        return stubPayload;
    }

    private Commit stubCommit() {
        Commit stubCommit = mock(Commit.class);
        when(stubCommit.getSha()).thenReturn("Sha");
        when(stubCommit.getMessage()).thenReturn("Message");
        return stubCommit;
    }

    public PushPayloadBuilder withRef(String ref) {
        this.ref = ref;
        return this;
    }

    public PushPayloadBuilder withOneCommit() {
        commitCount = 1;
        return this;
    }
}
