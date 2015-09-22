package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.StyledTextDataMother.mockDetailsStyledText;
import static com.github.mobile.ui.user.StyledTextDataMother.mockMainStyledText;
import static org.junit.Assert.assertEquals;

import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;

public class TestCreateEvent {

    @Test
    public void icon_should_be_create() {
        IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);
        Event event = new EventBuilder().
                defaultStubEventFor(Event.TYPE_CREATE).
                withPayload(new CreatePayloadBuilder().defaultStubPayload()).
                build();

        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                event,
                mockMainStyledText(),
                mockDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_CREATE, icon);
    }

}
