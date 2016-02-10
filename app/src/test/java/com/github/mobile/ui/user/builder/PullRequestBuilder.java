package com.github.mobile.ui.user.builder;

import org.eclipse.egit.github.core.PullRequest;

public class PullRequestBuilder {
    private String title;
    private String htmlUrl;

    public PullRequest build() {
        PullRequest pullRequest = new PullRequest();
        pullRequest.setTitle(title);
        pullRequest.setHtmlUrl(htmlUrl);
        return pullRequest;
    }

    public PullRequestBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public PullRequestBuilder withHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
        return this;
    }
}
