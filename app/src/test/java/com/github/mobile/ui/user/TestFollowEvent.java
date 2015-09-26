package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockMainStyledText;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.ui.user.builder.FollowPayloadBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;

public class TestFollowEvent {

    private final EventBuilder stubEvent = new EventBuilder().defaultStubEventFor(Event.TYPE_FOLLOW).
            withPayload(new FollowPayloadBuilder().defaultStubPayload());
    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);

    @Test
    public void icon_should_be_follow() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.build(),
                mockMainStyledText(),
                mockDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_FOLLOW, icon);
    }

    @Test
    public void actor_and_follow_target_should_be_bold_and_appended_to_main() {
        StyledText mockMainStyledText = mockMainStyledText();

        iconAndViewTextManager.setIconAndFormatStyledText(
                stubEvent.
                        withPayload(new FollowPayloadBuilder().defaultStubPayload().
                            withTarget("Target")).
                        withLoginUserName("LoginUserNameForFollow").
                        build(),
                mockMainStyledText,
                mockDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserNameForFollow");
        verify(mockMainStyledText).append(" started following ");
        verify(mockMainStyledText).bold("Target");
    }
}
