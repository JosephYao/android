package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.StyledTextDataMother.mockDetailsStyledText;
import static com.github.mobile.ui.user.StyledTextDataMother.mockMainStyledText;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import com.github.mobile.ui.StyledText;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;

public class TestCreateEvent {

    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);
    private EventBuilder stubEvent = new EventBuilder().defaultStubEventFor(Event.TYPE_CREATE).
            withPayload(new CreatePayloadBuilder().defaultStubPayload());

    @Test
    public void icon_should_be_create() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.build(),
                mockMainStyledText(),
                mockDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_CREATE, icon);
    }

    @Test
    public void actor_should_be_bold_to_main() {
        StyledText mockMainStyledText = mockMainStyledText();

        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withLoginUserName("LoginUserNameForCreate").
                        build(),
                mockMainStyledText,
                mockDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserNameForCreate");
    }

}
