package com.github.mobile.ui.user;

import android.text.TextUtils;

import org.eclipse.egit.github.core.event.EventRepository;

public class RepoFactory {
    public static Repo createRepoFromEventRepositoryAndRefType(EventRepository repo, String refType) {
        if (repo == null || TextUtils.isEmpty(repo.getName()))
            return new EmptyRepo();

        if (refType.equals("repository"))
            return new RepositoryRepo(repo.getName());
        else
            return new NonRepositoryRepo(repo.getName());
    }

    public static Repo createRepoFromEventRepository(EventRepository repo) {
        if (repo == null)
            return new EmptyRepo();

        return new NonRepositoryRepo(repo.getName());
    }
}