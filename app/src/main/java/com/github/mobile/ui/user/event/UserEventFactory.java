package com.github.mobile.ui.user.event;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.EventType;
import com.github.mobile.ui.user.IconAndViewTextManager;

import org.eclipse.egit.github.core.event.Event;

public class UserEventFactory {
    public static UserEvent create(final Event event, final IconAndViewTextManager iconAndViewTextManager) {
        if (event.getType().equals(Event.TYPE_COMMIT_COMMENT))
            return new CommitCommentEvent(event);

        return new UserEvent() {
            @Override
            public String generate(StyledText main, StyledText details) {
                return EventType.createInstance(event).generateIconAndFormatStyledText(iconAndViewTextManager, event, main, details);
            }
        };
    }
}
