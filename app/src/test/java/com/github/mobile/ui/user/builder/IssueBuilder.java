package com.github.mobile.ui.user.builder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.egit.github.core.Issue;

public class IssueBuilder {
    private Integer number;
    private String title;

    public IssueBuilder defaultStubIssue() {
        return this;
    }

    public IssueBuilder withNumber(Integer number) {
        this.number = number;
        return this;
    }

    public Issue build() {
        Issue stubIssue = mock(Issue.class);
        when(stubIssue.getNumber()).thenReturn(number);
        when(stubIssue.getTitle()).thenReturn(title);
        return stubIssue;
    }

    public IssueBuilder withTitle(String title) {
        this.title = title;
        return this;
    }
}
