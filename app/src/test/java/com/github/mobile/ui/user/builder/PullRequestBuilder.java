package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.PullRequest;

public class PullRequestBuilder {
    private String title;

    public PullRequestBuilder defaultStubPullRequest() {
        return this;
    }

    public PullRequest build() {
        PullRequest stubPullRequest = mock(PullRequest.class);
        when(stubPullRequest.getTitle()).thenReturn(title);
        return stubPullRequest;
    }

    public PullRequestBuilder withTitle(String title) {
        this.title = title;
        return this;
    }
}
