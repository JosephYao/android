package com.github.mobile.ui.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import com.github.mobile.ui.StyledText;
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

    private StyledText mockDetailsStyledText() {
        return mock(StyledText.class);
    }

    private StyledText mockMainStyledText() {
        return mock(StyledText.class);
    }
}
