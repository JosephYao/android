package com.github.mobile.ui.user.builder;

import org.eclipse.egit.github.core.PullRequest;

public class NullPullRequestBuilder extends PullRequestBuilder {

    @Override
    public PullRequest build() {
        return null;
    }
}
