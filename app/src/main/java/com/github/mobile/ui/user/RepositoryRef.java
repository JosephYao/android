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
    public void renderToMain(StyledText main) {
        main.append(refType);
        main.append(' ');
        repo.render(main);
    }

}
