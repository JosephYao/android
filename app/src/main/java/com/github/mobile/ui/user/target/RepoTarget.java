package com.github.mobile.ui.user.target;

import android.text.TextUtils;

import com.github.mobile.ui.StyledText;

import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.event.EventRepository;

public class RepoTarget implements Target {
    private final Event event;

    public RepoTarget(Event event) {
        this.event = event;
    }

    @Override
    public void render(StyledText text) {
        boldRepoName(text, event);
    }

    private StyledText boldRepoName(final StyledText text,
            final Event event) {
        EventRepository repo = event.getRepo();
        if (repo != null) {
            String name = repo.getName();
            if (!TextUtils.isEmpty(name)) {
                int slash = name.indexOf('/');
                if (slash != -1 && slash + 1 < name.length())
                    text.bold(name.substring(slash + 1));
            }
        }
        return text;
    }
}
