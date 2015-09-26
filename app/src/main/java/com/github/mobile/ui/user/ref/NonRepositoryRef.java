package com.github.mobile.ui.user.ref;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.repo.Repo;

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
    public void render(StyledText text) {
        text.append(refType);
        text.append(' ');
        text.append(ref);
        text.append(" at ");
        repo.render(text);
    }

}
