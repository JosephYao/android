package com.github.mobile.ui.user.event;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.comment.Comment;
import com.github.mobile.ui.user.display.Display;
import com.github.mobile.ui.user.display.TextDisplay;
import com.github.mobile.ui.user.repo.Repo;
import com.github.mobile.ui.user.user.User;
import com.github.mobile.util.TypefaceUtils;

import java.util.ArrayList;
import java.util.List;

public class CommitCommentUserEvent implements UserEvent {
    private final List<Display> displaysToMain = new ArrayList<>();
    private final List<Display> displaysToDetails = new ArrayList<>();

    public CommitCommentUserEvent(Comment comment, User actor, Repo repo) {
        this.displaysToMain.add(actor);
        this.displaysToMain.add(new TextDisplay(" commented on "));
        this.displaysToMain.add(repo);
        this.displaysToDetails.add(comment);
    }

    @Override
    public String generate(StyledText main, StyledText details) {
        for (Display display : displaysToMain)
            display.render(main);
        for (Display display : displaysToDetails)
            display.render(details);
        return TypefaceUtils.ICON_COMMENT;
    }

}
