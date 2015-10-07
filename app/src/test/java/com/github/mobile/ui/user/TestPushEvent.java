package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockMainStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.ui.user.builder.PushPayloadBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;

public class TestPushEvent {

    private final EventBuilder stubEvent = new EventBuilder().defaultStubEventFor(Event.TYPE_PUSH).
            withPayload(new PushPayloadBuilder().defaultStubPayload());
    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);

    @Test
    public void icon_should_be_push() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_PUSH, icon);
    }

    @Test
    public void actor_should_be_bold_and_appended_to_main() {
        StyledText mockMainStyledText = mockMainStyledText();

        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withLoginUserName("LoginUserNameForPush").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserNameForPush");
        verify(mockMainStyledText).append(" pushed to ");
    }
}
