package com.github.mobile.ui.user;

import com.github.mobile.ui.StyledText;

import org.eclipse.egit.github.core.event.EventRepository;

public class RepositoryRepo implements Repo {
    private final String name;

    public RepositoryRepo(EventRepository repo) {
        this.name = repo.getName();
    }

    @Override
    public void render(StyledText text) {
        if (hasNoSlashAndSlashAsLastChar())
            text.bold(repositoryName());
    }

    private String repositoryName() {
        return name.substring(slashPos() + 1);
    }

    private boolean hasNoSlashAndSlashAsLastChar() {
        return slashPos() != -1 && slashPos() + 1 < name.length();
    }

    private int slashPos() {
        return name.indexOf('/');
    }

}
