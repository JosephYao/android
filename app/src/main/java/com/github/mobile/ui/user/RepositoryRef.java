package com.github.mobile.ui.user;

import android.text.TextUtils;

import com.github.mobile.ui.StyledText;

import org.eclipse.egit.github.core.event.EventRepository;

public class RepositoryRef implements PayloadRef {
    private final String refType;
    private final EventRepository repo;

    public RepositoryRef(String refType, EventRepository repo) {
        this.refType = refType;
        this.repo = repo;
    }

    @Override
    public void renderToMain(StyledText main) {
        main.append(refType);
        main.append(' ');
        renderRepoName(main);
    }

    private void renderRepoName(final StyledText text) {
        if (repo != null && !TextUtils.isEmpty(repoName())) {
            int slash = repoName().indexOf('/');
            if (slash != -1 && slash + 1 < repoName().length())
                text.bold(repoName().substring(slash + 1));
        }
    }

    private String repoName() {
        return repo.getName();
    }
}
