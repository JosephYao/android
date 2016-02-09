package com.github.mobile.ui.user.repo;

import com.github.mobile.ui.StyledText;

public class RepositoryRepo implements Repo {
    private final String name;

    public RepositoryRepo(String name) {
        this.name = name;
    }

    @Override
    public void render(StyledText text) {
        if (hasSlashAndNotLastChar())
            text.bold(repositoryName());
    }

    private String repositoryName() {
        return name.substring(slashPos() + 1);
    }

    private boolean hasSlashAndNotLastChar() {
        return slashPos() + 1 < name.length();
    }

    private int slashPos() {
        return name.indexOf('/');
    }

}
