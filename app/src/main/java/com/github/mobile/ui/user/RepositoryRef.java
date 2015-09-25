package com.github.mobile.ui.user;

import com.github.mobile.ui.StyledText;

public class RepositoryRef implements PayloadRef {
    private final String refType;
    private final Repo repo;

    public RepositoryRef(String refType, Repo repo) {
        this.refType = refType;
        this.repo = repo;
    }

    @Override
    public void render(StyledText text) {
        text.append(refType);
        text.append(' ');
        repo.render(text);
    }

}
