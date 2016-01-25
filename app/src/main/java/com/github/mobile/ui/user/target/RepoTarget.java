package com.github.mobile.ui.user.target;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.repo.Repo;
import com.github.mobile.ui.user.repo.RepoFactory;

import org.eclipse.egit.github.core.event.Event;

public class RepoTarget implements Target {
    private final Repo repo;

    public RepoTarget(Event event) {
        repo = RepoFactory.createRepoFromEventRepositoryAndRefType(event.getRepo(), "repository");
    }

    @Override
    public void render(StyledText text) {
        repo.render(text);
    }

}
