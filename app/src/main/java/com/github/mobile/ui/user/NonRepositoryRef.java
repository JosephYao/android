package com.github.mobile.ui.user;

import com.github.mobile.ui.StyledText;

import org.eclipse.egit.github.core.event.EventRepository;

public class NonRepositoryRef implements PayloadRef {
    private final String ref;
    private final String refType;
    private final EventRepository repo;

    public NonRepositoryRef(String refType, String ref, EventRepository repo) {
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
        renderRepoName(main);
    }

    private void renderRepoName(StyledText main) {
        if (repo != null)
            main.bold(repo.getName());
    }

}
