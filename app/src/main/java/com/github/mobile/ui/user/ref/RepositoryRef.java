package com.github.mobile.ui.user.ref;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.repo.Repo;

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
