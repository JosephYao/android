package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.PullRequestPayload;

public class PullRequestPayloadBuilder implements PayloadBuilder {
    private String action;
    private int number;
    private PullRequestBuilder pullRequestBuilder;

    @Override
    public EventPayload build() {
        PullRequestPayload stubPayload = mock(PullRequestPayload.class);
        when(stubPayload.getAction()).thenReturn(action);
        when(stubPayload.getNumber()).thenReturn(number);
        when(stubPayload.getPullRequest()).thenReturn(pullRequest());
        return stubPayload;
    }

    private PullRequest pullRequest() {
        return pullRequestBuilder.build();
    }

    public PullRequestPayloadBuilder defaultStubPayload() {
        pullRequestBuilder = new PullRequestBuilder();
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
        pullRequestBuilder.withTitle(title);
        return this;
    }
}
