package com.github.mobile.ui.user.builder;

import org.eclipse.egit.github.core.Issue;

public class IssueBuilder {
    private Integer number;
    private String title;

    public IssueBuilder withNumber(Integer number) {
        this.number = number;
        return this;
    }

    public Issue build() {
        Issue issue = new Issue();
        issue.setNumber(number);
        issue.setTitle(title);
        return issue;
    }

    public IssueBuilder withTitle(String title) {
        this.title = title;
        return this;
    }
}
