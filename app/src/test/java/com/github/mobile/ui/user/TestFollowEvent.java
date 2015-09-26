package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockMainStyledText;
import static org.junit.Assert.assertEquals;

import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.ui.user.builder.FollowPayloadBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;

public class TestFollowEvent {

    @Test
    public void icon_should_be_follow() {
        IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);

        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                new EventBuilder().defaultStubEventFor(Event.TYPE_FOLLOW).
                        withPayload(new FollowPayloadBuilder().defaultStubPayload()).
                        build(),
                mockMainStyledText(),
                mockDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_FOLLOW, icon);
    }
}
