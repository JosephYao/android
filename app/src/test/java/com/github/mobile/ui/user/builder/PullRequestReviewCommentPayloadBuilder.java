package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;

import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.PullRequestReviewCommentPayload;

public class PullRequestReviewCommentPayloadBuilder implements PayloadBuilder {
    @Override
    public EventPayload build() {
        PullRequestReviewCommentPayload stubPayload = mock(PullRequestReviewCommentPayload.class);
        return stubPayload;
    }

    public PullRequestReviewCommentPayloadBuilder defaultStubPayload() {
        return this;
    }
}
