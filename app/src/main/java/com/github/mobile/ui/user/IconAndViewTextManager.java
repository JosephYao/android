package com.github.mobile.ui.user;

import android.text.TextUtils;

import com.github.kevinsawicki.wishlist.ViewUtils;
import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.event.UserEventFactory;
import com.github.mobile.util.TimeUtils;

import org.eclipse.egit.github.core.event.Event;

public class IconAndViewTextManager {
    private final NewsListAdapter newsListAdapter;

    public IconAndViewTextManager(NewsListAdapter newsListAdapter) {
        this.newsListAdapter = newsListAdapter;
    }

    protected void update(int position, Event event) {
        newsListAdapter.getAvatars().bind(newsListAdapter.imageViewAgent(0), event.getActor());

        StyledText main = new StyledText();
        StyledText details = new StyledText();
        String icon = setIconAndFormatStyledText(event, main, details);

        if (icon != null)
            ViewUtils.setGone(newsListAdapter.setTextAgent(3, icon), false);
        else
            newsListAdapter.setGoneAgent(3, true);

        newsListAdapter.setTextAgent(1, main);

        if (!TextUtils.isEmpty(details))
            ViewUtils.setGone(newsListAdapter.setTextAgent(2, details), false);
        else
            newsListAdapter.setGoneAgent(2, true);

        newsListAdapter.setTextAgent(4, TimeUtils.getRelativeTime(event.getCreatedAt()));
    }

    String setIconAndFormatStyledText(Event event, StyledText main, StyledText details) {
        return UserEventFactory.create(event).generate(main, details);
    }
}