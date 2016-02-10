package com.github.mobile.ui.user.builder;

import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.PullRequestPayload;

public class PullRequestPayloadBuilder implements PayloadBuilder {
    private String action;
    private int number;
    private PullRequestBuilder pullRequestBuilder = new PullRequestBuilder();

    @Override
    public EventPayload build() {
        PullRequestPayload pullRequestPayload = new PullRequestPayload();
        pullRequestPayload.setAction(action);
        pullRequestPayload.setNumber(number);
        pullRequestPayload.setPullRequest(pullRequest());
        return pullRequestPayload;
    }

    private PullRequest pullRequest() {
        return pullRequestBuilder.build();
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
