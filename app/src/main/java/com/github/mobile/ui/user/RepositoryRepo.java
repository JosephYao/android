package com.github.mobile.ui.user;

import android.text.TextUtils;

import com.github.mobile.ui.StyledText;

import org.eclipse.egit.github.core.event.EventRepository;

public class RepositoryRepo implements Repo {
    private final EventRepository repo;

    public RepositoryRepo(EventRepository repo) {
        this.repo = repo;
    }

    @Override
    public void render(StyledText text) {
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
