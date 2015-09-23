package com.github.mobile.ui.user;

import com.github.mobile.ui.StyledText;

import org.eclipse.egit.github.core.event.EventRepository;

public class NonRepositoryRepo implements Repo {
    private final EventRepository repo;

    public NonRepositoryRepo(EventRepository repo) {
        this.repo = repo;
    }

    @Override
    public void render(StyledText text) {
        text.bold(repo.getName());
    }
}
