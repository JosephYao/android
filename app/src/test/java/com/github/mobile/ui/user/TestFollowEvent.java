package com.github.mobile.ui.user;

import static com.github.mobile.ui.user.builder.StyledTextDataMother.mockMainStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubDetailsStyledText;
import static com.github.mobile.ui.user.builder.StyledTextDataMother.stubMainStyledText;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import com.github.mobile.BuildConfig;
import com.github.mobile.ui.StyledText;
import com.github.mobile.ui.user.builder.EventBuilder;
import com.github.mobile.ui.user.builder.FollowPayloadBuilder;
import com.github.mobile.util.TypefaceUtils;

import org.eclipse.egit.github.core.event.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class TestFollowEvent {

    IconAndViewTextManager iconAndViewTextManager = new IconAndViewTextManager(null);
    private final StyledText mockMainStyledText = mockMainStyledText();

    @Test
    public void icon_should_be_follow() {
        String icon = iconAndViewTextManager.setIconAndFormatStyledText(
                aFollowEvent().build(),
                stubMainStyledText(),
                stubDetailsStyledText());

        assertEquals(TypefaceUtils.ICON_FOLLOW, icon);
    }

    @Test
    public void actor_and_follow_target_should_be_bold_and_appended_to_main() {
        iconAndViewTextManager.setIconAndFormatStyledText(
                aFollowEventWithTarget("Target").
                        withLoginUserName("LoginUserNameForFollow").
                        build(),
                mockMainStyledText,
                stubDetailsStyledText());

        verify(mockMainStyledText).bold("LoginUserNameForFollow");
        verify(mockMainStyledText).append(" started following ");
        verify(mockMainStyledText).bold("Target");
    }

    private EventBuilder aFollowEventWithTarget(String target) {
        return aFollowEvent().with(aFollowPayload().withTarget(target));
    }

    private FollowPayloadBuilder aFollowPayload() {
        return new FollowPayloadBuilder();
    }

    private EventBuilder aFollowEvent() {
        return new EventBuilder(Event.TYPE_FOLLOW).with(aFollowPayload());
    }

}
