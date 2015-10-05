package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.PullRequestPayload;

public class PullRequestPayloadBuilder implements PayloadBuilder {
    private String action;
    private int number;
    private String title;

    @Override
    public EventPayload build() {
        PullRequestPayload stubPayload = mock(PullRequestPayload.class);
        when(stubPayload.getAction()).thenReturn(action);
        when(stubPayload.getNumber()).thenReturn(number);
        PullRequest stubPullRequest = stubPullRequest();
        when(stubPayload.getPullRequest()).thenReturn(stubPullRequest);
        return stubPayload;
    }

    private PullRequest stubPullRequest() {
        PullRequest stubPullRequest = mock(PullRequest.class);
        when(stubPullRequest.getTitle()).thenReturn(title);
        return stubPullRequest;
    }

    public PullRequestPayloadBuilder defaultStubPayload() {
        return this;
    }

    public PullRequestPayloadBuilder withAction(String action) {
        this.action = action;
        return this;
    }

    public PullRequestPayloadBuilder withNumber(int number) {
        this.number = number;
        return this;
    }

    public PullRequestPayloadBuilder withPullRequestTitle(String title) {
        this.title = title;
        return this;
    }
}
