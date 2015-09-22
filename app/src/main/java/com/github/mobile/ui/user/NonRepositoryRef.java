package com.github.mobile.ui.user;

import com.github.mobile.ui.StyledText;

public class NonRepositoryRef implements PayloadRef {
    private final String ref;
    private final String refType;
    private final Repo repo;

    public NonRepositoryRef(String refType, String ref, Repo repo) {
        this.refType = refType;
        this.ref = ref;
        this.repo = repo;
    }

    @Override
    public void renderToMain(StyledText main) {
        main.append(refType);
        main.append(' ');
        main.append(ref);
        main.append(" at ");
        repo.render(main);
    }

}
