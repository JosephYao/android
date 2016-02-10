package com.github.mobile.ui.user.builder;

import java.util.Collections;
import java.util.List;

import org.eclipse.egit.github.core.Commit;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.PushPayload;

public class PushPayloadBuilder implements PayloadBuilder {
    private String ref = "ref";
    private int numberOfCommits = 0;
    private String commitSha = "Sha";
    private String commitMessage = "Message";

    @Override
    public EventPayload build() {
        PushPayload pushPayload = new PushPayload();
        pushPayload.setRef(ref);
        pushPayload.setCommits(commits());
        return pushPayload;
    }

    private List<Commit> commits() {
        return Collections.nCopies(numberOfCommits, commit());
    }

    private Commit commit() {
        Commit commit = new Commit();
        commit.setSha(commitSha);
        commit.setMessage(commitMessage);
        return commit;
    }

    public PushPayloadBuilder withRef(String ref) {
        this.ref = ref;
        return this;
    }

    public PushPayloadBuilder withNumberOfCommits(int number) {
        numberOfCommits = number;
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
